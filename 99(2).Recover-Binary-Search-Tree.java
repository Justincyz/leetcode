/*99. Recover Binary Search Tree
Hard
链接: https://leetcode.com/problems/recover-binary-search-tree/

Two elements of a binary search tree (BST) are swapped by mistake.
Recover the tree without changing its structure.

Example 1:
Input: [1,3,null,null,2]

   1
  /
 3
  \
   2

Output: [3,1,null,null,2]

   3
  /
 1
  \
   2
   
Example 2:
Input: [3,1,4,null,null,2]

  3
 / \
1   4
   /
  2

Output: [2,1,4,null,null,3]

  2
 / \
1   4
   /
  3

Follow up:
A solution using O(n) space is pretty straight forward.
Could you devise a constant space solution?
*/

/*解题思路
题目告诉我们，有一个BST当中有两个元素的位置被调换了。让我们把这两个元素交换
回原来的位置。这道题目最简单的办法就跟easy level的差不多。用in-order的办法
把元素储存起来，再sort。最后输出正确的结果。

*/

class Solution {
    public void recoverTree(TreeNode root) {
        List<Integer> l1 = new ArrayList<>();
        List<TreeNode> l2 = new ArrayList<>();
        helper(l1, l2, root);
        Collections.sort(l1);
        for(int i=0; i<l1.size(); i++){
            if(l1.get(i) != l2.get(i).val){
                l2.get(i).val = l1.get(i);
            }
        }
        
    }
    
    public void helper(List<Integer> l1, List<TreeNode> l2, TreeNode root){
        if(root == null) return;
        helper(l1,l2, root.left);
        l1.add(root.val);
        l2.add(root);
        helper(l1,l2, root.right);
    }
}

/*
最符合题意的就是用morris Tree来做。
可以参考94: Binary Tree Inorder Traversal. 题我自己对morris tree的方法摘要

这道题的真正符合要求的解法应该用的 Morris 遍历，这是一种非递归且不使
用栈，
空间复杂度为 O(1) 的遍历方法。在其基础上做些修改，加入 first, second 和 
parent 指针，来比较当前节点值和中序遍历的前一节点值的大小，跟上面递归算法的思路相似，代码如下：
*/

class Solution {
    public void recoverTree(TreeNode root) {
        TreeNode pre = null;
        TreeNode first = null, second = null;
        // Morris Traversal
        TreeNode temp = null;
        while(root!=null){
          if(root.left!=null){
            // connect threading for root
            temp = root.left;
            while(temp.right!=null && temp.right != root)
              temp = temp.right;
            // the threading already exists
            if(temp.right!=null){
                if(pre!=null && pre.val > root.val){
                    if(first==null){first = pre;second = root;}
                    else{second = root;}
                }
                pre = root;
                temp.right = null;
                root = root.right;
            }else{
              // construct the threading
              temp.right = root;
              root = root.left;
            }
          }else{
            if(pre!=null && pre.val > root.val){
                if(first==null){
                  first = pre;second = root;
                }
                else{
                  second = root;
                }
            }
            pre = root;
            root = root.right;
          }
        }
        // swap two node values;
        if(first!= null && second != null){
            int t = first.val;
            first.val = second.val;
            second.val = t;
        }
  }
}