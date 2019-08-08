/*237. Delete Node in a Linked List
Easy: 
Write a function to delete a node (except the tail) in a 
singly linked list, given only access to that node.

Given linked list -- head = [4,5,1,9], which looks like following:

Example 1:
Input: head = [4,5,1,9], node = 5
Output: [4,1,9]
Explanation: You are given the second node with value 5, the 
linked list should become 4 -> 1 -> 9 after calling your function.
*/

/*解题思路
这道题目其实意义不是很大。给定一个linkedlist其中的一个节点。让我们把这个节点
删除。硬要操作是不可能的，因为没有前面节点的值，如果要删除的话谁来接下一位呢？
那我们只能把下一位的值赋给当前位，然后删掉下一位了。

*/
class Solution {
    public void deleteNode(ListNode node) {
        //ListNode temp = node;
        node.val = node.next.val;
        node.next = node.next.next;
    }
}

class Solution {
    public void deleteNode(ListNode node) {
        while(node.next.next!=null){
            node.val = node.next.val;
            node = node.next;
        }
        node.val = node.next.val;
        node.next = null;
    }
}