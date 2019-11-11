/*1058. Minimize Rounding Error to Meet Target
链接：https://leetcode.com/problems/minimize-rounding-error-to-meet-target/
Medium: 
Given an array of prices [p1,p2...,pn] and a target, round each price pi to Roundi(pi) so that the rounded array [Round1(p1),Round2(p2)...,Roundn(pn)] sums to the given target. Each operation Roundi(pi) could be either Floor(pi) or Ceil(pi).

Return the string "-1" if the rounded array is impossible to sum to target. Otherwise, return the smallest rounding error, which is defined as Σ |Roundi(pi) - (pi)| for i from 1 to n, as a string with three places after the decimal.

 

Example 1:

Input: prices = ["0.700","2.800","4.900"], target = 8
Output: "1.000"
Explanation: 
Use Floor, Ceil and Ceil operations to get (0.7 - 0) + (3 - 2.8) + (5 - 4.9) = 0.7 + 0.2 + 0.1 = 1.0 .
Example 2:

Input: prices = ["1.500","2.500","3.500"], target = 10
Output: "-1"
Explanation: 
It is impossible to meet the target.
*/

/*解题思路
题目给定了一堆浮点数字，我们可以向上取证也可以向下取整。给定一个target的
值，问，我们如何将所有的数值取证且相加等于target的数字，且整数和浮点之间的
error总和最小，如果没办法取证得到target的值的话，返回-1。如例子所示。其实说起来挺简单，我们先计算所有浮点数向下取整之后的和，和向上取整之后的和。如果
target不在这个范围之内，说明没办法取到值，如果target在这个范围之内，我们
一定可以取到值。为啥呢，因为每个数的上下取整相差为1，(除了原来就是整数之外)。
所以我们从下取整的合minV可以每一次反转一个数使得合增加1直到maxV位置。
那么我们只需要对每个数值计算两个值，一个是此数和他向下取证之间的差(0.6到0需要error = 0.6)，还有向上取整的差(0.6到1要error 0.4)。然后对
向下取证的差值从小到大排个序。因为我们要取得target这个值，所以有target-minV
这么多的数值需要向上取整，然后总个数再减去这个数需要向下取证。因为已经排好序了，直接取值就可以了。
最后把error的总和变成string返回答案就行

*/


class Solution {
    public String minimizeError(String[] prices, int target) {
        int maxV = 0, minV = 0;
        List<double[]> list = new ArrayList<>();
        
        for (String s : prices) {
            float f = Float.valueOf(s);
            double low = Math.floor(f);
            double high = Math.ceil(f);
            
            maxV +=high;
            minV +=low;
            double floorErr = f-low;
            double ceilErr = high - f;
            list.add(new double[]{floorErr, ceilErr});
        }
        
        if (target < minV || target > maxV)
            return "-1";
        
        Collections.sort(list, new Comparator<double[]>(){
            public int compare(double[] a, double[] b){
                if(a[0] < b[0]) return 1;
                else return -1;

            }
        });
        // The number of prices that need to be rounded up (rest are rounded down)
        int ceilCount = target-minV;
        
        float res =0;
        
        for(int i=0; i< ceilCount; i++){    
            res+=list.get(i)[1];
        }
        
        for(int i=list.size()-1; i >= ceilCount; i--){
            res+=list.get(i)[0];
        }
        
        return String.format("%.3f", res);
    }
}