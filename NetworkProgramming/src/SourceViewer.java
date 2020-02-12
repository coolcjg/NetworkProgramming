import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

public class SourceViewer {
	
	public static void main(String[] args){
		if(args.length>0){
			InputStream in = null;
			
			try{
				
				//읽기 위해 url을 연다
				URL u = new URL(args[0]);
				in = u.openStream();
				
				in = new BufferedInputStream(in);
				
				Reader r = new InputStreamReader(in);
				
				int c;
				while((c=r.read())!= -1){
					System.out.print((char)c);
				}
				
			}catch(MalformedURLException ex){
				System.err.println(args[0] + " is not a parseable URL");
			}catch(IOException ex){
				System.err.println(ex);
			}finally{
				if(in !=null){
					try{
						in.close();
					}catch(IOException e){
						
					}
				}
				
			}
		}
	}

}
