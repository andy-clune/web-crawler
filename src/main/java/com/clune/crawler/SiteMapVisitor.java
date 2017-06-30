/**
 * 
 */
package com.clune.crawler;

/**
 * @author andrewclune
 *
 */
public class SiteMapVisitor implements Visitor<String> {
	private int depth;
	private StringBuilder builder;
	
	public SiteMapVisitor() {
		this.depth = 0;
		this.builder = new StringBuilder();
	}

	public void visitEnter(String node) {
		for(int i = 0; i < this.depth; i++) {
			this.builder.append("\t");
		}
		this.builder.append(node + "\n");
		this.depth++;
	}

	public void visitLeave(String node) {
		this.depth--;
	}

	public void visit(String leaf) {
		for(int i = 0; i < this.depth; i++) {
			this.builder.append("\t");
		}
		this.builder.append(leaf + "\n");
	}

	@Override
	public String toString() {
		return this.builder.toString();
	}
	
	

}
