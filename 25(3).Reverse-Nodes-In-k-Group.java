/*25. Reverse Nodes in k-Group
Hard: 

Given a linked list, reverse the nodes of a linked list 
k at a time and return its modified list.

k is a positive integer and is less than or equal to the 
length of the linked list. If the number of nodes is not 
a multiple of k then left-out nodes in the end should 
remain as it is.

Example:
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
*/

/*解题思路
总共有三种不同的解题思路。第一种是从后往前遍历的recursive解法。首先需要判断当前链表长度
是不是大于k，同时将dummyHead往后移动到下一段的起始位置(第k+1位)。如果当前
长度小于k，直接返回head. 否则递归处理从dummy开始的下一段。reverse部分将dummy
当成prev，因为我们是要将当前段连接到后面已经翻转完成的链表的。然后reverse
部分就跟 206题的 reverse linkedlist一样了。最后返回的是dummy, 因为dummy是
最后一个有效节点。

*/

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null) return head;
        int len = 0;
        ListNode dummy = head;
        while(dummy!=null &&len< k){//这个顺序很重要，
            dummy = dummy.next;
            len++;
        }
        if(len < k) return head;
        
        dummy = reverseKGroup(dummy, k);
        
        while(k-- >0){
            ListNode next = head.next;
            head.next = dummy;
            dummy = head;
            head = next;
        }
        return dummy;
    }   
}

/*
这是第二种iterative的解法。
实际上是把原链表分成若干小段，然后分别对其进行翻转，那么肯定总共需
要两个函数，一个是用来分段的，一个是用来翻转的，因为翻转链表时头
结点可能会变化，所以要用一个新的dummyHead指向原来的head。 同时
用两个指针，cur指向第k个节点, pre用来指向每一次翻转过后的头部。
pre 和 next 分别指向要翻转的链表的前后的位置，然后翻转后 pre 的
位置更新到如下新的位置

(0)->1->2->3->4->5
 |        |  |
pre      cur next

(0)->3->2->1->4->5
    |     |  |
   cur   pre next

以此类推，只要 cur 走过k个节点，那么 next 就是 cur->next，
就可以调用翻转函数来进行局部翻转了，注意翻转之后新的 cur 和 
pre 的位置都不同了，那么翻转之后，cur 应该更新为 pre->next，
而如果不需要翻转的话，cur 更新为 cur->next。 reverse()方法稍
微跟原来有点不一样，因为每一次pre指向的都是当前翻转
完成的最后一位。从始至终只要有翻转(除了最后一组小于K个的链表)，pre
的指向都是翻转过后的头部。
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
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null) return head;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy, cur = dummy.next;
        
        for(int i=1; cur!=null; i++){
            if(i%k==0){
                pre = reverse(pre, k-1);//两个节点翻转一次，所以是k-1
                cur = pre.next;
            }else{
                cur = cur.next;
            }
        }
        return dummy.next;
    }
    
    public ListNode reverse(ListNode pre, int k){
        ListNode last = pre.next, cur = last.next;
        while(k-- >0){
           last.next = cur.next;
            cur.next = pre.next;
            pre.next = cur;
            cur = last.next;
        }
        return last;
    }
}


class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode tail = dummy, prev = dummy, then;
        
        while(true){
            for(int i=0; i< k;i++){
                tail = tail.next;
                if(tail == null){
                    return dummy.next;
                }
            }

            for(int j=0;j<k-1;j++){
                then = head.next;
                head.next = then.next;
                then.next = prev.next;
                prev.next = then;
            }
            
            prev = head;
            tail = head;
            head = prev.next;
               
        }
    }
}