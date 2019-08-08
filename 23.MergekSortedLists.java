/*23. Merge k Sorted Lists
Hard
Merge k sorted linked lists and return it as one sorted
 list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6
*/

/*解题思路
1. 用的办法是merge sort加递归的思路。
bottom-up两两合并，然后返回到上一层。逐层返回

2. 还可以用最小堆的办法来做。解释摘抄自网上，代码自己写。这种解法利用了最小堆这种数
据结构，我们首先把k个链表的首元素都加入最小堆中，它们会自动排好序。然后我们每次取
出最小的那个元素加入我们最终结果的链表中，然后把属于取出元素的链表的下一个元素再加
堆中，下次仍从堆中取出最小的元素做相同的操作，以此类推，直到堆中没有元素了，
此时k个链表也合并为了一个链表，返回首节点即可。虽然这个速度慢很多，但是不失为一种
方法。
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
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        return helper(lists, 0,  lists.length-1);
    }
    
    public ListNode helper(ListNode[] lists, int start, int end){
        if(start == end) return lists[start];
        
        int mid = start + (end -start)/2;
        ListNode a = helper(lists, mid+1, end);
        ListNode b = helper(lists, start, mid);
        return merge(a, b);
    }
    
    public ListNode merge(ListNode a, ListNode b){
        if(a == null) return b;
        else if(b == null) return a;
        else if(a.val > b.val){
            b.next = merge(a, b.next);
            return b;
        }else{
            a.next = merge(a.next, b);
            return a;
        }
    }
}

//用priorityqueue来做
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val-b.val);
        ListNode dummy = new ListNode(0);
        ListNode move = dummy;
        for(ListNode node: lists){
            if(node == null) continue;
            pq.add(node);
        } 
        if(pq.size() == 0) return null;//之所以这里要加一个判断条件是因为test中居然有[[]]这样的测试样例
        
        while(!pq.isEmpty()){
            ListNode temp = pq.poll();
            move.next = temp;
            if(temp.next !=null)
                pq.add(temp.next);
            move = move.next;
        }
        
        return dummy.next;
    }
}