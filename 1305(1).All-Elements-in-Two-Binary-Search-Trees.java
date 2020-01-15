/*1305. All Elements in Two Binary Search Trees
Medium
链接: https://leetcode.com/problems/all-elements-in-two-binary-search-trees/
Given two binary search trees root1 and root2.

Return a list containing all the integers from both 
trees sorted in ascending order.

Example 1:
    2         1
   / \       /  \
  1   4     0    3

Input: root1 = [2,1,4], root2 = [1,0,3]
Output: [0,1,1,2,3,4]

Example 2:
Input: root1 = [0,-10,10], root2 = [5,1,7,0,2]
Output: [-10,0,0,1,2,5,7,10]

Example 3:
Input: root1 = [], root2 = [5,1,7,0,2]
Output: [0,1,2,5,7]

Example 4:
Input: root1 = [0,-10,10], root2 = []
Output: [-10,0,10]
*/

/*解题思路
蛮简单的一道题目。给出两个BST，让我们将两个树里面的值合并到一个List当中，
list里面的元素保持升序的状态。一开始想的是在遍历的过程中进行比对添加。实际上
根本不用这么复杂，对BST做一个In-order traverse就好了。然后合并两个已经
排列好的list。

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
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = new ArrayList<>(), l2 = new ArrayList<>(), res = new ArrayList<>();
        helper(root1, l1);
        helper(root2, l2);
        int p1=0, p2 = 0;
        while(p1<l1.size() || p2 < l2.size()){
            if(p1<l1.size() && p2<l2.size()){
                if(l1.get(p1) > l2.get(p2)){
                    res.add(l2.get(p2++));
                }else{
                    res.add(l1.get(p1++));
                }
            }else if(p1 < l1.size()){
                res.add(l1.get(p1++));
            }else{
                res.add(l2.get(p2++));
            }
        }
        return res;
    }
    
    /*一开始写复杂了，直接用In-order traverse就好，因为已经告诉我们了
    一定是BST*/
    public void helper(TreeNode root, List<Integer> list){
        Stack<TreeNode> s = new Stack();
        
        while(root!=null || !s.isEmpty()){
            while(root!=null){
                s.push(root);
                root = root.left;
            }
            
            root = s.pop();
            list.add(root.val);
            root = root.right;
        }
    }
}



/*

 */
class Solution {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = new ArrayList<>(), l2 = new ArrayList<>(), res = new ArrayList<>();
        helper(root1, l1);
        helper(root2, l2);
        int p1=0, p2 = 0;
        while(p1<l1.size() || p2 < l2.size()){
            if(p1<l1.size() && p2<l2.size()){
                if(l1.get(p1) > l2.get(p2)){
                    res.add(l2.get(p2++));
                }else{
                    res.add(l1.get(p1++));
                }
            }else if(p1 < l1.size()){
                res.add(l1.get(p1++));
            }else{
                res.add(l2.get(p2++));
            }
        }
        return res;
    }
    
    public void helper(TreeNode root, List<Integer> list){
        if(root == null) return;
        helper(root.left, list);
        list.add(root.val);
        helper(root.right, list);
    }
}