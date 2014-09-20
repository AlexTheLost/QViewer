package webcrawler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class WebCrawler {

	private String readPage(String query) throws IOException {
		query = query.trim().replaceAll("\\s+", "+");
		URL url = new URL("http://www.google.com/search?q=" + query);
		URLConnection agent = url.openConnection();
		agent.addRequestProperty("User-Agent",
				"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
		InputStreamReader reader = new InputStreamReader(agent.getInputStream());
		int c;
		StringBuffer page = new StringBuffer();
		while ((c = reader.read()) != -1) {
			page.append((char) c);
		}
		return page.toString();
	}
	
	private List<String> parsingPage() {
		List<String> links = new ArrayList<String>();
		
		return links;
	}

	public static void main(String[] args) throws IOException {
		WebCrawler wc = new WebCrawler();
		wc.readPage("sdf # 43 32 ef sdf fwe");

//		System.out.println("asdsad @ sdf #dsfsdf s ".trim().replaceAll("\\s+", "+"));
	}

}
