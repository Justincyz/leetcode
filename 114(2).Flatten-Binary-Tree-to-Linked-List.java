/*114. Flatten Binary Tree to Linked List
链接：https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
Medium: 
Given a binary tree, flatten it to a linked list in-place.

For example, given the following tree:

    1
   / \
  2   5
 / \   \
3   4   6
The flattened tree should look like:

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
*/

/*解题思路
https://www.youtube.com/watch?v=LfKRZ_qCmYQ
这道题目先贴一个大神的做法。
我们从题目其实可以比较清楚地看出来，最后的链表(树)是利用前序遍历得到的，(根节点，左边子树，右边子树)。那么一个重要的问题来了，我们如何让根节点连接左子树第一个，且左子树的最后一个
连接上右子树的第一个呢?
那么我们不妨换一种思路来看，我们来搞一个反的前序遍历，先右边，然后左边，最后中间。
我们首先需要定义一个global node来储存每一次我们反方向遍历的上一位的节点。这样当前节点才好
粘上上一位节点。
顺序正好和前序遍历反过来，我们先右边，再左边，最后处理中间的根节点。
我们用上面的树举一个例子。
首先我们先找到最右边的点，此时prev = null.然后最右边的6的right = prev, 并且让left = null
这里一定要注意。然后将prev 赋值为root。此时prev = 6;
然后我们看5的左边，发现没有值，直接return null, 且prev的值并没有改变。
那此时 5.right = 6, 5.left = null, prev = 5;
对于根节点1来说，它的flatten(root.rigth)已经遍历完结了，现在开始flatten(root.left)。
那么我们在左子树中会先遇到4作为root，那么 4.right = 5, 4.left = null, prev = 4;
然后对于节点2来说，右边子树4已经遍历完了，此时遍历左子树。
3.right = 4, 3.left = null, prev = 3
然后现在到2了，
2.right = 3, 2.left = null (因为左边已经遍历完成，所以直接变成Null), prev = 2
最后是根节点1
1.right = 2, 1.left = null, prev = 1;
如果需要return 的话直接return 1就可以了

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
    TreeNode prev = null;
    public void flatten(TreeNode root) {
        if(root == null) return;
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }
}

//当然这个prev我们也可以带着走，没问题


class Solution { 
    public void flatten(TreeNode root) {
        flatten(root,null);
    }
    private TreeNode flatten(TreeNode root, TreeNode pre) {
        if(root==null) return pre; //返回的是Pre, 这个是重点。如果我们只看 2-3-4这个子树的话，当root为3时，必须要保证pre = 4这个值不会消失
        pre=flatten(root.right,pre);    
        pre=flatten(root.left,pre);
        root.right=pre;
        root.left=null;
        return root;
    }
}
/*
这里的pre指的是从下到上的前一位，比如，当root = 5的时候，pre = 6
*/