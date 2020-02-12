import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class p193 {
	
	public static void main(String[] args){
		String input = "http://www.google.com/search?hl=en&as_q=Java&as_epq=I%2F0";
		try{
			
		String output = URLDecoder.decode(input, "UTF-8");
		System.out.println(output);
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		
	}

}
