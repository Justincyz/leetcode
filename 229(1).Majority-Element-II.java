/*229. Majority Element II
Medium
链接: https://leetcode.com/problems/majority-element-ii/

Given an integer array of size n, find all elements that appear more than 
⌊ n/3 ⌋ times.

Note: The algorithm should run in linear time and in O(1) space.

Example 1:
Input: [3,2,3]
Output: [3]

Example 2:
Input: [1,1,1,3,3,2,2,2]
Output: [1,2]

*/

/*解题思路
基本思路和majority element I一样的。最后我们需要再遍历原数组两次检查n1和n2到底是不是
majority element。举个例子，
如果数组是[1,2,3,4,5]的话
最后n1 =4 ,n2 = 5, 且c1 == c2 == 1
因为前面的都被抵消掉了，此时如果不回原数组判断的话就会直接输出 4和5了，所以
一定要回原数组判断。

总时间还是O(N), 空间为O(1)

*/

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        
        int n1 = 0, n2 =0, c1 = 0, c2 = 0;
        for(int n : nums){
            if(n1 == n) c1++;
            else if(n2 == n) c2++;
            else if(c1 == 0){
                n1 = n;
                c1++;
            }else if(c2 == 0){
                n2 = n;
                c2++;
            }else{
                c1--;
                c2--;
            }
        }
        if(c1 > 0){
            int count =0 ;
            for(int n: nums){
                if(n == n1) count++;
            }
            if(count > nums.length/3) res.add(n1);
        }
        if(c2 > 0){
            int count =0 ;
            for(int n: nums){
                if(n == n2) count++;
            }
            if(count > nums.length/3) res.add(n2);
        }

        return res;
    }
}