/*4. Median of Two Sorted Arrays
链接：https://leetcode.com/problems/median-of-two-sorted-arrays/
Hard: 

There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.

Example 1:

nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:

nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
*/

/*解题思路
这道题目遇到的话只能祈祷了，因为corner case很多。
我们针对较短的数组进行二分查找，所有如果 nums1 > nums2的话，我们先交换一下位置。
首先我们要确定一下我们要找的数在两个数组合并之后会是第几位，但是因为我们不确定
两个数组的长度时奇数还是偶数，所以我们使用 (n1+n2+1)/2的办法来确定。如果长度时
偶数的话，最后我们需要找两位数，如果是奇数的话，我们直接判断中间数的前一位就好了。
因为我们的k是合并数组之后的中间位置，在nums1中我们如果取了 m1的长度的话，那么
在nums2中我们肯定要取 k-m1的长度，否则就不是中间位置了，这里是一个重点。

因为我们没有办法确定长度的奇偶性，所以我们最后的结果一定是在nums1中取两个值m1-1,m1
且在nums2中取两个值, m2-1, m2.如果是奇数长度的话，那么就在 m1-1和m2-1中取一个较大值。
如果是偶数长度的话，我们还要考虑 m1和m2中一个较小的值，这样我们就是取了四个数的两个中间
的

*/


class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        if(n1 > n2) return findMedianSortedArrays(nums2, nums1);
        
        int k = (n1+n2+1)/2;
        int l = 0, r = n1;
        while(l < r){
            int m1 = l+(r-l)/2;
            int m2 = k - m1;
            if(nums1[m1] < nums2[m2-1]){
                l = m1+1;
            }else 
                r = m1;
        }
        
        int m1 = l;
        int m2 = k-l;

        /*这里我们需要考虑到是不是某一个数组整体都比另外一个大
        如果m1<=0的时候，说明nums1整体比nums2大
		*/
        int c1 = Math.max(m1<=0 ? Integer.MIN_VALUE: nums1[m1-1],
                          m2<=0 ? Integer.MIN_VALUE: nums2[m2-1]);
        
        if((n1+n2)%2 == 1) return c1;
        
        
        int c2 = Math.min(m1>=n1 ? Integer.MAX_VALUE: nums1[m1],
                          m2>=n2 ? Integer.MAX_VALUE: nums2[m2]);
    
    
        return (c1+c2)*0.5;
    }
}
