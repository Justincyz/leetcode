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
                cur.next = head.next;
            }else{
                cur = cur.next;
            }
            head = head.next;
           
        }
        return dummyHead.next;
    }
}
