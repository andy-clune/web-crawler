/**
 * 
 */
package com.clune.crawler;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author andrewclune
 *
 */
public class SiteMapVisitorTest {
	private Visitor<String> sut;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.sut = new SiteMapVisitor();
	}

	@Test
	public void leafTest() {
		String leaf = "leaf";
		this.sut.visit(leaf);
		
		String siteMap = this.sut.toString();
		assertEquals(leaf + "\n", siteMap);
		
	}
	
	@Test
	public void nodeTest() {
		String rootValue = "root";
		String leafValue = "leaf";
		
		this.sut.visitEnter(rootValue);
		this.sut.visit(leafValue);
		this.sut.visitLeave(rootValue);
		
		String siteMap = this.sut.toString();
		assertEquals("root\n\tleaf\n", siteMap);
	}

}
