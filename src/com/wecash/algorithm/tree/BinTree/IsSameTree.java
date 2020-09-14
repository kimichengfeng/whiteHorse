package com.wecash.algorithm.tree.BinTree;

/**
 * Created with IntelliJ IDEA
 * Description:
 * 如果两个树结构相同，值相同，则认为他们相同
 * User: tong.cheng
 * Date: 2020-08-05
 * Time: 20:53
 */
public class IsSameTree {
    public boolean isSmameTree(TreeNode p,TreeNode q){
        if(p == null && q==null){
            return true;
        }
        if(p == null || q==null) {
            return false;
        }
        if(p.value != q.value) return false;
        return isSmameTree(p.rightChild,q.rightChild) && isSmameTree(p.leftChild,q.leftChild);
    }
}
