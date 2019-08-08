/*658. Find K Closest Elements
Medium: 
Given a sorted array, two integers k and x, find the k closest elements 
to x in the array. The result should also be sorted in ascending order. 
If there is a tie, the smaller elements are always preferred.

Example 1:
Input: [1,2,3,4,5], k=4, x=3
Output: [1,2,3,4]
Example 2:
Input: [1,2,3,4,5], k=4, x=-1
Output: [1,2,3,4]
Note:
The value k is positive and will always be smaller than the length of the 
sorted array.
Length of the given array is positive and will not exceed 104
Absolute value of elements in the array and x will not exceed 104
*/

/*解题思路
这道题目要求我们找到数组中最接近x的k个数字。首先说一个最简单的做法，因为x可能出现在数组中，
也可能不在。所以我们要找到可以囊括x的一个范围。通过从头开始遍历找到这个位置。然后比较左右
两个数字(如果只有一边有数字，那就直接取一边的值)。直到取完K个值。
同样的做法还有另外一种，因为反正我们要取的数组是连续的，所以我们也可以用两个指针一个指向头，
一个指向尾，然后比较头尾的链各个值，把较大的抛弃，直到最后范围为k。

https://www.youtube.com/watch?v=3ifFNvdfjyg
重点，现在我们来看一个二分法找sub-array的办法。这个办法很巧妙，忘记的话首先可以参考一下
上面的视频链接。因为我们要找的是一段连续的数组(因为数组已经排列好)，所以如果我们可以确定
数组的起始位置，那么往后加k位数我们就可以找到这一段子数组了。
假设数组是这样的
(start.................End)
我们将问题转化为查找目标数组的起始位置的话，那么这个其实位置最晚不能超过 End-k, 否则就会
越界了。
所以我们将查找范围缩减为
(start........Mid..........End-k)
假设A[Mid] >= x 的话，那么子数组的起始位置肯定在 [start, mid]这个范围内。
假设x > A[Mid]的话，此时我们没有办法判断起始位置在哪里，因为有可能在 [start, mid]和[mid+1,end-k]
的所有范围内。那么此时我们就需要借助一个长度为k的假定区间来验证，我们需要往哪一边走了。
(start........M,M+1,..,M+k..........End-k)
如果x更加靠近这个区间的起始位置(无论是在起始位置的左边还是右边)，也就是arr[M]的话，那么说明我们要取的起始点区间范围应该
在[start, mid]这个范围之内。因为当起始点取到最大，也就是mid时候，此时已经可以囊括mid+k
这个范围了。而且我们知道x更加靠近mid，所以[mid+k, end-k]这个范围内的值都可以被排除掉。
同理，假如x更加靠近这个区间的结束位置，也就是 arr[mid+k]的话。那么[start,mid]作为起始点就可以
全部被排除掉了，因为这个区间内的值没有一个会比mid代表的数值更加靠近x。



*/

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        
        int start=0, end = arr.length-k;
        while(start < end){
            int mid = start+(end-start)/2;
          //  if(x > arr[mid]){
                if((x- arr[mid]) > (arr[mid+k]-x)){
                    start = mid+1;
                }else{
                    end = mid;
                }
           // }else{
           //     end = mid;
           // }
        }
      
        List<Integer> res = new ArrayList<>();
        for(int i=start; i<start+k; i++) res.add(arr[i]);
        
        return res;
    }
}



/*
一开始用的方法
*/
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        LinkedList<Integer> list = new LinkedList<>();
        int start=0;
        while(arr[start] < x){
            start++;
        }
        int head = start-1, tail = start;
        while(list.size()<k){
            if(head>=0 && tail < arr.length){
                if(Math.abs(x-arr[head]) <= Math.abs(x- arr[tail])){
                    list.addFirst(arr[head--]);
                }else{
                    list.addLast(arr[tail++]);
                }
            }else if(head>=0){
                list.addFirst(arr[head--]);
            }else{
                list.addLast(arr[tail++]);
            }
        }
        
        
        return list;
    }
}