package helper.classes;

public class TreeProvider {
    
    public static Node getTree() {
        Node root = new Node(4);

        Node three = new Node(3);
        Node six = new Node(6);
        Node seven = new Node(7);
        Node eight = new Node(8);
        Node ten = new Node(10);
        Node tvelwe = new Node(12);
        Node thirteen = new Node(13);
        Node seventeen = new Node(17);

        root.left = eight;
        root.right = seven;

        eight.left = six;
        eight.right = three;

        six.right = seventeen;

        seven.right = thirteen;

        thirteen.right = ten;
        thirteen.left = tvelwe;

        return root;
    }
}
