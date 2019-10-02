/*98. Validate Binary Search Tree
Medium: 
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the 
node's key.
The right subtree of a node contains only nodes with keys greater than 
the node's key.
Both the left and right subtrees must also be binary search trees.

Example 1:

    2
   / \
  1   3

Input: [2,1,3]
Output: true

Example 2:
    5
   / \
  1   4
     / \
    3   6

Input: [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.
*/

/*解题思路
二叉搜索树的条件应该是左子树小于root, 右子树都大于root。每一层都是如此。
我们可以利用这个条件来判断。第一个是建立了一个global variable，用来储存上一次
遍历到的值，和当前遍历的值对比 (遍历是递增的)
第二个办法就是先用array存下来数值

*/


//这里有个小知识点，Double.MIN_VALUE 并不是小于0的值，要取小于0的最小值就要用负的max_value
class Solution {
    double global = -Double.MAX_VALUE;
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true; 
        
        if(!isValidBST(root.left)) return false;
       
        if((double)root.val <= global) return false;
        global = root.val;
        if(!isValidBST(root.right)) return false;
        return true;
    }
}


class Solution {
    TreeNode prev = null;
    public boolean isValidBST(TreeNode root) {
        if(root == null) return;
        boolean l = isValidBST(root.left);
        
        if(prev == null) prev = root;
        else{
            if(prev.val >= root.val) return false;
            else prev = root;
        }
        
        boolean r =isValidBST(root.right);
        return l && r;
    }
   
}



class Solution {
   
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        
        TreeNode prev = null;
        Stack<TreeNode> s = new Stack();
 
        while(!s.isEmpty() || root !=null){
            while(root!=null){
                s.push(root);
                root = root.left;
            }
            
            TreeNode cur = s.pop();
   
            if(prev == null) prev = cur;
            else{
                if(cur.val <= prev.val) return false;
                else prev = cur;
            }
            root = cur.right;
        }
        
        return true;
    }
}