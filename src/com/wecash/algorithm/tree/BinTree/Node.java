package com.wecash.algorithm.tree.BinTree;
/**
* 定义一个节点类，使节点与二叉树操作分离
* @author chengTong
* @date 2018-06-29 12:00
**/
public class Node {
    int value;
    Node leftChild;
    Node rightChild;

    Node(int value) {
        this.value = value;
    }

    public void display() {
        System.out.print(this.value + "\t");
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return String.valueOf(value);
    }

}
