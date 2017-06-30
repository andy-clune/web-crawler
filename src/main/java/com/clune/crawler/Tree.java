/**
 * 
 */
package com.clune.crawler;

import java.util.List;

/**
 * @author andrewclune
 *
 */
public class Tree<T> {
	private T value;
	private List<Tree<T>> children;
	
	public Tree(T value, List<Tree<T>> children) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public List<Tree<T>> getChildren() {
		return children;
	}
}
