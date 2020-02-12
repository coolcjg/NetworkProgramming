import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.text.ParseException;
import java.util.Date;



public class Time {
	
	private static final String HOSTNAME = "time.nist.gov";
	
	public static void main(String[] args) throws IOException, ParseException{
		Date d = Time.getDateFromNetwork();
		System.out.println("It is " + d);
	}
	
	public static Date getDateFromNetwork() throws IOException, ParseException{
		
		long differenceBetweenEpochs = 2208988800L;
		
		Socket socket = null;
		try {
			socket = new Socket(HOSTNAME, 37);
			socket.setSoTimeout(15000);
			
			InputStream raw = socket.getInputStream();
			
			long secondsSince1900 =0;
			for(int i=0; i<4; i++) {
				int tmp = raw.read();
				
				System.out.println("비트이동 : " + (secondsSince1900<<8));
				System.out.println("tmp   : " + tmp);
				secondsSince1900 = (secondsSince1900<<8) | tmp;
				System.out.println("secondsSince1900 : " + secondsSince1900);
			}
			
			long secondsSince1970 = secondsSince1900 - differenceBetweenEpochs;
			long msSince1970 = secondsSince1970*1000;
			Date time = new Date(msSince1970);
			
			return time;
		}finally {
			try {
				if(socket != null) socket.close();
			}catch(IOException ex) {
				
			}
		}
	}
	
	
	
	
	

}
