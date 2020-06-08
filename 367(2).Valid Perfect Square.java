/*367. Valid Perfect Square
Easy
链接: https://leetcode.com/problems/valid-perfect-square/
Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.

Example 1:
Input: 16
Output: true

Example 2:
Input: 14
Output: false
*/


/*解题思路
这道题目让我们找到某一个数是不是完全平方数(好像是这么叫的)。不允许用build-in的 sqrt()类似的方法。
最简单的办法就是用二分法来做。起始的范围是[0, num/2]。为什么是num/2,是因为某个数的一半的平方肯定是大于这个数字的。
这里记得要用Long来记录low和high.否则的话Integer无法存下某一些数字的平方。最后就是简单地二分法就可以了。

*/

class Solution {
    public boolean isPerfectSquare(int num) {
        if(num == 1) return true;
        Long low = 0L, high = Long.valueOf(num/2);
        while(low < high){
            Long mid = low+(high-low)/2;

            if(mid*mid >= num){
                high = mid;
            }else{
                low = mid+1;
            }
        }
 
        return low*low == num;
    }
}