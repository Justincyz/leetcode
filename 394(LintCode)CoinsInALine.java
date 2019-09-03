/*394. Coins in a Line
Medium: https://www.lintcode.com/problem/coins-in-a-line/description
 
有 n 个硬币排成一条线。两个参赛者轮流从右边依次拿走 1 或 2 个硬币，直到没有硬
币为止。拿到最后一枚硬币的人获胜。

请判定 先手玩家 必胜还是必败?

若必胜, 返回 true, 否则返回 false.

Example
样例 1:
输入: 1
输出: true

样例 2:
输入: 4
输出: true
解释: 
先手玩家第一轮拿走一个硬币, 此时还剩三个.
这时无论后手玩家拿一个还是两个, 下一次先手玩家都可以把剩下的硬币拿完.
Challenge
O(1) 时间复杂度且O(1) 存储。
*/

/*解题思路 这道题目和leetcode 292: Nim Game基本一样

这道题目是在学习九章算法的时候看到的。具体解法可以去看视频讲解。
我们用一个长度为n+1的数组代表 1到n枚硬币。首先判断边界条件，如果一枚硬币
都没有的情况下，先手就输了。如果只有一枚硬币的情况下，先手拿走这一枚硬币，
然后就赢了，因为后手已经没有硬币可以拿走了。然后我们首先判断dp[1] = true，
也就是代表上面只有一枚硬币的情况。然后我们从两枚硬币开始，对于先手来说，如果
前两次都可以稳赢，那么当前第dp[i]位就一定不能赢，因为最多可以取一个或者两个
棋子，而前两次都稳赢了，那么当前就无法胜利了。dp[i]如果是true的话就说明先手在
这一位可以赢，如果是false的话就说明后手在这一位可以赢。

dp[n]表示n个石子，先手的人，是必胜还是必输。拿1个石子，2个石子之后都是必胜，则当前必败；拿1个石子，2个石子之后都是必败，则当前必胜；如果拿1个石子，2个石子之后有必败，则当前必胜。
*/

public class Solution {
    /**
     * @param n: An integer
     * @return: A boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int n) {
        
        if(n == 0) return false;
        if(n == 1) return true;
        
        boolean[] dp = new boolean[n+1];
        
        dp[1] = true;
       
        for(int i=2; i<=n; i++){
            dp[i] = (!dp[i-1] || !dp[i-2]);
        }
        return dp[n];
    }
}

/*
这里用了两个变量代替前面的两位元素
*/

public class Solution {
    public boolean firstWillWin(int n) {
        if(n == 0) return false;
        if(n == 1) return true;
 
        boolean a = true;
        boolean b = false;
       
        for(int i=2; i<=n; i++){
            boolean temp = false;
            temp = (!a || !b);
            b = a;
            a = temp;
        }
        
        return a;
    }
}

//这个解法就是用 NIM GAME的解法做的。 如果是三的倍数的话就一定无法赢，因为
//对手可以通过最优手段使得先手面对只有三颗棋子的状态。这样无论如何先手都无法胜利
public class Solution {
    public boolean firstWillWin(int n) {
        // write your code here
        return n%3 !=0;
    }
}