/* 83. Remove Duplicates from Sorted List
Easy: LinkedList

Given a sorted linked list, delete all duplicates such that 
each element appear only once.

Example 1:

Input: 1->1->2
Output: 1->2
Example 2:

Input: 1->1->2->3->3
Output: 1->2->3
*/

/*解题思路
简单题，注意outter while loop边界条件是下一位不为空

*/

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return head;
        ListNode dummy = head, tail = dummy.next;
        while(tail!=null){
            while(tail!=null && tail.val == dummy.val){
                tail = tail.next;
            }
            dummy.next = tail;
            dummy = tail;
        }
        return head;
    }
}

//一个节点的做法
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return head;
        ListNode dummyHead = head;  
        while(head.next!=null){
            if(head.val == head.next.val){
                head.next = head.next.next;
            }else{
                head = head.next;
            }
        }
        return dummyHead;
    }
}