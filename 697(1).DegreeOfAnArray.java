/*697. Degree of an Array
Easy: 
Given a non-empty array of non-negative integers nums, the degree of 
this array is defined as the maximum frequency of any one of its elements.

Your task is to find the smallest possible length of a (contiguous) 
subarray of nums, that has the same degree as nums.

Example 1:
Input: [1, 2, 2, 3, 1]
Output: 2
Explanation: 
The input array has a degree of 2 because both elements 1 and 2 appear twice.
Of the subarrays that have the same degree:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
The shortest length is 2. So return 2.

Example 2:
Input: [1,2,2,3,1,4,2]
Output: 6
Note:

nums.length will be between 1 and 50,000.
nums[i] will be an integer between 0 and 49,999.
*/

/*解题思路
题目问的是，找到数组中一个最短的sub-array，且这个sub-array包含所有出现频率最高的数字。
我们需要用一个HashMap来记录每一个数字对应出现的频率，这个数字最早出现的位置，最晚出现的位置。
最早最晚出现的位置是为了直接定位sub-array的长度。注意，有可能出现频率最高的数字不止一个，比如
例子1里，最高频率出现的数字有 1和2。

*/

class Solution {
    public int findShortestSubArray(int[] nums) {
        int max=0;
        Map<Integer, int[]> map2 = new HashMap<>();
        
        for(int i=0;i<nums.length; i++){
            int num = nums[i];
            if(!map2.containsKey(num)) map2.put(num, new int[]{i, i, 0});
            else{
                map2.get(num)[1] = i;
                map2.get(num)[2] ++;
            }
            if(map2.get(num)[2] > max) max =map2.get(num)[2] ;
        }
      
        int res =Integer.MAX_VALUE;
        for(int[] h : map2.values()){
            if(h[2] == max){        
                res = Math.min(h[1]-h[0]+1, res);//找到最短的subarray
            }
        }
       
        return res;   
    }
}