// Approach: Cousins are nodes at the same level with different parent nodes. Traverse the tree level by level and check if both given
// values exist at that level while ensuring they do not have the same parent. If both conditions are met at any level in the tree, return
// true; otherwise, return false.
// Time Complexity: O(n)
// Space Complexity: O(n) for queue

// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// https://leetcode.com/problems/cousins-in-binary-tree/description/
// https://leetcode.com/problems/cousins-in-binary-tree/solutions/239376/java-bfs-time-and-space-beat-100/

import java.util.Deque;
import java.util.ArrayDeque;

public class BTCousins {
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode (int v) {
            val = v;
        }
    }

    TreeNode parentX, parentY;
    int depthX, depthY;

    BTCousins() {
        depthX = depthY = -1;
    }

    boolean areCousinsDFS(TreeNode root, int x, int y) {
        DFS(root, null, x, y, 0);
        return depthX == depthY && parentX != parentY;
    }

     void DFS(TreeNode root, TreeNode parent, int x, int y, int depth) {
        if (root == null) return;

        if (root.val == x) {
            parentX = parent;
            depthX = depth;
        }
        if (root.val == y) {
            parentY = parent;
            depthY = depth;
        }

        DFS(root.left, root, x, y, depth + 1);
        DFS(root.right, root, x, y, depth + 1);
    }

    boolean areCousinsBFS(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }
        Deque<TreeNode> Q = new ArrayDeque<>();
        Q.add(root);
        while (!Q.isEmpty()) {
            int levelSize = Q.size();
            boolean levelHasX = false, levelHasY = false;
            for (int i = 0; i < levelSize; i++) {
                TreeNode curr = Q.poll();
                if (curr.val == x) {
                    levelHasX = true;
                }
                if (curr.val == y) {
                    levelHasY = true;
                }
                if (curr.left != null && curr.right != null) {
                    if (curr.left.val == x && curr.right.val == y || curr.left.val == y && curr.right.val == x) {
                        return false;
                    }
                }
                if (curr.left != null) {
                    Q.add(curr.left);
                }
                if (curr.right != null) {
                    Q.add(curr.right);
                }
            }
            if (levelHasX && levelHasY) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        BTCousins btc = new BTCousins();

        // Input: root = [1,2,3,4], x = 4, y = 3
        // Output: false
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.right = new TreeNode(3);
//        root.right.left = new TreeNode(5);

        System.out.println("Nodes with given values are cousins (true/false): " + btc.areCousinsBFS(root, 4, 3));
    }
}