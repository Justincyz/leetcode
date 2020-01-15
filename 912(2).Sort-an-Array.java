/*912. Sort an Array
链接：https://leetcode.com/problems/sort-an-array/
Medium: 
Given an array of integers nums, sort the array in ascending order.

 

Example 1:

Input: nums = [5,2,3,1]
Output: [1,2,3,5]
Example 2:

Input: nums = [5,1,1,2,0,0]
Output: [0,0,1,1,2,5]
*/

/*解题思路
题目就是问所有的排序方法

可以Merge sort啊之类的
*/
class Solution {
    public List<Integer> sortArray(int[] nums) {
        Arrays.sort(nums);
        List<Integer> res = new ArrayList<>();
        for(int i: nums) res.add(i);
        
        return res;
    }
}

//添加一个quick sort的写法
class Solution {
    public List<Integer> sortArray(int[] nums) {
        //quicksort
        List<Integer> list = new ArrayList<>();
        helper(nums, 0, nums.length-1);
        for(int i: nums) list.add(i);
        return list;
    }
    
    public void helper(int[] nums, int start, int end){
        if(start >=end) return;
        
        int pivot = nums[start], l = start, r = end;
        while(l < r){
            while(l<r && nums[r] >= pivot) r--;
            while(l<r && nums[l] <= pivot) l++;
            
            swap(nums, l, r);
        }
        
        nums[start] = nums[l];
        nums[l] = pivot;
        helper(nums, start, l-1);
        helper(nums, l+1, end);
    }
    
    
    public void swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}