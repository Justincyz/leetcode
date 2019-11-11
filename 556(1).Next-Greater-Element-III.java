/*556. Next Greater Element III
链接：https://leetcode.com/problems/next-greater-element-iii/
Medium: 
Given a positive 32-bit integer n, you need to find the smallest 32-bit integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive 32-bit integer exists, you need to return -1.

Example 1:

Input: 12
Output: 21
 

Example 2:

Input: 21
Output: -1
*/

/*解题思路
这道题目其实和Next Permutation是一样的题目，只是将输入的整形数组变成了一个值
而已。这道题里有不少的坑，详见代码注解。

*/
class Solution {
    public int nextGreaterElement(int n) {
        String s = String.valueOf(n);
        char[] list = s.toCharArray();
        boolean flag = false;
        //这个for loop用来检查全部的元素是不是都是一样的，比如说"1111"
        for(int i=0; i<list.length-1; i++){
            if(list[i] != list[i+1]) flag = true;
        }
        if(!flag) return -1;

        
        if(list.length ==1) return -1;
        int tail = list.length-1;
        //从后往前找到第一个递减的元素
        while(tail>0 && list[tail] <= list[tail-1]) tail--;
        //如果全部都是递增的元素的话
        if(tail == 0 && list[0] > list[1]) return -1;
        
        //此时i的位置代表了第一个递减的元素，然后我们从i再往后找到最接近list[i]值的尾部元素位置
        int i = tail-1;
        while(tail < list.length && list[i] < list[tail]) tail ++;
        tail-=1;

        //交换这两个元素
        char temp = list[tail];
        list[tail] = list[i];
        list[i] = temp;
        //将尾部数组反转
        swap(i+1, list.length-1, list);
        StringBuffer sb = new StringBuffer();
        int res =0;
       
        //题目说只要找小于Integer.MAX_VALUE的值，如果发现大于了，则直接返回false;
        for(int j=0; j< list.length; j++){
            res+=(list[j]-'0');
            if(res > Integer.MAX_VALUE/10) return -1;
            res*=10;
        }
        return res/10;//此时多乘了一个10
    }
    
    public void swap(int l, int r, char[] list){
        while(l < r){
            char temp = list[l];
            list[l] = list[r];
            list[r] = temp;
            l++;
            r--;
        }
    }
}