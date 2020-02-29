import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class HTTPSClient {
	
	public static void main(String[] args) {
		if(args.length == 0) {
			System.out.println("Usage : java HTTPSClient2 host");
			return;
			
		}
		
		int port = 443;
		String host = args[0];
		
		SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
		SSLSocket socket = null;
		try {
			socket = (SSLSocket) factory.createSocket(host, port);
			
			String[] supported = socket.getSupportedCipherSuites();
			socket.setEnabledCipherSuites(supported);
			
			Writer out = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
			//https�� GET��û �� ��ü URL�� ����ؾ��Ѵ�.
			out.write("GET http://" + host + "/ HTTP/1.1\r\n");
			out.write("Host: " + host + "\r\n");
			out.write("\r\n");
			out.flush();
			
			//���� �б�
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			//��� �б�
			String s;
			while(!(s=in.readLine()).contentEquals("")) {
				System.out.println(s);
			}
			System.out.println("----");
			
			//���� �б�
			String contentLength = in.readLine();
			System.out.println("�۾� �� : " + contentLength);;
			int length = Integer.MAX_VALUE;
			try {
				length = Integer.parseInt(contentLength.trim(), 16);
				System.out.println("length : " + length);
			}catch(NumberFormatException ex) {
				
			}
			System.out.println("contentLength : " + contentLength);
			
			int c;
			int i =0;
			while((c=in.read())!=-1 && i++ < length) {
				System.out.write(c);;
			}
			
			System.out.println();
		}catch(IOException ex) {
			System.err.println(ex);
		}finally {
			try {
				if(socket != null) socket.close();
			}catch(IOException e) {}
		}
	}

}
