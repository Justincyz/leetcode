/*958. Check Completeness of a Binary Tree
Medium
链接: https://leetcode.com/problems/check-completeness-of-a-binary-tree/
Given a binary tree, determine if it is a complete binary tree.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is 
completely filled, and all nodes in the last level are as far left as 
possible. It can have between 1 and 2h nodes inclusive at the last level h.


Example 1:
      1
    /  \
   2    3
  /\   /
 4  5 6 
Input: [1,2,3,4,5,6]
Output: true
Explanation: Every level before the last is full (ie. levels with
 node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.

Example 2:
      1
    /  \
   2    3
  /\     \
 4  5     7 

Input: [1,2,3,4,5,null,7]
Output: false
Explanation: The node with value 7 isn't as far left as possible.

*/

/*解题思路
这道题让我们判断一个二叉树是否是完全二叉树，完全二叉树的定义是说，除了最后一层
子叶结点之外，所有的其它层级都是完整的。最后一层的节点也应该相左对齐，中间
不应该有任何的空格。
这道题目其实解法比较巧妙，其实我们只需要按照每一层从左往右按顺序遍历这棵树。
如果中间任意一个位置出现了gap, 如果是完全二叉树的话，之后不应该出现任何节点。
如果这棵树不是完全二叉树的话，那么还可能出现其他节点。所以我们用一个boolean变量gap
来判断之前是否出现过null 节点。如果出现过的话就将gap = true。如果我们第二次判断
出现了Null节点，说明这个树不是完全二叉树。

*/

class Solution {
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> traverse = new LinkedList<>();
        traverse.add(root);
        boolean gap = false;
        
        while(!traverse.isEmpty()){
            TreeNode parent = traverse.poll();
            if(parent.left == null){
                gap =  true;
            }else{
                if(gap) return false;
                traverse.add(parent.left);
            }

            if(parent.right == null){
                gap = true;
            }else{
                if(gap) return false;
                traverse.add(parent.right);
            } 
        }
        return true;
    }
}

/*

*/

    public boolean isCompleteTree(TreeNode root) {
        boolean end = false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if(cur == null) end = true;
            else{
                if(end) return false;
                queue.add(cur.left);
                queue.add(cur.right);
            }
        }
        return true;
    }