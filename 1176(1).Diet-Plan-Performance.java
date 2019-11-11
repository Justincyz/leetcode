/*1176. Diet Plan Performance
链接：https://leetcode.com/problems/diet-plan-performance/
Easy: 
A dieter consumes calories[i] calories on the i-th day. 

Given an integer k, for every consecutive sequence of k days (calories[i], calories[i+1], ..., calories[i+k-1] for all 0 <= i <= n-k), they look at T, the total calories consumed during that sequence of k days (calories[i] + calories[i+1] + ... + calories[i+k-1]):

If T < lower, they performed poorly on their diet and lose 1 point; 
If T > upper, they performed well on their diet and gain 1 point;
Otherwise, they performed normally and there is no change in points.
Initially, the dieter has zero points. Return the total number of points the dieter has after dieting for calories.length days.

Note that the total points can be negative.


Example 1:
Input: calories = [1,2,3,4,5], k = 1, lower = 3, upper = 3
Output: 0
Explanation: Since k = 1, we consider each element of the array separately and compare it to lower and upper.
calories[0] and calories[1] are less than lower so 2 points are lost.
calories[3] and calories[4] are greater than upper so 2 points are gained.

Example 2:
Input: calories = [3,2], k = 2, lower = 0, upper = 1
Output: 1
Explanation: Since k = 2, we consider subarrays of length 2.
calories[0] + calories[1] > upper so 1 point is gained.

Example 3:
Input: calories = [6,5,0,0], k = 2, lower = 1, upper = 5
Output: 0
Explanation:
calories[0] + calories[1] > upper so 1 point is gained.
lower <= calories[1] + calories[2] <= upper so no change in points.
calories[2] + calories[3] < lower so 1 point is lost.
*/

/*解题思路
题目写的挺长，意思是给我们一个代表个人消耗卡路里的数组，数值代表消耗掉的卡路里。在连续K天内的
卡路里消耗总和如果超过一个上界upper, 那么这个人就得一分，如果没达到要求的
下界则扣一分。问，总共这个人可以得几分。
需要先计算初始的前k个总和，然后就是sliding windows, 减掉最久的那一天，添加新
的一天。每一次都计算一下数值。

*/


class Solution {
    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        int totalCal = 0, point=0;
        for(int i=0; i<k;i++){
            totalCal+=calories[i];
        }
        if(totalCal > upper) point++;
        else if(totalCal < lower) point--;
        
        for(int i=k; i< calories.length; i++){
            totalCal-=(calories[i-k]);
            totalCal+=(calories[i]);
            if(totalCal > upper) point++;
            else if(totalCal < lower) point--;
        }
        return point;
    }
}