package com.clune.crawler;

import java.util.List;

public class Algorithms {

	public static <T> void depthFirstSearch(Tree<T> tree, Visitor<T> visitor) {
		T value = tree.getValue();
		
		List<Tree<T>> children = tree.getChildren();
		if (children.isEmpty()) {
			visitor.visit(value);
		}
		else 
		{
			visitor.visitEnter(value);
			for (Tree<T> child : children) {
				depthFirstSearch(child, visitor);
			}
			visitor.visitLeave(value);
		}
	}
}
