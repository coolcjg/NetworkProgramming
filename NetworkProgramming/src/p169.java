import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

public class p169 {
	
	public static void main(String[] args){
		
		try{
			
			URL u = new URL("http://www.slrclub.com/bbs/zboard.php?id=free");
			Class<?>[] types = new Class[3];
			types[0] = String.class;
			types[1] = Reader.class;
			types[2] = InputStream.class;
			Object o = u.getContent(types);
			
			if(o instanceof String){
				System.out.println("1");
				System.out.println(o);
			}else if(o instanceof Reader){
				System.out.println("2");
				int c;
				Reader r = (Reader) o;
				while((c = r.read()) != -1)
					System.out.print((char)c);
			}else if(o instanceof InputStream){
				System.out.println("3");
				int c;
				InputStream in = (InputStream) o;
				while((c = in.read())!= -1) System.out.write(c);;
			}else{
				System.out.println("Error : unexpected type " + o.getClass());
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
			
		}
		
	}
	
	

}
