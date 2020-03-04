package tree;

public class AVLTreeDemo {
	public static void main(String[] args) {
		//int[] arr = {4,3,6,5,7,8};
		//int[] arr = { 10, 12, 8, 9, 7, 6 };
		int[] arr = { 10, 11, 7, 6, 8, 9 };  
		//创建一个 AVLTree对象
		AVLTree avlTree = new AVLTree();
		//添加结点
		for(int i=0; i < arr.length; i++) {
			avlTree.add(new Node(arr[i]));
		}
		
		//遍历
		System.out.println("中序遍历");
		avlTree.infixOrder();
		
		System.out.println("在平衡处理~~");
		System.out.println("树的高度=" + avlTree.getRoot().height()); //3
		System.out.println("树的左子树高度=" + avlTree.getRoot().leftHeight()); // 2
		System.out.println("树的右子树高度=" + avlTree.getRoot().rightHeight()); // 2
		System.out.println("当前的根结点=" + avlTree.getRoot());//8
		
		
	}

}


class AVLTree{
	private Node root;
	public Node getRoot() {
		return root;
	}
	
	public Node search(int value) {
		if(root == null) {
			return null;
		}else {
			return root.search(value);
		}
	}
	
	public Node searchParent(int value) {
		if(root == null) {
			return null;
		}else {
			return root.search(value);
		}
	}
	
	public int delRightTreeMin(Node node) {
		Node target = node;
		while(target.left != null) {
			target = target.left;
		}
		
		delNode(target.value);
		return target.value;
	}
	
	public void delNode(int value) {
		if(root == null) {
			return;
		}else {
			Node targetNode = search(value);
			if(targetNode == null) {
				return;
			}
			if(root.left == null && root.right == null) {
				root = null;
				return;
			}
			
			Node parent = searchParent(value);
			if(targetNode.left == null && targetNode.right == null) {
				if(parent.left != null && parent.left.value == value) {
					parent.left = null;
				}
				else if(parent.right != null && targetNode.right != null) {
					int minVal = delRightTreeMin(targetNode.right);
					targetNode.value = minVal;
				}else {
					if(parent != null) {
						if(parent.left.value == value) {
							parent.left = targetNode.right;
						}else {
							parent.right = targetNode.right;
						}
					}else {
						root = targetNode.right;
					}
					
				}
			}
		}
	}
	
	public void add(Node node) {
		if(root == null) {
			root = node;
		}else {
			root.add(node);
		}
	}
	
	public void infixOrder() {
		if(root != null) {
			root.infixOrder();
		}else {
			System.out.println("二叉树排序树为空，不能遍历");
		}
		
	}
}

class Node{
	int value;
	Node left;
	Node right;
	public Node(int value) {
		this.value = value;
	}
	//返回左子树的高度
	public int leftHeight() {
		if(left == null) {
			return 0;
		}
		return left.height();
	}
	//返回右子树的高度
	public int rightHeight() {
		if(right == null) {
			return 0;
		}
		return right.height();
	}
	//返回 以该节点为根节点的树的高度；
	public int height() {
		return Math.max(left == null ? 0: left.height(), right == null ? 0: right.height())+1;
	}
	
	//左旋转方法
	private void leftRotate() {
		Node newNode = new Node(value);
		newNode.left = left;
		newNode.right = right.left;
		value = right.value;
		right = right.right;
		left = newNode;
	}
	
	//左旋转方法
	private void rightRotate() {
		Node newNode = new Node(value);
		newNode.right = right;
		newNode.left = left.right;
		value = left.value;
		left = left.left;
		right = newNode;
	}
	
	public Node search(int value) {
		if(value == this.value) {
			return this;
		}else if(value < this.value) {
			if(this.left == null) {
				return null;
			}
			return this.left.search(value);
		}else {
			if(this.right == null) {
				return null;
			}
			return this.right.search(value);
		}
	}
	
	public Node searchParent(int value) {
		if((this.left != null && this.left.value == value) || this.right != null && right.value == value) {
			return this;
		}else {
			if(value< this.value && this.left != null) {
				return this.left.searchParent(value);
			}else if(value >= this.value && this.right != null) {
				return this.right.searchParent(value);
			}else {
				return null;
			}
		}
	}
	
	public String toString() {
		return "Node [value=]" + value+ "]"; 
	}
	
	public void add(Node node) {
		if(node == null) {
			return;
		}
		
		if(node.value < this.value) {
			if(this.left == null) {
				this.left = node;
			}else {
				this.left.add(node);
			}
		}else {
			if(this.right == null) {
				this.right = node;
			}else {
				this.right.add(node);
			}
		}
		
		if(rightHeight() - leftHeight() > 1) {
			if(right != null && right.leftHeight() > right.rightHeight()) {
				right.rightRotate();
				leftRotate();
			}else {
				leftRotate();
			}
			return;
		}
		
		if(leftHeight() - rightHeight() > 1) {
			if(left != null && left.rightHeight() > left.leftHeight()) {
				left.leftRotate();
				rightRotate();
			}else {
				rightRotate();
			}
		}
	}
	
	public void infixOrder() {
		if(this.left != null) {
			this.left.infixOrder();
		}
		System.out.println(this);
		if(this.right != null) {
			this.right.infixOrder();
		}
	}
}
