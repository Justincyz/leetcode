/*998. Maximum Binary Tree II
链接：https://leetcode.com/problems/maximum-binary-tree-ii/
Medium: 
We are given the root node of a maximum tree: a tree where every node has a value greater than any other value in its subtree.

Just as in the previous problem(654.Maxmimum Binary Tree), 
the given tree was constructed from an list A (root = Construct(A)) recursively with the following Construct(A) routine:

If A is empty, return null.
Otherwise, let A[i] be the largest element of A.  Create a root node with value A[i].
The left child of root will be Construct([A[0], A[1], ..., A[i-1]])
The right child of root will be Construct([A[i+1], A[i+2], ..., A[A.length - 1]])
Return root.
Note that we were not given A directly, only a root node root = Construct(A).

Suppose B is a copy of A with the value val appended to it.  It is guaranteed that B has unique values.

Return Construct(B).

Example 1:
Input: root = [4,1,3,null,null,2], val = 5
Output: [5,4,null,1,3,null,null,2]
Explanation: A = [1,4,2,3], B = [1,4,2,3,5]

Example 2:
Input: root = [5,2,4,null,1], val = 3
Output: [5,2,4,null,1,null,3]
Explanation: A = [2,1,5,4], B = [2,1,5,4,3]

Example 3:
Input: root = [5,2,3,null,1], val = 4
Output: [5,2,4,null,1,3]
Explanation: A = [2,1,5,3], B = [2,1,5,3,4]
*/

/*解题思路
这道题目一定要结合着题目当中提到的previous problem来看。否则容易看不懂。
previous problem说的是这样的情况。(有一个整型数组，让我们建立一棵树。树中每个节点都比这个节点往下的子树
中所有元素都大。同时，我们以当前根节点的位置作为左右的子树区分点，然后继续往下
建立子树。)
题目在这个数组基础上增加了一个数字，告诉我们这个数字会被附在原数组的最后面，意味
着这个新增节点的位置会是最右端的节点。
最简单的办法就是先用in-order的办法遍历一次树，然后将新的加点加在这个List的
最后，然后用和654.Maxmimum Binary Tree一模一样的办法构建这棵树。
因为也是O(N)的时间，所以也会比较快。但是这里用了O(N)的空间复杂度，我们
还可以进一步优化

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
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        List<Integer> inorder = new ArrayList<>();
        inorderTraverse(root, inorder);
        appendNewNode(inorder, val);
        return buildResultTree(inorder);
    }
    
    public void inorderTraverse(TreeNode root, List<Integer> inorder){
        if(root == null) return;
        inorderTraverse(root.left, inorder);
        inorder.add(root.val);
        inorderTraverse(root.right, inorder);
    }
    
    public void appendNewNode(List<Integer> inorder, int newVal){
        inorder.add(newVal);
    }
    
    public TreeNode buildResultTree(List<Integer> inorder){
       Deque<TreeNode> q = new LinkedList<>();
        for(Integer num: inorder){
            TreeNode cur = new TreeNode(num);
            while(!q.isEmpty() && q.getLast().val < num){
                cur.left = q.pollLast();
            }
            
            if(!q.isEmpty()){
                q.peekLast().right = cur;
            }
            q.addLast(cur);
        }
        
        return q.isEmpty()? null : q.peekFirst();
    }
}



/**
这个代码可以缩的这么短。
注意两个点，第一个是新加入的节点一定是left child是老节点，原因是新插入的节点
在数组的最后面，所以之前的节点都是在他的左边。
然后就是root.right 是子树返还上来的节点。如果子树结构不变，那么root.right
的值就不会改变。如果子树当中被插入了新的节点，那么一定也是当前节点的右边。
 */
class Solution {
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if(root == null) return new TreeNode(val);
        
        if(root.val < val){
            TreeNode node = new TreeNode(val);
            node.left = root;
            return node;
        }
        root.right = insertIntoMaxTree(root.right, val);
        return root;
    }   
}