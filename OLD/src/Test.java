import java.net.URL;
import java.net.URLConnection;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {
	public static void main(String[] args) throws IOException {
		
		URL url = new URL("");
		URLConnection agent = url.openConnection();
		agent.addRequestProperty("User-Agent",
				"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
		InputStreamReader reader = new InputStreamReader(agent.getInputStream());

		int c;
		StringBuffer str = new StringBuffer();

		while ((c = reader.read()) != -1) {
			str.append((char)c);
		}
		
		System.out.println(str);
	}
}
