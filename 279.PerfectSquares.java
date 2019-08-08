/*
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Example 1:

Input: n = 12
Output: 3 
Explanation: 12 = 4 + 4 + 4.
Example 2:

Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
*/

class Solution {
    public int numSquares(int n) {
        int[] table = new int[n+1];
        Arrays.fill(table, Integer.MAX_VALUE);
        /* cntPerfectSquares[i] = the least number of perfect square numbers which sum to i. Note that cntPerfectSquares[0] is 0.*/
        
        table[0] =0;
        for(int i=1; i<=n;i++){ 
            for(int j=1; j*j<=i;j++){
                table[i] = Math.min(table[i], table[i-j*j]+1);//如果 j平方小于i, 那么看到达i-j^2那一步最小的步数
            }
        }
        return table[n];
    }
}


    