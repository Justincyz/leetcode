/*938. Range Sum of BST
链接：
Easy: 
Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive).

The binary search tree is guaranteed to have unique values.


Example 1:

Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
Output: 32
Example 2:

Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
Output: 23
*/

/*解题思路
比较简单的一道tree traverse. 题目让我们找BST中所有在范围[L, R]
的node的值的总和，我们直接利用BST的性质来求出来就可以了。定义
一个global sum来记录所有的总和。

*/
class Solution {
    int sum =0;
    public int rangeSumBST(TreeNode root, int L, int R) {
        if(root == null) return 0;
        if(root.val < L){
            rangeSumBST(root.right, L, R);
        }else if(root.val > R){
            rangeSumBST(root.left, L, R);
        }else{
            sum+=root.val;
            rangeSumBST(root.left, L, root.val);
            rangeSumBST(root.right, root.val, R);
        }
        
        return sum;
    }
}