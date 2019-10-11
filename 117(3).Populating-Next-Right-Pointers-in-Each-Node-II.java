/*117. Populating Next Right Pointers in Each Node II
链接：https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
Medium: 
Given a binary tree

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

     1 -> null
    / \ 
   2 ->3 -> null
  / \   \
 4-> 5 ->7 -> null


*/

/*解题思路


*/

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val,Node _left,Node _right,Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/
class Solution {
    public Node connect(Node root) {
        Node dummy = root;
        Node head = new Node();
        Node tail = head;
        
        while(dummy !=null){
            
            if(dummy.left!=null){
                tail.next = dummy.left;
                tail = tail.next;
            }
            if(dummy.right!=null){
                tail.next = dummy.right;
                tail = tail.next;
            }
            
            dummy = dummy.next;
            
            if(dummy == null){
                dummy = head.next;
                head.next = null; // 这里注意不能让head == null, 而是head.next == null，才能重复使用
                tail = head;
            }
           
        }
        
        return root;
    }
}



// 这是最简单的两个queue的办法
class Solution {
    public Node connect(Node root) {
        if(root == null) return root;
        Node dummy = root;
        Queue<Node> q = new LinkedList<>();
        q.add(dummy);
        while(!q.isEmpty()){
            Queue<Node> t = new LinkedList<>();
            
            while(!q.isEmpty()){
                Node n = q.poll();
                n.next = q.peek();
                if(n.left !=null) t.add(n.left);
                if(n.right !=null) t.add(n.right);
            }
            q = t;
        }
        
        return root;
    }
}


//一个queue的做法
class Solution {
    public Node connect(Node root) {
        if(root == null) return root;
        Node dummy = root;
        Queue<Node> q = new LinkedList<>();
        q.add(dummy);
        while(!q.isEmpty()){
            int size = q.size();
            
            while(size-- >0){
                Node n = q.poll();
                if(size != 0)
                    n.next = q.peek();
                if(n.left !=null) q.add(n.left);
                if(n.right !=null) q.add(n.right);
            }
            
        }
        
        return root;
    }
}