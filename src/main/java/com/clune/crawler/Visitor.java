/**
 * 
 */
package com.clune.crawler;

/**
 * Based on {@link http://wiki.c2.com/?HierarchicalVisitorPattern}
 * 
 * @author andrewclune
 *
 */
public interface Visitor<T> {
	public void visitEnter(T node);
	public void visitLeave(T node);
	public void visit(T leaf);
	

}
