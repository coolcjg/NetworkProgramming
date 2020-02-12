import java.net.InetAddress;

public class AddressTests {
	public static int getVersion(InetAddress ia){
		byte[] address = ia.getAddress();
		for(int i = 0; i<address.length; i++){
			System.out.println("배열 : " +  address[i]);
		}
		if(address.length ==4) return 4;
		else if(address.length ==16) return 6;
		else return -1;
	}

}
