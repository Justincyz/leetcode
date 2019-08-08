/*138. Copy List with Random Pointer
Medium: 
A linked list is given such that each node contains an additional 
random pointer which could point to any node in the list or null.

Return a deep copy of the list.

Input:
{"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}

Explanation:
Node 1's value is 1, both of its next and random pointer points to Node 2.
Node 2's value is 2, its next pointer points to null and its random pointer points to itself.
*/

/*解题思路
这道题目题意是说，有这么一种链表，每个指针除了指向下一个之外，同时还有一个指针指向链表中
一个任意的节点。让我们返回这个链表的一个deep copy。首先不推荐第一个解法，这个解法实际上
并没有完全切断新建立的链表和原链表之间的联系，因为虽然链表中的next都是新构造出来的节点，
但是random指向的都是原来链表的random节点。很奇葩，虽然也能通过，完全不推荐。

强推第二种算法。我们在每一个原节点后面建立一个新的节点。值和原节点一样。然后再链接上原节点的下一位。
假设原节点是 1-2-3-4
在原节点基础上增加完节点就是 1-1-2-2-3-3-4-4
到这里我们就可以看到一个很巧妙地做法了。我们下一步是要找到新的节点的每一个random pointer
指向市民位置。要找到新节点的random pointer我们利用原节点的每一个node.random，让
node.next.random = node.random.next。node.next 是新节点的，新节点的random指向node.ramdon
的下一位，下一位实际上就是新构建出来和node.random一样值的node。通过这一步我们就让所有新的
节点连接上了它的random pointer指向的值。
最后一步就是断开新的链表和原来的链表，用一个dummy指向新链表的第一位，然后顺序断开，返回dummy.next
就可以了

*/


class Solution {
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
     
        Node dummy = new Node(head.val, null, null);
        Node c1 = head.next, c2 = dummy;
        map.put(head, dummy);
        while(c1!=null){
            Node next = new Node(c1.val, null, null);
            c2.next = next;
            map.put(c1, next);
            c1 = c1.next;
            c2 = c2.next;
        }
        c2 = dummy; c1 = head;
        while(c1!= null){
            c2.random = map.get(c1.random);
            c1 = c1.next;
            c2 = c2.next;
        }
        return dummy;
        
    }
}



class Solution {
    public Node copyRandomList(Node head) {
        Node cur = head;
        while(cur!=null){
            Node t = new Node(cur.val, null, null);
            Node next = cur.next;
            cur.next = t;
            t.next = next;
            cur = next;
            
        }

        cur = head;
        while(cur !=null){
            if(cur.random!=null){
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }
        cur = head;
        Node d = new Node(0, null, null);
        Node dummy = d;
        while(cur !=null){
            dummy.next = cur.next;
            dummy = dummy.next;
            cur.next = cur.next.next;
            cur = cur.next;
        }
        return d.next;
    }
}

