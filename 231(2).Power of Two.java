/*231. Power of Two
Easy
链接: https://leetcode.com/problems/power-of-two/

Given an integer, write a function to determine if it is a power of two.

Example 1:
Input: 1
Output: true 
Explanation: 20 = 1

Example 2:
Input: 16
Output: true
Explanation: 24 = 16
Example 3:

Input: 218
Output: false

*/



/*解题思路
这道题给我们一个数字，让我们判断这个数是不是2的次方。
第一种方法时最简单的，我们循环除以2来判断这个数是不是可以被2整数。如果最后到了1的话，说明这个数是2的次方。如果在到达1之前就发现不能够被2整除的话，直接返回false

时间复杂度 O(logn)
*/

class Solution {
    public boolean isPowerOfTwo(int n) {
        if(n == 1) return true;
        if(n == 0) return false;
        if(n != 1 && n %2 != 0) return false;
        return isPowerOfTwo(n/2);
    }
}

/*
这道题还有一个O(1)的做法，真的很神奇。如果某一个数n是2的次方的话。那么(n-1)的值和n做一个 & 操作就会是0
*/

class Solution {
    public boolean isPowerOfTwo(int n){
        System.out.println( 4 & 3);
        return n > 0 && ((n & (n-1)) == 0);//只要是2的n次方，那么n = xx10000, n-1 = xxx1111这样的，做与运算之后会为0
    }
}