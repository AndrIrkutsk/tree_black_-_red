package lesson_9_task_copy;

public class Tree {
    private Node root;

    class Node {
        int value;
        Node left;
        Node right;
        Color color;

        Node(int value, Color color) {
            this.value = value;
            this.color = color;
        }
    }

    enum Color {
        BLACK,
        RED
    }

    public void insert(int value) {
        root = insert(root, value);
        root.color = Color.BLACK;
    }

    private Node insert(Node node, int value) {
        if (node == null) {
            return new Node(value, Color.RED);
        }

        if (value < node.value) {
            node.left = insert(node.left, value);
        } else if (value > node.value) {
            node.right = insert(node.right, value);
        } else {
            return node;
        }

        node = balance(node);

        return node;
    }

    private Node balance(Node node) {
        if (isRed(node.right) && !isRed(node.left)) {
            node = leftRotate(node);
        }

        if (isRed(node.left) && isRed(node.left.left)) {
            node = rightRotate(node);
        }

        if (isRed(node.left) && isRed(node.right)) {
            swapColors(node);
        }

        return node;
    }

    private Node leftRotate(Node node) {
        Node temp = node.right;
        node.right = temp.left;
        temp.left = node;
        temp.color = node.color;
        node.color = Color.RED;
        return temp;
    }

    private Node rightRotate(Node node) {
        Node temp = node.left;
        node.left = temp.right;
        temp.right = node;
        temp.color = node.color;
        node.color = Color.RED;
        return temp;
    }

    private void swapColors(Node node) {
        node.color = (node.color == Color.RED ? Color.BLACK : Color.RED);
        node.left.color = Color.BLACK;
        node.right.color = Color.BLACK;
    }

    private boolean isRed(Node node) {
        if (node == null) {
            return false;
        }
        return node.color == Color.RED;
    }

    public Node find(int value) {
        return find(root, value);
    }

    private Node find(Node node, int value) {
        if (node == null) {
            return null;
        }
        if (value < node.value) {
            return find(node.left, value);
        } else if (value > node.value) {
            return find(node.right, value);
        } else {
            return node;
        }
    }

    public void printTree() {
        printTree(root, "", true);
    }

    private void printTree(Node node, String indent, boolean last) {
        if (node != null) {
            System.out.println(indent + "+- " + node.value + " (" + node.color + ")");
            indent += last ? "   " : "|  ";

            printTree(node.left, indent, false);
            printTree(node.right, indent, true);
        }
    }
}
