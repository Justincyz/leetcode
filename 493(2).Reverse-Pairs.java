/*493. Reverse Pairs
Hard: Segment Tree, Merge sort, Binary Index Tree

Given an array nums, we call (i, j) an important reverse pair if i < j and 
nums[i] > 2*nums[j].

You need to return the number of important reverse pairs in the given array.

Example1:
Input: [1,3,2,3,1]
Output: 2

Example2:
Input: [2,4,3,5,1]
Output: 3
*/

/*解题思路: 还是首先参考一下 https://www.cnblogs.com/grandyang/p/6657956.html
这道题目要求我们找到数组中所有的pair, 定义的pair是 A, B两个数。A在B的前面，但是A的数值
要大于B的两倍。首先解释一种比较简单的递归做法，做法类似于Mergesort。
为什么是用mergesort呢？我们知道混合排序就是不断的将数组对半拆分成子数组，
拆到最小的数组后开始排序，然后一层一层的返回，最后原数组也是有序的了。
这里我们在混合排序的递归函数中，对于有序的两个子数组进行统计翻转对的个数，
然后再逐层返回。假设在数组中有这样两段子数组. [i, m]和[m+1, j]。
我们首先可以判断的就是左边的数组坐标一定都小于右边的数组。那么如果这两个数组已经
被排序好了得话。那么通过两个指针我们可以很快的找到前面数组中数字是后面数组两倍的个数。还是举个例子:
假设前半段是[5,6,7],后半段是[1,2,3,4].
[5,6,7]
对5来说，1，2都小于  5/2。此时后半段的指针在2的位置
对6来说，1，2都小于  6/2，此时后半段的指针还在2的位置
对7来说，1,2,3都小于 7/2， 此时后半段的指针向后再移动一位。到了3
所以我们只需要累加就可以了

那么我们相当于找到了[i,j]范围内的所有有效pair。此时我们直接调用arrays.sort对[i, j]
范围内排序。将累加的pair数量传入到上一层。

*/

class Solution {
    public int reversePairs(int[] nums) {   
        return helper(nums, 0, nums.length-1);
    }

    public int helper(int[] nums, int left, int right){
        if(left >= right) return 0;
        int mid = left + (right-left)/2;
        int res= helper(nums, left, mid)+helper(nums, mid+1, right);

        for(int l = left, r = mid+1; l <=mid; l++){
            //这里的除法要除以2.0来用double类型比较，否则会往下取整出问题。
            //同时注意要用除，而不是nums[r]*2。用以避免Integer.Max_value
            //会出现这样的例子[2147483647,2147483647,2147483647,2147483647]
            while(r<=right && (nums[l]/2.0)>nums[r]) r++;
            res+= r- (mid+1);
        }
        //这里的sort比较还是 O(nlogn)的做法，虽然简单，但是我们完全可以用
        //普通的mergeSort的办法来做
        Arrays.sort(nums, left, right+1);
        return res;
    }
}
//这里是改进之后每一层都用普通的mergeSort的办法来做的

class Solution {
    int total =0;
    int[] temp;//我们使用一个和原数组同样大小的temp数组作为零时存放变量的数组
    public int reversePairs(int[] nums) {
        temp = new int[nums.length];
        merge(nums, 0, nums.length-1);

        return total;
    }
    
    public void merge(int[] nums, int l, int r){
        if(l >=r) return;
        
        int mid = l+(r-l)/2;
        merge(nums, l, mid);
        merge(nums, mid+1, r);
        int p1 = l, p2 = mid+1;
        while(p1 <=mid &&  p2 <=r){
            int left = nums[p1], right = nums[p2];
            if(left/2.0 > right){
                total+=(mid-p1+1);
                p2++;
            }else{
                p1++;
            }
        }
        // Arrays.sort(nums, left, right+1);
        mergeSort(nums, l, mid, r);
        
    }
    
    public void mergeSort(int[] nums, int l,int mid, int r){
        //我们使用一个和原数组同样大小的temp数组作为零时存放变量的数组
        for(int i=l; i<=r; i++) temp[i] = nums[i];
        int p1 = l, p2 = mid+1;
        while(p1 <= mid || p2 <= r){
            if(p1 <= mid && p2 <= r){
                nums[l] = temp[p1]>temp[p2]? temp[p2++] : temp[p1++];
            }else if(p1 <=mid){
                 nums[l] = temp[p1++];
            }else{
                 nums[l] = temp[p2++];
            }
            l++;
        }
    }
}