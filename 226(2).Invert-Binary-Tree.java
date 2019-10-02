/*226. Invert Binary Tree
链接：
Easy: 
Invert a binary tree.

Example:

Input:

     4
   /   \
  2     7
 / \   / \
1   3 6   9
Output:

     4
   /   \
  7     2
 / \   / \
9   6 3   1
*/

/*解题思路
直接top down交换两个子节点就可以了，然后子节点再互相交换。这个树有可能不是满的树，比如说
   4
    \
     9
    /
   7
这样的，所以当某一个root = null的时候，就直接返回了。

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
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        
        TreeNode l = root.left;
        root.left = root.right;
        root.right = l;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}