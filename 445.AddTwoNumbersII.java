/*445. Add Two Numbers II
Medium:
You are given two non-empty linked lists representing two 
non-negative integers. The most significant digit comes first 
and each of their nodes contain a single digit. Add the two 
numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, 
except the number 0 itself.

Example:
Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7

Follow up:
What if you cannot modify the input lists? In other words, 
reversing the lists is not allowed.
*/

/*解题思路
跟第二题2:Add Two Numbers不一样的是，这一次我们要从最高位往最低位遍历。
既然题目的follow up 是不可以reverse这个list， 那么只能用两个stack来先储存
list，然后再一步步的Pop()出来计算了。下面这个答案是经过了我一步步优化后的结果。
虽然计算时间可能比原来的还慢了1ms， 但是代码长度大幅度缩短了。
答案并不要求我们要用原来的listNode, 所以对于每一个ListNode我们可以新建一个
ListNode, (一开始我不知道，所以第一版的答案里用的是原来List的listNode)

*/


class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> s1 = new Stack(), s2 = new Stack(); 
        while(l1 !=null){
            s1.push(l1);
            l1 = l1.next;
        }
        while(l2 !=null){
            s2.push(l2);
            l2 = l2.next;
        }

        int carry = 0;
        ListNode pre = null;
        while(!s1.isEmpty() || !s2.isEmpty() || carry!=0){
            ListNode temp = new ListNode(0);
            if(!s2.isEmpty()){
                temp.val += s2.pop().val;
            }
            if(!s1.isEmpty()){
                temp.val += s1.pop().val;
            }
            temp.val+=carry;
            carry = temp.val/10;
            temp.val %=10;
            temp.next = pre;
            pre = temp;
        }

        return pre;
    }
}


//这是第一次写的答案，用的是原来的listNode
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> s1 = new Stack(), s2 = new Stack(); 

        while(l1 !=null){
            s1.push(l1);
            l1 = l1.next;
        }
        while(l2 !=null){
            s2.push(l2);
            l2 = l2.next;
        }

        int carry = 0;
        ListNode pre = null;
        while(!s1.isEmpty() || !s2.isEmpty()){
            if(s1.isEmpty()){
                ListNode t2 = s2.pop();
                t2.val +=carry;
                carry = t2.val/10;
                t2.val %=10;
                t2.next = pre;
                pre = t2;
            }
            else if(s2.isEmpty()){
                ListNode t1 = s1.pop();
                t1.val +=carry;
                carry = t1.val/10;
                t1.val %=10;
                t1.next = pre;
                pre = t1;
            }else{
                ListNode t1 = s1.pop(),  t2 =s2.pop();
                t1.val+=(t2.val+carry);
                carry = t1.val/10;
                t1.val %=10;
                t1.next = pre;
                pre = t1;
            }
        }
        if(carry!=0){
            ListNode temp = new ListNode(carry);
            temp.next = pre;
            pre = temp
            
        } 
        return pre;
    }
}