/*103. Binary Tree Zigzag Level Order Traversal
Medium: 
Given a binary tree, return the zigzag level order traversal of its 
nodes' values. (ie, from left to right, then right to left for the 
next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
*/

/*解题思路
题目大意就是蛇形遍历二叉树，奇数层左往右，偶数层右往左。
那我们就普通的二叉树层遍历加一个flag就好了，奇数层先加左边的Node，然后再右边。偶数层
反过来，先右边再左边

*/

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Stack<TreeNode> s = new Stack();
        s.push(root);
        boolean flag = true;
        while(!s.isEmpty()){
            Stack<TreeNode> t = new Stack();
            List<Integer> l = new ArrayList<>();
            while(!s.isEmpty()){
                TreeNode node = s.pop();
                l.add(node.val);
                if(flag){
                    if(node.left!=null) t.push(node.left);
                    if(node.right!=null) t.push(node.right);
                }else{
                    if(node.right!=null) t.push(node.right);
                    if(node.left!=null) t.push(node.left);
                }
            }
            res.add(l);
            s = t;
            flag = !flag;
        }
        return res;
    }
}