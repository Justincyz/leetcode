/*141. Linked List Cycle
Easy: https://leetcode.com/problems/linked-list-cycle/
Given a linked list, determine if it has a cycle in it.

To represent a cycle in the given linked list, we use an integer pos 
which represents the position (0-indexed) in the linked list where tail 
connects to. If pos is -1, then there is no cycle in the linked list.

图就不放了，就是一个有环的图。链表的尾部会任意的连接到前面的某一个节点上。
*/

/*解题思路: 这道题可以用在 142:linked list II
用的是快慢指针的思路。我们可以细细剖析一下这个办法的原理。我一直有个问题比较困扰。
为什么我们确定最后快指针可以在循环2n步内追上慢指针。也就是慢指针在快慢指针相遇的时候最多只会
最多走n+1步？其实我们可以这样来看待快慢指针。在前进第一步之后，慢指针比快指针慢一位。此时两个
指针相差的距离是n(以快指针追赶慢指针的角度来看，因为慢指针不可能追的上快指针)。当快慢指针
再移动一次时，因为快慢指针的速度差为1,所以此时快慢指针相差的距离为n-1。所以每一次缩减的距离都是1。
所以最多只需要n次两个指针就会相遇，而此时慢指针最多进入到第二次循环的第一位。

*/

public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) return true;
        }
        return false;
    }
}
