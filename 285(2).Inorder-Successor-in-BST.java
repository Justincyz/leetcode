/*285. Inorder Successor in BST
Medium
题目描述:
Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

The successor of a node p is the node with the smallest key greater than p.val.

Example 1:
   2
  / \
 1   3

Input: root = [2,1,3], p = 1
Output: 2
Explanation: 1's in-order successor node is 2. Note that both p and the return value is of TreeNode type.

Example 2:
     5
    / \
   3   6
  / \
 2   4
/
1

Input: root = [5,3,6,2,4,null,null,1], p = 6
Output: null
Explanation: There is no in-order successor of the current node, so the answer is null.
*/

/*解题思路
这道题目因为是考察二叉搜索树的后继节点，所以还是比较简单的。我们只需要
就O(nlogn)的时间复杂度就可以完成一次查找的过程。如果p的值小于当前
root的值，说明我们要往左走，此时左子树所有值的upper就是当前root的值了。
如果我们要往右边走，此时不用更新upper因为对于找后继节点来说，我们不需要记录他的
下边界。所以使用了一个upper来不停地逼近我们要找的节点的值的上界。最后返回
的就是upper, 如果upper直到最后也没有被更新的话，说明给出的节点P是这个BST中最大的值。

这道题目有个follow-up 是给定树中任意的一个节点，然后找后继节点。当然每个Node中会包含
一个parent attribute用来往上查找.  510: Inorder Success in BST
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
    TreeNode  upper =null;
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root == null){
            return upper;
        }
        else if(root.val <= p.val){
            inorderSuccessor(root.right, p);
        }else{
            upper = root;
            inorderSuccessor(root.left, p);
        }
        
        return upper;
    }
}