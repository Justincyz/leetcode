/*206. Reverse Linked List
Easy:
Reverse a singly linked list.

Example:
Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
Follow up:

A linked list can be reversed either iteratively 
or recursively. Could you implement both?
*/

/*解题思路
很经典的也很基础的一道linked list题目
我们还是用iterative 和recursive两种解来解决这个问题
首先是迭代的方法: 思路是在原链表之前建立一个空的newHead，因为首
节点会变，然后从head开始，将之后的一个节点移到newHead之后，重复
此操作直到head成为末节点为止，
*/

class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode dummy = null;
        while(head!=null){
            ListNode next = head.next; //先储存好下一位
            head.next = dummy; // 让当前位指向前一位
            dummy = head; // 在此处赋值，将前一位变量名指向当前位地址
            head = next;
        }
        return dummy;
    }
}

/*
还有递归的解法:
不断的进入递归函数，直到head指向倒数第二个节点，因为head指向空或者是
最后一个结点都直接返回了。newHead则指向对head的下一个结点调用递归函数
返回的头结点，每一次的newHead都是指向最后一个结点，这点不会变。然后head
的下一个结点的next指向head本身，这个相当于把head结点移动到末尾。
因为回溯的操作，所以head的下一个结点总是在上一轮被移动到末尾了。但head
之后的next还没有断开，所以在顺势将head移动到末尾后，需要再把next断开，
最后返回newHead即可
*/

class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return dummy;
    }
}

