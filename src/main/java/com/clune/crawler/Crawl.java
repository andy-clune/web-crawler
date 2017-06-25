/**
 * 
 */
package com.clune.crawler;

import java.io.IOException;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

/**
 * @author andrewclune
 *
 */
public class Crawl {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String root = "http://wiprodigital.com";
		
		Deque<String> queue = new ArrayDeque<String>();
		
		queue.add(root);
		
		int i =0;
		while (!queue.isEmpty() && i++ < 3) {
			String current = queue.pop();
			System.out.println("Processing " + current);
			try {
				URL url = new URL(current);
				StringWriter writer = new StringWriter();
				IOUtils.copy(url.openStream(), writer);

				Pattern pattern = Pattern.compile("href=\"(http://.+?)\"");
				Matcher match = pattern.matcher(writer.toString());

				while (match.find()) {
					queue.addLast(match.group(1));
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

}
