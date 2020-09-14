package com.wecash.algorithm.tree.BinTree;

import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.List;

/**
 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。

 例如：

 给定二叉树 [3,9,20,null,null,15,7]，

 3
 / \
 9  20
 /  \
 15   7
 返回它的最大深度 3 。

  

 提示：

 节点总数 <= 10000

 */
public class MaxDepth {

    /**
     * 后序遍历（DFS Depth-First-Search）
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if(root == null) {return 0;}
        return Math.max(maxDepth(root.leftChild), maxDepth(root.rightChild)) + 1;
    }

    /**
     * 层序遍历（BFS Breadth-First-Search）
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        if(root == null) {return 0;}
        List<TreeNode> queue = new LinkedList<TreeNode>() {{ add(root); }}, tmp;
        int res = 0;
        while(!queue.isEmpty()) {
            tmp = new LinkedList<>();
            for(TreeNode node : queue) {
                if(node.leftChild != null) {tmp.add(node.leftChild);}
                if(node.rightChild != null){tmp.add(node.rightChild);}
            }
            queue = tmp;
            res++;
        }
        return res;
    }

}
