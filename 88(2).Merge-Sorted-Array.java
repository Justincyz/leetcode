/*88. Merge Sorted Array
链接：https://leetcode.com/problems/merge-sorted-array/
Easy: 
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:

The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.

Example:

Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]
*/

/*解题思路
题目告诉我们，有两个已经排序号的array。我们需要将nums2 merge 到nums1里面。并且告诉我们确保有足够的
空间可以容纳nums2。唯一要注意的是nums2可能为空，所以需要预先判断一下。
同样我们还是用三根指针，p1指向nums1最后一个有效位置，p2指向nums2最后一个
有效位置。p3指向Nums1的末尾，用来merge两个数组。
需要注意的是判断条件，最后两个判断条件是(p1 <0)或者(p2 <0)，而不是等于0哦。

*/
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 =  m-1, p2 = n-1, p3 = m+n-1;
       
        if(nums2.length == 0 ) return;
            
        while(p1>=0 || p2 >=0){
            if(p1>=0 && p2 >=0){
                if(nums1[p1] > nums2[p2]){
                    nums1[p3--] = nums1[p1--];
                }else{
                    nums1[p3--] = nums2[p2--];
                }
            }
            else if(p1 < 0) nums1[p3--] = nums2[p2--];
            else if(p2 < 0) nums1[p3--] = nums1[p1--];
        }
        return;
    }
}