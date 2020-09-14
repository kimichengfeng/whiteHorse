package com.wecash.algorithm.listNode;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 * 获取两个链表第一个公共节点
 * 双指针法，浪漫相遇
 * User: tong.cheng
 * Date: 2020-08-03
 * Time: 21:26
 */
public class GetIntersectionNode {
      ListNode getIntersectionNode(ListNode head1,ListNode head2){
          ListNode node1 = head1;
          ListNode node2 = head2;
          while (node1 != node2){
              node1 = node1!= null?node1.next:head2;
              node2 = node2!= null?node2.next:head1;
          }
          return node1;
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
        node3.next = null;
        node4.next = node5;
        node5.next = node2;
        GetIntersectionNode getIntersectionNode = new GetIntersectionNode();
        ListNode node = getIntersectionNode.getIntersectionNode(node1,node4);
        System.out.println(node.val);
    }
}
