/*94. Binary Tree Inorder Traversal
链接：https://leetcode.com/problems/binary-tree-inorder-traversal/
Medium: 
Given a binary tree, return the inorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]
Follow up: Recursive solution is trivial, could you do it iteratively?
*/

/*解题思路
这道题目让我们in-order的顺序遍历二叉树，
跟这道题目类似的还有: 144. Binary Tree Preorder Traversal 和 145. Binary Tree Postorder Traversal。
都是一个系列的。Inorder就是先遍历根节点左子树所有节点，然后根节点，最后右子树节点。
循环的做法非常简单，我们主要来看下面迭代的做法。

*/

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorderRes = new ArrayList<>();
        recursiveHelper(root, inorderRes);
        return inorderRes;
    }
    
    public void recursiveHelper(TreeNode root, List<Integer> inorderRes){
        if(root == null) return;
        recursiveHelper(root.left, inorderRes);
        inorderRes.add(root.val);
        recursiveHelper(root.right, inorderRes);
    }
}


/**
iterative的做法
我们需要一个stack来储存遍历的节点。遍历的顺序是先走到根节点的最左边的节点。
储存当前节点，然后让root等于root.right。如果此时root.right不为空，那么
自然而然的我们就做的是inorder的遍历。如果root.rigth为空，那么第二次循环
的时候就不会进入嵌套的while loop, 而是pop上一次储存节点的父节点出来。
往后不记得的话可以很简单的画一个图就记得了。
 */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorderRes = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        
        while(!stack.isEmpty() || root !=null){
            while(root!=null){
                stack.push(root);
                root = root.left;
            }
            
            root = stack.pop();
            inorderRes.add(root.val);
            root = root.right;
        }
        return inorderRes;
    }
}