/*653. Two Sum IV - Input is a BST
链接：https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
Easy: 
Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.

Example 1:

Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

Output: True
 

Example 2:

Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

Output: False
*/

/*解题思路
这道题目让我们找到BST中的两个元素相加为target, 一开始在想有没有o(logn)的解法，
但是可惜没有，因为我们在每一个节点没有办法判断接下来的走势，所以只能用
O(n)的办法了，在做dfs的时候把每一个元素用hashset记录一下。

*/


class Solution {
    Set<Integer> set = new HashSet<>();
    public boolean findTarget(TreeNode root, int k) {
        if(root == null) return false;
        if(set.contains(k-root.val)) return true;
        set.add(root.val);
        return findTarget(root.left, k) || findTarget(root.right, k);
    }
}

//这个办法是对每一个元素都做一个dfs search， 可能不需要计算哈希值所以快一些
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        return dfs(root, root,  k);
    }
    
    public boolean dfs(TreeNode root,  TreeNode cur, int k){
        if(cur == null)return false;
        return search(root, cur, k - cur.val) || dfs(root, cur.left, k) || dfs(root, cur.right, k);
    }
    
    public boolean search(TreeNode root, TreeNode cur, int value){
        if(root == null)return false;
        return (root.val == value) && (root != cur) 
            || (root.val < value) && search(root.right, cur, value) 
                || (root.val > value) && search(root.left, cur, value);
    }
}