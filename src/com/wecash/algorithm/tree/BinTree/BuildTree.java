package com.wecash.algorithm.tree.BinTree;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。
 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。

  

 例如，给出

 前序遍历 preorder = [3,9,20,15,7]
 中序遍历 inorder = [9,3,15,20,7]
 返回如下的二叉树：

  3
 / \
 9  20
    / \
   15 7
  

 限制：

 0 <= 节点个数 <= 5000
 */
public class BuildTree {

    /**方法一：递归。
     * 二叉树的前序遍历顺序是：根节点、左子树、右子树，每个子树的遍历顺序同样满足前序遍历顺序。
     *
     * 二叉树的中序遍历顺序是：左子树、根节点、右子树，每个子树的遍历顺序同样满足中序遍历顺序。
     */
    class Solution {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if (preorder == null || preorder.length == 0) {
                return null;
            }
            Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
            int length = preorder.length;
            for (int i = 0; i < length; i++) {
                indexMap.put(inorder[i], i);
            }
            TreeNode root = buildTree(preorder, 0, length - 1, inorder, 0, length - 1, indexMap);
            return root;
        }

        public TreeNode buildTree(int[] preorder, int preorderStart, int preorderEnd, int[] inorder, int inorderStart, int inorderEnd, Map<Integer, Integer> indexMap) {
            if (preorderStart > preorderEnd) {
                return null;
            }
            int rootVal = preorder[preorderStart];
            TreeNode root = new TreeNode(rootVal);
            if (preorderStart == preorderEnd) {
                return root;
            } else {
                int rootIndex = indexMap.get(rootVal);
                int leftNodes = rootIndex - inorderStart, rightNodes = inorderEnd - rootIndex;
                TreeNode leftSubtree = buildTree(preorder, preorderStart + 1, preorderStart + leftNodes, inorder, inorderStart, rootIndex - 1, indexMap);
                TreeNode rightSubtree = buildTree(preorder, preorderEnd - rightNodes + 1, preorderEnd, inorder, rootIndex + 1, inorderEnd, indexMap);
                root.leftChild = leftSubtree;
                root.rightChild = rightSubtree;
                return root;
            }
        }
    }
    /**
     迭代
     */
    class Solution2 {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if (preorder == null || preorder.length == 0) {
                return null;
            }
            TreeNode root = new TreeNode(preorder[0]);
            int length = preorder.length;
            Stack<TreeNode> stack = new Stack<TreeNode>();
            stack.push(root);
            int inorderIndex = 0;
            for (int i = 1; i < length; i++) {
                int preorderVal = preorder[i];
                TreeNode node = stack.peek();
                if (node.value != inorder[inorderIndex]) {
                    node.leftChild = new TreeNode(preorderVal);
                    stack.push(node.leftChild);
                } else {
                    while (!stack.isEmpty() && stack.peek().value == inorder[inorderIndex]) {
                        node = stack.pop();
                        inorderIndex++;
                    }
                    node.rightChild = new TreeNode(preorderVal);
                    stack.push(node.rightChild);
                }
            }
            return root;
        }
    }

}
