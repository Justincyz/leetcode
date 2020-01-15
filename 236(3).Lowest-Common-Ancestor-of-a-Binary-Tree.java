/*236. Lowest Common Ancestor of a Binary Tree
链接：https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/

Medium: 

Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]

 	   3
     /	\
    5    1
   / \  / \
  6   2 0  8
     / \
    7   4

Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.

Example 2:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
*/

/*解题思路
题目让我们找二叉树两个节点的最低公共祖先。因为题目首先就确定了肯定有
这个公共祖先，所以事情就好办很多了。总共有三种情况，第一个是某一个节点
左右子树中都没有我们要找到两个节点，那就直接返回Null.第二个是如果当前节
点的值与某p,q中某一个节点相等，那就直接返回这个节点，也不用继续往下查了。原因是
我们要找最低公共子节点，如果另一个节点在当前节点的子树中，那当前节点就是
最低公共子节点，如果不是的话，那就继续向上返回就好，剩下的交给第三种情况。第三种情况就是某一个
节点不是p和q,但是p和q分别出现在当前节点的左边和右边，或者我们上一步找到
了最低的公共祖先了。那么当前节点就是最低公共祖先了。

这道题目有一个follow-up,就是如果树中不一定存在我们要找的两个节点怎么办。
那么最简单的办法就是我们先按照上面的办法去找到这个潜在的最低公共子节点，
然后从我们找到的节点再向下出发，去验证是否两个节点都在我们找到的最低
公共节点的子树中就好。详见代码

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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;
        else if(root.val == p.val || root.val == q.val) return root;
        else if(root.val != p.val && root.val !=q.val){
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            
            if(left !=null && right !=null) return root;
            else if(right != null) return right;
            else return left;
            
        }
        return null;
    }
}




//针对follow-up 的代码
//如果树中不存在我们给出的节点怎么办
public class Main {
    //Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
    public static void main(String[] args) {
        System.out.println("Hello World!");
        TreeNode root = new TreeNode(3), n1= new TreeNode(5), n2 =  new TreeNode(1), n3 = new TreeNode(6), n4 =  new TreeNode(2), n5 =  new TreeNode(0), n6 =  new TreeNode(8), n7 = new TreeNode(7), n8 = new TreeNode(4);
        
        TreeNode n9 = new TreeNode(1);//测试用，树中不存在此节点
  
        root.left = n1; root.right = n2; n1.left = n3; n1.right = n4; n2.left = n5; n2.right = n6; n4.left = n7; n4.right = n8;
        Solution s = new Solution();
        System.out.println(s.lowestCommonAncestor(root, n1, n9).val);
    }
}


class Solution {
    public Solution(){}
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        TreeNode ans = helper(root, p, q);
        boolean[] c = {false, false};
       
        check(ans, p, q, c);
    
        if(c[0] && c[1]){
            return ans;
        }else{
            
            return new TreeNode(Integer.MAX_VALUE);
        }
       
    }
    
    public TreeNode helper(TreeNode root, TreeNode p, TreeNode q){
        if(root == null) return null;
        System.out.println(p.val+" "+q.val);
        if(root.val == p.val || root.val == q.val) return root;
        TreeNode left = helper(root.left, p, q);
        TreeNode right = helper(root.right, p, q);
        
        if(left !=null && right != null){
            return root;
        }else if(left !=null){
            return left;
        }else if(right !=null){
            return right;
        }
        
        return null;
    }
    
    //添加的check代码
    public void check(TreeNode root, TreeNode p, TreeNode q, boolean[] c){
        if(root == null){
            return;
        }
        boolean left = false, right = false;
        if(root.val == p.val){
            c[0] = true;
        }
        if(root.val == q.val){
            c[1] = true;
        }
        
       check(root.left, p, q, c); check(root.right, p, q,c);
    }
}


class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
 }