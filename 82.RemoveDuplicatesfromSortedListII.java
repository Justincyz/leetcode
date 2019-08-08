/* 82. Remove Duplicates from Sorted List II
Medium:

Given a sorted linked list, delete all nodes that have duplicate 
numbers, leaving only distinct numbers from the original list.

Example 1:

Input: 1->2->3->3->4->4->5
Output: 1->2->5
Example 2:

Input: 1->1->1->2->3
Output: 2->3 
*/

/*解题思路
和之前那道 Remove Duplicates from Sorted List不同的地方是这里要
删掉所有的重复项，由于链表开头可能会有重复项，被删掉的话头指针会改变，
而最终却还需要返回链表的头指针。所以需要定义一个新的节点，然后链上原链表，
然后定义一个前驱指针和一个现指针，每当前驱指针指向新建的节点，现指针从
下一个位置开始往下遍历，遇到相同的则继续往下，直到遇到不同项时，把前驱
指针的next指向下面那个不同的元素(只是先指向而已)。如果现指针遍历的第一
个元素就不相同，则把前驱指针向下移一位。前驱指针的作用就是将单独出现的数字
串起来。前两个办法都是用循环来做，第三个是递归的方法

*/

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        //prev的作用是把所有无重复的数字串起来
        ListNode current = dummy.next, prev = dummy;
       
        while(current!=null){
        	//只有当前位和下一位不同时，才往下走。所以结束时current.val和 current.next.val时不一样的
            while(current.next!=null && current.val == current.next.val){
                current = current.next;
            }
            //这里的判断很巧妙，不需要boolean flag之类的用作判断
            if(prev.next == current){
                prev = prev.next;
            }else{
                prev.next = current.next;
            }
            current = current.next;
        }
        return dummy.next;
    }
}

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
       
        while(current.next!=null && current.next.next !=null){
            if(current.next.val == current.next.next.val){
                int check = current.next.val;
                //这里只是在对下一位进行操作,而对当前的current并没有改变
                while(current.next !=null && current.next.val == check){
                    current.next = current.next.next;
                }
            }else{
                current = current.next;
            }
        }
        return dummy.next;
    }
}


/*
递归的方法比较容易理解，首先判断当前值是否为空。如果head为空，直接返回。
然后判断，若head之后的结点存在，且和当前节点值相等，那么我们先进行一个
while循环，跳过后面所有值相等的结点，到最后一个值相等的点停下。比如对于
例子2来说，head停在第三个结点1处，然后我们对后面一个结点调用递归函数，
即结点2，这样做的好处是，返回的值就完全把所有的结点1都删掉了。若head之
后的结点值不同，那么我们还是对head之后的结点调用递归函数，将返回值连到
head的后面，这样head结点还是保留下来了，因为值不同，最后返回head即可，
*/
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return head;
        if(head.next!=null &&head.val == head.next.val){
            while(head.next!=null &&head.val == head.next.val){
                head = head.next;
            }
            return deleteDuplicates(head.next);
        }
        head.next = deleteDuplicates(head.next);
        return head;
    }
}