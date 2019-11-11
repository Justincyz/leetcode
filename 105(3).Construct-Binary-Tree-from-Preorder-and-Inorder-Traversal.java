/*105. Construct Binary Tree from Preorder and Inorder Traversal
Medium: 
Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
*/

/*解题思路 (懒得打了，直接贴了一个解释: https://www.cnblogs.com/grandyang/p/4296500.html)
这道题要求用先序和中序遍历来建立二叉树，跟之前那道Construct Binary Tree from Inorder 
and Postorder Traversal 由中序和后序遍历建立二叉树原理基本相同，针对这道题，由于先序的
顺序的第一个肯定是根，所以原二叉树的根节点可以知道，题目中给了一个很关键的条件就是树中没有相同
元素，有了这个条件我们就可以在中序遍历中也定位出根节点的位置，并以根节点的位置将中序遍历拆分为
左右两个部分，分别对其递归调用原函数。：

我们下面来看一个例子, 某一二叉树的中序和前序遍历分别为：

Preorder:　  　5　　4　　11　　8　　13　　9

Inorder:　　 　11　　4　　5　　13　　8　　9

 

5　　4　　11　　8　　13　　9　　　　　　=>　　　　　　　　　  5

11　　4　　5　　13　　8　　9　　　　　　　　　　　　　　　　/　　\

 

4　　11　　 　　8　　 13　　9　　　　　　=>　　　　　　　　5

11　　4　　　　 13　　8　　9　　 　　　　　　　　　　　　/　　\

　　　　　　　　　　　　　　　　　　　　　　　　　　　　　4　　　8

 

11　　　　 　　13　　　　9　　　　　　　　=>　　　　　　　　　5

11　　　　　　 13　　　　9　　　　 　　　　　　　　　     /　　\

　　　　　　　　　　　　　　　　　　　　　　　　　　　　　4　　  　8

　　　　　　　　　　　　　　　　　　　　　　　　　　　　/　　　 /     \

　　　　　　　　　　　　　　　　　　　　　　　　　　　11　　  13　　   9

*/

class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map < Integer, Integer > inMap = new HashMap < Integer, Integer > ();

        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        TreeNode root = buildTree(preorder, 0, preorder.length-1, inorder, 0, inorder.length - 1, inMap);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map < Integer, Integer > inMap) {
        if (preStart > preEnd || inStart > inEnd) return null;

        TreeNode root = new TreeNode(preorder[preStart]);
        int inRoot = inMap.get(root.val);
        int numsLeft = inRoot - inStart;//这里的位置是重点，第三次写的时候还是出现了失误

        root.left = buildTree(preorder, preStart + 1, preEnd, inorder, inStart, inRoot - 1, inMap);
        root.right = buildTree(preorder, preStart + numsLeft + 1, preEnd, inorder, inRoot + 1, inEnd, inMap);

        return root;
    }
}