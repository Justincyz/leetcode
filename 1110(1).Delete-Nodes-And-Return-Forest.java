/*1110. Delete Nodes And Return Forest
链接：https://leetcode.com/problems/delete-nodes-and-return-forest/
Medium: 
Given the root of a binary tree, each node in the tree has a distinct value.

After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

Return the roots of the trees in the remaining forest.  You may return the result in any order.


Example 1:

     1
    / \
   2   3
  /\  / \
 4  5 6  7

Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]
*/

/*解题思路
这道题目好像是谷歌最近电面的高频题目。题目意思是说，给出一个二叉树，树上
每个节点的数值都不一样。然后给我们一组节点叫做to_delete。 意思是说让我们
将这些节点从树中都删除，那么删除完这个节点如果此节点有左右子树的话就各为一棵树。最后返回所有独立小树的根节点。这也是为什么叫return forest的原因，因为会返回多颗树的根节点。那么这道题目我就用了bottom-up的
方法做出来的。首先我们需要将所有要被删除的节点存在一个hashset中，这样比较方便快速查找节点的值是否在list当中。
如果当前节点在List当中，
1. 那么向上返回的结果就是null。因为可以告知当前
节点的父节点，他的子节点被移除了，所以父节点可以对这一颗子树设为null。
算是断开了父子之间的联系。
2. 他的两个/一个 子树会独立开来，所以要将他们加入到结果当中。

当然，如果当前节点并不是我们要找的节点的话，那么直接返回当前节点到上一位。

记得最后要判断一下根节点！！
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
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for(int i: to_delete) set.add(i);
        
        TreeNode r = helper(res, set, root);
        if(r !=null) res.add(r);
        return res;
    }
    
    public TreeNode helper(List<TreeNode> res, Set<Integer> set, TreeNode root){
        if(root == null) return null;
        
        TreeNode left = helper(res, set, root.left);
        TreeNode right = helper(res, set, root.right);

        if(left==null) root.left = null;
        if(right==null) root.right = null;
        
        if(set.contains(root.val)){
            if(left!=null) res.add(left);
            if(right!=null) res.add(right);
            root.left = null;
            root.right = null;
            return null;
        }else{
            
            return root;
        }
    }
}