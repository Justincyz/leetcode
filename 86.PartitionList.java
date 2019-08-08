/*86. Partition List
Medium: 
Given a linked list and a value x, partition it such that 
all nodes less than x come before nodes greater than or 
equal to x.

You should preserve the original relative order of the nodes 
in each of the two partitions.

Example:

Input: head = 1->4->3->2->5->2, x = 3
Output: 1->2->2->4->3->5
*/

/*解题思路
第一个办法真是很没有技术含量，但是是一个可行的方案。分别用两个dummy head, 一个
记录大于等于x的，一个记录小于x。最后让小于x的 tail连上大于x的head,就可以了
*/

class Solution {
    public ListNode partition(ListNode head, int x) {
        if(head==null || head.next == null) return head;
        ListNode less = new ListNode(0), more = new ListNode(0);
        ListNode h1 = less, h2 = more;
        less.next = head;
        more.next = head;
        
        while(head!=null){
            ListNode next = head.next;
           if(head.val < x){
               less.next = head;
               less = less.next;
           }else{
               more.next = head;
               more = more.next;
           }
            head = next;
        }
        
        more.next = null;//记住这里要设为空，否则会在原linkedlist中循环
        less.next = h2.next;
    
        return h1.next;
    }
}
/*
这个方法是用两个指针，每次移动的是p指针。每一次都将p遇到p.val小于x的node换到
tail.next里面。所以tail指针的下一位永远指向下一个要被交换的node。一定要注意corner
case。比如说第一位就是小于x的node, 那么两个指针都向后移动一位
*/

public ListNode partition(ListNode head, int x) {
    ListNode dummy=new ListNode(0);
    dummy.next=head;
    ListNode p=dummy;
    ListNode tail=dummy;
    while(p!=null && p.next!=null){
        if(p.next.val>=x)
            p=p.next;
        else{
            if(p==tail){  // don't forget the edge cases when p==tail
                tail=tail.next;
                p=p.next;
            }
            else{
                ListNode tmp=p.next;
                p.next=tmp.next;
                tmp.next=tail.next;
                tail.next=tmp;
                tail=tmp; // don't forget to move tail.
            }
        }
    }
    return dummy.next;
}