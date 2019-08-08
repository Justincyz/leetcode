/*276. Paint Fence
Easy
There is a fence with n posts, each post can be painted with 
one of the k colors.

You have to paint all the posts such that no more than two 
adjacent fence posts have the same color.
Return the total number of ways you can paint the fence.

Note:
n and k are non-negative integers.

Example:
Input: n = 3, k = 2
Output: 6
Explanation: Take c1 as color 1, c2 as color 2. All possible ways are:

            post1  post2  post3      
 -----      -----  -----  -----       
   1         c1     c1     c2 
   2         c1     c2     c1 
   3         c1     c2     c2 
   4         c2     c1     c1  
   5         c2     c1     c2
   6         c2     c2     c1
*/

/*解法
这道题虽然是一个easy的题目，但是突然一下还是蛮难看出来dp的递归式的。对于两个
连续的post 来说，第一个有k种选择。如果第二个和第一个颜色一样的话，那就是
1种选择。如果第二个和第一个颜色不一样的话，那么有k-1个选择。那么在第二位
的时候，总共的选择数目就有(k*1+k*(k-1))。for循环从第三个位置开始。
如果当前位要选择和上一位不一样的颜色，此时如果前面两位原来的颜色一样，那么
就有k-1种解法。如果前两个颜色不一样，那么为了和上一种颜色不一样，还是有
k-1种解法。所以可以合并。第二种情况是如果当前的要和上一位的选择同一样的颜色，
那么直接等于上一次preDiff的颜色组合(其实应该是preDiff*1)。最后把两种组合
相加返回值
*/

class Solution {
    public int numWays(int n, int k) {
        if(n==0) return 0;
        if(n==1) return k;
        int preSame = k*1;
        int preDiff = k*(k-1);
        
        for(int i=3;i<=n; i++){//注意这里的i=3
            int diff = preDiff;
            preDiff = (preDiff+preSame)*(k-1);
            preSame = diff;
        }
        
        return preSame+preDiff;
    }
}


