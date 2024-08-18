import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NaryTree {
    public static class TreeNode{
        int val;
        List<TreeNode> children = new LinkedList<>();

        TreeNode(int data){
            val = data;
        }

        TreeNode(int data,List<TreeNode> child){
            val = data;
            children = child;
        }

        @Override
        public String toString() {
            // TODO Auto-generated method stub
            return val + " ";
        }
    }

    private static void printNAryTreeLevelOrderIterative(TreeNode root){
        if(root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int len = queue.size();
            for(int i=0;i<len;i++) {
                TreeNode node = queue.poll();
                assert node != null;
                System.out.print(node.val + " ");
                for (TreeNode item : node.children) {
                    queue.offer(item);
                }
            }
            System.out.println();
        }
    }
    private static void printNAryTreePreorderRecursion(TreeNode root){
        if(root == null) return;
        System.out.print(root.val + " ");
        for(TreeNode node : root.children){
            printNAryTreePreorderRecursion(node);
        }
    }

    private static void printNAryTreePreorderDepthRecursion(TreeNode root, int depth){
        if(root == null) return;
        // System.out.printf("%d(%d) ", root.val , depth);
        System.out.println(depth + ": " + "  ".repeat(depth) + root.val);
        for(TreeNode node : root.children){
            printNAryTreePreorderDepthRecursion(node, depth+1);
        }
    }


    private static void printNAryTreePostorderRecursion(TreeNode root){
        if(root == null) return;
        for(TreeNode node : root.children){
            printNAryTreePostorderRecursion(node);
        }
        System.out.print(root.val + " ");
    }

    private static void printNAryTreeInorderRecursion(TreeNode root){
        if(root == null) return;
        for(TreeNode node : root.children){
            printNAryTreeInorderRecursion(node);
        }
        System.out.print(root.val + " ");
    }

    private static void maxDepthIteration(TreeNode root){
        if(root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while(!queue.isEmpty()) {
            int len = queue.size();
            for(int i=0;i<len;i++) {
                TreeNode node = queue.poll();
                assert node != null;
                for (TreeNode item : node.children) {
                    queue.offer(item);
                }
            }
            depth++;
        }
        System.out.println("Max Depth : " + depth);
    }

    private static void displayWithDepthIteration(TreeNode root){
        if(root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        System.out.println("queue : " + queue.toString());
        int depth = 0;
        while(!queue.isEmpty()) {
            System.out.println("Depth:" + depth + "\tqueue : " + queue.toString());
            int len = queue.size();
            for(int i=0;i<len;i++) {
                TreeNode node = queue.poll();
                System.out.println("Polled: " + node);
                System.out.println("Depth:" + depth + "\tqueue : " + queue.toString());
                assert node != null;
                for (TreeNode item : node.children) {
                    System.out.println(" Add: " + item);
                    queue.offer(item);
                }
                System.out.println("Depth:" + depth + "\tqueue : " + queue.toString());
            }
            depth++;
        }
        System.out.println("Max Depth : " + depth);
    }

    private static int maxDepthRecursion(TreeNode root){
        if(root == null) return 0;
        int max = 0;
        for(TreeNode child : root.children){
            max = Math.max(max, maxDepthRecursion(child));
        }
        return max + 1;
    }

    private static int maxDepthRecursionParent(TreeNode root){
        if(root == null) return 0;
        int max = 0;
        TreeNode deepestNode = root;
        for(TreeNode child : root.children){
            max = Math.max(max, maxDepthRecursionParent(child));
        }
        return max + 1;
    }

    private static void getDeepestRec(TreeNode root, int level, int[] max, TreeNode[] node){
        if(node == null) return;
        if(level > max[0]){
            node[0] = root;
            max[0] = level;
        }
        for(TreeNode child : root.children){
            getDeepestRec(child, level+1, max, node);
        }
    }

    private static TreeNode getDeepestRecursion(TreeNode root){
        if(root == null) return null;
        int[] max = new int[1];
        TreeNode[] deepestNode = new TreeNode[] {root};
        getDeepestRec(root, 0, max, deepestNode);
        return deepestNode[0];
    }



    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.children.add(new TreeNode(2));
        root.children.add(new TreeNode(3));
        // root.children.add(new TreeNode(4));
        // root.children.get(0).children.add(new TreeNode(5));
        // root.children.get(0).children.add(new TreeNode(6));
        // root.children.get(0).children.add(new TreeNode(7));
        root.children.get(1).children.add(new TreeNode(8));
        // root.children.get(2).children.add(new TreeNode(9));
        // root.children.get(2).children.add(new TreeNode(10));
        // root.children.get(2).children.add(new TreeNode(11));
        // root.children.get(0).children.get(1).children.add(new TreeNode(61));
        // printNAryTreeLevelOrder(root);
        printNAryTreePreorderDepthRecursion(root, 1);
        // printNAryTreePostorder(root);
        // printNAryTreeInorder(root);
        // maxDepth(root);
        // displayWithDepth(root);
        // System.out.println("Max Depth : " + maxDepthRecursionParent(root));
        System.out.println("Deepest Node : " + getDeepestRecursion(root).val);
    }
}
