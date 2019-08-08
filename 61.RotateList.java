/*61. Rotate List
Medium: 

Given a linked list, rotate the list to the right by k places, 
where k is non-negative.

Example 1:

Input: 1->2->3->4->5->NULL, k = 2
Output: 4->5->1->2->3->NULL
Explanation:
rotate 1 steps to the right: 5->1->2->3->4->NULL
rotate 2 steps to the right: 4->5->1->2->3->NULL
Example 2:

Input: 0->1->2->NULL, k = 4
Output: 2->0->1->NULL
Explanation:
rotate 1 steps to the right: 2->0->1->NULL
rotate 2 steps to the right: 1->2->0->NULL
rotate 3 steps to the right: 0->1->2->NULL
rotate 4 steps to the right: 2->0->1->NULL
*/

/*解题思路
这道题其实和19: Remove Nth Node From End of List的基本做法一样。
因为题目要求的是把链表向右移动k位，所以我们还是要先找到(链表长度-k)的
位置。然后把两个链表结合在一起。但是在这个之前，我们不知道K是不是比我们
链表的长度还长，所以我们需要先遍历链表，找到链表长度len, 然后 k = k%len
来避免多余的循环。同时记得要再检查一遍K是否为0。然后就是常规操作，先把tail
指针向右移动k位，然后dummyHead和tail同时移动，当tail.next == null时，tail
和dummy同时移动了length-k-1位，然后此时断开前后连接点。让后半段的secHead
作为链表的新head, tail.next = 老的head，返回secHead就可以了

这道题还有一种解法，跟上面的方法类似，但是不用快慢指针，一个指针就够了，
原理是先遍历整个链表获得链表长度n，然后此时把链表头和尾链接起来，
在往后走n - k % n个节点就到达新链表的头结点前一个点，这时断开链表即可
*/

class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || k ==0) return head;
        ListNode dummy = head, tail = dummy;
        int length = 0;
        while(tail!=null){
            length++;
            tail = tail.next;
        }
       
        k = k%length;
        if(k == 0) return head; //再检查一遍

        tail = head;
        while(k-->0) tail = tail.next;
            
        while(tail.next!=null){
            dummy = dummy.next;
            tail = tail.next;
        } 
        ListNode secDummy = dummy.next;
        dummy.next = null;
        
        tail.next = head;
        return secDummy;
    }
}

//第二种做法
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || k ==0) return head;
        ListNode tail = head;
        int length = 1;
        while(tail.next!=null){
            length++;
            tail = tail.next;
        }
       
        k = k%length;
        
        tail.next = head;
        int step = length-k;
        
        while(step-- >0) tail = tail.next;
        
        ListNode dummy = tail.next;
        tail.next = null;
        return dummy;
    }
}