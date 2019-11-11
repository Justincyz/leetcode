/*496. Next Greater Element I
链接：https://leetcode.com/problems/next-greater-element-i/
Easy: 
You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.

The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.

Example 1:
Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
Output: [-1,3,-1]
Explanation:
    For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
    For number 1 in the first array, the next greater number for it in the second array is 3.
    For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
Example 2:
Input: nums1 = [2,4], nums2 = [1,2,3,4].
Output: [3,-1]
Explanation:
    For number 2 in the first array, the next greater number for it in the second array is 3.
    For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
Note:
All elements in nums1 and nums2 are unique.
The length of both nums1 and nums2 would not exceed 1000.
*/

/*解题思路
这道题目的最优解是O(nums2)。因为nums1的所有值在nums2中都会出现，所以
我们直接用stack+hashmap的方法把 nums2中每一个值右边比这个值大的第一个数
用hashmap建立联系。最后遍历一次nums1中的数值，如果map中存在这个值就直接
赋值，否则就赋值-1



We use a stack to keep a decreasing sub-sequence, whenever we see a number x greater than stack.peek() we pop all elements less than x and for all the popped ones, their next greater element is x
For example [9, 8, 7, 3, 2, 1, 6]
The stack will first contain [9, 8, 7, 3, 2, 1] and then we see 6 which is greater than 1 so we pop 1 2 3 whose next greater element should be 6
*/

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
       
        int[] res = new int[nums1.length];
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> s = new Stack<>();
        
        for(int i=0; i< nums2.length; i++){
            while(!s.isEmpty() && s.peek() < nums2[i]){
                map.put(s.pop(), nums2[i]);
            }
            s.push(nums2[i]);
        }
        
        for(int i=0; i< nums1.length; i++){
            int num = nums1[i];
            if(map.containsKey(num)){
                res[i] = map.get(num);
            }else{
                res[i] = -1;
            } 
        }
        
        return res;
    }
}




//这是第一次做的O(MN)的方法
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[] res = new int[m];
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int j=0; j< m; j++){
            int idx = 0;
            res[j]  =-1;
            for(int i=0; i<n; i++){
                if(nums1[j] == nums2[i]){
                    idx = i+1;
                }
            }
            
            for(int i=idx ; i< n; i++){
                if(nums2[i] > nums1[j]){
                    res[j] = nums2[i];
                    break;
                }
            }
        }
        
        
        return res;
    }
}