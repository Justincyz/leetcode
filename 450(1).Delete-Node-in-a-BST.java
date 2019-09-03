/*450. Delete Node in a BST
链接：https://leetcode.com/problems/delete-node-in-a-bst/
Medium: 
Given a root node reference of a BST and a key, delete the node with 
the given key in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

Search for a node to remove.
If the node is found, delete the node.
Note: Time complexity should be O(height of tree).

Example:

root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

Given key to delete is 3. So we find the node with value 3 and delete it.

One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

    5
   / \
  4   6
 /     \
2       7

Another valid answer is [5,2,6,null,4,null,7].

    5
   / \
  2   6
   \   \
    4   7
*/

/*解题思路
这道题让我们删除二叉搜索树中的一个节点，这道题的难点在于删除完节点并补上那个节点的位置后还应该是一棵二叉搜索树。被删除掉的节点位置，不一定是由其的左右子节点补上，比如下面这棵树：

         7
        / \
       4   8
     /   \   
    2     6
     \   /
      3 5

如果我们要删除节点4，那么应该将节点5补到4的位置，这样才能保证还是BST，那么结果是如下这棵树：

         7
        / \
       5   8
     /   \   
    2     6
     \   
      3

首先判断根节点是否为空。由于BST的左<根<右的性质，使得我们可以快速定位到要删除的节点，我们对于当前节点值不等于key的情况，根据大小关系对其左右子节点分别调用递归函数。若当前节点就是要删除的节点，我们首先判断是否有一个子节点不存在，是的话那么我们就将root变成那个存在的子节点(相当于就删除了当前节点)，如果root是leaf node的话，也就是左右子节点都不存在，那么root就赋值为空了。难点就在于处理左右子节点都存在的情况，我们需要在右子树找到最小值，即右子树中最左下方的节点，然后将该最小值赋值给root，然后再在右子树中调用递归函数来删除这个值最小的节点。
我们其实不算是删除了这个节点，而是将下一位比当前大的值赋给了当前值而已。

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
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;
        if(root.val == key){

            if(root.right == null || root.left == null){
            	//特殊情况就是左右都为空，那么root就等于Null
                root = root.left == null? root.right : root.left;
            }else{
                TreeNode temp = root.right;
                while(temp.left!=null) temp = temp.left;
                root.val = temp.val;
                root.right = deleteNode(root.right, temp.val);
            }
        }else if(root.val > key){
            root.left = deleteNode(root.left, key);
        }else{
            root.right = deleteNode(root.right, key);
        }
        return root;
    }
}