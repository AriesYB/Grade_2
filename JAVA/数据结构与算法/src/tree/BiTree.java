package tree;

public class BiTree<T> {

	class TreeNode
	{
		T data;
		TreeNode leftChild;
		TreeNode rightChild;
		TreeNode parent;
	}
	
	BiTree<T> root;
	
	public TreeNode parent(TreeNode t,TreeNode current)	//t为开始节点
	{
		if (t==null)
		{
			return null;
		}
		if (t.leftChild==current||t.rightChild==current) 
		{
			return t;
		}
		TreeNode n=parent(t.leftChild,current);
		if (n!=null) 
		{
			return n;
		}
		return parent(t.rightChild,current);
	}
	
}
