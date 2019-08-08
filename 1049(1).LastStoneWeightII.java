/*1049 Last Stone Weight II    
Medium: 
We have a collection of rocks, each rock has a positive integer weight.

Each turn, we choose any two rocks and smash them together.  Suppose the 
stones have weights x and y with x <= y.  The result of this smash is:

If x == y, both stones are totally destroyed;
If x != y, the stone of weight x is totally destroyed, and the stone of 
weight y has new weight y-x.
At the end, there is at most 1 stone left.  Return the smallest possible 
weight of this stone (the weight is 0 if there are no stones left.)


Example 1:
Input: [2,7,4,1,8,1]
Output: 1
Explanation: 
We can combine 2 and 4 to get 2 so the array converts to [2,7,1,8,1] then,
we can combine 7 and 8 to get 1 so the array converts to [2,1,1,1] then,
we can combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
we can combine 1 and 1 to get 0 so the array converts to [1] then that's the optimal value.

*/

/*解题思路
题目大意是说，现在有一堆石头，每个石头有自己的重量。两个石头发生碰撞时，如果两个石头的
重量不一样，那么较小的那个石头会粉碎，较大的那个石头重量会减小小石头那么重。如果是两个
石头重量一样的话，那么直接两个都粉碎。那么两两相碰的话最后会剩下0个或者一个，问最后剩下
的石头可能的最小值。
首先最简单的解法可以参见解法二。我们每一次往后遍历石头的时候，其实无非就是不砸这个石头或者
砸这个石头。假设石头重量是 w的话，那么我们遍历前一次的hashset,对之前所有石头重量组合的
可能性+w 和 -w。然后把新的状态储存在一个新的hashset里面。最后当循环结束的时候。我们
在最后一个hashset中找到一个绝对值最小的值。这种解法虽然可以过，但是最后空间和时间可能是指数
形式的增长(2^1+2^2+2^3+...+ 2^n)。所以我们可以用dp来解决这个问题。

其实这道题目和494: Target Sum 如出一辙。
假设所有石头的总质量是sum的话，如果要让所有石块碰撞之后结果越接近于0越好，相当于就是找到这么
一组石头，让他们的质量总和接近于 sum/2。这样碰撞完之后才能让总和越小。
那么我们就可以借助dp来做。dp[i][j]指的是sum为i的情况下，到石头j时可否累加到i。


第二个对数组做了优化，我们首先找到在(0, sum)之内所有可以组合的sum。注意每一次都要
从后往前走，这样不会重复。然后再用一个loop 从 1/sum的地方开始往0循环。遇到最大的
值之后直接return sum-2*i


*/

//4ms
class Solution {
    public int lastStoneWeightII(int[] stones) {
        if(stones.length ==0) return 0;
        int sum =0, res = Integer.MIN_VALUE, n = stones.length;
        for(int s: stones) sum+=s;
        int sum2 = sum/2;
        boolean[][] dp = new boolean[sum2+1][n+1];
        
        for(int i =0; i<=n; i++) dp[0][i] = true;
        
        for(int s=1; s<=sum2; s++){
            for(int j=1; j<=n; j++){
                /*
                如果之前的累加已经可以累加到当前的sum, 或者可以累加到
                s-stones[j-1],所以加上当前石块就可以够到s
                */
                if(dp[s][j-1] || (s>=stones[j-1] && dp[s - stones[j-1]][j-1] )){
                    dp[s][j] = true;
                    res = Math.max(res, s);
                }
            }
        }
        
        return sum - 2*res;
    }
}

//1ms
class Solution {
    public int lastStoneWeightII(int[] stones) {
        int sum =0;
        for(int s: stones) sum+=s;
        boolean[] dp = new boolean[sum+1];
        
        dp[0] = true;
        int sumA = 0;
        for (int a : stones) {
            sumA += a;
            for (int i = sumA; i >= a; --i)
                dp[i] = (dp[i] ||dp[i - a]);
        }
        for (int i = sumA / 2; i > 0; --i)
            if (dp[i]) return sumA - i - i;
        return 0;
    }
}



//intuitive solution, 11ms
class Solution {
    public int lastStoneWeightII(int[] stones) {
        if(stones.length ==0) return 0;
        Arrays.sort(stones);
        Set<Integer> set = new HashSet<>();
        set.add(stones[0]);
        set.add(-stones[0]);
        for(int i=1; i<stones.length; i++){
            Set<Integer> temp = new HashSet<>();
            for(int s: set){
                temp.add(s+stones[i]);
                temp.add(s-stones[i]);
            }
            set = temp;
        }
        int res = Integer.MAX_VALUE;
        for(int s: set) res = Math.min(Math.abs(s), res);
        
        
        return res;
    }
}