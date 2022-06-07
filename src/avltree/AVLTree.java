package avltree;

public class AVLTree {

    private Node root;
    
    public void insert(int ...nodeKeys) {
        for (int key : nodeKeys) root = insert(root, key);
    }
    
    public void remove(int ...nodeKeys) {
        for (int key : nodeKeys) root = remove(root, key);
    }
    
    public Node search(int key) {
        Node node = root;
        while (node != null) {
            System.out.print(node.getKey() + " "); // Exibe a chave no console
            if (node.getKey() == key) break;
            node = key > node.getKey() ? node.getRight() : node.getLeft();
        }
        System.out.println();
        return node;
    }
    public void printPreOrder() {
        printPreOrder(root);
    }
    
    public void printPostOrder() {
        printPostOrder(root);
    }
    
    public void printInOrder() {
        printInOrder(root);
    }
    
    public void printLevelOrder() {
        if (root == null) return;
        for (int i=0; i<=root.getHeight(); i++)
            printLevelOrder(root, 0, i);
    }
    
    public Node getRoot() {
        return root;
    }
    
    private Node insert(Node node, int nodeKey) {
        if(node == null) {
            return new Node(nodeKey); // Cria um novo node como folha
        }
        else if (node.getKey() > nodeKey) {
            node.setLeft(insert(node.getLeft(), nodeKey));
        }
        else if (node.getKey() < nodeKey) {
            node.setRight(insert(node.getRight(), nodeKey));
        }
        else {
            return node;
        }
        return balancedNode(node);
    }
    
    private Node remove(Node node, int nodeKey) {
        if (node == null) {
            return node;
        }
        if (nodeKey > node.getKey()) {
            node.setRight(remove(node.getRight(), nodeKey));
        } else if (nodeKey < node.getKey()) {
            node.setLeft(remove(node.getLeft(), nodeKey));
        } else {
            node = removeRoot(node);
        }
        return balancedNode(node);
    }
    
    private Node removeRoot(Node node) {
        if (node.getHeight() == 0) return null;
        
        Node newNode;
        if (node.getLeftHeight() > node.getRightHeight()) {
            newNode = new Node(getHighestKey(node.getLeft()));
            newNode.setLeft(remove(node.getLeft(), newNode.getKey()));
            newNode.setRight(node.getRight());
        } else {
            newNode = new Node(getLowestKey(node.getRight()));
            newNode.setLeft(node.getLeft());
            newNode.setRight(remove(node.getRight(), newNode.getKey()));
        }
        return newNode;
    }
    private Node balancedNode(Node node) {
        if (node == null) return node;
        
        node.updateHeight();
        int balance = node.getLeftHeight() - node.getRightHeight();
        if (balance < -1) {
            if (node.getRight().getRightHeight() > node.getRight().getLeftHeight()) {
                node = rotateLeft(node);
            } else {
                node.setRight(rotateRight(node.getRight()));
                node = rotateLeft(node);
            }
        } else if (balance > 1) {
            if (node.getLeft().getLeftHeight() > node.getLeft().getRightHeight()) {
                node = rotateRight(node);
            } else {
                node.setLeft(rotateLeft(node.getLeft()));
                node = rotateRight(node);
            }
        }
        return node;
    }
    private Node rotateLeft(Node node) {
        Node newRoot = node.getRight();
        Node newRight = newRoot.getLeft();
        newRoot.setLeft(node);
        node.setRight(newRight);
        node.updateHeight();
        newRoot.updateHeight();
        return newRoot;
    }
    private Node rotateRight(Node node) {
        Node newRoot = node.getLeft();
        Node newLeft = newRoot.getRight();
        newRoot.setRight(node);
        node.setLeft(newLeft);
        node.updateHeight();
        newRoot.updateHeight();
        return newRoot;
    }
    
    private void printPreOrder(Node node) {
        if (node == null) return;
        System.out.print(node.getKey() + " ");
        printPreOrder(node.getLeft());
        printPreOrder(node.getRight());
    }
    
    private void printPostOrder(Node node) {
        if (node == null) return;
        printPostOrder(node.getLeft());
        printPostOrder(node.getRight());
        System.out.print(node.getKey() + " ");
    }
    
    private void printInOrder(Node node) {
        if (node == null) return;
        printInOrder(node.getLeft());
        System.out.print(node.getKey() + " ");
        printInOrder(node.getRight());
    }
    
    private void printLevelOrder(Node node, int currentLevel, int maxLevel) {
        if (node == null) return;
        if (currentLevel == maxLevel) {
            System.out.print(node.getKey() + " ");
        } else {
            printLevelOrder(node.getLeft(), currentLevel + 1, maxLevel);
            printLevelOrder(node.getRight(), currentLevel + 1, maxLevel);
        }
    }
    private int getLowestKey(Node node) {
        while (node.getLeft() != null) node = node.getLeft();
        return node.getKey();
    }
    private int getHighestKey(Node node) {
        while (node.getRight() != null) node = node.getRight();
        return node.getKey();
    }
    
}
