package trees;

import static org.junit.Assert.*;

import org.junit.Test;

public class FindInOrderSuccessorTests {

	@Test
	public void test_perfect() {
		
		TreeNode<Integer> n1 = new TreeNode<Integer>(1);
		TreeNode<Integer> n2 = new TreeNode<Integer>(2);
		TreeNode<Integer> n3 = new TreeNode<Integer>(3);
		TreeNode<Integer> n4 = new TreeNode<Integer>(4);
		TreeNode<Integer> n5 = new TreeNode<Integer>(5);
		TreeNode<Integer> n6 = new TreeNode<Integer>(6);
		TreeNode<Integer> n7 = new TreeNode<Integer>(7);
		
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n2.right = n5;
		n3.left = n6;
		n3.right = n7;
		
		assertSame(n2, FindInOrderSuccessor.find(n1, n4));
		assertSame(n5, FindInOrderSuccessor.find(n1, n2));
		assertSame(n1, FindInOrderSuccessor.find(n1, n5));
		assertSame(n6, FindInOrderSuccessor.find(n1, n1));
		assertSame(n3, FindInOrderSuccessor.find(n1, n6));
		assertSame(n7, FindInOrderSuccessor.find(n1, n3));
		assertNull(FindInOrderSuccessor.find(n1, n7));
	}
	
	@Test
	public void test_unbalanced() {
		
		TreeNode<Integer> n1 = new TreeNode<Integer>(1);
		TreeNode<Integer> n2 = new TreeNode<Integer>(2);
		TreeNode<Integer> n3 = new TreeNode<Integer>(3);
		TreeNode<Integer> n4 = new TreeNode<Integer>(4);
		TreeNode<Integer> n5 = new TreeNode<Integer>(5);
		TreeNode<Integer> n6 = new TreeNode<Integer>(6);
		TreeNode<Integer> n7 = new TreeNode<Integer>(7);
		
		n1.left = n2;
		n2.left = n3;
		n3.left = n4;
		n4.left = n5;
		n5.left = n6;
		n6.left = n7;
		
		assertSame(n6, FindInOrderSuccessor.find(n1, n7));
		assertSame(n5, FindInOrderSuccessor.find(n1, n6));
		assertSame(n4, FindInOrderSuccessor.find(n1, n5));
		assertSame(n3, FindInOrderSuccessor.find(n1, n4));
		assertSame(n2, FindInOrderSuccessor.find(n1, n3));
		assertSame(n1, FindInOrderSuccessor.find(n1, n2));
		assertNull(FindInOrderSuccessor.find(n1, n1));
	}

}
