package com.wecash.algorithm.listNode;

import java.util.List;

/**
 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。

 k 是一个正整数，它的值小于或等于链表的长度。

 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

  

 示例：

 给你这个链表：1->2->3->4->5

 当 k = 2 时，应当返回: 2->1->4->3->5

 当 k = 3 时，应当返回: 3->2->1->4->5

  

 说明：

 你的算法只能使用常数的额外空间。
 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。

 */
public class ReverseKGroupSolution {


    static class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            if (head == null || head.next == null){
                return head;
            }
            //定义一个假的节点。
            ListNode dummy=new ListNode(0);
            //假节点的next指向head。
            // dummy->1->2->3->4->5
            dummy.next=head;
            //初始化pre和end都指向dummy。pre指每次要翻转的链表的头结点的上一个节点。end指每次要翻转的链表的尾节点
            ListNode pre=dummy;
            ListNode end=dummy;

            while(end.next!=null){
                //循环k次，找到需要翻转的链表的结尾,这里每次循环要判断end是否等于空,因为如果为空，end.next会报空指针异常。
                //dummy->1->2->3->4->5 若k为2，循环2次，end指向2
                for(int i=0;i<k&&end != null;i++){
                    end=end.next;
                }
                //如果end==null，即需要翻转的链表的节点数小于k，不执行翻转。
                if(end==null){
                    break;
                }
                //先记录下end.next,方便后面链接链表
                ListNode next=end.next;
                //然后断开链表
                end.next=null;
                //记录下要翻转链表的头节点
                ListNode start=pre.next;
                //翻转链表,pre.next指向翻转后的链表。1->2 变成2->1。 dummy->2->1
                pre.next=reverse(start);
                //翻转后头节点变到最后。通过.next把断开的链表重新链接。
                start.next=next;
                //将pre换成下次要翻转的链表的头结点的上一个节点。即start
                pre=start;
                //翻转结束，将end置为下次要翻转的链表的头结点的上一个节点。即start
                end=start;
            }
            return dummy.next;

        }
        // 1-> 2->3
      public ListNode reverse(ListNode head){
            ListNode pre = null;
            ListNode cur = head;
            while(cur != null){
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            return pre;
      }
    }
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4= new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        Solution solution = new Solution();
        ListNode pre = solution.reverseKGroup(node1,3);
        while(pre != null) {
            System.out.println(pre.val);
            pre = pre.next;
        }

    }
}
