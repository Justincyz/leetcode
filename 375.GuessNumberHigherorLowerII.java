/*
We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked.

Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.

However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.

Example:

n = 10, I pick 8.

First round:  You guess 5, I tell you that it's higher. You pay $5.
Second round: You guess 7, I tell you that it's higher. You pay $7.
Third round:  You guess 9, I tell you that it's lower. You pay $9.

Game over. 8 is the number I picked.

You end up paying $5 + $7 + $9 = $21.
Given a particular n ≥ 1, 
find out how much money you need to have to guarantee a win.

*/

//dp[i][j] is the minimal cost to guess from range(i...j).
//第一种比较直观
public class Solution {
    public int getMoneyAmount(int n) {
        int[][] table = new int[n+1][n+1];
        return DP(table, 1, n);
    }
    
    int DP(int[][] t, int s, int e){
        if(s >= e) return 0;
        if(t[s][e] != 0) return t[s][e];
        int res = Integer.MAX_VALUE;
        /*这个for loop指的是从start到end这个范围内，在保证可以取到在s到e的范围需要的钱的前提下，
		花费最少的钱。这个值可能存在在x的左边或者右边，所以需要在两边之间选择较大的那一边以保证可以
		覆盖往左走或者往右走的开销*/
        for(int x=s; x<=e; x++){
            int tmp = x + Math.max(DP(t, s, x-1), DP(t, x+1, e));
            res = Math.min(res, tmp);
        }
        t[s][e] = res;
        return res;
    }
}


class Solution {
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n+1][n+1];

        for(int j=2; j<=n; j++){ //这种遍历的方法有点诡异，但是的确可以cover住每一个取值范围的所有组合
        	for(int i=j-1; i>0;i--){
        		int rangeMin = Integer.MAX_VALUE;
        		//在已经保证最大金钱的前提小找到最小的那一个
        		for(int k=i+1; k<j; k++){
        			//找到在i到j的范围内随意访问第k个值的需要的最大可能金钱
        			int localMax = k+Math.max(dp[i][k-1], dp[k+1][j]);
        			rangeMin = rangeMin > localMax ? localMax: rangeMin;
        		}
        		dp[i][j] = i+1==j? i : rangeMin;
        	}
        }
        return dp[1][n];
    }
}