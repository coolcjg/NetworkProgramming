import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class Last24 {
	
	public static void main(String[] args){
		
		//현재 날짜와 시간으로 Date 객체 초기화
		Date today = new Date();
		long millisecondsPerDay = 24*60*60*1000;
		
		for(int i=0; i<args.length; i++){
			try{
				URL u = new URL(args[i]);
				URLConnection uc = u.openConnection();
				System.out.println("Original if modified since: " + new Date(uc.getIfModifiedSince()));
				uc.setIfModifiedSince((new Date(today.getTime()-millisecondsPerDay)).getTime());
				System.out.println("Will retrieve file if it's modified since " + new Date(uc.getIfModifiedSince()));
				try(InputStream in = new BufferedInputStream(uc.getInputStream())){
					Reader r = new InputStreamReader(in);
					int c;
					while((c = r.read())!= -1){
						System.out.print((char)c);
					}
					System.out.println();
				}
			}catch(IOException ex){
				System.err.println(ex);
			}
		}
	}
}
