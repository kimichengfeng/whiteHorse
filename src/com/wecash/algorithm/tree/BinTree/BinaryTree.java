package com.wecash.algorithm.tree.BinTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
* 二叉查找树
 * https://blog.csdn.net/fengrunche/article/details/52305748
* @author chengTong
* @date 2018-06-29 12:01
**/
public class BinaryTree {
    private TreeNode root = null;

    public BinaryTree(int value) {
        root = new TreeNode(value);
        root.leftChild  = null;
        root.rightChild = null;
    }
    public BinaryTree(){

    }
    /**
     * 查找
     */
    public TreeNode findKey(int value) {
        TreeNode current = root;
        while(true){
            if(current.value == value){
                return current;
            }else if(current.value>value){
                current = current.leftChild;
            }else {
                current = current.rightChild;
            }

            if(current == null){
                return null;
            }
        }
    }
    /**
     * 插入：与查找数据类似，不同点在于当节点为空时，不是返回而是插入
     */
    public String insert(int value) {
        String error = null;

        TreeNode treeNode = new TreeNode(value);
        if (root == null) {
            root = treeNode;
            root.leftChild  = null;
            root.rightChild = null;
        } else {
            TreeNode current = root;
            TreeNode parent = null;
            while (true) {
                if (value < current.value) {
                    parent = current;
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = treeNode;
                        break;
                    }
                } else if (value > current.value) {
                    parent = current;
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = treeNode;
                        break;
                    }
                } else {
                    error = "having same value in binary tree";
                }
            } // end of while
        }
        return error;
    }
    /**
     * 中序遍历(递归)：
     *    1、调用自身来遍历节点的左子树
     *    2、访问这个节点
     *    3、调用自身来遍历节点的右子树
     */
    public void inOrderTraverse() {
        System.out.print("中序遍历:");
        inOrderTraverse(root);
        System.out.println();

    }
    private void inOrderTraverse(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        inOrderTraverse(treeNode.leftChild);
        treeNode.display();
        inOrderTraverse(treeNode.rightChild);
    }
    /**
     * 中序非递归遍历：
     *     1）对于任意节点current，若该节点不为空则将该节点压栈，并将左子树节点置为current，重复此操作，直到current为空。
     *     2）若左子树为空，栈顶节点出栈，访问节点后将该节点的右子树置为current
     *     3) 重复1、2步操作，直到current为空且栈内节点为空。
     */
    public void inOrderByStack() {
        System.out.print("中序非递归遍历:");
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.leftChild;
            }

            if (!stack.isEmpty()) {
                current = stack.pop();
                current.display();
                current = current.rightChild;
            }
        }
    }

    /**
     * /前序遍历(递归)：
     *    1、访问这个节点
     *    2、调用自身来遍历节点的左子树
     *    3、调用自身来遍历节点的右子树
     */
    public void preOrderTraverse() {
        System.out.print("前序遍历:");
        preOrderTraverse(root);
        System.out.println();
    }
    private void preOrderTraverse(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        treeNode.display();
        preOrderTraverse(treeNode.leftChild);
        preOrderTraverse(treeNode.rightChild);
    }

    /**
     * 前序非递归遍历：
     *     1）对于任意节点current，若该节点不为空则访问该节点后再将节点压栈，并将左子树节点置为current，重复此操作，直到current为空。
     *     2）若左子树为空，栈顶节点出栈，将该节点的右子树置为current
     *     3) 重复1、2步操作，直到current为空且栈内节点为空。
     */
    public void preOrderByStack() {
        System.out.print("前序非递归遍历:");
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current.display();
                current = current.leftChild;
            }

            if (!stack.isEmpty()) {
                current = stack.pop();
                current = current.rightChild;
            }
        }

    }

    /**
     *  //后序遍历(递归)：
     *    1、调用自身来遍历节点的左子树
     *    2、调用自身来遍历节点的右子树
     *    3、访问这个节点
     */
    public void postOrderTraverse() {
        System.out.print("后序遍历:");
        postOrderTraverse(root);
        System.out.println();
    }
    private void postOrderTraverse(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        postOrderTraverse(treeNode.leftChild);
        postOrderTraverse(treeNode.rightChild);
        treeNode.display();
    }

    /**
     *  后序非递归遍历：
     *     1）对于任意节点current，若该节点不为空则访问该节点后再将节点压栈，并将左子树节点置为current，重复此操作，直到current为空。
     *     2）若左子树为空，取栈顶节点的右子树，如果右子树为空或右子树刚访问过，则访问该节点，并将preNode置为该节点
     *     3) 重复1、2步操作，直到current为空且栈内节点为空。
     */
    public void postOrderByStack() {
        System.out.print("后序非递归遍历:");
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode current = root;
        TreeNode preTreeNode = null;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.leftChild;
            }

            if (!stack.isEmpty()) {
                current = stack.peek().rightChild;
                if (current == null || current == preTreeNode) {
                    current = stack.pop();
                    current.display();
                    preTreeNode = current;
                    current = null;
                }
            }
        }
        System.out.println();
    }

    /**
     * 得到最小(大)值：依次向左(右)直到空为之
     * @return
     */
    public int getMinValue() {
        TreeNode current = root;
        while (true) {
            if (current.leftChild == null) {
                return current.value;
            }
            current = current.leftChild;
        }

    }

    /**
     * 广度优先遍历
     * @param root
     * @return
     */
    public ArrayList<Integer> wide(TreeNode root) {
        ArrayList<Integer> lists=new ArrayList<Integer>();
        if(root==null)
            return lists;
        Queue<TreeNode> queue=new LinkedList<TreeNode>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node.leftChild!=null){
                queue.offer(node.leftChild);
            }
            if(node.rightChild!=null){
                queue.offer(node.rightChild);
            }
            lists.add(node.value);
        }
        lists.stream().forEach(item-> System.out.print(item));
        System.out.println("");
        return lists;
    }

}
