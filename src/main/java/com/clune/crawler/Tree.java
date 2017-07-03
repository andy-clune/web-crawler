/**
 * 
 */
package com.clune.crawler;

import java.util.ArrayList;
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
		this.children = children;
	}
	
	public Tree(T value) {
		this.value = value;
		this.children = new ArrayList<Tree<T>>();
	}

	public T getValue() {
		return value;
	}

	public List<Tree<T>> getChildren() {
		return children;
	}
	
	public void addChild(Tree<T> child) {
		this.children.add(child);
	}
}
