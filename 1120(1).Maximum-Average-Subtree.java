/*1120. Maximum Average Subtree
链接：
Medium: 
Given the root of a binary tree, find the maximum average value of any subtree of that tree.

(A subtree of a tree is any node of that tree plus all its descendants. The average value of a tree is the sum of its values, divided by the number of nodes.)

 

Example 1:
      5
     / \
    6   1

Input: [5,6,1]
Output: 6.00000
Explanation: 
For the node with value = 5 we have an average of (5 + 6 + 1) / 3 = 4.
For the node with value = 6 we have an average of 6 / 1 = 6.
For the node with value = 1 we have an average of 1 / 1 = 1.
So the answer is 6 which is the maximum.
*/

/*解题思路
题目还是很简单的，注意这道题目问的是sub-tree，包含了某个节点到其所有子节点。
而不是比如说根节点和左子树连接而成的树。
我们自底向上，计算出现过的节点数，总数值就行。

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
    double global = Double.MIN_VALUE;
    public double maximumAverageSubtree(TreeNode root) {
        if(root == null) return 0.0;
        
        helper(root);
        return global;
    }
    
    public double[] helper(TreeNode root){
        if(root == null) return new double[]{0,0};
        double[] left = helper(root.left);
        double[] right = helper(root.right);
        
        double totalNum = left[0] + right[0]+1;
        double totalVal = left[1] + right[1]+(double)root.val;
        global = Math.max(global, totalVal/totalNum);
        return new double[]{totalNum, totalVal};
    }
}