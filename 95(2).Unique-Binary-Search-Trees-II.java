/*95. Unique Binary Search Trees II
链接：https://leetcode.com/problems/unique-binary-search-trees-ii/submissions/

Medium: 
Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.

Example:

Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
Explanation:
The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
*/

/*解题思路
这道题是之前的 Unique Binary Search Trees 的延伸，之前那个只要求算出
所有不同的二叉搜索树的个数，这道题让把那些二叉树都建立出来。这种建树问题
一般来说都是用递归来解，这道题也不例外，划分左右子树，递归构造。这个其实
是用到了大名鼎鼎的分治法 Divide and Conquer，类似的题目还有之前的那道 
Different Ways to Add Parentheses 
用的方法一样，用递归来解，划分左右两个子数组，递归构造。刚开始时，将区间 
[1, n] 当作一个整体，然后需要将其中的每个数字都当作根结点，其划分开了左
右两个子区间，当做左右两个子树，然后分别调用递归函数，会得到两个结点数组，接下来要做的就是
从这两个数组中每次各取一个结点，当作当前根结点的左右子结点，然后将根结点
加入结果 res 数组中即可

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
    public List<TreeNode> generateTrees(int n) {
        if(n == 0) return new ArrayList<>();
        return helper( 1, n);
    }
    
    public List<TreeNode> helper(int s, int e){
        if(s > e){
            List<TreeNode> res = new ArrayList<>();
            res.add(null);
            return res;
        }
        List<TreeNode> list = new ArrayList<>();
        for(int i=s; i<=e; i++){
            List<TreeNode> l = helper(s, i-1);
            List<TreeNode> r = helper(i+1, e);
            for(int p=0; p< l.size(); p++){
                for(int q =0; q< r.size(); q++){
                    TreeNode root = new TreeNode(i);
                    root.left = l.get(p);
                    root.right = r.get(q);
                    list.add(root);
                }
            }
        }
        return list;
    }
}
/*
这道题目有一个dynamic programming的tag，说明在递归的过程中我们
可以使用记忆化递归的办法继续缩短时间。dp[i][j]代表从i到j位可以产生
所有树的集合。所以我们用了List<TreeNode>[][] dp 这一种形式。
*/


class Solution {
    public List<TreeNode> generateTrees(int n) {
        if(n == 0) return new ArrayList<>();
        List<TreeNode>[][] dp = new List[n+1][n+1];
        return helper( 1, n, dp);
    }
    
    public List<TreeNode> helper(int s, int e, List<TreeNode>[][] dp){
        if(s > e){
            List<TreeNode> res = new ArrayList<>();
            res.add(null);
            return res;
        }
        if(dp[s][e] != null){
            return dp[s][e];    
        } 
        
        List<TreeNode> list = new ArrayList<>();
        for(int i=s; i<=e; i++){
            List<TreeNode> l= helper(s, i-1, dp), r=helper(i+1, e, dp);
            
            for(int p=0; p< l.size(); p++){
                for(int q =0; q< r.size(); q++){
                    TreeNode root = new TreeNode(i);
                    root.left = l.get(p);
                    root.right = r.get(q);
                    list.add(root);
                }
            }
        }
        dp[s][e] = list;
        return list;
    }
 
}