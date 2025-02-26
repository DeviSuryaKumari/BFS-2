// Approach: Only the last element at each level is visible when viewing a binary tree from the right side. Traverse the tree level by
// level using BFS with a queue. Whenever the last element of a level is encountered, add it to the answer array. Return the answer array
// after traversing all levels.
// Time Complexity: O(n)
// Space Complexity: O(n) for queue

// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// https://leetcode.com/problems/binary-tree-right-side-view/description/

import java.util.Deque;
import java.util.ArrayDeque;

import java.util.List;
import java.util.ArrayList;

public class RightViewOfBT {
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode (int v) {
            val = v;
        }
    }

    List<Integer> rightView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Deque<TreeNode> Q = new ArrayDeque<>();
        Q.add(root);
        while (!Q.isEmpty()) {
            int levelSize = Q.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode curr = Q.poll();
                if (i == levelSize - 1) {
                    ans.add(curr.val);
                }
                if (curr.left != null) {
                    Q.add(curr.left);
                }
                if (curr.right != null) {
                    Q.add(curr.right);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        RightViewOfBT rvobt = new RightViewOfBT();
        // Input: root = [1,2,3,null,5,null,4]
        // Output: [1,3,4]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);
        System.out.println("Right view of given binary tree is: " + rvobt.rightView(root).toString()); // prints [1,3,4]
    }
}