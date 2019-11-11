/*429. N-ary Tree Level Order Traversal
Easy
链接: https://leetcode.com/problems/n-ary-tree-level-order-traversal/

Given an n-ary tree, return the level order traversal of its nodes' values. 
(ie, from left to right, level by level).

For example, given a 3-ary tree:
      1
    / | \
   3  2  4
  / \
 5  6

We should return its level order traversal:

[
  [1],
  [3,2,4],
  [5,6]
]
*/

/*解题思路
一开始习惯性的以为还是二叉树，注意这里是多叉树。我们用一个level来获取对应层数
的list，然后添加。

*/

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, root, 0);
        return res;
    }
    
    public void helper(List<List<Integer>> res, Node root, int level){
        if(root == null) return;
        if(res.size() <= level) res.add(new ArrayList());
        res.get(level).add(root.val);
        for(Node n : root.children)
            helper(res, n, level+1);
    }
}