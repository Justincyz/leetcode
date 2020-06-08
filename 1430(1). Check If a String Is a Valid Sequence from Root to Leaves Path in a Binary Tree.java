/*1430. Check If a String Is a Valid Sequence from Root to Leaves Path in a Binary Tree 
Medium
链接: https://leetcode.com/problems/check-if-a-string-is-a-valid-sequence-from-root-to-leaves-path-in-a-binary-tree/

Given a binary tree where each path going from the root to any leaf form a valid sequence, check if a given string is a valid sequence in such binary tree. 

We get the given string from the concatenation of an array of integers arr and the concatenation of all values of the nodes along a path results in a sequence in the given binary tree.

Example 1:
        0
      /   \
    1      0
   / \     /
  0   1   0
  \  / \
   1 0  0

Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,1,0,1]
Output: true
Explanation: 
The path 0 -> 1 -> 0 -> 1 is a valid sequence (green color in the figure). 
Other valid sequences are: 
0 -> 1 -> 1 -> 0 
0 -> 0 -> 0

Example 2:

     0
    / \
   1   0
 /  \  /
0   1 0
\  / \
 1 0  0

Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,1,1]
Output: false
Explanation: The path 0 -> 1 -> 1 is a sequence, but it is not a valid sequence.
Constraints:

1 <= arr.length <= 5000
0 <= arr[i] <= 9
Each node’s value is between [0 – 9].

*/

/*解题思路


这道题目的意思还是很简单的，就是让我们找到一条从root贯穿到leaf的path
但是比较令人难受的是这道题边界条件还是挺多的。比如说，从root到leaf这一个条件就比较麻烦。如果整个树只有根节点自己，那么如果数组也有且只有一位并且可以跟根节点match的话。那么就算是true。但是如果根节点左边还有一个元素，那么根节点就不算是leaf了。
比如说数组是[1],树的结构是
    1
   /
 3
 这样就不能算是从根节点match到leaf了，因为1并不是左右都为空的节点。所以这样的就会要返回false。
 所以就此推测，我们判断某一个节点是不是leaf node的依据就是这个节点的左右两个子节点是不是空的。如果是的话，说明我们到达了leaf node。此时我们看当前节点的值是不是跟数组当中idx的位置上的节点的值一致。我们还需要判断此时数组节点是不是同样也是最后一个了。如果两个条件都是true的话，那么我们就返回true。如果某一个节点为空或者数组此时已经遍历完了的话，说明没有兜住上面这个条件。所以此时返回false。因为我们只需要有一条path完成就算是完成了，所以每一个节点的左右subtree中只要有宜宾完成了的话就是true.

时间复杂度和空间复杂度都是O(N)
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isValidSequence(TreeNode root, int[] arr) {
       
        return helper(root, arr, 0);
    }
    
    public boolean helper(TreeNode root, int[] arr, int idx){
        if(root == null || idx == arr.length || arr[idx] != root.val){
            return false;
        }
        if(root != null && root.left==null && root.right == null && idx==arr.length-1 && arr[idx]==root.val){
            return true;
        }
        
        boolean left = helper(root.left, arr, idx+1) ;
        boolean right = helper(root.right, arr, idx+1);
        return left || right;
    }
}