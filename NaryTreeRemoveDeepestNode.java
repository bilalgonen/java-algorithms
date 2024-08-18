import java.util.LinkedList;
import java.util.List;

public class NaryTreeRemoveDeepestNode {
    public static class TreeNode{
        int val;
        List<TreeNode> children = new LinkedList<>();

        TreeNode(int data){
            val = data;
        }


        @Override
        public String toString() {
            return val + " ";
        }
    }

    private static void printNAryTreeWithDepth(TreeNode root, int depth){
        if(root == null) {
            System.out.println("Tree is empty.");
            return;
        }
        System.out.println("depth:" +depth + " | " + "  ".repeat(depth-1) + root.val);
        for(TreeNode node : root.children){
            printNAryTreeWithDepth(node, depth+1);
        }
    }


    private static void getDeepestRec(TreeNode root, int level, int[] max, TreeNode[] node){
        if(root == null) return;
        if(level > max[0]){
            node[1] = node[0];
            node[0] = root;
            max[0] = level;
        }
        if(level == max[0] && root.children.size() > 0){
            node[0] = root;
        }
        for(TreeNode child : root.children){
            getDeepestRec(child, level+1, max, node);
        }
    }

    private static TreeNode removeDeepestNodeByRecursion(TreeNode root){
        if(root == null) return null;
        int[] max = new int[1];
        TreeNode[] deepestNode = new TreeNode[] {root, null};
        getDeepestRec(root, 0, max, deepestNode);
        System.out.println("\nDeepest Node: " + deepestNode[0]);
        if (deepestNode[1] == null) {
            deepestNode[0] = null;
            root = null;
            System.out.println("Deepest Node is the root. So, the tree is empty now.");
        }
        else {
            System.out.println("Its Parent: " + deepestNode[1]);
            System.out.println();
            deepestNode[1].val += deepestNode[0].val;
            deepestNode[1].children.remove(deepestNode[0]);
            System.out.println("After removing the deepest node, the tree is: ");
        }

        return root;
    }

    private static TreeNode case0(){
        return null;
    }

    private static TreeNode case1(){
        TreeNode root = new TreeNode(1);
        return root;
    }

    private static TreeNode case2(){
        TreeNode root = new TreeNode(1);
        root.children.add(new TreeNode(2));
        root.children.add(new TreeNode(3));
        root.children.get(0).children.add(new TreeNode(10));
        return root;
    }


    private static TreeNode case3(){
        TreeNode root = new TreeNode(1);
        root.children.add(new TreeNode(2));
        root.children.add(new TreeNode(3));
        root.children.get(0).children.add(new TreeNode(5));
        root.children.get(0).children.add(new TreeNode(6));
        root.children.get(1).children.add(new TreeNode(8));
        root.children.get(0).children.get(1).children.add(new TreeNode(10));
        return root;
    }

    private static TreeNode case4(TreeNode root){
        root = new TreeNode(1);
        root.children.add(new TreeNode(2));
        root.children.add(new TreeNode(3));
        root.children.add(new TreeNode(4));
        root.children.get(0).children.add(new TreeNode(5));
        root.children.get(0).children.add(new TreeNode(6));
        root.children.get(0).children.add(new TreeNode(7));
        root.children.get(1).children.add(new TreeNode(8));
        root.children.get(2).children.add(new TreeNode(9));
        root.children.get(2).children.add(new TreeNode(10));
        root.children.get(2).children.add(new TreeNode(11));
        root.children.get(0).children.get(1).children.add(new TreeNode(10));
        return root;
    }

    public static void main(String[] args) {
        System.out.println("N-ary Tree Remove Deepest Node");
        System.out.println("\n\n###  Case 0:  Tree is NULL  ###");
        TreeNode root = null;
        printNAryTreeWithDepth(root, 1);
        root = removeDeepestNodeByRecursion(root);
        printNAryTreeWithDepth(root, 1);

        System.out.println("\n\n###  Case 1:  Tree has 1 node  ###");
        root = case1();
        printNAryTreeWithDepth(root, 1);
        root = removeDeepestNodeByRecursion(root);
        printNAryTreeWithDepth(root, 1);

        System.out.println("\n\n###  Case 2:  Tree has 4 nodes  ###");
        root = case2();
        printNAryTreeWithDepth(root, 1);
        root = removeDeepestNodeByRecursion(root);
        printNAryTreeWithDepth(root, 1);

        System.out.println("\n\n###  Case 3:  Tree has 7 nodes  ###");
        root = case3();
        printNAryTreeWithDepth(root, 1);
        root = removeDeepestNodeByRecursion(root);
        printNAryTreeWithDepth(root, 1);

    }
}
