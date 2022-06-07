package avltree;

public class Node {
    
    private int key;
    private int height;
    private Node left;
    private Node right;

    public Node(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getHeight() {
        return height;
    }
    
    public int getLeftHeight() {
        return left == null ? -1 : left.getHeight();
    }
    
    public int getRightHeight() {
        return right == null ? -1 : right.getHeight();
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
    
    public void updateHeight() {
        this.height = 1 + Math.max(getLeftHeight(), getRightHeight());
    }
    
    @Override
    public String toString() {
        return String.format("[%s %d %s]", left == null ? "" : left, key, right == null ? "" : right);
    }
    
}
