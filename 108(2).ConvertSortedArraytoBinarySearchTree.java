/*108. Convert Sorted Array to Binary Search Tree
Easy: 

Given an array where elements are sorted in ascending order, convert 
it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary 
tree in which the depth of the two subtrees of every node never differ
 by more than 1.

Example:
Given the sorted array: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the 
following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5

*/

/*解题思路
其实就是每次取中间的值当做current root的值，然后左右两边继续循环变成root的左右子树

*/

class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {

        return helper(0, nums.length-1, nums);
    }
    
    public TreeNode helper(int start, int end, int[] nums){
        if(start > end) return null;
        
        int mid = (end+start)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.right = helper(mid+1, end, nums);
        root.left = helper(start, mid-1, nums);
        return root;
    }
}