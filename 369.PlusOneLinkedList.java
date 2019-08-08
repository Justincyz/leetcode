/*369. Plus One Linked List
Medium: 
Given a non-negative integer represented as non-empty a singly l
inked list of digits, plus one to the integer.

You may assume the integer do not contain any leading zero, except 
the number 0 itself.

The digits are stored such that the most significant digit is at 
the head of the list.

Example :
Input: [1,2,3]
Output: [1,2,4]
*/

/*解题思路
题目要求给一个练笔爱哦代表的数字加1. 链表给的是链表头，所以我们肯定是要从尾部往前遍历才知道如何
去累加carry。
这道题目有iterative和recursive两种解法。都是先遍历到尾部，然后再往前一步步的累加carry.
不难，了解边际case, 比如说  9-> 9这样的情况，最后的结果是 1-> 0 ->0就可以了
*/

class Solution {
    public ListNode plusOne(ListNode head) {
        
        ListNode dummy = new ListNode(1);
        dummy.next = head;
        int carry = check(head);
        if(carry ==1) return dummy;
        else return head;
        
    }
    public int check(ListNode node){
        if(node == null){
            return 1;
        }
        int carry = check(node.next);
        if(carry == 0) return 0;
        int total = (node.val+1);

        node.val =  total % 10;
        return total/10;
    }
}


class Solution {
    public ListNode plusOne(ListNode head) {
        if(head ==null ) return null;
        ListNode dummy = new ListNode(1);
        dummy.next = head;
        Stack<ListNode> stack = new Stack();
        while(head!=null){
            stack.push(head);
            head = head.next;
        }
        
        int carry = 1;
        while(!stack.isEmpty()){
            ListNode t = stack.pop();
            t.val += carry;
            carry = t.val /10;
            if(carry == 0) return dummy.next;
            t.val %=10;
        }
        
        return dummy;
        
    }
}