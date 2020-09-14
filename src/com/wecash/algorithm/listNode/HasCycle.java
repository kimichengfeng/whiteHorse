package com.wecash.algorithm.listNode;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 * 快慢指针，总会相遇
 * User: tong.cheng
 * Date: 2020-08-03
 * Time: 21:11
 */
public class HasCycle {
     boolean hasCycle(ListNode head){
         ListNode slow = head;
         ListNode fast = head;
         while(fast != null && fast.next != null){
             fast = fast.next.next;

             if(fast == slow){
                 return true;
             }
             slow = slow.next;
         }
         return false;
     }
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4= new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node2;
        HasCycle hasCycle = new HasCycle();
        boolean hasCycle1 = hasCycle.hasCycle(node1);
        System.out.println(hasCycle1);
    }
}
