/*437. Path Sum III
链接：https://leetcode.com/problems/path-sum-iii/
Easy: 
You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11
*/

/*解题思路  backtrack + preSum
这道题目很有意思，题目给出一个树的根节点和一个sum。我们可以从某一个节点出发向下往这个节点的子节点前进获取path sum. 问这样的path sum总共有几条。例子当中其实就很好的体现了这个问题。
这道题目一开始我想的是穷举所有的例子，但是实际上转念一想根本没有必要。这这道题其实就是
subarray sum to k这种思想的延伸。原来是Subarray, 只不过现在每一个path我们可以都把它当做
一个array来看，然后用pre-sum + backtracking的办法来获取这棵树的所有path sum.
一开始我会担心别的树枝上面的和会不会影响到当前的path, 所以backtrack的时候我会从
Map当中删除掉value为0的pre-sum. 但是后来发现其实没有影响，因为
即使map.containsKey(preSum - sum) 这个key还存在， value已经为0了。

时间复杂度O(N),空间复杂度取决于sum的个数有多少。



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
    int total =0;
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        helper(root, 0,sum, map);
        return total;
    }
    
    public void helper(TreeNode root, int preSum, int sum, Map<Integer, Integer> map){
        if(root == null) return;
        preSum+=root.val;
        if(map.containsKey(preSum - sum)){
            total+= map.get(preSum - sum);
        }
        map.put(preSum, map.getOrDefault(preSum, 0)+1);
        helper(root.left, preSum, sum, map);
        helper(root.right, preSum, sum, map);
        /*我一开始在考虑要不要把为0的结果从map当中拿掉。因为怕不同的树枝
        直接相互影响。实际上不需要，因为
        首先一进一出不可能总数为负数。其次我们将相对应值已经降为0了，所以
        total也只是加了0而已。
        */
        map.put(preSum, map.get(preSum)-1);
    }
}