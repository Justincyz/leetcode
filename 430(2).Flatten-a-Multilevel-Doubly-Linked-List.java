/*430. Flatten a Multilevel Doubly Linked List
Medium:
You are given a doubly linked list which in addition to the next 
and previous pointers, it could have a child pointer, which may 
or may not point to a separate doubly linked list. These child 
lists may have one or more children of their own, and so on, to 
produce a multilevel data structure, as shown in the example below.

Flatten the list so that all the nodes appear in a single-level, 
doubly linked list. You are given the head of the first level of 
the list.

 
Example:
Input:
 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL

Output:
1-2-3-7-8-11-12-9-10-4-5-6-NULL
*/

/*解题思路
这道题给了我们一个多层的双向链表，让我们压平成为一层的双向链表，题目中给了形象的图例，
不难理解题意(需要回原题看)。根据题目中给的例子，我们可以看出如果某个结点有下一层双向链表，
那么下一层双向链表中的结点就要先加入进去，如果下一层链表中某个结点还有下一层，那么还是优
先加入下一层的结点，整个加入的机制是DFS的，就是有岔路先走岔路，走到没路了后再返回，这就
是深度优先遍历的机制。那么既然是DFS，肯定优先考虑递归，来看具体怎么递归。
由于给定的多层链表本身就是双向的，所以我们只需要把下一层的结点移到第一层即可，那么没有子
结点的结点就保持原状，不作处理。只有对于那些有子结点的，我们需要做一些处理。首先记录分支
出来一个当前dummy节点的子节点，然后遍历子节点的next值直到下一层的末尾。此时我们可以把
下一层的末尾链接到当前层dummy的下一位了。同时也将dummy.next.prev指向下一层的末尾。
此时相当于我们已经链接了下一层到当前层。我们暂时不必考虑当前层还有没有分支，因为我们
将所有子层走完之后会回到 dummy.next这一位(我们在之前的操作里已经链接上了)。此时我们的
dummy.next指向dummy.child, 意思是我们下一位直接开始遍历子链表了。既然已经开始遍历
子链表了，那注意要让dummy.child = null， 否则会无限循环。
*/

/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {}

    public Node(int _val,Node _prev,Node _next,Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
};
*/
class Solution {
    public Node flatten(Node head) {
        if(head == null) return head;
        Node dummy = head;
        while(dummy!=null){
            if(dummy.child ==null){
                dummy = dummy.next;
                continue;
            }
            
            Node c = dummy.child;
            while( c.next!=null) c = c.next;
            c.next = dummy.next;
            if(dummy.next!=null) dummy.next.prev = c;
            dummy.next = dummy.child;
            dummy.child.prev = dummy;
            dummy.child = null;
              
        }
        return head;
    }
}

// 2019/9/22 再做
class Solution {
    public Node flatten(Node head) {
        if(head == null) return head;
        Node dummy = head;
        while(dummy !=null){
            if(dummy.child !=null){
                Node temp = dummy.next;
                Node nextHead = dummy.child;
                while(nextHead.next != null){
                    nextHead = nextHead.next;
                }
                
                dummy.next = dummy.child;
                dummy.child.prev = dummy;
                
                nextHead.next = temp;
                if(temp != null)
                    temp.prev = nextHead;
            }
            dummy.child = null;//注意题目要求让我们把child清空
            dummy = dummy.next;
        }

        
        
        return head;
    }
}
/*
这道题同样可以用递归来做。
我们需要做一些处理，由于子结点链接的双向链表要加到后面，所以当前结点之后要断开，再断开之前，
我们用变量next指向下一个链表，然后我们对子结点调用递归函数，我们suppose返回的结点已经压平了，
那么就只有一层，那么就相当于要把这一层的结点加到断开的地方，所以我们需要知道这层的最后一个结点
的位置，我们用一个变量last，来遍历到压平的这一层的末结点。现在我们就可以开始链接了，首先把子
结点链到cur的next，然后把反向指针prev也链上。此时cur的子结点child可以清空，然后压平的这一
层的末节点last链上之前保存的next结点，如果next非空，那么链上反向结点prev。这些操作完成后，
我们就已经将压平的这一层完整的加入了之前层断开的地方，我们继续在之前层往下遍历即可
*/


class Solution {
    public Node flatten(Node head) {
        Node dummy = head;
        while(dummy !=null){
            if(dummy.child !=null){
                Node next = dummy.next;
                dummy.child = flatten(dummy.child);
                Node last = dummy.child;
                while(last.next !=null) last = last.next;
                dummy.next = dummy.child;
                dummy.next.prev = dummy;
                dummy.child = null;
                last.next = next;
                if(next != null) next.prev = last;
            }
            dummy = dummy.next;
        }
        return head;
    }
    
}

