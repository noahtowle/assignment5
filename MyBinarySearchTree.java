package accidentpack;

class TreeNode {
    Report report;
    TreeNode left, right;
    int leftChildren;
    int rightChildren;

    public TreeNode(Report report) {
        this.report = report;
        this.leftChildren = 0;
        this.rightChildren = 0;
        left = right = null;
    }
}

public class MyBinarySearchTree {
    private TreeNode root;

    public MyBinarySearchTree() {
        root = null;
    }

    public void insert(Report report) {
        root = insertRec(root, report);
    }

    private TreeNode insertRec(TreeNode root, Report report) {
        // If the tree is empty, create a new node
        if (root == null) {
            root = new TreeNode(report);
            return root;
        }

        // Otherwise, recur down the tree
        if (report.getStart_time().compareTo(root.report.getStart_time()) < 0) {
        	root.leftChildren++;
            root.left = insertRec(root.left, report);
        } else if (report.getStart_time().compareTo(root.report.getStart_time()) > 0) {
        	root.rightChildren++;
            root.right = insertRec(root.right, report);
        }

        return root;
    }

    // Search for a report in the tree, return true or false if found/not found
    public boolean search(Report report) {
        return searchRec(root, report);
    }

    private boolean searchRec(TreeNode root, Report report) {
        // Base cases: root is null or the key is equal to the root
        if (root == null || root.report.getStart_time() == report.getStart_time()) {
            return root != null;
        }

        // Report is smaller than the root's report, search in the left subtree
        if (report.getStart_time().compareTo(root.report.getStart_time()) < 0) {
            return searchRec(root.left, report);
        }

        // Report is larger than the root's report, search in the right subtree
        return searchRec(root.right, report);
    }

    // Inorder traversal of the tree (prints the keys in sorted order)
    public void inorder() {
        inorderRec(root);
    }

    private void inorderRec(TreeNode root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.report.getStart_time() + " ");
            inorderRec(root.right);
        }
    }
    
}