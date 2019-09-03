/*572. Subtree of Another Tree
链接：
Easy: 
Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

Example 1:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
Given tree t:
   4 
  / \
 1   2
Return true, because t has the same structure and node values with a subtree of s.

Example 2:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
    /
   0
Given tree t:
   4
  / \
 1   2
Return false.
*/

/*解题思路
问我们t树是不是s树的子树。子树的定义是从S的某一个节点开始，子树上所有节点的结构和值
都要和t一样。注意，当t树结束之后，s树的子树也必须要结束

*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s == null) return t == null;
        if(s.val == t.val && checkTree(s, t)) return true; 
        if(isSubtree(s.left, t) || isSubtree(s.right, t)) return true;
     
        return false;
    }

    
    public boolean checkTree(TreeNode s, TreeNode t){
        if(s == null || t == null){
            return s == t;
        }
        if(s.val == t.val){
            return checkTree(s.left, t.left) && checkTree(s.right, t.right);    
        }
        return false;
        
    }
}