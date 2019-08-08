/*107. Binary Tree Level Order Traversal II
Easy: 
Given a binary tree, return the bottom-up level order traversal of 
its nodes' values. (ie, from left to right, level by level from 
leaf to root).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]
*/

/*解题思路
题目大意说的是让我们层遍历二叉树，但是是从底向上遍历。本来这是一道Medium的题目，估计做出来的人太多了，所以就降级成为一道Easy的题目了。
做法都比较容易让人理解。循环的做法就是先生成树高度的list，然后回溯的时候添加元素。
遍历的方法应该更加容易让人理解，注意我们用的是linkedlist<>。这样才可以使用
addFirst()方法

*/

class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if(root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            LinkedList<Integer> temp = new LinkedList<>();
            while(size-- >0){
                TreeNode t = q.remove();
                temp.add(t.val);
                if(t.left!=null) q.add(t.left);
                if(t.right!=null) q.add(t.right);
            }
            res.addFirst(temp);
        }
        
        return res;
    }
}




class Solution {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        helper(root,0);
        return result;
    }
    
    public void helper(TreeNode root, int height){
      if (root == null) return;
        if (height >= result.size()) {
            result.add(0,new LinkedList<Integer>());
        }
        result.get(result.size()-height-1).add(root.val);
        helper(root.left, height+1);
        helper(root.right, height+1);
        
    }  
    
}