/* 
 * AVL tree
 * ���� Ž�� Ʈ���� Ž�� ������ O(log2n)�� �ð����⵵�� ��������, ������ ���� ���� ���� O(n)�� ����� �ð� ���⵵�� ����
 * AVL Ʈ���� �̷��� ���� Ž�� Ʈ���� ������ ������ ���� ���� ���� Ʈ�� �� �ϳ�
 * ��尡 �߰��� �� �Ǵ� ��尡 ������ �� Ʈ���� ���� ���¸� �ľ��� �� ������ �����Ͽ� ������ ����
 * ���� �μ� = ���� ���� Ʈ�� ���� - ������ ���� Ʈ�� ����
 * ���� �μ��� ���밪�� 2 �̻��� ��쿡 Ʈ�� ������ ����
 * ������ ���� Ž�� Ʈ���� ������� �Ͽ� Ʈ���� ���̸� ���ϴ� �޼ҵ�, ������ ȸ���� ���� �޼ҵ�, ������ �޼ҵ� �߰�
 * ����� ���԰� ���� �޼ҵ� ����
 * 
 */

package tree;

public class AVLTree {

	public static void main(String[] args) {
		AVLT avlt = new AVLT();
		
		avlt.insert(1);
		avlt.insert(2);
		avlt.insert(3);
		avlt.insert(4);
		avlt.insert(5);
		avlt.insert(6);
		avlt.insert(7);
		avlt.insert(8);
		avlt.insert(9);
		avlt.insert(0);
		
		avlt.showAllData();
		System.out.println();
		
		System.out.println("��Ʈ ��� : " + avlt.root.getData());
		
		avlt.remove(5);
		avlt.showAllData();
		System.out.println();
		
		System.out.println("��Ʈ ��� : " + avlt.root.getData());
		
		avlt.remove(4);
		avlt.showAllData();
		System.out.println();
		
		System.out.println("��Ʈ ��� : " + avlt.root.getData());
		
	}
	
	static class AVLT {
		private Tree tree;
		private Node root;
		
		AVLT() {
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
			
			// ��� �߰� �� ���뷱��
			root = rebalance(root);
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
				Node rpNode = removeNode;   // ��ü ����� �θ� ���
				
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
			
			// ��� ���� �� ��������
			root = rebalance(root);
			// ������ ��� ����
			return removeNode;
		}
		
		// ��� ������ ���� ��ȸ������� �ܼ�â�� ���
		public void showAllData() {
			tree.inorderTraversal(root);
		}
		
		// Ʈ���� ���̸� ����Ͽ� ��ȯ(Ʈ���� ��� ��� �߿��� ���� ���� ���� ����� ���� ��ȯ)
		public int getHeight(Node node) {
			int leftHeight;
			int rightHeight;
			
			if(node == null) {
				return 0;
			}
			
			leftHeight = getHeight(tree.getLeftSubTree(node));
			rightHeight = getHeight(tree.getRightSubTree(node));
			
			if(leftHeight > rightHeight) {
				return leftHeight + 1;
			} else {
				return rightHeight + 1;
			}
			
		}
		
		// �� ���� Ʈ���� ������ ��(���� �μ�)�� ��ȯ
		public int getHeightDiff(Node node) {
			int leftHeight;
			int rightHeight;
			
			if(node == null) {
				return 0;
			}
			
			leftHeight = getHeight(tree.getLeftSubTree(node));
			rightHeight = getHeight(tree.getRightSubTree(node));
			
			return leftHeight - rightHeight;
		}
		
		// LL ȸ��
		public Node rotationLL(Node node) {
			Node parent = node;
			Node child = tree.getLeftSubTree(parent);
			
			// LLȸ���� ��� �θ� ����� �������� �ڽĳ���� ������ ��带 �����ϰ�, �ڽĳ���� �����ʿ� �θ��带 ����, ����� �ڽĳ�尡 ��Ʈ��尡 ��
			tree.makeLeftSubTree(parent, tree.getRightSubTree(child));
			tree.makeRightSubTree(child, parent);
			
			return child;  // ����� ��Ʈ��带 ����
		}
		
		// RR ȸ��
		public Node rotationRR(Node node) {
			Node parent = node;
			Node child = tree.getRightSubTree(node);
			
			// RRȸ���� ��� �θ� ����� ���������� �ڽĳ���� ���� ��带 �����ϰ�, �ڽĳ���� ���ʿ� �θ��带 ����, ����� �ڽĳ�尡 ��Ʈ��尡 ��
			tree.makeRightSubTree(parent, tree.getLeftSubTree(child));
			tree.makeLeftSubTree(child, parent);
			
			return child;  // ����� ��Ʈ��带 ����
		}
		
		// LR ȸ�� - �κ��� RRȸ���� �̾ LLȸ���� ����
		public Node rotationLR(Node node) {
			Node parent = node;
			Node child = tree.getLeftSubTree(parent);
			
			tree.makeLeftSubTree(parent, rotationRR(child));  // �κ��� RRȸ��
			
			return rotationLL(parent);      // LLȸ�� �� ����� ��Ʈ��� ����
		}
		
		// RL ȸ�� - �κ��� LLȸ���� �̾ RRȸ���� ����
		public Node rotationRL(Node node) {
			Node parent = node;
			Node child = tree.getRightSubTree(node);
			
			tree.makeRightSubTree(parent, rotationLL(child));  // �κ��� LLȸ��
			
			return rotationRR(parent);      // RRȸ�� �� ����� ��Ʈ��� ����
		}
		
		public Node rebalance(Node node) {
			int heightDiff = getHeightDiff(node);
			
			// ���� �μ��� +2 �̻��̸� LL �Ǵ� LR �����̴�.
			if(heightDiff > 1) {
				if(getHeightDiff(tree.getLeftSubTree(node)) > 0) {
					node = rotationLL(node);    // LL ������ ���
				} else {
					node = rotationLR(node);    // LR ������ ���
				}
			}
			
			// ���� �μ��� -2 �����̸� RR �Ǹ� RL �����̴�.
			if(heightDiff < -1) {
				if(getHeightDiff(tree.getRightSubTree(node)) < 0) {
					node = rotationRR(node);    // RR ������ ���
				} else {
					node = rotationRL(node);    // RL ������ ���
				}
			}
			
			return node;    // ����� ��Ʈ��带 ����
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
