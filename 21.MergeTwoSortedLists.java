/* 21. Merge Two Sorted Lists
Easy

Merge two sorted linked lists and return it as a new list. 
The new list should be made by splicing together the nodes
 of the first two lists.

Example:
Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4
*/

/*解题思路
直接用循环做就可以。要记得考虑如果有一个list为空的情况

这道题的解可以直接用在23题, Merge K sorted Lists里面
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        else if(l2 == null) return l1;
        else if(l1.val >l2.val){
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }else{
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
    }
}