/*378. Kth Smallest Element in a Sorted Matrix
链接：https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
Medium: 

Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.

Note:
You may assume k is always valid, 1 ≤ k ≤ n2.
*/

/*解题思路
这道题让我们求有序矩阵中第K小的元素，这道题的难点在于数组并不是蛇形有序的，意思是当
前行的最后一个元素并不一定会小于下一行的首元素，所以我们并不能直接定位第K小的元素.
这里最简单的办法当然就是先用一个ArrayList把所有的元素储存下来，然后排序，直接获取
第K大的元素。当然这是最慢的，下面我们来看看二分法怎么做。

首先我们使用的是区间寻找的办法，我们二分的范围是矩阵当中的最小值，也就是左上角的
值和右下角的最大值直接使用二分。我们每一次在两个范围内取一个中间值，然后计算小于
这个中间值的数字个数有多少。如果个数小于k个的话，说明我们要取的范围在[mid+1, high]
当中，如果个数大于等于K个的话，说明我们要取得范围在[low, mid]之间。

(
一开始用这个方法的时候我有一个疑惑，这个不一定出现在矩阵当中的mid值是怎么帮我们判断
一个最后一定在矩阵中的元素的位置呢？其实我们抽象一下，假设一个数组只有三个元素，[1,10,100]。
我们要找到第2个元素，按照这道题目的逻辑，顺序会是这样。

初始化 low = 1, high = 100 --->  mid = 50, 小于等于50的元素个数有2个，high = 50
--->  low = 1, high = 50 ---> mid = 25, 小于等于25的元素个数有2个, high = 25
--->  low = 1, high = 25 ---> mid = 13, 小于等于13的元素个数有2个, high = 13
--->  low = 1, high = 13 ---> mid = 7,  小于等于7的元素个数有1个, low = 7+1 = 8
--->  low = 8, high = 13 ---> mid = 10, 小于等于10的元素个数有2个, high = 10
--->  low = 8 high = 10 ---> mid = 9, 小于等于9的元素个数有1个, low = 8+1 = 9
--->  low = 9 high = 10 ---> mid = 9, 小于等于9的元素个数有1个, low = 9+1 = 10

结果为10

看起来好像有点复杂，实际上最后这个low和high一定会落到某个区间中存在的数字上面去。
就像我们要猜[1, 10]中间的某一个数字(假设是6)，一开始问，大于5的有几个，回答有一个。
那么我们就可以摒弃[1,5]直接的元素，从[6,10]的范围中找。第二次问，大于(6+10)/2 = 8的
元素有几个，回答是0个，那么区间就缩短到[6,8]的之间了。我们继续这样问，直到low > high时，
我们就可以确定是哪一个数字了。第一次仔细的系统回答这个问题，可能有一点啰嗦，希望对以后
的复习有帮助。
)

接下来就是如何快速计算小于等于mid的元素个数有多少个了。我们可以充分利用这个矩阵的特性，
从第一排的最后一个开始确定范围，如果这个元素小于mid, 那么所有这一排的元素都小于Mid.
如果这个元素大于mid, 那我们就进入到while loop来找到这一排第一个小于Mid的元素在哪里。
我们之所有可以用一个变量j来确定所有行的纵坐标，是因为这个矩阵的每一个数字，都大于等于
其左上角的所有数字，所以每一次查找的时间复杂度是(行的个数+列的个数).
总的时间复杂度为O((m+n)log(high-low))，m和n对应的是行的个数+列的个数
*/

class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int row = matrix.length, col = matrix[0].length;
        int low = matrix[0][0], high = matrix[row-1][col-1];
        
        while(low < high){
            int mid = (low+high)/2;
            int cntSmallerThanMid =0;
            int j=col-1;
            for(int i=0; i<row; i++){
                while(j>=0 && matrix[i][j] > mid) j--;
                cntSmallerThanMid+=(j+1);
            }
            if(cntSmallerThanMid < k) low = mid+1;
            else high = mid;
        }
        return low;
    }
}

