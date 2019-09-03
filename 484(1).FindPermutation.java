/*484. Find Permutation
Medium: 

By now, you are given a secret signature consisting of character 
'D' and 'I'. 'D' represents a decreasing relationship between two 
numbers, 'I' represents an increasing relationship between two 
numbers. And our secret signature was constructed by a special 
integer array, which contains uniquely all the different number 
from 1 to n (n is the length of the secret signature plus 1). 
For example, the secret signature "DI" can be constructed by 
array [2,1,3] or [3,1,2], but won't be constructed by array 
[3,2,4] or [2,1,3,4], which are both illegal constructing special 
string that can't represent the "DI" secret signature.

On the other hand, now your job is to find the lexicographically 
smallest permutation of [1, 2, ... n] could refer to the given 
secret signature in the input.

Example 1:
Input: "I"
Output: [1,2]
Explanation: [1,2] is the only legal initial spectial string 
can construct secret signature "I", where the number 1 and 2 
construct an increasing relationship.

Example 2:
Input: "DI"
Output: [2,1,3]
Explanation: Both [2,1,3] and [3,1,2] can construct the secret 
signature "DI", 
but since we want to find the one with the smallest lexicographical
 permutation, you need to output [2,1,3]
*/

/*解题思路
这道题目挺有意思的，首先还是解释一下题意。D代表降序，I代表升序。问，如果给出(1-n)个数字，且
按照给定的升序降序字符串如何排列出最小的一个数。根据例子我们可以看出来，如果输入的长度(D和I的组合)
是n的话，那么我们总共就有n+1个数字(1 到 n+1)。首先，如果一个组合想要越小越好，那么我们
要尽量的把较小的数字放在前面。我们首先创建一个长度为n+1的array, 从 (0到 n)赋值(1到n+1)个
数字。因为我们要尽可能的把数字小的放在前面，所以这样的初始化可以帮助我们把较小的数字放在前面。
假设我们在[i,j]遇到了连续的increase,且此时我们已经排列好了[0,i]的数字，那我们不需要管这一段，
因为原数组就是自然升序的，且末尾j之后不会出现比[i,j]之间数值更小的数字。如果我们在[i,j]遇到了连续的
decrease，那么我们只需要将[i,j]的所有数字翻转过来就可以了。因为对于这一段的结尾数字nums[j]
来说，在这之后不会有比nums[j]更小的数以满足降序[i,j]之间的降序条件了。还是要注意，最后要再检查
一次head是否到了末尾，如果没有的话且 head 到 tail这一段都是decrease的话，需要再反转一次。

*/

class Solution {
    public int[] findPermutation(String s) {
        int n = s.length();
        int[] res = new int[n+1];
        for(int i=1; i<=(n+1);i++) res[i-1] = i;
        
        int head=0, tail = 0;
        while(tail <n){
            while(tail<n && s.charAt(tail) == s.charAt(head)){
                tail++;
            }
            if(s.charAt(head) == 'D'){
                swap(res, head, tail);
            }
            head = tail;
            tail++;
        }

        if(head<n&&s.charAt(head) == 'D'){
                swap(res, head, tail);
        }
        
        return res; 
    }
    
    public void swap(int[] list, int start, int end){
        while(start<end){
            int t = list[end];
            list[end] = list[start];
            list[start] = t;
            start++;
            end--;
        }
    }
}