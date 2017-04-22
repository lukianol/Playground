public class BinarySearchTree<T extends Comparable<T>> {
	
	private TreeNode<T> _root;
	
	public void addValue(T value) {
		if (this._root == null) {
			this._root = new TreeNode<T>(value);
		}
		this.addValue(value, this._root);
	}

	public TreeNode<T> getRoot() {
		return this._root;
	}
	
	private void addValue(T value, TreeNode<T> node) {
		int comparison = value.compareTo(node.value);
		if (comparison > 0) {
			if (node.right == null) {
				node.right = new TreeNode<T>(value);
			} else {
				this.addValue(value, node.right);
			}
		} else if (comparison < 0) {
			if (node.left == null) {
				node.left = new TreeNode<T>(value);
			} else {
				this.addValue(value, node.left);
			}
		}
	}
}
