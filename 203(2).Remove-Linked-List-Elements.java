/* 203. Remove Linked List Elements
Easy: 
Remove all elements from a linked list of integers that have value val.

Example:
Input:  1->2->6->3->4->5->6, val = 6
Output: 1->2->3->4->5
*/

/*解题思路
主要还是注意如果整个List的数字都要被磨掉应该怎么办。
我们需要借助dummy head,因为很有可能head也是我们要remove的值。
只有当下一位的值不是val时，才让cur = cur.next。此时也是串上了
dummy 和我们选择的List之间的关系。

*/

class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode cur = dummyHead;
        while(head!=null){
            if(head.val == val){
                //指向新的一位，但是此时cur本身并没有变化
                cur.next = head.next;
            }else{
                cur = cur.next;
            }
            head = head.next;
           
        }
        return dummyHead.next;
    }
}

/*
第二次写，新的心得。
比较麻烦的一点，假设以下的情况。
1->1->null
我们要移除的就是1
第二次写一开始里面不是用while loop判断，而是用了if，
这样的话跳过了第一个1之后，temp就会是第二个1，此时
不管怎么样我们都不可能取消连接temp本身。
所以必须要一个while loop排除所有target value节点。
所以还是用上面的方法是最简单的。
*/

class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;
        dummy.next = head;
        while(temp != null && temp.next!=null){
            
            while(temp.next!=null && temp.next.val == val){
                temp.next = temp.next.next;
            }
            temp = temp.next;    
                     
        }
       
        return dummy.next;
    }
}