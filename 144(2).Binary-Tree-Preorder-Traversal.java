/*144. Binary Tree Preorder Traversal
Medium
链接: https://leetcode.com/problems/binary-tree-preorder-traversal/
Given a binary tree, return the preorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,2,3]

Follow up: Recursive solution is trivial, could you do it iteratively?
*/

/*解题思路
这道题让我们用pre-order的办法遍历树，pre-order是先根节点，然后左子树，最后
右子树。循环的办法很简单，不用多说，主要是看如何用迭代的办法来做。
*/

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(res, root);
        return res;
    }
    
    public void helper(List<Integer> res, TreeNode root){
        if(root == null) return;
        res.add(root.val);
        helper(res, root.left);
        helper(res, root.right);
    }
}

/*

*/

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        
        while(root!= null || !stack.isEmpty()){
            if(root==null){
                root = stack.pop();
            }
            res.add(root.val);
            if(root.right != null){
                stack.push(root.right);
            }
            root = root.left;
        }
        return res;

    }
}