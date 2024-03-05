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
        } else if (report.getStart_time().compareTo(root.report.getStart_time()) == 0) {
        	root.rightChildren++;
            root.right = insertRec(root.right, report);
        }

        return root;
    }

    // Search for a date in the tree, return true or false if found/not found
    public int search(String date) {
        return searchRec(root, date);
    }
    
    // Searches for state values during setup
    public boolean searchState(String state) {
        return searchRecState(root, state);
    }

    private int searchRec(TreeNode root, String date) {
        // Base case: root is null return 0, no data
        if (root == null) {
            return 0;
        }
        
        if(root.report.getDate().compareTo(date) == 0) {
        	return root.rightChildren;
        }
        
        if(root.report.getDate().compareTo(date) > 0) {
        	return root.rightChildren;
        }


        return searchRec(root.right, date);
    }
    
    private boolean searchRecState(TreeNode root, String state) {
        // Base case: root is not established so the state cannot be equal
        if (root == null) {
            return false;
        }
        
        // Report state values are equal so return true
        if (root.report.getState().equals(state)) {
        	return true;
        }
        // Report state values are not equal so return false
        return false;
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
    

    //calculate the number of records, 
        public int numberOfRecords(String date) {
            return calculateNumberOfRecordsRec(root, date);
        }

        //Recursive helper method to help calculate the number of records in BTS 
        private int calculateNumberOfRecordsRec(TreeNode root, String date) {
            if (root == null) {
                return 0;
            }

            //finds the number of records in the subtree (left and right)
            int leftCount = calculateNumberOfRecordsRec(root.left, date);
            int rightCount = calculateNumberOfRecordsRec(root.right, date);

            //add 1 for the current node, subtracting 2 to ignore two fields 
            return leftCount + rightCount + 1 - 2;

    }

    
}