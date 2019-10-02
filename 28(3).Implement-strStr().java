/*28. Implement strStr()
链接：https://leetcode.com/problems/implement-strstr/
Easy: 
Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:

Input: haystack = "hello", needle = "ll"
Output: 2
Example 2:

Input: haystack = "aaaaa", needle = "bba"
Output: -1
*/

/*解题思路
直接一个个的对比整个string就可以了，注意边界条件

*/
class Solution {
    public int strStr(String haystack, String needle) {
        if(needle.length() == 0) return 0;
        if(haystack.length() == 0 || haystack.length() < needle.length() ) return -1;
      

        for(int i=0; i<= (haystack.length()-needle.length()) ; i++){
            if(haystack.charAt(i) == needle.charAt(0)){
                if(haystack.substring(i, i+needle.length()).equals(needle)) return i;
            }
        }
        
        return -1;
    }
}