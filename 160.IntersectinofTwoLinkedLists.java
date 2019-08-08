/*160. Intersection of Two Linked Lists
Easy: 
Write a program to find the node at which the intersection of two 
singly linked lists begins.

For example, the following two linked lists:

a1 - a2 
       \
        c1 - c2 - c3
      /
   b1

begin to intersect at node c1.
注意这道题不是值的比较，是listNode地址的比较
*/

/*解题思路
这道题有很多的方法，比如说用hashset来找。或者计算两个链表的长度，然后获得步数差等。
虽然都是O(n)的办法。但是第一个是最快的。

假设两个List 长度不一致，B比A长且长度差为K。当A遍历到尾部时，B距离尾部还差K。
此时如果让A的下一位接到B的头部。再走K+1位。此时B也会接到A的头部。那么A, B距离
各自的尾部距离就是一致的了。继续往前走就可以找到交汇点在哪里了。
*/

public class Solution {
   public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    //boundary check
    if(headA == null || headB == null) return null;
    
    ListNode a = headA;
    ListNode b = headB;
    
    //if a & b have different len, then we will stop the loop after second iteration
    while( a != b){
    	//for the end of first iteration, we just reset the pointer to the head of another linkedlist
        a = a == null? headB : a.next;
        b = b == null? headA : b.next;    
    }

    return a;
	}
}


//用hashSet来查找B中和A第一个相同的listNode, 7ms beats 21%。
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int step = 0;
        HashSet<ListNode> A = new HashSet<>();
        
        while(headA!=null){
            A.add(headA);
            headA = headA.next;
        }
        
        while(headB !=null){
            if(A.contains(headB)) return headB;
            headB = headB.next;
        }
        
        return null;
        
    }
}