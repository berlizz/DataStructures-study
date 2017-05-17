/* 
 * Binary search Tree
 * ���� Ž�� Ʈ��
 * ���� Ž�� Ʈ���� ��忡 ����� Ű(������)�� ����
 * ��Ʈ ����� Ű�� ���� ���� Ʈ���� �����ϴ� ��� ����� Ű���� ŭ
 * ��Ʈ ����� Ű�� ������ ���� Ʈ���� �����ϴ� ��� ����� Ű���� ����
 * ���ʰ� ������ ���� Ʈ���� ���� Ž�� Ʈ��
 * �����͸� ���� �� ��� ������ �������� ũ�� ������������ ��Ģ�� �������� �����͸� ����
 * Ž���� ���������� �״�� ����
 * TreeŬ������ tree.BinaryTree�� TreeŬ�������� removeLeftSubTree, removeRightSubTree�޼ҵ� �߰� 
 * 
 */

package tree;

public class BinarySearchTree {

	public static void main(String[] args) {
		BSTree tree = new BSTree();
		tree.insert(5);  //                     5
		tree.insert(8);  //                   /   \
		tree.insert(1);  //                  1     8
		tree.insert(6);  //                   \   / \
		tree.insert(4);  //                    4 6   9
		tree.insert(9);  //                   /   \   
		tree.insert(3);  //                  3     7    
		tree.insert(2);  //                 /
		tree.insert(7);  //                2
		
		tree.showAllData();
		System.out.println();
		
		tree.remove(3);
		tree.showAllData();
		System.out.println();
		
		tree.remove(1);
		tree.showAllData();
		System.out.println();
		
		tree.remove(6);
		tree.showAllData();
		System.out.println();
		
	}
	
	static class BSTree {
		private Tree tree;
		private Node root;
		
		BSTree() {
			tree = new Tree();
			root = null;
		}
		
		public int getNodeData(Node node) {
			return tree.getNodeData(node);
		}
		
		// ������ �߰��ϱ�
		public void insert(int data) {
			Node parent = null;
			Node child = root;
			Node newNode = tree.makeNode(data);
			
			// �ڽ� ��尡 null�� ������ 
			while(child != null) {
				// ������(Ű)�� �ߺ��� ������� ����
				if(data == tree.getNodeData(child)) {
					return;
				}
				
				parent = child;
				
				// ����� �����Ͱ� ���� ������ �����ͺ��� ū ��� ��������, �ݴ��� ��� ���������� ������ ��ġ�� ã��
				if(tree.getNodeData(parent) > data) {
					child = tree.getLeftSubTree(child);
				} else {
					child = tree.getRightSubTree(child);
				}
			}
			
			// parent�� null�� ���� ����ִ� Ʈ���� ó������ �����Ͱ� ���Ե� ���
			if(parent != null) {
				// parent����� �����Ͱ� ���� ������ �����ͺ��� ū ��� �������� Ʈ������, �ݰ��� ��� ����������
				if(tree.getNodeData(parent) > data) {
					tree.makeLeftSubTree(parent, newNode);
				} else {
					tree.makeRightSubTree(parent, newNode);
				}
			} else {
				root = newNode;
			}
			
		}
		
		// ������ Ž��
		public Node search(int data) {
			Node node = root;
			int nodeData;
			  
			while(node != null) {
				nodeData = tree.getNodeData(node);
				
				if(nodeData == data) {
					return node;                         // Ž�� ���� �� �ش� ��� ����
				} else if(nodeData > data) {
					node = tree.getLeftSubTree(node);    // Ž���� ����� �����Ͱ� ã���� �ϴ� �����ͺ��� ū ��� �������� Ž��
				} else {
					node = tree.getRightSubTree(node);   // Ž���� ����� �����Ͱ� ã���� �ϴ� �����ͺ��� ���� ��� ���������� Ž��
				}
			}
			
			// Ž�� ����
			return null;
		}
		
		// ��� ����
		public Node remove(int data) {
			Node vRoot = tree.makeNode(0);  // ���� ��Ʈ ���
			Node parentNode = vRoot;
			Node childNode = root;
			Node removeNode = null;
			
			// ��Ʈ ��带 vRoot�� ������ �ڽ� ���� �ǰ� �Ѵ�
			tree.makeRightSubTree(vRoot, childNode);
			
			// ������ ��带 Ž��
			while(childNode != null && tree.getNodeData(childNode) != data) {
				parentNode = childNode;
				
				if(tree.getNodeData(childNode) > data) {
					childNode = tree.getLeftSubTree(childNode);
				} else {
					childNode = tree.getRightSubTree(childNode);
				}
			}
			
			// Ž�� ����, ������ ��� �������� ����
			if(childNode == null) {
				return null;
			}
			
			removeNode = childNode;
			
			// ������ ��尡 �ܸ� ����� ���
			if(tree.getLeftSubTree(removeNode) == null && tree.getRightSubTree(removeNode) == null) {
				if(tree.getLeftSubTree(parentNode) == removeNode) {
					tree.removeLeftSubTree(parentNode);    // ������ ��尡 �θ��� ���ʿ� �ִ� ���, �θ����� ���� ����Ʈ�� ����
				} else {
					tree.removeRightSubTree(parentNode);   // ������ ��尡 �θ��� �����ʿ� �ִ� ���, �θ����� ������ ����Ʈ�� ����
				}
			} 
			
			// ������ ��尡 �ϳ��� �ڽ� ��带 ������ ���
			else if(tree.getLeftSubTree(removeNode) == null || tree.getRightSubTree(removeNode) == null) {
				Node removeChildNode; // ������ ����� �ڽ� ���
				
				if(tree.getLeftSubTree(removeNode) != null) {
					removeChildNode = tree.getLeftSubTree(removeNode);    // ������ ����� �ڽ��� ���ʿ� �ִ� ��� 
				} else {
					removeChildNode = tree.getRightSubTree(removeNode);   // ������ ����� �ڽ��� �����ʿ� �ִ� ���
				}
				
				if(tree.getLeftSubTree(parentNode) == removeNode) {
					tree.makeLeftSubTree(parentNode, removeChildNode);    // ������ ��尡 �θ����� ���ʿ� �ִ� ���
				} else {
					tree.makeRightSubTree(parentNode, removeChildNode);   // ������ ��尡 �θ����� �����ʿ� �ִ� ���
				}
			}
			
			// ������ ��尡 �ΰ��� �ڽ� ��带 ������ ���
			else {
				Node rNode = tree.getRightSubTree(removeNode);    // ��ü ���
				Node rpNode = null;   // ��ü ����� �θ� ���
				
				// ������ ��带 ��ü�� ��带 ã�´�. ������ ����� ������ Ʈ�� �� ���� ���� ���� ã�� ����
				while(tree.getLeftSubTree(rNode) != null) {
					rpNode = rNode;
					rNode = tree.getLeftSubTree(rNode);
				}
				
				int removeData = tree.getNodeData(removeNode);  // ������ ����� ������
				removeNode.setData(tree.getNodeData(rNode));    // ������ ��忡 ��ü��� ������ ����
				
				if(tree.getLeftSubTree(rpNode) == rNode) {
					tree.makeLeftSubTree(rpNode, tree.getRightSubTree(rNode));   // ��ü�� ��尡 �θ����� ���ʿ� �ִ� ���
				} else {
					tree.makeRightSubTree(rpNode, tree.getRightSubTree(rNode));  // ��ü�� ��尡 �θ����� �����ʿ� �ִ� ���
				}
				
				removeNode = rNode;
				removeNode.setData(removeData);    // ������ ��� ������ ����(������ ��� ����)
			}
			
			// ������ ��尡 ��Ʈ ����� ���
			if(tree.getRightSubTree(vRoot) != root) {
				root = tree.getRightSubTree(vRoot);
			}
			
			// ������ ��� ����
			return removeNode;
		}
		
		// ��� ������ ���� ��ȸ������� �ܼ�â�� ���
		public void showAllData() {
			tree.inorderTraversal(root);
		}
		
	}
	
	static class Tree {
		
		public Node makeNode(int data) {
			Node newNode = new Node(data);
			
			return newNode;
		}
		
		public int getNodeData(Node node) {
			return node.getData();
		}
		
		public Node getLeftSubTree(Node node) {
			return node.getLeft();
		}
		
		public Node getRightSubTree(Node node) {
			return node.getRight();
		}
		
		public void makeLeftSubTree(Node parent, Node child) {
			parent.setLeft(child);
		}
		
		public void makeRightSubTree(Node parent, Node child) {
			parent.setRight(child);
		}
		
		// ���޹��� ����� ���� �ڽĳ�� ����, ���ŵ� ��� ��ȯ
		public Node removeLeftSubTree(Node node) {
			Node removeNode = null;
			if(node != null) {
				removeNode = node.getLeft();
				node.setLeft(null);
			}
			
			return removeNode;
		}
		// ���޹��� ����� ������ �ڽĳ�� ����, ���ŵ� ��� ��ȯ		
		public Node removeRightSubTree(Node node) {
			Node removeNode = null;
			if(node != null) {
				removeNode = node.getRight();
				node.setRight(null);
			}
			
			return removeNode;
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
		
	}
	
	static class Node {
		private int data;
		private Node left;
		private Node right;
		
		Node(int data) {
			this.data = data;
		}

		public final int getData() {
			return data;
		}

		public final void setData(int data) {
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
