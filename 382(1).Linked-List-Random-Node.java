/*382. Linked List Random Node
链接：https://leetcode.com/problems/linked-list-random-node/
Medium: 
Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.

Follow up:
What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?

Example:
// Init a singly linked list [1,2,3].
ListNode head = new ListNode(1);
head.next = new ListNode(2);
head.next.next = new ListNode(3);
Solution solution = new Solution(head);

// getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
solution.getRandom();
*/

/*解题思路
题目给我们一个linkedlist的head, 让我们创造一种可以随机获得这个
linkedlist中任意一个元素的算法。最简单的办法当然就是先用一个arraylist
把整个linkedlist当中的元素都拷贝下来。然后用math.random()的办法随机
从List当中获取一个节点的值。

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
    List<ListNode> list;
    
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
        list = new ArrayList<>();
        while(head!=null){
            list.add(head);
            head = head.next;
        }
         
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        int r = (int)(Math.random()*list.size());
        return list.get(r).val;
    }
}

/*  时间复杂度O(N),空间复杂度O(1)
Follow up 中说链表可能很长，我们没法提前知道长度，这里用到了著名了 
水塘抽样 Reservoir Sampling 的思路，由于限定了 head 一定存在，
所以初始化返回值 res 等于 head 的节点值，然后让 cur 指向 head 的下一个
节点，定义一个变量i(代表了目前为止链表的长度)，初始化为2，若 cur 
不为空则开始循环，在 [0, k - 1] 中取一个随机数，如果取出来0，则更新
 res 为当前的 cur 的节点值，然后此时k自增一，cur 
 指向其下一个位置。这里其实相当于维护了一个大小为k的水塘，然后随机数生成
 为0的话(从0-（k-1)个位置当中选到0的概率为 1/k)，则将新的元素
 覆盖res，这样可以保证每个数字的概率相等，

具体证明过程
Problem:
-Choose k entries from n numbers. Make sure each number is selected with the probability of k/n
Basic idea:
-Choose 1, 2, 3, ..., k first and put them into the reservoir.
-For k+1, pick it with a probability of k/(k+1), and randomly replace a number in the reservoir.
-For k+i, pick it with a probability of k/(k+i), and randomly replace a number in the reservoir.
-Repeat until k+i reaches n

Proof:
-For k+i, the probability that it is selected and will replace a number in the reservoir is k/(k+i)
-For a number in the reservoir before (let's say X), the probability that it keeps staying in the reservoir is
-P(X was in the reservoir last time) × P(X is not replaced by k+i)
= P(X was in the reservoir last time) × (1 - P(k+i is selected and replaces X))
= k/(k+i-1) × （1 - k/(k+i) × 1/k）
= k/(k+i) //即使i增大，分子K也没有随着i改变而改变
When k+i reaches n, the probability of each number staying in the reservoir is k/n

Example
-Choose 3 numbers from [111, 222, 333, 444]. Make sure each number is selected with a probability of 3/4
First, choose [111, 222, 333] as the initial reservior
Then choose 444 with a probability of 3/4
For 111, it stays with a probability of
P(444 is not selected) + P(444 is selected but it replaces 222 or 333)
= 1/4 + 3/4*2/3
= 3/4
The same case with 222 and 333
Now all the numbers have the probability of 3/4 to be picked

This problem is the sp case where k=1
*/


class Solution {
    ListNode head;

    public Solution(ListNode head) {
        this.head = head;
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        int range = 2, res = head.val;
        ListNode cur = head.next;
        while(cur !=null){
            int random = (int)(Math.random()*range);
            if(random == 0) res = cur.val;
            cur = cur.next;
            range++;
        }
        return res;
    }
}
