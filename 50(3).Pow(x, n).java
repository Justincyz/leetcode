/*50. Pow(x, n)
链接：https://leetcode.com/problems/powx-n/submissions/
Medium: 
Implement pow(x, n), which calculates x raised to the power n (xn).

Example 1:

Input: 2.00000, 10
Output: 1024.00000
Example 2:

Input: 2.10000, 3
Output: 9.26100
Example 3:

Input: 2.00000, -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25
Note:

-100.0 < x < 100.0
n is a 32-bit signed integer, within the range [−2^31, 2^31 − 1]
*/

/*解题思路
这道题目我被美团考过一次，当时没有给出最优解法。
这道题的解法就是二分拆分，比如说2的十次方就是两个2的五次方相乘。
2的九次方我们可以当做两个2的四次方(9/2)相乘再乘以一个2。这就是我们
递归的算法，那么basecase就是当n==0的时候，此时返回一。
比如说我们要计算3的三次方。
我们从下往上是这样计算的。
当n == 0的时候，返回1给上一层，
此时上一层的x等于3，t = 1(递归的下一层返回)
此时再向上返回t*t*x 也就是1*1*3，返回3上去。
再上一层就变成3*3*3也就是 x*t*t这样。

用递归来折半计算，每次把n缩小一半，这样n最终会缩小到0，任何数的0次方都为1，
这时候再往回乘，如果此时n是偶数，直接把上次递归得到的值算个平方返回即可，
如果是奇数，则还需要乘上个x的值。还有一点需要引起注意的是n有可能为负数，对
于n是负数的情况，我可以先用其绝对值计算出一个结果再取其倒数即可，之前是可以
的，但是现在 test case 中加了个负2的31次方后，这就不行了，因为其绝对值超
过了整型最大值，会有溢出错误，不过可以用另一种写法只用一个函数，在每次递归中
处理n的正负，然后做相应的变换即可，代码如下：
*/


class Solution {
    public double myPow(double x, int n) {
        if(x == 0) return 0.0;
        if(n == 0 || x ==1) return 1.0;
        
        if(n < 0){
            return 1.0/helper(x, -n);
        }else{
            return helper(x, n);
        } 
    }
    
    public double helper(double x, int n){
        if(n == 0){
            return 1;
        }
        
        double t = helper(x, n/2);
        if(n%2==0){
            return t*t;
        }else{
            return t*t*x;
        }
    }
}