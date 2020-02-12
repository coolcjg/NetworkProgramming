import java.net.NetworkInterface;
import java.net.SocketException;

public class p135 {
	public static void main(String[] args){
		try{
			NetworkInterface ni = NetworkInterface.getByName("lo");
			if(ni == null){
				System.err.println("No souch interface : lo");
			}else{
				System.out.println("aa : " + ni.toString());
			}
		}catch(SocketException ex){
			
		}
	}

}
