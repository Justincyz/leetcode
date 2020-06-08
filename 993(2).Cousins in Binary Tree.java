/*993. Cousins in Binary Tree
Easy
链接: https://leetcode.com/problems/cousins-in-binary-tree/
In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.

Two nodes of a binary tree are cousins if they have the same depth, but have different parents.

We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.

Return true if and only if the nodes corresponding to the values x and y are cousins.

Example 1:
Input: root = [1,2,3,4], x = 4, y = 3
Output: false

Example 2:
Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
Output: true

Example 3:
Input: root = [1,2,3,null,4], x = 2, y = 3
Output: false
 

Note:
The number of nodes in the tree will be between 2 and 100.
Each node has a unique integer value from 1 to 100.
 
*/



/*解题思路

*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    TreeNode p = null;
    int d = -1;
    public boolean isCousins(TreeNode root, int x, int y) {
        return helper(root, root.left, x, y, 1) || helper(root, root.right, x, y, 1);
    }
    
    public boolean helper(TreeNode parent, TreeNode root, int x, int y, int depth){
        if(root == null) return false;
        if(root.val == x || root.val == y){
            if(p == null){
                p = parent;
                d = depth;
            } 
            else{
                return parent != p && depth == d;    
            } 
        }
        
        return helper(root, root.left, x, y, depth+1) || helper(root, root.right, x, y, depth+1);
    }
}


//第一次写的
class Solution {
    TreeNode p1 = new TreeNode(0);
    TreeNode p2 = new TreeNode(0);
    int depth1=0;
    int depth2 =0;
    public boolean isCousins(TreeNode root, int x, int y) {
        dfs(root, x, y, 0, null);
        if(depth1 == depth2 && p1 != p2) return true;
        return false;
    }
    
     public void dfs(TreeNode root, int x, int y, int depth, TreeNode parent){
        if(root == null) return;
        if(root.val == x){
            p1 = parent; 
            depth1 = depth;
        }
        if(root.val == y){
            p2 = parent;
            depth2 = depth;
        } 
        dfs(root.left, x, y, depth+1, root);
        dfs(root.right, x, y, depth+1, root);
      
    }
    
}