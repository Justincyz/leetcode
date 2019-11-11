/*106. Construct Binary Tree from Inorder and Postorder Traversal
链接：https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
Medium: 
Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

inorder = [9,3,15,20,7]
postorder = [9,15,7,20,3]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
*/

/*解题思路
这道题目有两个注意的点，第一个是hashmap来存inorder的位置，因为可以根据Inorder的
位置算出来postorder相对应的值。其次就是在计算numRight的时候，计算的是相对
的位置

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
    Map<Integer, Integer> map;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        map = new HashMap<>();
        for(int i=0; i< inorder.length; i++){
            map.put(inorder[i], i);
        }
        
        return helper(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1);
    }
    
    public TreeNode helper(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd){
        if(inStart > inEnd || postStart > postEnd) return null;
        
        TreeNode root = new TreeNode(postorder[postEnd]);
        
        int mid = map.get(root.val);
        int numRight = inEnd - mid;
        
        root.left = helper(inorder, inStart, mid-1, postorder, postStart, postEnd - numRight-1);
        root.right = helper(inorder, mid+1, inEnd, postorder, postEnd-numRight,postEnd-1);
        return root;
    }
}