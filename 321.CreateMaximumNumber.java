/*
Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits.

Note: You should try to optimize your time and space complexity.

Example 1:
Input:
nums1 = [3, 4, 6, 5]
nums2 = [9, 1, 2, 5, 8, 3]
k = 5
Output:
[9, 8, 6, 5, 3]

Example 2:
Input:
nums1 = [6, 7]
nums2 = [6, 0, 4]
k = 5
Output:
[6, 7, 6, 0, 4]

Example 3:
Input:
nums1 = [3, 9]
nums2 = [8, 9]
k = 3
Output:
[9, 8, 9]
*/

class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;  
        int n = nums2.length;  
        int[] res = new int[k];

        for(int i = Math.max(0, k-n); i<= k && i<=m ;i++){//如果 n<k，那么我们需要循环所有的nums2的值得话，我们就需要从k-n开始
            int[] temp = merge(findMax(nums1, i), findMax(nums2, k-i), k);
            if(greater(temp,0, res,0)){
                res = temp;
            }
        }
        return res;
    }

    public int[] merge(int[] nums1, int[] nums2, int size){
        int[] res = new int[size];
        for (int l1 = 0, l2 = 0, index = 0; index < size; index++) {
            res[index] = greater(nums1, l1, nums2, l2) ? nums1[l1++] : nums2[l2++];
        }
        return res;
    }

    private boolean greater(int[] nums1, int i, int[] nums2, int j) {
        while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
            i++;
            j++;
        }
        return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
    }
    /*
    在一个有序的数组中，找到长度为 size (size<=n)的subsequence (not substring)组合而成的
    最大数字组合。首先，j是在 res[]中循环的指针, 从第0位开始。i是在 nums[]中循环的，也是从第0位
    开始。while loop 中(n-i > size -j)的意思是说，要确保在替换原来res 中第j位元素时，nums之后
    的元素个数可以满足最后组合填满size数量的数组，而不能有空位。记住，这里是因为结果必须要保持原来
    数组的顺序。 例如原数组是 [4,3,5,9,7],要组成三位数的数组，步骤如下:
    [4]
    [4,3]
    [5,3]
    [5,9]此时9不可以再前进一步替换掉5了，因为否则的话最后的数组只有[9,7]不能满足 size = 3
    [5,9,7]

    */  
    public int[] findMax(int[] nums, int size){
        int n = nums.length;
        int[] res = new int[size];
        for(int i=0,j=0; i<n; i++){
            while(n-i > size-j && j>0 && nums[i] > res[j-1]){
                j--;
            }
            if(j < size){
                res[j++] = nums[i];
            }
        }
        return res;
    }
}

/*
这道题有好几个知识点。首先，大体思路是说，因为我们要找长度为k的最大组合数字的话。将k分为长度为i和
长度为k-i的两个子长度。然后分别在 nums1[]和nums2[]中去找在对应的长度下最大的数字组合。然后通过
merge两个 array获得长度为k的组合。每一次merge 就比较一次大小，最后返回最大的那个
*/