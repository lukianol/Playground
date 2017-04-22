
public class TreeNode<T> {
	public TreeNode(T value){
		this(value, null, null);
	}
	public TreeNode(T value, TreeNode<T> left, TreeNode<T> right){
		this.value = value;
		this.left = left;
		this.right = right;
	}
	public T value;
	public TreeNode<T> left;
	public TreeNode<T> right;
}
