import java.net.MalformedURLException;
import java.net.URL;

public class ProtocolTester {
	
	public static void main(String[] args){
		
		//하이퍼텍스트 전송 프로토콜
		testProtocol("http://www.abc.org");
		
		//보안 http
		testProtocol("http://www.amazon.com/exec/obidos/order2/");
		
		//파일 전송 프로토콜
		testProtocol("ftp://ibiblio.org/pub/languages/java/javafaq/");
		
		//간이 메일 전송 프로토콜
		testProtocol("mailto:elharo@ibiblio.org");
		
		//텔넷
		testProtocol("telnet://dibner.poly.edu/");
		
		//로컬 파일 접근
		testProtocol("file:///etc/passwd");
		
		//gopher
		testProtocol("gopher://gopher.anc.org.za/");
		
		//LDAP, 경량 디렉터리 접근 프로토콜
		testProtocol("ldap://ldap.itd.umich.edu/o=University%20of%20Michigan,c=US?postalAddress");
		
		//JAR
		testProtocol("jar:http://cafeaulait.org/books/javaio/ioexamples/javaio.jar!"+"/com/macfaq/io/StreeamCopier.class");
		
		//NFS, 네트워크 파일 시스템
		testProtocol("nfs://utopia.poly.edu/usr/tmp/");
		
		//JDBC를 위한 사용자 정으 ㅣ프로토콜
		testProtocol("jdbc:mysql://luna.ibiblio.org:3306/NEWS");
		
		//rmi, 원격 메소드 호출을 위한 사용자 정으 ㅣ프로토콜
		testProtocol("rmi://ibiblio.org/RenderEngine");
		
		//HotJava 를 위한 사용자 정의 프로토콜
		testProtocol("doc:/UsersGuide/release.html");
		testProtocol("netdoc:/UsersGuide/release.html");
		testProtocol("systemresource://www.adc.org/+/index.html");
		testProtocol("verbatim:http://www.adc.org/");
	}
	
	
	private static void testProtocol(String url){
		try{
			URL u = new URL(url);
			System.out.println(u.getProtocol() + " is supported");
		}catch(MalformedURLException ex){
			String protocol = url.substring(0, url.indexOf(':'));
			System.out.println(protocol + " is not supported");
		}
	}
	

}
