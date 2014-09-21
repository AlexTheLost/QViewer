package webcrawler;

import java.util.List;
import java.util.ArrayList;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler {

	public String getURLs(String query) throws UnsupportedEncodingException,
			IOException {
		List<String> links = parsingPage(readPage(query, 0));
		// Для некоторых запросов возвращало менее 10 ссылок.
		links.addAll(parsingPage(readPage(query, 10)));
		if (links.size() <= 10)
			return conversion(links);
		else
			return conversion(links.subList(0, 10));
	}

	private String readPage(String stringQuery, int startLink)
			throws IOException {
		// Воспользовался методом получения страницы предложенным в тексте задания.
		String google = "https://www.google.by/search?q=";
		String charset = "UTF-8";
		String startLinkParam = "&start=" + startLink;
		String query = URLEncoder.encode(stringQuery, charset);
		URL url = new URL(google + query + startLinkParam);
		URLConnection agent = url.openConnection();
		agent.addRequestProperty("User-Agent",
				"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
		InputStreamReader stream = new InputStreamReader(
				agent.getInputStream(), charset);
		int c;
		StringBuffer page = new StringBuffer();
		while ((c = stream.read()) != -1) {
			page.append((char) c);
		}
		return page.toString();
	}

	private List<String> parsingPage(String htmlPage)
			throws UnsupportedEncodingException {
		List<String> links = new ArrayList<String>();
		Elements elements = Jsoup.parse(htmlPage).select("li.g>h3>a");
		for (Element element : elements) {
			String url = element.attr("href");
			url = java.net.URLDecoder.decode(url, "UTF-8");
			if (!url.startsWith("/url?q="))
				continue;
			url = url.substring(url.indexOf("/url?") + 7, url.indexOf("&sa="));
			links.add(url);
		}
		return links;
	}

	public String conversion(List<String> links) {
		StringBuffer result = new StringBuffer();
		int i = 1;
		for (String str : links)
			result.append(i++ + " " + str + '\n');
		return result.toString();
	}

	// public static void main(String[] args) throws IOException {
	// WebCrawler wc = new WebCrawler();
	// System.out.println(wc.getURLs("What is mars?"));
	// }

}
