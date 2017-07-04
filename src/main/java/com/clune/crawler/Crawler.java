/**
 * 
 */
package com.clune.crawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author andrewclune
 *
 */
public class Crawler {

	public static Tree<String> crawl(String url) {
		String targetDomain = getDomain(url);
		
		Deque<Tree<String>> queue = new ArrayDeque<Tree<String>>();
		Tree<String> treeRoot = new Tree<String>(url);
		queue.add(treeRoot);

		// visited list
		Set<String> visited = new HashSet<String>();

		int i = 0;
		while (!queue.isEmpty() && i++ < 100) {
			Tree<String> current = queue.pop();
			if (!visited.contains(current.getValue()) && targetDomain.equalsIgnoreCase(getDomain(current.getValue()))) {
				System.out.println("Processing " + current.getValue());
				visited.add(current.getValue());
				try {
					Document doc = Jsoup.connect(current.getValue()).get();
					Elements links = doc.select("a[href]");
					
					//  This is to dedup links found ON A SINGLE page
					Set<String> localVisited = new HashSet<String>();
					for (Element link : links) {
						String childUrl = cleanUrl(link.attr("abs:href"));
						if (!localVisited.contains(childUrl)) {
							localVisited.add(childUrl);
							Tree<String> childTree = new Tree<String>(childUrl);
							current.addChild(childTree);
							queue.addLast(childTree);
						}
					}
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return treeRoot;
	}

	private static String cleanUrl(String urlStr) {
		String cleanUrl = urlStr;
		int index = urlStr.lastIndexOf("#");
		if (index != -1) {
			cleanUrl = urlStr.substring(0, index);
		}

		index = urlStr.lastIndexOf("?");
		if (index != -1) {
			cleanUrl = cleanUrl.substring(0, index);
		}

		return cleanUrl;

	}
	
	private static String getDomain(String url) {
		String domain = "";
		try {
			URI uri = new URI(url);
			domain = uri.getHost();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return domain;
	}

}
