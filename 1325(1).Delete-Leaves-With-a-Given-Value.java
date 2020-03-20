/*1325. Delete Leaves With a Given Value
链接：https://leetcode.com/problems/delete-leaves-with-a-given-value/
Medium: 
Given a binary tree root and an integer target, delete all the leaf nodes with value target.

Note that once you delete a leaf node with value target, if it's parent node becomes a leaf node and has the value target, it should also be deleted (you need to continue doing that until you can't).
Example 1:
    1                 1             1
   / \               / \             \
  2   3   ---->     2   3     --->    3
 /   / \                 \             \
2   2   4                 4             4

Input: root = [1,2,3,2,null,2,4], target = 2
Output: [1,null,3,null,4]
Explanation: Leaf nodes in green with value (target = 2) are removed (Picture in left). 
After removing, new nodes become leaf nodes with value (target = 2) (Picture in center).


Example 2:
    1                1
   / \              /
  3   3    ---->   3
 / \                \
3   2                2


Input: root = [1,3,3,3,2], target = 3
Output: [1,3,null,null,2]


Example 3:
      1            1           1       1
     /            /           /
    2     --->   2     --->  2   --->
   /            /
  2            2
 /
2


Input: root = [1,2,null,2,null,2], target = 2
Output: [1]
Explanation: Leaf nodes in green with value (target = 2) are removed at each step.
Example 4:

Input: root = [1,1,1], target = 1
Output: []
Example 5:

Input: root = [1,2,3], target = 1
Output: [1,2,3]
 

Constraints:

1 <= target <= 1000
Each tree has at most 3000 nodes.
Each node's value is between [1, 1000].
*/

/*解题思路 Bottom-up
这道题目不难，题目让我们把树的叶子节点中数值等于target的节点全部去掉。
如果去掉之后非叶子节点也变成了叶子节点，那么把这一些节点也一起去掉。
这是一道典型的bottom-up来做的题目。小难点在于如果当前叶子节点被去掉之后，
如何通知这个叶子节点的父节点当前节点被去掉。因为如果父节点也变成了叶子节点，那么父节点也要被去掉。
那么最简单的办法就是被去掉的节点就设置为null, 这样父节点如果两边的子节点
都变成null了，那么自己本身就变成叶子节点了，此时再进一步判断。否则说明叶子节点
没有被去掉。

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
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if(root == null) return null;
        
        TreeNode left = removeLeafNodes(root.left, target);
        TreeNode right = removeLeafNodes(root.right, target);
        if(left == null) root.left = null;
        if(right == null) root.right = null;
        if(root.left == null && root.right == null && root.val == target){
            return null;
        }else{
            return root;
        }
    }
}