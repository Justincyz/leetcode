/*1365. How Many Numbers Are Smaller Than the Current Number
链接：https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/
Easy: 

Given the array nums, for each nums[i] find out how many numbers in the array are smaller than it. That is, for each nums[i] you have to count the number of valid j's such that j != i and nums[j] < nums[i].

Return the answer in an array.

Example 1:
Input: nums = [8,1,2,2,3]
Output: [4,0,1,1,3]
Explanation: 
For nums[0]=8 there exist four smaller numbers than it (1, 2, 2 and 3). 
For nums[1]=1 does not exist any smaller number than it.
For nums[2]=2 there exist one smaller number than it (1). 
For nums[3]=2 there exist one smaller number than it (1). 
For nums[4]=3 there exist three smaller numbers than it (1, 2 and 2).

Example 2:
Input: nums = [6,5,4,8]
Output: [2,1,0,3]

Example 3:
Input: nums = [7,7,7,7]
Output: [0,0,0,0]
*/

/*解题思路 1
这道题目让我们计算数组中每一个数字对应比他小的数字有多少。最简单的办法就是
用一个二维数组，数组中每一位的第一个是这个数字本身，第二个是这个数字在数组
中的位置。然后对二维数组的第一位进行排列，这样可以获得一个从小到大排列好的
数组，然后根据排列好的数组中找到原数组对应元素的位置，将计算出比他小的元素
个数放置在原数组位置上(复用原数组)。最后返回。时间复杂度是O(nlogn)因为要排序。

*/

class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        
        int[][] copy = new int[nums.length][2];
        for(int i=0; i< nums.length; i++){
            copy[i] = new int[]{nums[i], i};    
        }
        Arrays.sort(copy, (a, b) -> a[0]-b[0]);
        int count =0;
        for(int i=0; i<copy.length; i++){
        	nums[copy[i][1]] = count;
            if(i < copy.length-1 && copy[i][0] != copy[i+1][0]){
                count = i+1;
            }
        }
        return nums;
    }
}

/*解题思路 2
这道题目还有一个投机取巧的地方，题目当中给出来整数都是在0到100范围内的，
所以我们可以根据这个信息建立一个长度为101的array用来存放对应元素所在的坐标。
然后遍历一遍新的数组，计算每一个位置元素的个数并且累加。最后求得答案

*/


class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        List<Integer>[] numToPos = new ArrayList[101];
        
        for(int i=0; i< nums.length; i++){
            if(numToPos[nums[i]] == null) numToPos[nums[i]] = new ArrayList<>();
            numToPos[nums[i]].add(i);
        } 

        int count =0;
        for(int i=0; i< numToPos.length; i++){
            if(numToPos[i] != null){
                for(int pos: numToPos[i]){
                    nums[pos] = count;
                }
                count+=numToPos[i].size();
            }
        }
        
        return nums;
    }
}


//或者这样也可以
class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] count = new int[101];
        int[] res = new int[nums.length];
        
        for (int i =0; i < nums.length; i++) {
            count[nums[i]]++;
        }
        
        for (int i = 1 ; i <= 100; i++) {
            count[i] += count[i-1];    
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                res[i] = 0;
            else 
                res[i] = count[nums[i] - 1];
        }
        
        return res;        
    }
}