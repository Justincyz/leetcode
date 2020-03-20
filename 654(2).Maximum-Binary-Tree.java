/*654. Maximum Binary Tree
链接：https://leetcode.com/problems/maximum-binary-tree/
Medium: 
Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

The root is the maximum number in the array.
The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
Construct the maximum tree by the given array and output the root node of this tree.

Example 1:
Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:

      6
    /   \
   3     5
    \    / 
     2  0   
       \
        1
Note:
The size of the given array will be in the range [1,1000].
*/

/*解题思路
题目大意是，有一个整型数组，让我们建立一棵树。树中每个节点都比这个节点往下的子树
中所有元素都大。同时，我们以当前根节点的位置作为左右的子树区分点，然后继续往下
建立子树。比如上面的例子，根节点中6是最大的值，然后以6为分界，再左右构造子树。
最简单的办法就是我们top-down构建树，每一层都用O(N)的时间找到若干个最大节点。
总共有O(logn)层，所以总的时间是O(nlogn)。这个方法可以beats 92%。

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
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return arrayPartition(nums, 0, nums.length-1);
    }
    
    public TreeNode arrayPartition(int[] nums, int s, int e){
        if(s > e) return null;
    
        int curRoot =  nums[s], pos = s;
        for(int i=s+1; i<=e; i++){
            if(nums[i] > curRoot){
                curRoot = nums[i];
                pos = i;
            }    
        }  
        
        TreeNode node = new TreeNode(curRoot);
        node.left = arrayPartition(nums, s, pos-1);
        node.right = arrayPartition(nums, pos+1, e);
        return node;
    }
}


/*
这个是amortized O(n)的做法，保证每一个元素最多入栈一次，出栈一次。
我们遍历数组，对于每个遍历到的数字，创建一个结点，然后进行循环，如果linkedlist q不空，
且末尾结点值小于当前数字，那么将末尾结点连到当前结点的左子结点，并且移除数组中的末尾结
点，这样可以保证子结点都会小于父结点。循环结束后，如果此时linkedlist q仍不为空，说明结
点值很大，那么将当前结点连到数组末尾结点的右子结点上。之后别忘了将当前结点加入数组v中，
最后返回linkedlist 
q的首结点即可，如果不太容易理解的话，就把题目中的例子带入一步一步运行看一下吧

利用了数组的顺序性
*/


class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        Deque<TreeNode> q = new LinkedList<>();
        for(int num: nums){
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