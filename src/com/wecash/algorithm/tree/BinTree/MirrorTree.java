package com.wecash.algorithm.tree.BinTree;

import com.wecash.nevermore.json.GsonUtils;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA
 请完成一个函数，输入一个二叉树，该函数输出它的镜像。

 例如输入：

      4
    /   \
   2     7
  / \   / \
 1   3 6   9
 镜像输出：

      4
    /   \
   7     2
  / \   / \
 9   6 3   1

  

 示例 1：

 输入：root = [4,2,7,1,3,6,9]
 输出：[4,7,2,9,6,3,1]

 */
public class MirrorTree {

    /**
     * 方法一：递归法
     * 根据二叉树镜像的定义，考虑递归遍历（dfs）二叉树，交换每个节点的左 / 右子节点，即可生成二叉树的镜像。
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {
        if(root == null) return null;
        TreeNode tmp = root.leftChild;
        root.leftChild = mirrorTree(root.rightChild);
        root.rightChild = mirrorTree(tmp);
        return root;
    }
    public TreeNode mirrorTree2(TreeNode root){
        if(root == null) return null;
        Stack<TreeNode> stack = new Stack<TreeNode>(){{add(root);}};
        while(!stack.isEmpty()){
            TreeNode treeNode = stack.pop();
            if(treeNode.leftChild != null){
                stack.push(treeNode.leftChild);
            }
            if(treeNode.rightChild != null){
                stack.push(treeNode.rightChild);
            }
            TreeNode tmp = treeNode.leftChild;
            treeNode.leftChild = treeNode.rightChild;
            treeNode.rightChild = tmp;
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(9);

        node1.leftChild = node2;
        node1.rightChild = node3;

        node2.leftChild = node4;
        node2.rightChild = node5;

        node3.leftChild = node6;
        node3.rightChild = node7;

        BinaryTree binaryTree = new BinaryTree();
       binaryTree.wide(node1);

        MirrorTree mirrorTree = new MirrorTree();
        TreeNode root = mirrorTree.mirrorTree2(node1);
        binaryTree.wide(root);

    }
}
