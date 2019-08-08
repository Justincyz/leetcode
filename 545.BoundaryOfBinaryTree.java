/*545. Boundary of Binary Tree
Medium: 
Given a binary tree, return the values of its boundary in anti-clockwise 
direction starting from root. Boundary includes left boundary, leaves, and 
right boundary in order without duplicate nodes.  (The values of the nodes 
may still be duplicates.)

Left boundary is defined as the path from root to the left-most node. Right 
boundary is defined as the path from root to the right-most node. If the root
doesn't have left subtree or right subtree, then the root itself is left 
boundary or right boundary. Note this definition only applies to the input 
binary tree, and not applies to any subtrees.

The left-most node is defined as a leaf node you could reach when you always 
firstly travel to the left subtree if exists. If not, travel to the right 
subtree. Repeat until you reach a leaf node.

The right-most node is also defined by the same way with left and right exchanged.


Example 1

Input:
  1
   \
    2
   / \
  3   4

Ouput:
[1, 3, 4, 2]

Explanation:
The root doesn't have left subtree, so the root itself is left boundary.
The leaves are node 3 and 4.
The right boundary are node 1,2,4. Note the anti-clockwise direction means you 
should output reversed right boundary.
So order them in anti-clockwise without duplicates and we have [1,3,4,2].
 

Example 2

Input:
    ____1_____
   /          \
  2            3
 / \          / 
4   5        6   
   / \      / \
  7   8    9  10  
       
Ouput:
[1,2,4,7,8,9,10,6,3]

Explanation:
The left boundary are node 1,2,4. (4 is the left-most node according to definition)
The leaves are node 4,7,8,9,10.
The right boundary are node 1,3,6,10. (10 is the right-most node).
So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].
*/

/*解题思路
这道题有点白痴。题目大意就是找到逆时针包围着这棵树的边界。我们要分三步来找这个边界。
首先我们要沿着root.left找到这棵树左边界。我们用例子2来举例，那就应该是[2,4]。那我们就沿着最左边
的这条边一直向下延伸。注意，并不是说一直往左边走，假如4的右孩子非空.那么我们还要往右边走。
所以这条边会是一条蜿蜒曲折的边。
紧接着，我们对root的左子树和右子树分别做递归。找到这棵树的所有leaf。[4,7,8,9,10]
最后，我们对root.right也向下找到最右边的边。[10,6,3]
大体思路如上。我选择用的数据结构是双向链表Dequeue()。因为找左边的边我们我们要用queue(当然也可以直接
加到list中，只是写起来方便些)。找leaf也用的是queue。但是找右边的边用的应该是stack<>。因为我们要
从底向上打印边。综上所述，用dequeue<>是最方便的。
还有一点要注意，左右两条边的最后一个我们都加了两次(在找边的时候加了一次，做dfs找leaf的时候
又加了一遍)。所以在找左右两条边的时候最后需要remove()这两个多余的数。这道题就算结束了。

*/


class Solution {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;
        Deque<Integer> q = new LinkedList<>();
        list.add(root.val);
        leftBound(root.left, q);
        while(!q.isEmpty()) list.add(q.poll());
        
        bottomLevel(root.left, q);
        bottomLevel(root.right, q);
        while(!q.isEmpty()) list.add(q.poll());
        
		rightBound(root.right, q);
        while(!q.isEmpty()) list.add(q.removeLast());
        
        return list;
    }

    public void leftBound(TreeNode dummy, Deque<Integer> q){
    	while(dummy!=null){
            q.add(dummy.val);
            if(dummy.left == null) dummy = dummy.right;
            else dummy = dummy.left;
        }
        if(!q.isEmpty()) q.removeLast();
    }

     public void rightBound(TreeNode dummy, Deque<Integer> q){
     	while(dummy!=null){
            q.add(dummy.val);
            if(dummy.right == null) dummy = dummy.left;
            else dummy = dummy.right;
        }
        if(!q.isEmpty()) q.removeLast();
     }

    public void bottomLevel(TreeNode dummy, Deque<Integer> q){
        if(dummy == null) return;
        if(dummy !=null && dummy.left ==null && dummy.right == null){
            q.add(dummy.val);
            return;
        }
        if(dummy.left !=null) bottomLevel(dummy.left, q);
        if(dummy.right !=null) bottomLevel(dummy.right, q);
    }
}