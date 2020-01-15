/*69. Sqrt(x)
Easy
链接: 
Implement int sqrt(int x).

Compute and return the square root of x, where x is guaranteed to be a 
non-negative integer.

Since the return type is an integer, the decimal digits are truncated 
and only the integer part of the result is returned.

Example 1:
Input: 4
Output: 2

Example 2:
Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since 
             the decimal part is truncated, 2 is returned.



*/

/*解题思路
一开始我想的就是第二种办法，但是速度实在太慢了，要84ms。后来想到
其实这道题可以用二分法来做啊，于是就有了第一种方法，只要1ms

*/

class Solution {
    public int mySqrt(int x) {
        if(x == 0) return 0;
        long low = 0, high = x;
        while(low < high){
            long mid = low+(high-low)/2;
            if(mid* mid == x) return (int)mid;
            else if(mid*mid > x){
                high = mid;
            }else{
                low = mid+1;
            }
        }
    	//这里最后要判断一下，虽然我也不知道为什么。。
        if(low*low > high) return (int)low-1;
        return (int)low;
    }
}


//这个就是每个都尝试一下，看
class Solution {
    public int mySqrt(int x) { 
        if (x == 0) return 0;
        for (int i = 1; i <= x / i; i++) 		
            if (i <= x / i && (i + 1) > x / (i + 1))// Look for the critical point: i*i <= x && (i+1)(i+1) > x
                return i;		
        return -1;
    }
}