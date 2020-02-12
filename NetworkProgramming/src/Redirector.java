import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Redirector {
	
	private static final Logger logger = Logger.getLogger("Redirector");
	
	private final int port;
	private final String newSite;
	
	public Redirector(String newSite, int port) {
		this.port = port;
		this.newSite = newSite;
	}
	
	public void start() {
		try(ServerSocket server = new ServerSocket(port)){
			logger.info("Redirecting connections on port " + server.getLocalPort() + " to " + new Site);
			
			while(true) {
				try {
					Socket s = server.accept();
					Thread t = new RedirectThread(s);
					t.start();
					
				}catch(IOException ex) {
					logger.warning("Exception accepting connection");
					
				}catch(RuntimeException ex) {
					logger.log(Level.SEVERE, "Unexpedted error", ex);
				}
			}
		}catch(BindException ex) {
			logger.log(Level.SEVERE, "Could not start server.", ex);
		}catch(IOException ex) {
			logger.log(Level.SEVERE, "Error opening server socket", ex);
			
		}
	}
	
	private class RedirectThread extends Thread{
		private final Socket connection;
		
		RedirectThread(Socket s){
			this.connection =s;
		}
		
		public void run() {
			try {
				Writer out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "US-ASCII"));
				
				Reader in = new InputStreamReader(new BufferedInputStream(connection.getInputStream()));
				
				StringBuilder request = new StringBuilder(80);
				while(true) {
					int c = in.read();
					if(c =='\r' || c == '\n' || c == -1) break;
					request.append((char)c);
				}
				
				String get = request.toString();
				String[] pieces = get.split("\\w*");
				String theFile = pieces[1];
				
				if(get.indexOf("HTTP") != -1) {
					out.write("HTTP/1.0 302 FOUND\r\n");
					Date now = new Date();
					out.write("Date: " + now + "\r\n");
					out.write("Server: Redirector 1.1\r\n");
					out.write("Location: " + newSite + theFile + "\r\n");
					out.write("Content-type: text/html\r\n\r\n");
					out.flush();
				}
				
				
				
				
				
				
			}catch(IOException ex) {
				logger.log(Level.WARNING, "Error talking to " + connection.getRemoteSocketAddress(), ex);
			}finally {
				try {
					connection.close();
				}catch(IOException ex) {}
			}
		}
	}

}
