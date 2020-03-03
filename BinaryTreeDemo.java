package tree;

public class BinaryTreeDemo {
	public static void main(String[] args) {
		BinaryTree binaryTree = new BinaryTree();
		TreeNode root = new TreeNode(1,"小一");
		TreeNode node2 = new TreeNode(2,"小二");
		TreeNode node3 = new TreeNode(3,"小三");
		TreeNode node4 = new TreeNode(4,"小四");
		TreeNode node5 = new TreeNode(5,"小五");
		
		root.setLeft(node2);
		root.setRight(node3);
		node3.setRight(node4);
		node3.setLeft(node5);
		binaryTree.setRoot(root);
		
		System.out.println("前序遍历");
		binaryTree.preOrder();
		
//		System.out.println("中序遍历");
//		binaryTree.infixOrder();
		
//		System.out.println("后序遍历");
//		binaryTree.postOrder();
		
//		System.out.println("前序遍历方法~~~~");
//		TreeNode resNode = binaryTree.postOrderSearch(5);
//		if(resNode != null) {
//			System.out.printf("找到了，信息为 no=%d name = %s", resNode.getNo(),resNode.getName());
//		}else {
//			System.out.printf("没有找到 no = %d ", 5);
//		}
		
		//中序遍历查找
		//中序遍历3次
//		System.out.println("中序遍历方式~~~");
//		TreeNode resNode = binaryTree.infixOrderSearch(5);
//		if (resNode != null) {
//			System.out.printf("找到了，信息为 no=%d name=%s", resNode.getNo(), resNode.getName());
//		} else {
//			System.out.printf("没有找到 no = %d ", 5);
//		}
		
//		System.out.println("删除前,前序遍历");
//		binaryTree.preOrder(); //  1,2,3,5,4
//		binaryTree.delNode(5);
//		//binaryTree.delNode(3);
//		System.out.println("删除后，前序遍历");
//		binaryTree.preOrder(); // 1,2,3,4
		
		
	}

}


class BinaryTree{
	private TreeNode root;
	
	public void setRoot(TreeNode root) {
		this.root = root;
	}
	
	//删除结点
	public void delNode(int no) {
		if(root != null) {
			if(root.getNo() == no) {
				root = null;
			}else {
				root.delNode(no);
			}
		}else {
			System.out.println("空树，无法删除");
		}
	}
	
	//前序遍历
	public void preOrder() {
		if(this.root != null) {
			this.root.preOrder();
		}else {
			System.out.println("二叉树为空，无法遍历");
		}
	}
	
	//中序遍历
	public void infixOrder() {
		if(this.root != null) {
			this.root.infixOrder();
		}else {
			System.out.println("二叉树为空，无法遍历");
		}
	}
	
	//后序遍历
	public void postOrder() {
		if(this.root != null) {
			this.root.postOrder();
		}else {
			System.out.println("二叉树为空，无法遍历");
		}
	}
	
	//前序遍历
		public TreeNode preOrderSearch(int no) {
			if(root != null) {
				return root.preOrderSearch(no);
			} else {
				return null;
			}
		}
		//中序遍历
		public TreeNode infixOrderSearch(int no) {
			if(root != null) {
				return root.infixOrderSearch(no);
			}else {
				return null;
			}
		}
		//后序遍历
		public TreeNode postOrderSearch(int no) {
			if(root != null) {
				return this.root.postOrderSearch(no);
			}else {
				return null;
			}
		}
	
}

class TreeNode{
	private int no;
	private String name;
	private TreeNode left;
	private TreeNode right;
	public TreeNode(int no, String name) {
		this.no = no;
		this.name = name;
	}
	
	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public TreeNode getLeft() {
		return left;
	}
	
	public void setLeft(TreeNode left) {
		this.left = left;
	}
	
	public TreeNode getRight() {
		return right;
	}
	public void setRight(TreeNode right) {
		this.right = right;
	}
	
	public String toString() {
		return "HeroNode [ no = "+no+",  name = "+name+"]";
	}
	
	public void delNode(int no) {
		if(this.left != null && this.left.no == no) {
			this.left = null;
			return;
		}
		
		if(this.left != null) {
			this.left.delNode(no);
		}
		
		if(this.right != null) {
			this.right.delNode(no);
		}
	}
	
	//编写前序遍历
	public void preOrder() {
		System.out.println(this);
		
		if(this.left != null) {
			this.left.preOrder();
		} 
		if(this.right != null) {
			this.right.preOrder();
		}
	}
	
	//中序遍历
	public void infixOrder() {
		if(this.left != null) {
			this.left.infixOrder();
		}
		System.out.println(this);
		if(this.right != null) {
			this.right.infixOrder();
		}
	}
	
	//后序遍历
	public void postOrder() {
		if(this.left != null) {
			this.left.postOrder();
		}
		if(this.right != null) {
			this.right.postOrder();
		}
		System.out.println(this);
	}
	
	public TreeNode preOrderSearch(int no) {
		System.out.println("进入前序遍历");
		if(this.no == no) {
			return this;
		}
		
		TreeNode resNode = null;
		if(this.left != null) {
			resNode = this.left.preOrderSearch(no);
		}
		if(resNode != null) {
			return resNode;
		}
		
		if(this.right != null) {
			resNode = this.right.preOrderSearch(no);
		}
		return resNode;
	}
	
	public TreeNode infixOrderSearch(int no) {
		TreeNode resNode = null;
		if(this.left != null) {
			resNode = this.left.preOrderSearch(no);
		}
		if(resNode != null) {
			return resNode;
		}
		System.out.println("进入中序查找");
		
		if(this.no == no) {
			return this;
		}
		if(this.right != null) {
			resNode = this.right.preOrderSearch(no);
		}
		return resNode;
	}
	
	public TreeNode postOrderSearch(int no) {
		TreeNode resNode = null;
		if(this.left != null) {
			resNode = this.left.preOrderSearch(no);
		}
		if(resNode != null) {
			return resNode;
		}
		
		if(this.right != null) {
			resNode = this.right.preOrderSearch(no);
		}
		if(resNode != null) {
			return resNode;
		}
		System.out.println("进入后序查找");
		if(this.no == no) {
			return this;
		}
		return resNode;
	}
	
}