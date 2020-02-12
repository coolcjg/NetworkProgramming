import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class Whois {
	public final static int DEFAULT_PORT=43;
	public final static String DEFAULT_HOST ="whois.internic.net";
	
	private int port = DEFAULT_PORT;
	private InetAddress host;
	
	public Whois(InetAddress host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public Whois(InetAddress host) {
		this(host, DEFAULT_PORT);
	}
	
	public Whois(String hostname, int port) throws UnknownHostException{
		this(InetAddress.getByName(hostname), DEFAULT_PORT);
	}
	
	public Whois() throws UnknownHostException{
		this(DEFAULT_HOST, DEFAULT_PORT);
	}
	
	//검색아이템
	public enum SearchFor{
		ANY("Any"), NETWORK("Network"), PERSON("Person"), HOST("Host"), DOMAIN("Domain"), ORGANIZATION("Organization"),
		GROUP("Group"), GATEWAY("Gateway"), ASN("ASN");
		
		private String label;
		
		private SearchFor(String label) {
			this.label = label;
		}
	}
	
	//검색 카테고리
	public enum SearchIn{
		ALL(""), NAME("Name"), MAILBOX("Mailbox"), HANDLE("!");
		
		private String label;
		
		private SearchIn(String label) {
			this.label = label;
		}
	}
	
	public String lookUpNames(String target, SearchFor category, SearchIn group, boolean exactMath) throws IOException{
		String suffix = "";
		
		if(!exactMath) suffix=".";
		
		String prefix = category.label + " " + group.label;
		String query = prefix + target + suffix;
		
		Socket socket = new Socket();
		
		try {
			SocketAddress address = new InetSocketAddress(host, port);
			socket.connect(address);
			Writer out = new OutputStreamWriter(socket.getOutputStream(),"ASCII");

			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "ASCII"));
			out.write(query + "\r\n");
			out.flush();
			
			StringBuilder response = new StringBuilder();
			String theLine = null;
			while((theLine =in.readLine())!=null) {
				response.append(theLine);
				response.append("\r\n");
			}
			
			return response.toString();
		}finally {
			socket.close();
		}
	}
	
	public InetAddress getHost() {
		return this.host;
	}
	
	public void setHost(String host) throws UnknownHostException{
		this.host = InetAddress.getByName(host);
		
	}

}
