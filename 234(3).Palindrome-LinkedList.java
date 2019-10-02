/*234. Palindrome Linked List
Easy: 

Given a singly linked list, determine if it is a palindrome.

Example 1:
Input: 1->2
Output: false

Example 2:
Input: 1->2->2->1
Output: true

Follow up:
Could you do it in O(n) time and O(1) space?
*/

/*解题思路
这道题要求我们判断linkedlist是不是palindrome。直接上最优解，O(n) time和 O(1) space。
首先还是快慢指针来判断中点。我们一边用slow遍历List前半段的时候，我们一边可以把字符串翻转过来。
这样当我们找到中间点的时候。前半段的List也翻转过来了。对于这个问题有一个很重要的点就是判断
这个list的长度是奇数还是偶数。因为我们是从中间开始往两边遍历，所以确定中点的位置很关键。
我们可以用fast指针来判断这个list的奇偶性。如果一个List是奇数，比如说，3，5或者7。那么
while loop会停止是因为fast.next ==null而不是fast本身是null。因为我们是从1开始，
每一次跳两位。如果listnode是偶数个的话，那么fast就会等于Null。当判断长度为奇数时，那么我们对指向
后半段list的slow指针往后挪一位。因为此时slow是在中间的位置。然后prev和slow都各自往前走，如果
有不同的值得话，那就return false。否则结果为true.

*/

class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;
        ListNode fast = head, slow = head, prev = slow;
        
        while(fast!=null &&  fast.next !=null){
            ListNode next = slow.next;
            fast = fast.next.next;
            slow = slow.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        if(fast != null) slow = slow.next;
       
        while(prev !=null && slow !=null){
            if(prev.val != slow.val) return false;
            prev = prev.next;
            slow = slow.next;
        }
        
        
        return true;
    }
}




class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;
        ListNode fast = head, slow = head, dummy = null, prev = null;
        while(fast != null && fast.next!=null){
            ListNode temp = slow.next;
            fast = fast.next.next;
            slow.next = dummy;
            dummy = slow;
            slow = temp;
        }
        if(slow.next == null) return dummy.val == slow.val;//为了解决只有两个元素时候的越界问题
        if(fast != null) slow = slow.next;
        
        
        while( dummy!=null && slow !=null){
            if(dummy.val != slow.val) return false;
            dummy = dummy.next;
            slow = slow.next;
        }
        
        return true;
        
    }
}
