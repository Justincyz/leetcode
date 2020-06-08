/* 1008. Construct Binary Search Tree from Preorder Traversal
Medium: 
链接: https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
Return the root node of a binary search tree that matches the 
given preorder traversal.

(Recall that a binary search tree is a binary tree where for 
every node, any descendant of node.left has a value < node.val,
 and any descendant of node.right has a value > node.val.  
 Also recall that a preorder traversal displays the value of 
 the node first, then traverses node.left, then traverses node.right.)

 Example 1:
 input

       8
     /  \
    5   10
   / \    \
  1   7    12
[8,5,1,7,10,12]
Note:
1 <= preorder.length <= 100
The values of preorder are distinct.
*/

/*解题思路: 相似题目: 449. Serialize and Deserialize BST 等
这道题只给出了preorder的array让我们来构建BST。首先我们知道，BST中每个节点的左子树值会小于节点右子树的值。preorder是先储存根节点，然后是左边子树，然后是右边子树。所以一开始我们会沿着根节点一直顺着左边找到最小的值。注意，除了BST中最大的值(也即是最数组末尾的值)之外，所有的节点都处于大于前驱节点而小于后继节点的范畴。所以我们根据这个性质，在遍历的时候带入一个Prev数值。如果是遍历node左边节点的话，prev就是当前节点node的值，如果是遍历右子树节点的话，prev就是node.parent节点的值。任何一个prev不会大于下一位要遇到的值。如果prev大于下一位，说明我们已经到叶节点了。用上面的例子来看，当我们遍历到1时，我们指针pos指向数组的下一位，也就是7。1的左子节点因为preorder[pos] >= prev (在这里是 7>1)的原因，所以返回null，符合题意。1的右子节点因为 preorder[pos] >= prev (在这里是 7>5)的原因，也是返回null。所以我们成功的使得1的左右子节点都为空。接下来就要遍历1的前驱节点5的右子树那一边了。以此类推，如果我们遇到了叶节点，那么指针指向的下一位肯定是前驱节点的右子树。在代码里我们用一个global variable pos代表array中的指针。

TIME & SPACE: O(n)
*/

class Solution {
    public TreeNode bstFromPreorder(int[] preorder) {
        if(preorder.length ==0) return null;
        return helper( preorder, new int[]{0}, Integer.MAX_VALUE);
    }
    
    public TreeNode helper(int[] preorder, int[] pos, int prev){
        if(pos[0] >=preorder.length || preorder[pos[0]] >= prev) return null;
        
        TreeNode root = new TreeNode(preorder[pos[0]++]);
        root.left = helper(preorder, pos, root.val);
        root.right = helper(preorder, pos, prev);
        return root;
    }
}
//或者
class Solution {
    int idx = 0;
    public TreeNode bstFromPreorder(int[] preorder) {
        return construct(preorder, Integer.MAX_VALUE);
    }
    
    public TreeNode construct(int[] preorder, int parent){
        if(idx>=preorder.length || preorder[idx] > parent ) return null;
        TreeNode root = new TreeNode(preorder[idx]);
        idx++;
        root.left = construct(preorder, root.val);
        root.right = construct(preorder, parent);
        return root;
    }
}

/*这是一开始写的最简单的办法。我们知道树是通过pre-order的方式构造的，那么每一层的第一个数就是当前的根节点。然后在每一层找到一个分割左右子树的位置，带入下一层循环求解。

TIME O(N^2)
SPACE O(N)
*/
class Solution {
    public TreeNode bstFromPreorder(int[] preorder) {
        return construct(preorder, 0, preorder.length-1);
    }
    
    public TreeNode construct(int[] preorder, int left, int right){
        if(left > right) return null;
        
        TreeNode root = new TreeNode(preorder[left]);
        int mid = left+1;
        for(; mid<=right; mid++){
            if(preorder[mid] > root.val){
                break;
            }
        }
       
        root.left = construct(preorder, left+1, mid-1);
        root.right = construct(preorder, mid, right);
        
        return root;
    }
}