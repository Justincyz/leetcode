/*145. Binary Tree Postorder Traversal
Hard
链接: https://leetcode.com/problems/binary-tree-postorder-traversal/
Given a binary tree, return the postorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [3,2,1]

Follow up: Recursive solution is trivial, could you do it iteratively?
*/

/*解题思路
这道题目让我们后序遍历一棵树，首先是最简单的循环的做法。

*/

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(res, root);
        return res;
    }
    
    public void helper(List<Integer> res, TreeNode root){
        if(root == null) return;
        helper(res, root.left);
        helper(res, root.right);
        res.add(root.val);
    }
}

/*
这是一种比较取巧的办法，因为postorder是 左-右-中。比较麻烦，所以
我们反过来，按照 中-右-左 的办法来获取所有的元素，最后把获取的list
整个翻过来，就是结果了。 这种办法可以参考preorder的做法，原理想通。
*/

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        //先根节点，然后右子树，然后左子树，最后reverse所有的元素
        while(root!= null || !stack.isEmpty()){
            if(root == null){
                root = stack.pop();
            }
            res.add(root.val);
            if(root.left != null){
                stack.push(root.left);
            } 
            root = root.right;
            
        }
        Collections.reverse(res);
        
        return res;
    }
}

/*
这个方法本质上和上面的方法是一样的，利用了linkedlist得特性，在添加
元素到头部的时候插入复杂度是O(1).这样最后的时候就不需要call reverse了
*/

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
       // Stack<TreeNode> stack2 = new Stack<TreeNode>();
        LinkedList<Integer> list = new LinkedList<Integer>();
        
        if(root ==null){return list;}

        while(stack.size() >0){
            TreeNode node = stack.pop();
            list.addFirst(node.val);
            if(node.left !=null){
                stack.push(node.left);
            }
            
            if(node.right !=null){
                stack.push(node.right);
            }
        }
      
        
        return list;
    }
}
