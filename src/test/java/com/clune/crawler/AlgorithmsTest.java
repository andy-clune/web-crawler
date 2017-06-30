/**
 * 
 */
package com.clune.crawler;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.inOrder;

import java.util.ArrayList;
import org.junit.Test;
import org.mockito.InOrder;

/**
 * @author andrewclune
 *
 */
public class AlgorithmsTest {

	@Test
	@SuppressWarnings("unchecked")
	public void leafTest() {
		// TODO figure out how to do mocks with generics without warnings.
		Tree<String> leaf = mock(Tree.class);
		String leafValue = "leaf";
		when(leaf.getValue()).thenReturn(leafValue);
		when(leaf.getChildren()).thenReturn(new ArrayList<Tree<String>>());
		
		Visitor<String> visitor = mock(Visitor.class);
		
		Algorithms.depthFirstSearch(leaf, visitor);	
		
		verify(leaf).getChildren();
		
		verify(visitor).visit(leafValue);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void nodeTest() {
		Tree<String> root = mock(Tree.class);
		Tree<String> leaf = mock(Tree.class);
		
		//configure the root mock
		String rootValue = "root";
		when(root.getValue()).thenReturn(rootValue);
		ArrayList<Tree<String>> children = new ArrayList<Tree<String>>();
		children.add(leaf);
		when(root.getChildren()).thenReturn(children);
		
		// configure the leaf mock
		String leafValue = "leaf";
		when(leaf.getValue()).thenReturn(leafValue);
		when(leaf.getChildren()).thenReturn(new ArrayList<Tree<String>>());
		
		Visitor<String> visitor = mock(Visitor.class);
		
		Algorithms.depthFirstSearch(root, visitor);	
		
		InOrder inOrder = inOrder(root, leaf);
		
		inOrder.verify(root).getChildren();
		inOrder.verify(leaf).getChildren();
		
		InOrder visitorInOrder = inOrder(visitor);
		
		visitorInOrder.verify(visitor).visitEnter(rootValue);
		visitorInOrder.verify(visitor).visit(leafValue);
		visitorInOrder.verify(visitor).visitLeave(rootValue);
	}

}
