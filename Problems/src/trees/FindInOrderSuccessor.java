package trees;

import trees.TreeNode;

public class FindInOrderSuccessor {
	
	static class  BooleanRef {
		boolean value;
	}
	
	public static <T> TreeNode<T> find(TreeNode<T> root, TreeNode<T> node) {
		if (node == null) {
			return null;
		}
		
		return FindInOrderSuccessor.traverseInOrder(root, node, new BooleanRef());
	}
	
	private static <T> TreeNode<T> traverseInOrder(TreeNode<T> root, TreeNode<T> node, BooleanRef found) {
		if (root == null) {
			return null;
		}
		
		TreeNode<T> result = traverseInOrder(root.left, node, found);
		
		if (result != null) {
			return result;
		}
		
		if (found.value) {
			return root;
		} else if (node == root) {
			found.value = true;
		}
		
		return traverseInOrder(root.right, node, found);
	}
}
