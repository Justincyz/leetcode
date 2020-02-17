/*199. Binary Tree Right Side View
链接：https://leetcode.com/problems/binary-tree-right-side-view/
Medium: 
Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example:

Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
*/

/*解题思路
这道题目最简单的解法就是使用树的层级遍历来做，然后把每一层的最后一个节点
放到我们要找的结果list当中。

考虑这样的case
   1            <---
 /   \
2     3         <---
 \     
  5            <---

结果是[1,3,5]
*/

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            while(size > 0){
                TreeNode t = q.poll();
                size--;
                if(size == 0) res.add(t.val);
                if(t.left!=null) q.add(t.left);
                if(t.right !=null) q.add(t.right);
            }
        }
        return res;
        
    }
}


/*
这种做法更加的聪明，每一次都先尽量往右边的分支上面去取得结果，如果右边没有的话
才考虑左边的分支。
*/

public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        rightView(root, result, 0);
        return result;
    }
    
    public void rightView(TreeNode curr, List<Integer> result, int currDepth){
        if(curr == null){
            return;
        }
        if(currDepth == result.size()){
            result.add(curr.val);
        }
        //先考虑右边的分支
        rightView(curr.right, result, currDepth + 1);
        rightView(curr.left, result, currDepth + 1);
        
    }
}