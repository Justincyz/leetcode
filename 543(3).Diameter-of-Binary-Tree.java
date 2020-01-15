/*543. Diameter of Binary Tree
链接：https://leetcode.com/problems/diameter-of-binary-tree/
Easy: 
Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

Example:
Given a binary tree
          1
         / \
        2   3
       / \     
      4   5    
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of edges between them.
*/

/*解题思路
题目问的是求二叉树中最远距离的两个节点距离有多远。那么我们要用bottom up 的思路来做。首先这个最远距离不一定经过root, 比如一个root只有左子树的话，那么
最远距离的两个Node可能只会出现在左边。
我们维护一个全局的max变量，每一次bottom up的时候，经过每一个节点，都计算以这个
节点为根的话，左右子树的最长路径是多长。然后向上返回的时候就返回包括
当前根节点的左右两边选一条较长的路径。注意我们不是要计算有多少个节点，
而是计算有多少条边，边的数量是path的节点数-1。
因为如此所以我们也不能直接recursive调用diameterOfBinaryTree() 方法本身。
需要一个helper

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
    int max =0;
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        helper(root);
        return max;
    }
    
    public int helper(TreeNode root){
        if(root == null) return 0;
        int left = helper(root.left);
        int right = helper(root.right);
        max = Math.max(max, left+right);
        return left>right ? left+1: right+1;
    }
}