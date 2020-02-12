import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SourceViewer3 {
	public static void main(String[ ]args) {
		for(int i=0; i<args.length; i++) {
			try {
				URL u = new URL(args[i]);
				HttpURLConnection uc = (HttpURLConnection)u.openConnection();
				int code = uc.getResponseCode();
				String response = uc.getResponseMessage();
				System.out.println("HTTP/1.x " + code + " " + response);
				for(int j=1; ; j++ ) {
					String header = uc.getHeaderField(j);
					System.out.println("header : " + header);
					String key = uc.getHeaderFieldKey(j);
					System.out.println("key : " + key);
					if(header == null || key == null) break;
					System.out.println(uc.getHeaderFieldKey(j) + ": " + header);
				}
				System.out.println();
				
				try(InputStream in = new BufferedInputStream(uc.getInputStream())){
					//InputStream을 Reader에 연결한다.
					Reader r = new InputStreamReader(in);
					int c;
					while((c=r.read())!= -1) {
						System.out.print((char) c);
					}
				}
			}catch(MalformedURLException ex) {
				System.err.println(args[0] + "is not a parseable URL");
			}catch(IOException ex) {
				System.err.println(ex);
			}
		}
	}

}
