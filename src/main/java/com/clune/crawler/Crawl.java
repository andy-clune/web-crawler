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
		
		Tree<String> tree = Crawler.crawl(root);
		SiteMapVisitor visitor = new SiteMapVisitor();
		Algorithms.depthFirstSearch(tree, visitor);
		System.out.println(visitor.toString());
	}

}
