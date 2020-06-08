/*230. Kth Smallest Element in a BST/ Kth Largest Element in a BST
链接：https://leetcode.com/problems/kth-smallest-element-in-a-bst/
Medium: 
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note:
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Example 1:

Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1
Example 2:

Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3
*/

/*解题思路
搞一个in-order traverse就好了

*/


class Solution {
    public int kthSmallest(TreeNode root, int k) {
        if(root == null) return -1;
        TreeNode dummy = root;
        Stack<TreeNode> s= new Stack();
        while(!s.isEmpty() || dummy!=null){
            while(dummy!=null){
                s.push(dummy);
                dummy = dummy.left;
            }
            
            TreeNode t = s.pop();
            k--;
            if(k==0) return t.val;
            dummy = t.right;
        }
        return -1;
    }
}


//拓展一下，找第K个大的数，倒过来就可以了
class Solution {

    public int kthSmallest(TreeNode root, int k) {

        if(root == null) return -1;
        TreeNode dummy = k;
        Stack<TreeNode> s= new Stack();
        while(!s.isEmpty() || dummy!=null){
            while(dummy!=null){
                s.push(dummy);
                dummy = dummy.right;
            }
            
            TreeNode t = s.pop();
            found--;
            
            if(found==0) return t.val;
            dummy = t.left;
        }
        return -1;
    }

}


//也可以用循环的方式来写
class Solution {
    int count =0;
    int res =0;
    public int kthSmallest(TreeNode root, int k) {
        traverse(root, k);
        return res;
    }
    
    public void traverse(TreeNode root, int k){
        if(root == null || count >k) return;
        
        traverse(root.left, k);
        
        count++;
        if(count == k){
            res = root.val;
            return;
        }
        
        traverse(root.right, k);
    }
}