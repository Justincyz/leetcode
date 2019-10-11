/*414. Third Maximum Number
Easy
链接: https://leetcode.com/problems/third-maximum-number/

Given a non-empty array of integers, return the third maximum number in 
this array. If it does not exist, return the maximum number. The time 
complexity must be in O(n).

Example 1:
Input: [3, 2, 1]
Output: 1
Explanation: The third maximum is 1.

Example 2:
Input: [1, 2]
Output: 2
Explanation: The third maximum does not exist, so the maximum (2) is returned 
instead.

Example 3:
Input: [2, 2, 3, 1]
Output: 1
Explanation: Note that the third maximum here means the third maximum distinct number.
Both numbers with value 2 are both considered as second maximum.


*/

/*解题思路
这道题目让我们找到数组中第三大的元素，注意重复的元素不算。如果这个数组中没有第三大的元素。
比如说[1,1,2,2], 那么直接返回第一大的元素。在这里我们需要设置三个long型的元素，l1代表
最大的，l2代表次大的，l3代表第三大的。为什么不用Integer类型的是因为数组中有可能出现下面
这样的情况，就不好来判断第三大的元素是整形最小值本身还是我们赋予的初始值，如果是整形最小值
本身我们就应该返回这个数，否则应该返回最大的数。

[-2147483648,1,2]
[-2147483648,1,1]

所以我们这里就改成了Long类型的值。
因为我们要找的是第三大的值，而不是排第三的值，所以每一次比对范围的时候都不能取等于号。
我们要从小值往大值的方向进行比对。然后无非就是将正确的值放在正确的位置罢了。还是
注意精度的转换。
*/

class Solution {
    public int thirdMax(int[] nums) {
        long l1 = Long.MIN_VALUE, l2 = Long.MIN_VALUE, l3 = Long.MIN_VALUE;

        for(int num: nums){
            if(num > l3 && num < l2){
                l3 = (long)num;
            }else if(num > l2 && num < l1){
                l3 = l2;
                l2 = (long)num;
            }else if(num > l1){
                l3 = l2;
                l2 = l1;
                l1 = (long)num;
            }
        }

        return l3 == Long.MIN_VALUE ? (int)l1 : (int)l3;
    }
}