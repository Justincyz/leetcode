/*143. Reorder List
Medium
链接: https://leetcode.com/problems/reorder-list/
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You may not modify the values in the list's nodes,
only nodes itself may be changed.

Example 1:

Given 1->2->3->4, reorder it to 1->4->2->3.
Example 2:

Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
*/

/*解题思路
这道题首先可以用stack来解决。stack解决也有两种方法:
1. 用快慢指针先找到linkedlist的中间点。然后把后半部分用stack储存起来。
同时记得要切断前后半个list的连接。然后只需要交替的遍历前半部分和用stack储存
起来的后半部分就可以了。
2. 直接用一个stack储存整个linkedlist, 然后计算出stack的大小。我们之夭夭后半段的
listnode所以将size 设为(size-1)/2。还是交替遍历前半段和stack中的前size个元素，
最后记得切断前后部分的连接点。

然后有一种O(1)的解决办法。三部分的思路是，先找到中间点，然后将后半部分reverse,
然后merge前后两个linkedlist. 

以上三种办法都要考虑到list长度为奇数还是偶数，还有就是一定要切断前后list之间的
连接

*/

class Solution {
    public void reorderList(ListNode head) {
        if(head == null || head.next == null) return;
        
        //find the middle point
        ListNode fast = head, slow = head, prev = slow;
        while(fast!=null && fast.next != null){
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
         
        if(fast == null){ //偶数个元素
            prev.next = null;
        }else{            //奇数个元素
            ListNode next = slow.next;
            slow.next = null;
            slow = next;
        }
       
        //reverse second list
        ListNode secondListHead = reverseLinkedList(slow);
        //merge two linked list
        mergeTwoList(head, secondListHead);
    }
    
    public ListNode reverseLinkedList(ListNode slow){
        ListNode dummyHead = null;
        while(slow !=null){
            ListNode next = slow.next;
            slow.next = dummyHead;
            dummyHead = slow;
            slow = next;
        }
        return dummyHead;
    }
    
    public void mergeTwoList(ListNode l1, ListNode l2){
        if(l1 == null && l2 == null) return;
        if(l1 !=null){
            ListNode next = l1.next;
            l1.next = l2;
            l1 = next;
        }
        if(l2 != null){
            ListNode next = l2.next;
            l2.next = l1;
            l2 = next;
        }
        mergeTwoList(l1, l2);
    }
}


//两种用stack来做的方法

public class Solution {
    public void reorderList(ListNode head) {
        if (head==null||head.next==null) return;
        Stack<ListNode> stack = new Stack<>();
        ListNode ptr=head;
        while (ptr!=null) {
            stack.push(ptr); ptr=ptr.next;
        }
        int cnt=(stack.size()-1)/2;
        ptr=head;
        while (cnt-->0) {
            ListNode top = stack.pop();
            ListNode tmp = ptr.next;
            ptr.next=top;
            top.next=tmp;
            ptr=tmp;
        }
        stack.pop().next=null;
    }
}


class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null)
          return;
        ListNode fast = head,  slow = head, pointer = head;
        ListNode pre = slow;
        Stack<ListNode> stack = new Stack<ListNode>();
        //找到中间点
        while(fast!=null&&fast.next!=null){
            fast = fast.next.next;
            pre = slow;
            slow = slow.next;
        }
        //说明有奇数个node，让pre指向最后一个slow, 因为在之前的循环中指的是slow前面一位
        if(fast != null){
            pre = slow;
            slow = slow.next; //奇数情况
        } 
        pre.next = null;
        while(slow !=null){
            stack.push(slow);
            slow = slow.next;
        }
        
        while(pointer !=null && !stack.empty()){
            ListNode temp = pointer.next;
            pointer.next = stack.pop();
            pointer.next.next = temp;
            pointer = pointer.next.next;
        }

    }
}