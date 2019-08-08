/*548. Split Array with Equal Sum
Medium
Given an array with n integers, you need to find if there are triplets 
(i, j, k) which satisfies following conditions:

0 < i, i + 1 < j, j + 1 < k < n - 1
Sum of subarrays (0, i - 1), (i + 1, j - 1), (j + 1, k - 1) and (k + 1, n - 1)
should be equal.
where we define that subarray (L, R) represents a slice of the original array
starting from the element indexed L to the element indexed R.

Example:
Input: [1,2,1,2,1,2,1]
Output: True
Explanation:
i = 1, j = 3, k = 5. 
sum(0, i - 1) = sum(0, 0) = 1
sum(i + 1, j - 1) = sum(2, 2) = 1
sum(j + 1, k - 1) = sum(4, 4) = 1
sum(k + 1, n - 1) = sum(6, 6) = 1
*/

/*解题思路
这道题就是一个简单地循环，可以一开始不用pre-sum的办法。因为一开始用pre-sum建立数组
的话找准确的index很麻烦。直接用在循环里面用一个sum来代替就可以了。最重要的就是注意边际条件。
还有两个特殊的条件要注意
第一个优化是在for循环里面，如果i不等于1，且当前数字和之前数字均为0，那么跳过这个
位置，因为加上0也不会对target有任何影响，那为什么要加上i不等于1的判断呢，因为输入
组如果是七个0，那么实际上应该返回true的，而如果没有i != 1这个条件限制，后面的代码
不会得到执行，那么就直接返回false了，是不对的。第二个优化的地方是在递归函数里面，
只有当curSum等于target了，才进一步调用递归函数，这样就相当于做了剪枝处理，减少了
大量的不必要的运算

再来说下子函数for循环的终止条件 i < n - 5 + 2*cnt 是怎么得来的，是的，
这块的确是个优化，因为i的位置是题目中三个分割点i，j，k的位置，所以其分别有自己
可以取值的范围，由于只有三个分割点，所以cnt的取值可以是0，1，2。
-> 当cnt=0时，说明是第一个分割点，那么i < n - 5，表示后面必须最少要留5个数字，
因为分割点本身的数字不记入子数组之和，那么所留的五个数字为：数字，第二个分割点，数字，第三个分割点，数字。
-> 当cnt=1时，说明是第二个分割点，那么i < n - 3，表示后面必须最少要留3个数字，
因为分割点本身的数字不记入子数组之和，那么所留的三个数字为：数字，第三个分割点，数字。
-> 当cnt=2时，说明是第三个分割点，那么i < n - 1，表示后面必须最少要留1个数字，
因为分割点本身的数字不记入子数组之和，那么所留的一个数字为：数字。

*/

class Solution {
    public boolean splitArray(int[] nums) {
        int n = nums.length, sum =0, target =0;
        for(int num: nums) sum+=num;
        for(int i=1; i<(n-5); i++){
            //注意这里
            if (i != 1 && nums[i - 1] == 0  && nums[i] == 0) continue;
            target+=nums[i-1];
            if(helper(nums, i+1, 1, target, sum-target-nums[i])) return true;
        }
        
        return false;
    }
    
    public boolean helper(int[] nums, int start, int count, int target, int leave){
        if(count == 3){
            if(leave == target) return true;
            else return false;
        } 
        
        int sum =0;
        for(int i=start+1; i<nums.length+2*count-5; i++){//这种边际条件最讨厌了
            sum+= nums[i-1];
            if(sum != target) continue;
            if(helper(nums, i+1, count+1, target, leave-sum-nums[i])) return true;
        }
        return false;
    }
}

//97行更改了边际条件，会更加直观一些
class Solution {
    public boolean splitArray(int[] nums) {
        int n = nums.length, sum =0, target =0;
        for(int num: nums) sum+=num;
        for(int i=1; i<(n-5); i++){
            if (i != 1 && nums[i - 1] == 0  && nums[i] == 0) continue;
            target+=nums[i-1];
            if(helper(nums, i+1, 2, target, sum-target-nums[i])) return true;
        }
        
        return false;
    }
    
    public boolean helper(int[] nums, int start, int count, int target, int leave){
        if(count == 0){
            if(leave == target) return true;
            else return false;
        }
        
        int sum =0;
        //更改了边际条件
        for(int i=start+1; i<nums.length-2*count+1; i++){//这种边际条件最讨厌了
            sum+= nums[i-1];
            if(sum != target) continue;
            if(helper(nums, i+1, count-1, target, leave-sum-nums[i])) return true;
        }
        return false;
    }
}