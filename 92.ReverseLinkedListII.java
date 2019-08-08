/*92. Reverse Linked List II
Medium: LinkedList

Reverse a linked list from position m to n. Do it in one-pass.

Note: 1 ≤ m ≤ n ≤ length of list.

Example:
Input: 1->2->3->4->5->NULL, m = 2, n = 4
Output: 1->4->3->2->5->NULL
*/

/*解题思路
对于这种头部指向有可能被改变的链表，我们一般都需要一个dummy head来
指向头部，这样万一头部也需要被翻转，还有指针可以指向head。我们首先
要找到被翻转段的前一位和翻转位的第一位。同时用两个指针tail1和tail2指着。然后
保存翻转位的第二位。用tail2(也就是被翻转段的头端)指向被翻转位2的下一位。我们
可以从下面的途图中看出来。这样的做法是保证B的头可以连到C的头。为什么我们可以
判断被翻转位2的下一位可以被指到呢(不会出现两个null的状况)？因为题目中给出来，
n<=length, 所以n的下一位可以安全的被指到。图示的很清楚。

假设被翻转的链表一共有三段
  A      B           C
------|----------|---------
tail1指向的是B的尾端
tail2代表的是B的头端，指向的是C的头端
head代表的是C的头端的下一位

*/

class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode tail1 = dummy, tail2 = dummy;
        for(int i=1; i<m;i++){//i==1是因为我们从1开始计数，到m的前一位
            tail1 = tail1.next;
            head = head.next;
        }

        tail2 = head;
        head = head.next;
      
        for(int i=m; i<n; i++){
            tail2.next = head.next;
            head.next = tail1.next;
            tail1.next = head;
            head = tail2.next;
        }

        return dummy.next;
    }
} 

