/*1099. Two Sum Less Than K
链接：https://leetcode.com/problems/two-sum-less-than-k/
Easy: 
Given an array A of integers and integer K, return the maximum S such that there exists i < j with A[i] + A[j] = S and S < K. If no i, j exist satisfying this equation, return -1.

 

Example 1:

Input: A = [34,23,1,24,75,33,54,8], K = 60
Output: 58
Explanation: 
We can use 34 and 24 to sum 58 which is less than 60.
Example 2:

Input: A = [10,20,30], K = 15
Output: -1
Explanation: 
In this case it's not possible to get a pair sum less that 15.
*/

/*解题思路
题目让我们找两个数的和最接近于K却小于K。这道题目没办法在O(n)时间内解出来，
只能先sort然后用两个指针从头和尾部一步步来。

*/
//beats 100%
class Solution {
    public int twoSumLessThanK(int[] A, int K) {
        Arrays.sort(A);
        int s =0, e = A.length-1, sum =-1;
        
        while(s < e){
            if(A[s]+A[e] < K){
                sum = Math.max(sum , A[s]+A[e]);
                s++;
            }else{
                e--;
            }
        }
        return sum;
    }
}

//当然这道题也可以用treeset来解
class Solution {
    public int twoSumLessThanK(int[] A, int K) {
        TreeSet<Integer> set = new TreeSet<>();
        int ans = -1;
        for (int a : A) {
            Integer b = set.lower(K - a);
            if (b != null) {
                ans = Math.max(ans, a + b);
            }
            set.add(a);
        }
        
        return ans;
    }
}