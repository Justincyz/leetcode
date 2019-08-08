/*328. Odd Even Linked List
Medium: 
Given a singly linked list, group all odd nodes together 
followed by the even nodes. Please note here we are talking 
about the node number and not the value in the nodes.

You should try to do it in place. The program should run in
 O(1) space complexity and O(nodes) time complexity.

Example 1:
Input: 1->2->3->4->5->NULL
Output: 1->3->5->2->4->NULL

Example 2:
Input: 2->1->3->5->6->4->7->NULL
Output: 2->3->6->7->1->5->4->NULL

*/

/*解题思路
还是双指针的做法来做。用两个奇偶指针分别指向奇偶节点的起始位置，另外需要一个单独的指针
ehead来保存偶节点的起点位置，然后把奇节点的指向偶节点的下一个(一定是奇节点)，此奇节点
后移一步，再把偶节点指向下一个奇节点的下一个(一定是偶节点)，此偶节点后移一步，以此类推直
至末尾，此时把分开的偶节点的链表连在奇节点的链表后即可；

*/
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode oddhead = head, evenhead = head.next;
        ListNode ehead = evenhead;
        while(oddhead!=null && evenhead !=null){
            oddhead.next = evenhead.next;
            if(oddhead.next == null) break;
            oddhead = oddhead.next;
            evenhead.next = oddhead.next;
            evenhead = evenhead.next;
        }
       
        if(evenhead != null) evenhead.next = null;
        oddhead.next = ehead;
        return head;
    }
}

//简洁版的
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if(head ==null) return head;
        
        ListNode odd = head;
        ListNode even = head.next, evenHead = even;
        while(even != null && even.next !=null){
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
                
        }
        odd.next = evenHead;
        return head;
    }

}
