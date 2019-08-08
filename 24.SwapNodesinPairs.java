/* 24. Swap Nodes in Pairs
Medium: Linkedlist

Given a linked list, swap every two adjacent nodes and return its head.

You may not modify the values in the list's nodes, only nodes itself
 may be changed.

Example:

Given 1->2->3->4, you should return the list as 2->1->4->3.
*/

/*解题思路
这道题有recursive 和 iterative的解法。
对于iterative的解法来说，写的时候犯的一个错就是在遍历的过程中，
不能直接用head node作为循环的node(就是说将head代替代码中所有的current的节点)
.而是要有一个current node指向我们dummy节点，然后用这个current来循环这个
linkedlist。否则会破坏这个结构，因为一开始我们的dummy指的就是head。

Recursive的解就比较简单了但是还是要注意终止条件。

*/

class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        dummy.next = head;
        while(current.next!=null && current.next.next!=null){
            ListNode second = current.next.next;
            ListNode first = current.next;
            //每次破坏链表的结构都要从尾部开始破坏
            first.next = second.next;
            current.next = second;
            second.next = first;
            current = current.next.next;
    
        }
        return dummy.next;
    }
}

//Recursive
class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode current = head.next;
        head.next = swapPairs(head.next.next);
        current.next = head;
        return current;
    } 
}
