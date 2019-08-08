/*147. Insertion Sort List
Medium: 
Algorithm of Insertion Sort:

Insertion sort iterates, consuming one input element each repetition, 
and growing a sorted output list.
At each iteration, insertion sort removes one element from the input 
data, finds the location it belongs within the sorted list, and inserts 
it there. It repeats until no input elements remain.

Example 1:
Input: 4->2->1->3
Output: 1->2->3->4

Example 2:
Input: -1->5->3->4->0
Output: -1->0->3->4->5
*/

/*解题思路
这道题目要求我们实现一个链表的插入算法。插入算法其实就是每一次从老的链表中
取一个值，然后按顺序插入到新的链表中。时间复杂度是O(n^2)，但是空间复杂度是O(1)。
算是一种用时间换取空间的做法。
我们要用一个dummyHead当做新的链表的头部，这样元素插入的时候有可以链接的地方。
第一种naive approach 33ms只能beats 38%。
第二个就增加了一条代码，快了十倍
*/

class Solution {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummyHead = new ListNode(0);
    	ListNode cur = dummyHead;

        while(head!=null){
            ListNode next = head.next;
            
            while(cur.next!=null && cur.next.val < head.val){
                cur = cur.next;
            }
            head.next = cur.next;
            cur.next = head;
            head = next;
           
        }
        return dummyHead.next;
    }
}



//下面的代码除了增加了一段之外其余的和上面的一模一样, 变成了3ms
class Solution {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummyHead = new ListNode(0);
    	ListNode cur = dummyHead;

        while(head!=null){
            ListNode next = head.next;
            
            /* Before insert, the cur is at the last insertion position.
           Only the last node's value is larger than the current inserting node 
           should we move the temp back to the head. cause that means 
           we need to insert the new element before the last insertion
           position*/
            if (cur.val >= head.val) cur = dummyHead;
            while(cur.next!=null && cur.next.val < head.val){
                cur = cur.next;
            }
            head.next = cur.next;
            cur.next = head;
            head = next;
           
        }
        return dummyHead.next;
    }
}





