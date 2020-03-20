/*96. Unique Binary Search Trees
链接: https://leetcode.com/problems/unique-binary-search-trees/
Medium:
Given n, how many structurally unique BST's (binary search trees) 
that store values 1 ... n?

Example:

Input: 3
Output: 5
Explanation:
Given n = 3, there are a total of 5 unique BST's:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

*/

/*解题思路: 这道题实际上是 卡塔兰数 Catalan Numbe 的一个例子
题目问的是，由1到n的所有数字的组合，总共可以构成多少种不一样的BST。其实这道题目
和输入的数字大小没有太大关系，只要是n个不同的数字从小到大排列好了就可以。

我们这道题用的是dp的思路，我们不需要真正的考虑每个树结构是什么，对于二叉树来说
只要考虑对于每个节点来说，比它小的数字都应该放在这个节点的左边，比这个节点大的节点
都放在节点的右边。只要我们知道每一边有多少个数字，那么不管这些数字具体是什么，我们
都可以通过dp来找到k个节点可以构成的所有BST总和。

                    1                        n = 1

                2        1                   n = 2
               /          \
              1            2
  
   1         3     3      2      1           n = 3
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3


就跟斐波那契数列一样，我们把 n = 0 时赋为1，因为空树也算一种二叉搜索树，那么 n = 1 
时的情况可以看做是其左子树个数乘以右子树的个数，左右子树都是空树，所以1乘1还是1。
那么 n = 2 时，由于1和2都可以为根，分别算出来，再把它们加起来即可。n = 2 的情况
可由下面式子算出（这里的 dp[i] 表示当有i个数字能组成的 BST 的个数）：

dp[2] =  dp[0] * dp[1]　　　(1为根的情况，则左子树一定不存在，右子树可以有一个数字)
　　　　+ dp[1] * dp[0]　　  (2为根的情况，则左子树可以有一个数字，右子树一定不存在)

同理可写出 n = 3 的计算方法：
dp[3] =   dp[0] * dp[2]　　　(1为根的情况，则左子树一定不存在，右子树可以有两个数字)
　　　 　+ dp[1] * dp[1]　　  (2为根的情况，则左右子树都可以各有一个数字)
 　　　  + dp[2] * dp[0]　　  (3为根的情况，则左子树可以有两个数字，右子树一定不存在)

*/


 class Solution {
    public int numTrees(int n) {

        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i=2; i<=n; i++){
            for(int j=0; j<i;j++){
            	//总共有i个元素的时候，此时计算的是左边j个元素可以构成的总和乘以右边(i-j-1)个
            	//元素可以构成的总和，之所以要减去1是因为根节点不属于左右子树
                dp[i] += (dp[j]*dp[i-j-1]);
            }
        }
        return dp[n];
    }
}

//3/17/2020 第二次写

class Solution {
    public int numTrees(int n) {
        int[] sizeOfTree = new int[n+1];
        sizeOfTree[0] =1;sizeOfTree[1] =1;

        for(int i=2; i<=n; i++){
            int numOfNode = 0;
            for(int j=0; j<i; j++){
                numOfNode += sizeOfTree[j]*sizeOfTree[i-j-1];
            }
            sizeOfTree[i] = numOfNode;
        }
        return sizeOfTree[n];
    }
}


