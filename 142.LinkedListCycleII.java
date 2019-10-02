/*142: Linked List Cycle II
Medium:

Given a linked list, return the node where the cycle begins. If there 
is no cycle, return null.

To represent a cycle in the given linked list, we use an integer pos 
which represents the position (0-indexed) in the linked list where 
tail connects to. If pos is -1, then there is no cycle in the linked list.

Note: Do not modify the linked list.

Example 1:
Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects 
to the second node. 

3 --> 2 --> 0 --> -4 ->
      |_______________|


Example 2:
Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail 
connects to the first node.

1 -- > 2
|______|

Example 3:
Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.
1

*/

/*解题思路
这道题目网上讲的有点乱，自己总结一下。首先这是基于 141: linked list cycle I的做法。
用的快慢指针，而且快的指针一定可以在慢指针遍历Linked list第一遍之内追上慢指针(参考
141的解题思路)。假设我们有以下的图(这里不能贴图，可以看自己在gitbook上面那张图)。
最后的尾结点连在第三个节点成环。

|----L1---|---L2---|
X -- X -- X -....- X -- X
		  |_______L3____|

这张图里，L1代表的是从头到环的第一个节点的距离， L2代表的是环的第一位到两个快慢指针
相遇的距离。L3其实应该代表的是环的长度，不太好画。。。
已经相遇点的位置距离head节点为L1 + L2, 这也是慢指针所走过的距离。那么快指针
走过的距离便是慢指针走过距离的两倍。也就是 2*(L1+L2)。假设此时快指针已经绕环走
过了n圏。那么另外一种表示快指针走过的距离就是 L1+L2 + nL3
所以:  L1+L2+nL3 = 2*(L1+L2) --->   nL3 -L2= L1 
其实对于L3来说，快指针已经从交汇点走一圈..两圈..三圈..。所以可以直接去掉这个n。
那么就是 L3-L2 = L1。k = (L3 - L2)的意思是再走 k步就可以到交汇点了。而这个k
的值虽然不知道是什么，但是是和L1的距离是一样的。也就是从head出发往前一步步的走，和
从交汇点一步步的往前走，这两个点交叉点也就是 L1 = (L3 -L2)的时候，就是这个环的进入点。

*/

public class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head == null) return head;
        ListNode fast = head, slow = head;
       
        while(fast!=null && fast.next !=null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) break;
        }
        //检查一下有没有环
        if(fast == null || fast.next == null) return null;
        
        while(head!=slow){
            head = head.next;
            slow = slow.next;
        }
        
        return head;
    }
}
