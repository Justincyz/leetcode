/* 1008. Construct Binary Search Tree from Preorder Traversal
Medium: 
Return the root node of a binary search tree that matches the 
given preorder traversal.

(Recall that a binary search tree is a binary tree where for 
every node, any descendant of node.left has a value < node.val,
 and any descendant of node.right has a value > node.val.  
 Also recall that a preorder traversal displays the value of 
 the node first, then traverses node.left, then traverses node.right.)

 Example 1:
 input

       8
     /  \
    5   10
   / \    \
  1   7    12
[8,5,1,7,10,12]
Note:
1 <= preorder.length <= 100
The values of preorder are distinct.
*/

/*解题思路: 相似题目: 449. Serialize and Deserialize BST 等
这道题只给出了preorder的array让我们来构建BST。
首先我们知道，BST中每个节点的左边的值会小于节点右边的值。preorder是先储存根节点，
然后是左边子树，然后是右边子树。所以一开始我们会沿着根节点一直顺着左边找到最小
的值。注意，除了BST中最大的值(也即是最右边的值)之外，所有的节点都处于大于前驱节点
而小于后继节点的范畴。所以任何一个prev不会大于下一位要遇到的值。如果prev大于下一位，说明我们已经到叶节点了。用上面的例子来看，当我们遍历到1时，我们指针指向
数组的下一位，也就是7。7比prev的1大，所以此时1的左节点就是Null。当root等于1时，1的后继节点prev是5。当把5传入root.right的循环中时，此时5还是小于7。那么说明
当前root为1时这课子树已经遍历完毕了。此时就要遍历1的前驱节点5的右子树那一边了。总结来说，如果我们遇到了叶节点，那么指针指向的下一位肯定是前驱节点的右子树。
在代码里我们用一个只有一个元素的数组代表array中的指针。因为这个值是共享的，
否则在循环当中这个值就不同步了，因为这样每一层都会分叉一个当前状态的index出去
而有一些index是会被return null的。


*/

class Solution {
    public TreeNode bstFromPreorder(int[] preorder) {
        if(preorder.length ==0) return null;
        return helper( preorder, new int[]{0}, Integer.MAX_VALUE);
    }
    
    public TreeNode helper(int[] preorder, int[] pos, int prev){
        if(pos[0] >=preorder.length || preorder[pos[0]] >= prev) return null;
        
        TreeNode root = new TreeNode(preorder[pos[0]++]);
        root.left = helper(preorder, pos, root.val);
        root.right = helper(preorder, pos, prev);
        return root;
    }
}