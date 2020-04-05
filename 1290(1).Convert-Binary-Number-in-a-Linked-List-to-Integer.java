/*1290. Convert Binary Number in a Linked List to Integer
Easy
链接: https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/

Given head which is a reference node to a singly-linked list. The value of each node in the linked list is either 0 or 1. The linked list holds the binary representation of a number.

Return the decimal value of the number in the linked list.

Example 1:
1 ---> 0 ---> 1

Input: head = [1,0,1]
Output: 5
Explanation: (101) in base 2 = (5) in base 10

Example 2:
Input: head = [0]
Output: 0

Example 3:
Input: head = [1]
Output: 1

Example 4:
Input: head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]
Output: 18880

Example 5:
Input: head = [0,0]
Output: 0
 

Constraints:

The Linked List is not empty.
Number of nodes will not exceed 30.
Each node's value is either 0 or 1.
*/

/*解题思路
这道题目给我们一个链表，我们将这个链表当做一个整数的二进制表达形式。
head指向的是二进制链表的高位。让我们最后返回这个链表所代表的整数数字。

我们其实直接从高位往低位遍历一遍就可以了。每一次遇到节点为1的时候就给
结果加一，但是每一次都要给结果乘以2.最后还要记得除以2.

假设这个链表代表的数字为6: 1 ---> 1 ---> 0
res = 0

1 ---> 1 ---> 0
|
res = (0+1)*2 = 2

1 ---> 1 ---> 0
       |
res = (2+1)*2 = 6

1 ---> 1 ---> 0
              |
res = (6*2) = 12

最后结果还要除以2，因为除了最低位为1之外，其余的都是2的整数倍，所以最后要除个2

*/

class Solution {
    public int getDecimalValue(ListNode head) {
        int res = 0;
        
        while(head !=null){
            if(head.val == 1) res+=1;
            res*=2;
            head = head.next;
        }  
        
        return res/2;
    }
}