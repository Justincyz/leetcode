/*124. Binary Tree Maximum Path Sum
链接：https://leetcode.com/problems/binary-tree-maximum-path-sum/
Hard: 
Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6
Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42
*/

/*解题思路 bottom-up
这道题目让我们找到二叉树中的某一条path,使得合最大。
path的定义是任意两个节点之间的路径。
这道题目名不副实，其实完全算不上是hard的题目。
在每一个节点其实我们都只有两个选择，第一个是此节点单独
存在是否会是最大结果。或者此节点加上左右path的节点是否
可以构成最大结果。之所以可以直接左右比较是因为我们保证
左右返回的值最大就是0，如果小于0的话是不会向上返回结果
的。因为一个负数组成的path只会拖累当前节点的和。
我们向上继续返回的时候挑一个数值较大的边加上当前节点，
注意这里必须要加上当前节点因为这一个path不能断。
如果加上当前节点的和小于0的话，那么我们就返回一个0.


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
    int res =Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        helper(root);
        return res;
    }
    
    public int helper(TreeNode root){
        if(root == null) return 0;
        int left = helper(root.left);
        int right = helper(root.right);
       
        res = Math.max(res, Math.max(root.val, root.val+left+right));
        int cur = root.val+Math.max(left, right);
       
        return cur>0?cur: 0;
    }
}


class Solution {
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if(root == null) return 0;
        helper(root);
        return max;
    }
    
    public int helper(TreeNode node){
        if(node == null) return 0;
        int left = Math.max(0, helper(node.left));
        int right = Math.max(0, helper(node.right));
        
        max = Math.max(left+right+node.val, max);
           
        return Math.max(left, right) + node.val;
    }
}


//这是一个follow-up, 让打印出这一个路径的节点，也不清楚对不对。自己写的，测试没问题
class Solution {
    int max = Integer.MIN_VALUE;
    HashMap<Integer, ArrayList<Integer>> map= new HashMap<>();
    public int maxPathSum(TreeNode root) {
        if(root == null) return 0;
        helper(root); 
        
        ArrayList<Integer> list= map.get(max);
        
        return max;
    }
    
    public int helper(TreeNode node){
        if(node == null) return 0;

        int left = Math.max(0, helper(node.left));
        int right = Math.max(0, helper(node.right));
       
        ArrayList<Integer> leftList = new ArrayList<>();
        ArrayList<Integer> rightList = new ArrayList<>();
        
        if(!map.containsKey(left)){
            map.put(left, new ArrayList<>());
            map.get(left).add(left);
        }
         if(!map.containsKey(right)){
            map.put(right, new ArrayList<>());
            map.get(right).add(right);
        }
        
        leftList = map.get(left);
        rightList = map.get(right);
       
        ArrayList<Integer> totalList = new ArrayList<>();
       
        int total = left+right+node.val;
        if(total > max){
            if(left !=0) totalList.addAll(leftList);
            totalList.add(node.val);
            if(right !=0) totalList.addAll(rightList);
            
            map.put(total, totalList);
            
            max = total;
        }
        
        ArrayList<Integer> pass = new ArrayList<>();
        if(left > right){
            pass.add(node.val);        
            if(leftList !=null) pass.addAll(leftList);
            map.remove(right);
            return left+node.val;
        }else{
            pass.add(node.val);
            if(rightList !=null) pass.addAll(rightList);
            map.remove(left);
            return right+node.val;
        }   
    }
}