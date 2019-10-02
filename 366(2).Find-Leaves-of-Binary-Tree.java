/*366. Find Leaves of Binary Tree
Medium
链接: https://leetcode.com/problems/find-leaves-of-binary-tree/

Given a binary tree, collect a tree's nodes as if you were doing this: 
Collect and remove all leaves, repeat until the tree is empty.

Example:
Input: [1,2,3,4,5]
  
          1
         / \
        2   3
       / \     
      4   5    

Output: [[4,5,3],[2],[1]]

Explanation:

1. Removing the leaves [4,5,3] would result in this tree:

          1
         / 
        2          
 

2. Now removing the leaf [2] would result in this tree:

          1          
 

3. Now removing the leaf [1] would result in the empty tree:

          []       

*/

/*解题思路
这道题目是一个bottom up的做法，每一个要被添加的Node所处的位置应该是这个node
距离其最远的leaf node的距离

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
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    public List<List<Integer>> findLeaves(TreeNode root) {
        if(root == null) return result;
        helper(root);
        return result;
    }
    
    public int helper(TreeNode root){
        if(root == null) return -1;
        int height = 1+Math.max(helper(root.left), helper(root.right));
        if(result.size() < height+1) result.add(new ArrayList<>()); //如果高度大于 result的长度，说明需要新加入 arraylist
        result.get(height).add(root.val);
        return height;
    }
}


//当然比较容易的就是借助hashmap
class Solution {
    int deep = 0;
    Map<Integer, List<Integer>> map = new HashMap<>();
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        helper(root);
        while(map.containsKey(deep)){
            res.add(map.get(deep--));
        }
        Collections.reverse(res);
        return res;
    }
    
    public int helper(TreeNode root){
        if(root == null){
            return 0;
        }
        int leftLevel = helper(root.left);
        int rightLevel = helper(root.right);
        int level = Math.max(leftLevel, rightLevel);
        if(!map.containsKey(level)) map.put(level, new ArrayList());
        deep = Math.max(deep, level);
        map.get(level).add(root.val);
        return level+1;
    }
}