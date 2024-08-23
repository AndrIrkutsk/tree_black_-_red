package lesson_9_task_copy;

public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree();

        for (int i = 1; i <= 5; i++) {
            tree.insert(i);
        }

        tree.printTree();
    }
}
