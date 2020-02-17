/*814. Binary Tree Pruning
Medium
链接: https://leetcode.com/problems/binary-tree-pruning/

We are given the head node root of a binary tree, where additionally 
every node's value is either a 0 or a 1.

Return the same tree where every subtree (of the given tree) not 
containing a 1 has been removed.

(Recall that the subtree of a node X is X, plus every node that is 
a descendant of X.)

Example 1:
Input: [1,null,0,0,1]
Output: [1,null,0,null,1]
 
Explanation: 
Only the red nodes satisfy the property "every subtree not containing a 1".
The diagram on the right represents the answer.

 1						1
  \					     \
   0       ---->          0
  / \                      \
 0   1                      1


Example 2:
Input: [1,0,1,0,0,0,1]
Output: [1,null,1,null,1]

 	 1						1
   /  \					     \
  0     0       ---->          0
 /\    / \                      \
0  0  0   1                      1

*/

/*解题思路
题目让我们把子树总和为0的整个子树删除掉，实际上比较简单，肯定都是用bottom-up
的方法来做，因为这样子才可以从子树到全部。

*/

class Solution {
    public TreeNode pruneTree(TreeNode root) {
        int res = helper(root);
        return res == 0? null:root;
    }
    
    public int helper(TreeNode root){
        if(root == null) return 0;
        int left = helper(root.left);
        int right = helper(root.right);
        if(left == 0) root.left = null;
        if(right == 0) root.right = null;
        
        if(left+right+root.val == 0){
            return 0;    
        }else{
            return 1;
        }
    }
}



/*
一种更加简单的解题方法。
*/
class Solution {
    public TreeNode pruneTree(TreeNode root) {
        if(root == null) return null;
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        
        if(root.left == null && root.right == null && root.val == 0) return null;
        else return root;
    }
}