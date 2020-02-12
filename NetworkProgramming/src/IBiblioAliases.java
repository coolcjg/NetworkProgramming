import java.net.InetAddress;
import java.net.UnknownHostException;

public class IBiblioAliases {
	
	public static void main(String args[]){
		try{
			InetAddress ibiblio = InetAddress.getByName("www.ibiblio.org");
			InetAddress helios = InetAddress.getByName("www.naver.com");
			
			if(ibiblio.equals(helios)){
				System.out.println("www.ibiblio.org is the same");
			}else{
				System.out.println("not");
			}
			
		}catch(UnknownHostException ex){
			System.out.println("Host lookup failed.");
		}
		
	}

}
