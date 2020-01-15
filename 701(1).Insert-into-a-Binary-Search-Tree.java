/*701. Insert into a Binary Search Tree
链接：https://leetcode.com/problems/insert-into-a-binary-search-tree/
Medium: 
Given the root node of a binary search tree (BST) and a value to be inserted into the tree, insert the value into the BST. Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.

Note that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. You can return any of them.

For example, 

Given the tree:
        4
       / \
      2   7
     / \
    1   3
And the value to insert: 5
You can return this binary search tree:

         4
       /   \
      2     7
     / \   /
    1   3 5
This tree is also valid:

         5
       /   \
      2     7
     / \   
    1   3
         \
          4
*/

/*解题思路
这道题一开始想的太复杂了，因为不需要平衡，所以每一个新的值直接挂到leaf去就可以。
可以有iterative 和 recursive的两种解法

*/

class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
       
        if(root == null) return new TreeNode(val);
        if(root.val > val){
            root.left = insertIntoBST(root.left, val);
        }else{
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }
}


class Solution {
	public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null) return new TreeNode(val);
        TreeNode cur = root;
        while(true) {
            if(cur.val <= val) {
                if(cur.right != null) cur = cur.right;
                else {
                    cur.right = new TreeNode(val);
                    break;
                }
            } else {
                if(cur.left != null) cur = cur.left;
                else {
                    cur.left = new TreeNode(val);
                    break;
                }
            }
        }
        return root;
    }
}






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
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);
        if(root.val > val)
            helper(root, root.left, node);
        else
            helper(root, root.right, node);
        return root;
    }
    
    public void helper(TreeNode prev, TreeNode root,  TreeNode node){
        if(root == null){
            if(prev.val < node.val) prev.right = node;
            else prev.left = node;
            return;
        }
        if(root.val > node.val)
            helper(root, root.left, node);
        else
            helper(root, root.right, node);
    }
}