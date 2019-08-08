/*2. Add Two Numbers
Medium

You are given two non-empty linked lists representing two non-negative 
integers. The digits are stored in reverse order and each of their 
nodes contain a single digit. Add the two numbers and return it as 
a linked list.

You may assume the two numbers do not contain any leading zero, 
except the number 0 itself.

Example:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
*/

/*解题思路
首先要注意，题目里已经把两个数字反向连接了。所以直接从头往尾部走输出的
就是一个结果。要注意的就是carry，其他的没什么很特别的了

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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry =0;
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while(l1 != null || l2 !=null){
            int temp =0;
            if(l1 !=null){
                temp+=l1.val;
                l1 = l1.next;
            } 
            if(l2 !=null){
                temp+=l2.val;
                l2 = l2.next;
            } 
            temp+=carry;
            carry = temp/10;
            ListNode next = new ListNode(temp%10);
            tail.next = next;
            tail = tail.next;
        }
        if(carry !=0){
            ListNode next = new ListNode(carry);
            tail.next = next;
        }
        return head.next;
    }
}