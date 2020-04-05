/*1302. Deepest Leaves Sum
链接：
Medium: 
Given a binary tree, return the sum of values of its deepest leaves.
 
Example 1:

     1
    / \
   2   3
  / \   \
 4   5   6
/         \
7          8

Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
Output: 15

Constraints:
The number of nodes in the tree is between 1 and 10^4.
The value of nodes is between 1 and 100.
*/

/*解题思路
题目的意思就是让我们计算最深一排leaf的总和是多少，比如上面的例子就是7+8 = 15

最简单的解法就是层级遍历，用一个sum变量持续(!)更新每一行的总和，这样当最后没有
层级的时候，Sum就储存的是最后也是最深一行的和了。

*/
class Solution {
    public int deepestLeavesSum(TreeNode root) {
        if(root == null) return 0;
        Queue<TreeNode>  curLevel = new LinkedList<>();
        curLevel.add(root);
        int sum = root.val;
        
        while(!curLevel.isEmpty()){
            sum = 0;
            int size = curLevel.size();
            while(size -- > 0){
                TreeNode curLevelNode = curLevel.poll();
                sum+= curLevelNode.val;
                if(curLevelNode.left != null) curLevel.add(curLevelNode.left);
                if(curLevelNode.right != null) curLevel.add(curLevelNode.right);
            }        
        }

        return sum;
    }
}
/*
还有一种方法是记录pre-order遍历当中，每一个元素的深度，如果目前visit 节点的深度
大于我们此前遍历节点的最大深度，就更新一下sum (先让sum = 0, 再加上当前元素的值)。
这样我们最后sum保存的就是最大深度所有元素的总和了。
*/

class Solution {
    int sum =0, deepest = 0;
    public int deepestLeavesSum(TreeNode root) {
        if(root == null) return 0;
        dfs(root, 0);
        return sum;
    }
    
    public void dfs(TreeNode root, int deep){
        if(root == null) return;
        
        if(deep == deepest){
            sum+= root.val;
        }
        if(deep > deepest){
            deepest = deep;
            sum = 0;
            sum+=root.val;
        }
        dfs(root.left, deep+1);
        dfs(root.right, deep+1);
    }
}