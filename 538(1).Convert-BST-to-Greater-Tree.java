/*538. Convert BST to Greater Tree
链接：
Easy: 
Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

Example:

Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13

Output: The root of a Greater Tree like this:
             18
            /   \
          20     13
*/

/*解题思路
我们遍历的方向就是二叉树的从大往小遍历就可以了。然后累加每一次
遇到的元素。座椅方向是右子树，根节点，然后左子树

*/
class Solution {
    int prev = 0;
    public TreeNode convertBST(TreeNode root) {
        if(root == null) return null;
        helper(root);
        return root;
    }
    
    public void helper(TreeNode  root){
        if(root == null) return;
        helper(root.right);
        
        int val = root.val;
        root.val +=prev;
        prev +=val;
        helper(root.left);
    }
}