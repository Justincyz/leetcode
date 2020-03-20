/*120. Triangle
链接：https://leetcode.com/problems/triangle/
Medium: 
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:

Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
*/

/*解题思路 dp
题目给出了一个金字塔形状的List组合，每一层都比上一层多出一位数。
让我们找到从第一层到最后一层和为最小的路径。这个路径的设定是这样的，
对于每一层位置i, 我们只能取上一层的第i-1位或者第i位的值。
用例子来说的话，第二层的[3,4]对应的是上一层的[2],
再下面一层的，6对应上一层[3], 5对应上一层[3,4], 7对应上一层[4]

题目让我们用O(n)的space, 实际上最简单的也就是O(n)的空间。
我们维持一个长度为triangle.size()的数组(事实上是最底层长度的数组)。
每一层我们的每个位置对应上一层对应的两个位置i-1和i，然后找到这两个
当中的较小值，更新dp[i]的位置。注意，每一行最后的一位特殊处理，因为
是新加入的，只能取到对应位i-1的值。

*/

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        int res = Integer.MAX_VALUE;
        for(List<Integer> level: triangle){
            int size = level.size();
            int prev = 0;
            for(int i=0; i< size-1; i++){
                int temp = dp[i];
                dp[i]+=level.get(i);
                //当i =0时无法取到-1的数值
                dp[i] = i == 0? dp[i] : Math.min(dp[i], prev+level.get(i));
                prev = temp;
            }
            //每一行最后新加的一位特殊处理
            dp[size-1] = level.get(size-1)+prev;
           
        }
        for(int num: dp)  res = Math.min(res, num);

        return res;
    }
}

//同样，从下到上也可以，而且更加简单
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.size() == 0) return 0;
        
        int n = triangle.size();
        int[] dp = new int[n + 1];
        
        for(int i = n - 1; i >= 0; i--){
            for(int j = 0; j < triangle.get(i).size(); j++){
                dp[j] = Math.min(dp[j], dp[j+1]) + triangle.get(i).get(j);
            }
        }
        
        return dp[0];
    }
}
