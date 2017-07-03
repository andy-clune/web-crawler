/**
 * 
 */
package com.clune.crawler;

import java.io.IOException;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

/**
 * @author andrewclune
 *
 */
public class Crawler {
	
	public static Tree<String> crawl(String root) {
		Deque<Tree<String>> queue = new ArrayDeque<Tree<String>>();
		Tree<String>  treeRoot = new Tree<String>(root);
		queue.add(treeRoot);
		
		// Taboo list
		List<String> tabooList = new ArrayList<String>();
		
		int i =0;
		while (!queue.isEmpty() && i++ < 100) {
			Tree<String> current = queue.pop();
			if(tabooList.contains(current.getValue())) {
				continue;
			} 
			tabooList.add(current.getValue());
			System.out.println("Processing " + current.getValue());
			
			try {
				URL url = new URL(current.getValue());
				if (url.getHost().equalsIgnoreCase("wiprodigital.com") && isCrawlable(url)) {
					StringWriter writer = new StringWriter();
					IOUtils.copy(url.openStream(), writer);
					Pattern pattern = Pattern.compile("href=\"(http://.+?)\"");
					Matcher match = pattern.matcher(writer.toString());
					while (match.find()) {
						String child = match.group(1);
						Tree<String> childTree = new Tree<String>(child);
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
		
		return treeRoot;
	}
	
	private static boolean isCrawlable(URL url) {
		boolean crawlable = true;
		String path = url.getPath();
		
		if(path.endsWith(".php") || path.endsWith(".css") || path.endsWith(".js")) {
			crawlable = false;
		}
		
		return crawlable;
	}

}
