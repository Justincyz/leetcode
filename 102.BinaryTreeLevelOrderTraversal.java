/*102. Binary Tree Level Order Traversal
Medium: 
Given a binary tree, return the level order traversal of its nodes' 
values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]

*/

/*解题思路
这道题目我被linkedin问过一次。记忆很深刻，我们可以用递归或者迭代的办法来做这道题目。
不难，注意细节就行。

*/

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> q = new LinkedList();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            while(size -- >0){
                TreeNode node =q.poll();
                level.add(node.val);
                if(node.left!=null) q.add(node.left);
                if(node.right!=null) q.add(node.right);
            }
            res.add(level);
        }
        return res;
    }
}



class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list= new ArrayList<List<Integer>>();
        treeLevel(root,list,0);       
        return list;
    }
    private void treeLevel(TreeNode root, List<List<Integer>> list, int level) {
        if(root != null){
            level++;
            if(list.size()<level){
                List<Integer> l = new ArrayList<Integer>();
                l.add(root.val);
                list.add(l);
            }
            else{
                list.get(level-1).add(root.val);
            }
            treeLevel(root.left,list,level);
            treeLevel(root.right,list,level);
        }
    }
}