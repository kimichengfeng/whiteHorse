package com.wecash.algorithm.tree.BinTree;

/**
 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。

 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

     1
    / \
   2   2
  / \ / \
 3  4 4  3
 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

     1
    / \
   2   2
    \   \
    3    3

  

 示例 1：

 输入：root = [1,2,2,3,4,4,3]
 输出：true
 示例 2：

 输入：root = [1,2,2,null,3,null,3]
 输出：false

 */
public class IsSymmetric {
    public boolean isSymmetric(TreeNode root) {
        return root == null ? true : recur(root.leftChild, root.rightChild);
    }
    boolean recur(TreeNode L, TreeNode R) {
        if(L == null && R == null) return true;
        if(L == null || R == null || L.value != R.value) return false;
        return recur(L.leftChild, R.rightChild) && recur(L.rightChild, R.leftChild);
    }

}
