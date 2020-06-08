/*1433. Check If a String Can Break Another String
Medium
链接: https://leetcode.com/problems/check-if-a-string-can-break-another-string/

Given two strings: s1 and s2 with the same size, check if some permutation of string s1 can break some permutation of string s2 or vice-versa (in other words s2 can break s1).

A string x can break string y (both of size n) if x[i] >= y[i] (in alphabetical order) for all i between 0 and n-1.

 
Example 1:
Input: s1 = "abc", s2 = "xya"
Output: true
Explanation: "ayx" is a permutation of s2="xya" which can break to string "abc" which is a permutation of s1="abc".

Example 2:
Input: s1 = "abe", s2 = "acd"
Output: false 
Explanation: All permutations for s1="abe" are: "abe", "aeb", "bae", "bea", "eab" and "eba" and all permutation for s2="acd" are: "acd", "adc", "cad", "cda", "dac" and "dca". However, there is not any permutation from s1 which can break some permutation from s2 and vice-versa.

Example 3:
Input: s1 = "leetcodee", s2 = "interview"
Output: true
 

Constraints:
s1.length == n
s2.length == n
1 <= n <= 10^5
All strings consist of lowercase English letters.
*/



/*解题思路
这道题目一开始很让人费解，主要是不清楚什么叫做string x break string y。后来才知道是这么一个意思，所有的字符串都是由小写字母组成的。我们按照顺序从a排列到z。我们先用第二个例子来举个例子。
   [a,b,c,d,e,f,g,....,z]
s1: x x     x
s2: x   x x
上面的例子是每个string中每个字符出现的位置，所谓的Break就是看s1中的每个字符是不是都可以在s2中找到一个比大的字符，将s1中的break掉。比如说s1中的a可以被s2中的a break掉，s1中的b可以被s2中的c或者d给break掉。但是s1中的e就没有办法在s2中找到一个在e之后出现的字符将它break掉。同样的我们反过来将s2作为基础参考。
s2中的a可以被s1中的a给break掉，但是s1中的e只能break掉s2中的c或者d中的一个。所以反过来也不能成立。
这样举一个例子就很好理解了。
我们使用两个不同的array分别以s1和s2作为基准记录是否另一个string是否可以break当前的这个string。作为基准的字符串我们在数组对应的位置上-1，作为break的字符串我们在对应的位置上+1。然后我们从后面往前面遍历两个字符串，如果array上累加到某一位的时候为负数了，那么说明没有办法break掉当前的字符串了。我们用两个flag记录两个数组是否有可能被break掉，如果两个都没有办法的话，就返回false, 反之为true.

TIME & SAPCE: O(N)
*/

class Solution {
    public boolean checkIfCanBreak(String s1, String s2) {
        int[] array1 = new int[26], array2 = new int[26];
        for(int i=0; i<s1.length(); i++){
            array1[s1.charAt(i)-'a']--;
            array1[s2.charAt(i)-'a']++;
            array2[s1.charAt(i)-'a']++;
            array2[s2.charAt(i)-'a']--;
        }
        int accumulate1 = 0, accumulate2 =0;
        boolean flag1 = true, flag2 = true;
        
        for(int i=25; i>=0; i--){
            accumulate1 +=array1[i];
            accumulate2 +=array2[i];
            if(accumulate1 < 0) flag1 = false;
            if(accumulate2 < 0) flag2 = false;
        }
        
        return flag1 || flag2;
    }
}