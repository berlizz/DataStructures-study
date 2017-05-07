/*
 * Binary tree
 * ����Ʈ��
 * ����Ʈ���� ������ ������̹Ƿ�, ��ȸ�� ����� ��������� ����
 * ����� Ż�� ������ ��尡 null�� ���  
 *  
 */

package tree;

public class BinaryTree {

	public static void main(String[] args) {
		BinaryTree.Tree tree = new BinaryTree.Tree();
		
		BinaryTree.Node n1 = tree.makeNode(1);
		BinaryTree.Node n2 = tree.makeNode(2);
		BinaryTree.Node n3 = tree.makeNode(3);
		BinaryTree.Node n4 = tree.makeNode(4);
		BinaryTree.Node n5 = tree.makeNode(5);

		tree.makeLeftSubTree(n1, n2);           //      1
		tree.makeRightSubTree(n1, n3);          //     / \
		tree.makeLeftSubTree(n2, n4);           //    2   3
		tree.makeRightSubTree(n2, n5);          //   / \
		                                        //  4   5
		
		System.out.println(tree.getNodeData(tree.getLeftSubTree(n1)));
		System.out.println(tree.getNodeData(tree.getLeftSubTree(tree.getLeftSubTree(n1))));
		System.out.println(tree.getNodeData(tree.getRightSubTree(n2)));
		
		System.out.print("inorderTraversal : ");
		tree.inorderTraversal(n1);
		System.out.println();
		
		System.out.print("preorderTraversal : ");
		tree.preorderTraversal(n1);
		System.out.println();
		
		System.out.print("postorderTraversal : ");
		tree.postorderTraversal(n1);
		System.out.println();
	}
	
	static class Tree {
		
		// ��� ����
		public Node makeNode(Object object) {
			Node newNode = new Node(object);
			
			return newNode;
		}
		
		// ������ ��������
		public Object getNodeData(Node node) {
			return node.getData();
		}
		
		// ����� ���� �ڽĳ�� ��ȯ
		public Node getLeftSubTree(Node node) {
			return node.getLeft();
		}
		
		// ����� ������ �ڽĳ�� ��ȯ
		public Node getRightSubTree(Node node) {
			return node.getRight();
		}
		
		// parent����� ���ʿ� child��� ����
		public void makeLeftSubTree(Node parent, Node child) {
			parent.setLeft(child);
		}
		
		// parent����� �����ʿ� child��� ����
		public void makeRightSubTree(Node parent, Node child) {
			parent.setRight(child);
		}
		
		// Ʈ�� ���� ��ȸ
		public void inorderTraversal(Node node) {
			if(node == null) {
				return;
			}
			
			inorderTraversal(node.getLeft());
			System.out.print(node.getData() + " ");
			inorderTraversal(node.getRight());
		}
		
		// Ʈ�� ���� ��ȸ
		public void preorderTraversal(Node node) {
			if(node == null) {
				return;
			}
			
			System.out.print(node.getData() + " ");
			preorderTraversal(node.getLeft());
			preorderTraversal(node.getRight());
		}
		
		// Ʈ�� ���� ��ȸ
		public void postorderTraversal(Node node) {
			if(node == null) {
				return;
			}
			
			postorderTraversal(node.getLeft());
			postorderTraversal(node.getRight());
			System.out.print(node.getData() + " ");
		}
		
	}
	
	static class Node {
		private Object data;
		private Node left;
		private Node right;
		
		Node(Object data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}

		public final Object getData() {
			return data;
		}

		public final void setData(Object data) {
			this.data = data;
		}

		public final Node getLeft() {
			return left;
		}

		public final void setLeft(Node left) {
			this.left = left;
		}

		public final Node getRight() {
			return right;
		}

		public final void setRight(Node right) {
			this.right = right;
		}
		
	}

}
