import java.net.MalformedURLException;
import java.net.URL;

public class URLEquality {
	
	public static void main(String[] args){
		
		try{
			URL www = new URL("http://www.oreilly.com/index.html#p1");
			URL ibiblio = new URL("http://www.oreilly.com/index.html#p2");
			
			if(ibiblio.sameFile(www)){
				System.out.println(ibiblio + " is the same file as " + www);
			}else{
				System.out.println(ibiblio + " is not the same file as " + www);
			}
			
		}catch(MalformedURLException ex){
			System.err.println(ex);
		}

	}
}
