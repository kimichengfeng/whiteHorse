package com.wecash.algorithm.listNode;

import java.util.HashSet;

/**
 * Created with IntelliJ IDEA
 * Description:删除链表中重复的结点
 * User: tong.cheng
 * Date: 2020-07-31
 * Time: 21:11
 */
public class DeleteDuplication {

    /**
     * 1. 辅助空间
     * 多次遍历，第一次遍历把重复的结点值存入 set 容器，
     * 第二次遍历，当结点值存储在 set 容器中，就删除该结点
     *
     * 时间复杂度：HashSet 是基于哈希表实现的，查找效率为 O(1)，所以总的效率是 O(n)
     * 空间复杂度：最坏的情况是存一半结点 O(n/2)，最好的情况是一个也不存，O(1)
     * @param pHead
     * @return
     */
    public ListNode deleteDuplication(ListNode pHead){
        if(pHead == null){
            return  null;
        }
        // 先找出相同结点，存入 set
        HashSet<Integer> set = new HashSet<>();
        ListNode pre = pHead;
        ListNode cur = pHead.next;
        while(cur != null){
            if(cur.val == pre.val){
                set.add(cur.val);
            }
            pre = cur;
            cur = cur.next;
        }
        // 再根据相同节点删除
        // 先删头部
        while(pHead != null && set.contains(pHead.val)){
            pHead = pHead.next;
        }
        if(pHead == null){
            return null;
        }
        // 再删中间结点
        pre = pHead;
        cur = pHead.next;
        while(cur != null){
            if(set.contains(cur.val)){
                pre.next = cur.next;
                cur = cur.next;
            }else{
                pre = cur;
                cur = cur.next;
            }
        }
        return pHead;
    }

    /**
     * 遍历的同时删除
     * 借助辅助头结点，可避免单独讨论头结点的情况。
     * 设置两个结点 pre 和 cur，当 cur 和 cur.next 值相等，cur 一直向前走，
     * 直到不等退出循环，这时候 cur 指的值还是重复值，调整 cur 和 pre 的指针再次判断.
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param pHead
     * @return
     */
    public ListNode deleteDuplication2(ListNode pHead){
        if(pHead == null || pHead.next == null){
            return pHead;
        }
        // 自己构建辅助头结点
        ListNode head = new ListNode(Integer.MIN_VALUE);
        head.next = pHead;
        ListNode pre = head;
        ListNode cur = head.next;
        while(cur!=null){
            if(cur.next != null && cur.next.val == cur.val){
                // 相同结点一直前进
                while(cur.next != null && cur.next.val == cur.val){
                    cur = cur.next;
                }
                // 退出循环时，cur 指向重复值，也需要删除，而 cur.next 指向第一个不重复的值
                // cur 继续前进
                cur = cur.next;
                // pre 连接新结点
                pre.next = cur;
            }else{
                pre = cur;
                cur = cur.next;
            }
        }
        return head.next;
    }
}
