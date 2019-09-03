/*31. Next Permutation
Medium: 
Implement next permutation, which rearranges numbers into the 
lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the 
lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its 
corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
*/

/*解题思路
题目让我们找到当前数字的下一位数字。参考题目给出的例子。这道题目还有一个兄弟题目，就是
找Previous Permutation。先说一下流程，如果我们要找到当前数字的下一位数字。那么我们从
后往前遍历数字，找到连续递增数列的下一位，也就是第一位递减的数字。
如果是 [4,1,4,7,6,5]。那么第一位递减的数字就是4。然后在刚才遍历过的尾部中找到最接近
4且比4大的数。交换这两位。
变为 [4,1,5,7,6,4]
最后将整个后半段翻转过来，变为[4,1,5,4,6,7]。这样就完成了找到下一位比当前数字大的数了

*/

class Solution {
    public void nextPermutation(int[] nums) {
        int cur = nums.length-1;
        while(cur>=1 && nums[cur-1] >= nums[cur]){
            cur --;
        }
        if(cur == 0){
            swap(nums, 0, nums.length-1);
            return;
        } 
        
        int pt = cur-1;//指向第一个变小的数
        while(cur<nums.length && nums[pt] < nums[cur]){
            cur++;
        }
        cur--;
        int temp = nums[pt];
        nums[pt] = nums[cur];
        nums[cur] = temp;
        swap(nums, pt+1, nums.length-1);
        
    }
    
    public void swap(int[] nums, int start, int end){
        while(start< end){
            int i = nums[start];
            nums[start] = nums[end];
            nums[end] = i;
            start++;
            end--;
        }
    }
}


    