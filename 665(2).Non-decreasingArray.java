/*665. Non-decreasing Array
Easy: 
Given an array with n integers, your task is to check if it could 
become non-decreasing by modifying at most 1 element.

We define an array is non-decreasing if array[i] <= array[i + 1] 
holds for every i (1 <= i < n).

Example 1:
Input: [4,2,3]
Output: True
Explanation: You could modify the first 4 to 1 to get a non-decreasing array.

Example 2:
Input: [4,2,1]
Output: False
Explanation: You can't get a non-decreasing array by modify at most one element.
*/

/*解题思路
这道题目问我们是否可以通过最多改变一个数字使得整个数组呈升序状态。第一个想到的无非就是用当前位
和前一位的数字进行对比呗，假设当前位比前一位小，说明有一个乱序的数字，count就加一。
乍一看去没啥难点，但是有一种情况必须要考虑到，就是前半段数组(前半段长度大于1)大小大于后半段
或者大于后半段数组两个数以上。比如说
[4,6,9,5,6,8,10]，
在这种情况下，count最后会等于1。但是实际上通过一次变换是没有办法使得整个数组大小呈升序的。
那么我们该怎么办呢？因为题目说只要大于一次replace就return false。假设我们遍历到了第i位。
如果可以通过变换一次就使得数组正确的话，有两种情况。1) 我们去掉i之后数组会正确. 2) 我们去掉i-1的话
数组会变正确。会出现这两种情况是因为我们不知道乱序的数是大了还是小了。假设是上面例子里
的情况的话，我们可以发现，不管是去掉 5 还是去掉 9，都没有办法使得数组变为升序数组。这里的判断
对应的是两个else if 语句。
同时我们要注意。假设(i == 0或者 i == nums.length-1)的情况是一个边界条件，这种情况对应
的是[4,1,2,3]和[1,2,3,4,2]这样的，所以只需要count++就可以了。如果所有的条件都不满足
的话，就return false。最后还要比较count 是否小于1.


*/

class Solution {
    public boolean checkPossibility(int[] nums) {
        int count =0;
        for(int i=1; i<nums.length; i++){
            if(nums[i]<nums[i-1]){
                count++;
                if(i == 1 || i == nums.length-1) continue;
                else if(i>=2 && nums[i] >= nums[i-2]) continue;
                else if(i<(nums.length-1) && nums[i+1] >= nums[i-1]) continue;
                else return false;
            } 
        }
        return count <= 1? true: false;
    }
}