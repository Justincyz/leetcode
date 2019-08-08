/*19. Remove Nth Node From End of List
Medium: Linkedlist

Given a linked list, remove the n-th node from the end of list 
and return its head.

Example:
Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list 
becomes 1->2->3->5.
Note:
Given n will always be valid.
remove倒数第n个节点
*/

/*解题思路
要移除倒数第n个，相当于我们用两个指针，第一个先向前走n步，然后
前后一起走直到先出发的指针指向null。因为两个指针差了n步，所以此时
只需要移除后出发的那个指针的下一位就可以了。
有两个需要注意的点。
1. 如果n大于linkedlist的长度。那么就把第一位remove掉，比如说:
[1,2,3], n=5 --> [2,3]
2. 开头要用新建的node当做指针，因为如果需要remove第一个元素的话
也可以直接用slow.next = slow.next.next语句
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode start = new ListNode(0);
        ListNode slow = start, fast = start;
        slow.next = head;
        
        while(fast!=null && n-->=0) fast = fast.next;
        
        while(fast!=null){
            fast = fast.next;
            slow = slow.next;
        }
      
        slow.next = slow.next.next;
        return start.next;
    }
}