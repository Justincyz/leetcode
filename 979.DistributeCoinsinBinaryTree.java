/*
Medium
Given the root of a binary tree with N nodes, each node in the tree
 has node.val coins, and there are N coins total.

In one move, we may choose two adjacent nodes and move one coin 
from one node to another.  (The move may be from parent to child,
 or from child to parent.)

Return the number of moves required to make every node have exactly
 one coin.

Example 1
   3
  / \
 0   0

Input: [3,0,0]
Output: 2
Explanation: From the root of the tree, we move one coin 
to its left child, and one coin to its right child.

Example 2:
    1
   / \
  0   0
   \
    3
Input: [1,0,0,null,3]
Output: 4
node val =3的节点需要往上分发自己的val, 到左边的0要一步，到右边的0要三步 

*/

/*解题思路
第一个是自己写的，基本所有的思路都和这个差不多。第二个是简化版本。意思还是一样的。
首先，对于节点i来说，如果以当前节点为根的子节点缺少了value,那么就相当于当前节点要给
子节点供应value, 如果此时当前节点有足够的value, 那就可以正常供应。如果没有的话，
可以想象成先提供子节点需要的值，然后在之后的步骤中再去向上面的节点借value。其实往上
累计借value的数目和要经过的path是一样的。因为是逐级递增的，比如说在level 3借一个，
那就会返回-1.level 2如果没有足够的话，再往上返回-1，就相当于level 借出去的往level 3
要走两步。 这就是两个 if(left <0||right <0) 的作用。如果左右和当前值相加>0的话，
那么就说明有多余的要向上传递。所以也要加到总和里面去。
第二个和第一个解法一个意思，只不过综合了三个 if statement而已
*/

class Solution {
    int total= 0;
    public int distributeCoins(TreeNode root) {
        helper(root);
        return total;
    }
    
    public int helper(TreeNode root){
        if(root == null) return 0;
        int left = helper(root.left);
        int right = helper(root.right);
        if(left<0) total+= (left)*-1;
        if(right<0) total+= (right)*-1;
        int sum = left+right+root.val;
        if(sum>0) total+= (sum-1);
        return sum-1;
    }
}

//
class Solution {
    public int distributeCoins(TreeNode root) {
        int[] res = {0};//只是为了穿值而已
        helper(root,res);
        return res[0];
    }
    
    public int helper(TreeNode root, int[] res){
        if(root==null)return 0;
        int left = helper(root.left, res);
        int right = helper(root.right, res);
        res[0]+=Math.abs(left);
        res[0]+=Math.abs(right);
        return left+right+root.val-1;
    }
}