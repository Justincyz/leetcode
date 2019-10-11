/*1089. Duplicate Zeros
链接：https://leetcode.com/submissions/detail/267771405/
Easy: 
Given a fixed length array arr of integers, duplicate each occurrence of zero, shifting the remaining elements to the right.

Note that elements beyond the length of the original array are not written.

Do the above modifications to the input array in place, do not return anything from your function.

 

Example 1:

Input: [1,0,2,3,0,4,5,0]
Output: null
Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]
Example 2:

Input: [1,2,3]
Output: null
Explanation: After calling your function, the input array is modified to: [1,2,3]
*/

/*解题思路
题目让我们将数组中的0复制一个放在原来的0后面，之后的所有元素往后
推一位，如果有元素超出了数组的长度，那么直接不要这个元素。
这道题目最佳的办法就是在O(n)时间，O(1)空间内写完。
首先计算0的个数，因为这就是我们要往外推出去元素的个数。然后
两个指针, p2指向原来数组的末尾，p1指向原来数组+0元素个数的虚拟
数组的末尾，如果当p1的长度在数组范围内的时候，我们就可以in-place
复制元素了。有个地方要注意，一个是如果arr[p2]==0的时候，我们要
检查两次是否p1在范围之内，因为有可能p1-1之后就落入原数组范围之内了。

*/
class Solution {
    public void duplicateZeros(int[] arr) {
        int countZero =0;
        for(int i: arr){
            if(i==0) countZero++;
        }
        
        int p1 = arr.length+countZero-1, p2 = arr.length-1;
        while(p2>=02){
            if(arr[p2] == 0){
                if(p1 < arr.length) arr[p1] = 0; 
                //要复制两个0元素
                p1--;
                if(p1 < arr.length) arr[p1] = 0; 
            }else{
                if(p1 < arr.length) arr[p1] = arr[p2];
            }
            p1--;
            p2--;
        }
    }
}              
