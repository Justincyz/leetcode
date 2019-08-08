/*101. Symmetric Tree
Easy: 
Given a binary tree, check whether it is a mirror of itself 
(ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
 

But the following [1,2,2,null,3,null,3] is not:

    1
   / \
  2   2
   \   \
   3    3
*/

/*解题思路
题目问的是验证一个Binary tree是否左右对称。一下两种办法都是思路，当然第一种
最快，每一次都比较正好对称的两个节点。但是第二种也是一种思路。

*/
class Solution {
    public boolean isSymmetric(TreeNode root) {
        return root==null || helper(root.left, root.right);
    }
    
    public boolean helper(TreeNode r1, TreeNode r2){
        if(r1 == null && r2 == null) return true;
        
        if(r1 == null || r2 == null) return false;
        if(r1.val!=r2.val) return false;
        return helper(r1.left, r2.right) && helper(r1.right, r2.left);
    }
}

/*
用arraylist先记录左边的，再在右边一一对应比较。
*/
class Solution {
    public boolean isSymmetric(TreeNode root) {
         if(root == null) return true;
         List<Integer> list = new ArrayList<>();
         helper(root.left, list);
         return helper2(root.right, list) && (list.size() ==0);
    }
    
    public void helper(TreeNode root, List<Integer> list){
        if(root == null){
            list.add(Integer.MIN_VALUE);
            return;
        } 
        list.add(root.val);
        helper(root.left, list);
        helper(root.right,list);
    }
    
    public boolean helper2(TreeNode root, List<Integer> list){
        if(root == null){
            if(list.remove(0) == Integer.MIN_VALUE) return true;
            else return false;
        }
        if(list.remove(0) != root.val) return false;
        return helper2(root.right, list) && helper2(root.left, list);
    }
}
