package com.wecash.algorithm.listNode;

/**
 * Created with IntelliJ IDEA
 * Description:
 * 获取中间元素的问题
 * User: tong.cheng
 * Date: 2020-08-03
 * Time: 21:03
 */
public class MiddleNode {

    ListNode middleNode(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
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
        node5.next = node6;
        MiddleNode middleNode = new MiddleNode();
        ListNode middle = middleNode.middleNode(node1);
        System.out.println(middle.val);
    }
}
