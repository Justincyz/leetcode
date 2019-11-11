/*1064. Fixed Point
链接：https://leetcode.com/problems/fixed-point/
Easy: 


Given an array A of distinct integers sorted in ascending order, return the smallest index i that satisfies A[i] == i.  Return -1 if no such i exists.

Example 1:

Input: [-10,-5,0,3,7]
Output: 3
Explanation: 
For the given array, A[0] = -10, A[1] = -5, A[2] = 0, A[3] = 3, thus the output is 3.
Example 2:

Input: [0,2,5,8,17]
Output: 0
Explanation: 
A[0] = 0, thus the output is 0.
Example 3:

Input: [-10,-5,3,4,7,9]
Output: -1
Explanation: 
There is no such i that A[i] = i, thus the output is -1.
*/

/*解题思路
这道题目让我们找到元素在数组中的位置正好和值一样的那一个，注意，如果有多个的话要返回最左边也就是index最小的那一个。对于这种明显可以用O(n)来解决的问题那么
最优结肯定就是o(logn)来解。
比如说这样的数值，[-10,-5,-2,0,4,5,6,7,8,9,10]
第四位和第五位都符合要求，但是我们用二分法来找的话不能到了第五位就
直接返回val,而是要继续在左半边找。每一个符合要求的值和smallestidx来比较，取一个较小的值。


*/


class Solution {
    public int fixedPoint(int[] A) {
        int start = 0, end = A.length-1;
        int smallestIdx = A.length;
        while(start < end){
            int mid = start+(end-start)/2;
            if(A[mid] == mid){
                smallestIdx = Math.min(smallestIdx, mid);
            }
            if(A[mid] < mid){
                start = mid+1;
            }else{
                end = mid;
            }
        }
        return smallestIdx == A.length ? -1 : smallestIdx;
    }
}