/**
 * 
 */
package com.clune.crawler;

/**
 * @author andrewclune
 *
 */
public class Crawl {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String root = args[0];
		Tree<String> tree = Crawler.crawl(root);
		SiteMapVisitor visitor = new SiteMapVisitor();
		Algorithms.depthFirstSearch(tree, visitor);
		System.out.println(visitor.toString());
	}
}
