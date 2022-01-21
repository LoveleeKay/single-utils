import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * leet code 数组构造二叉树， 广度搜索，null代表叶子节点为空，空节点下面不会再接东西
     * @param integers
     * @return
     */
    public static TreeNode constructTree(Integer[] integers) {
        int len = integers.length;
        TreeNode[] treeNodes = new TreeNode[len];
        List<Integer> currentParentIndexs = new ArrayList<>();
        currentParentIndexs.add(0);
        List<Integer> nextParentIndexs = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (integers[i] != null) {
                treeNodes[i] = new TreeNode(integers[i]);
            }
        }
        int i = 1;
        while (i < len) {
            int currentParentIndexsSize = currentParentIndexs.size();
            for (int j = 0; j < currentParentIndexsSize; j++) {
                if (treeNodes[i] != null) {
                    nextParentIndexs.add(i);
                }
                treeNodes[currentParentIndexs.get(j)].left = treeNodes[i++];
                if (i >= len) {
                    break;
                }
                if (treeNodes[i] != null) {
                    nextParentIndexs.add(i);
                }
                treeNodes[currentParentIndexs.get(j)].right = treeNodes[i++];
                if (i >= len) {
                    break;
                }
            }
            currentParentIndexs.clear();
            currentParentIndexs.addAll(nextParentIndexs);
            nextParentIndexs.clear();
        }

        if (treeNodes.length > 0) {
            return treeNodes[0];
        }
        return null;
    }

    private void frontTravel(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || (!stack.isEmpty())) {
            while (root != null) {
                System.out.println(root.val);
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            root = root.right;

        }

    }

    private void midTravel(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || (!stack.isEmpty())) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();

            System.out.println(root.val);

            root = root.right;
        }
    }

    private void behindTravel(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || (!stack.isEmpty())) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || pre == root.right) {
                System.out.println(root.val);
                pre = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
    }

    private void bfs(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            System.out.println(root.val);
            if (root.left != null) {
                queue.offer(root.left);
            }
            if (root.right != null) {
                queue.offer(root.right);
            }

        }
    }

    @Override
    public String toString(){
        return String.valueOf(val);
    }
}
