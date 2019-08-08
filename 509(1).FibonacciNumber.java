/*509. Fibonacci Number
Easy: 
The Fibonacci numbers, commonly denoted F(n) form a sequence, 
called the Fibonacci sequence, such that each number is the sum of 
the two preceding ones, starting from 0 and 1. That is,

F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), for N > 1.
Given N, calculate F(N).

Example 1:
Input: 2
Output: 1
Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.

Example 2:
Input: 3
Output: 2
Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
*/

/*解题思路
很基础的Fibonacci数列，注意当N =0和 N=1时就好了

*/

class Solution {
    public int fib(int N) {
        if(N== 0) return 0;
        if(N == 1) return 1;
        int p1 = 0, p2 = 1;
        N-=2;
        while(N-- >0){
            int cur = p1+p2;
            p1 = p2;
            p2 = cur;
        }
        return p1+p2;
    }
}