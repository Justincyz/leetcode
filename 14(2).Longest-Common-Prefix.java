/*14. Longest Common Prefix
Easy
链接: https://leetcode.com/problems/longest-common-prefix/

Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:

Input: ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
Note:

All given inputs are in lowercase letters a-z.

*/

/*解题思路
这道题让我们找到最长的前缀，一开始想到的是用trie的结构来做，但是其实根本
不需要这么麻烦的来做。直接用第一个和后面的对比就可以了。

*/

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length ==0) return "";
        String st = strs[0];
        for(int i=1; i< strs.length; i++){
            if(st.length() == 0) return "";
            int min = Math.min(st.length(), strs[i].length());
            /*之所以还是要用一个变量size来记录长度，就是因为
            如果碰到例子比如说[aa,a]的话，我们用第一个作为基准，和第二个进行比较
            的时候可能会出问题   */
            int size = 0;
            for(int j=0; j< min; j++){
                if(st.charAt(j) == strs[i].charAt(j)){
                    size = j+1;//如果可以正确匹配的话，那么这两个的common prefix就是截止到当前
                    continue;
                }else{
                    size = j;//如果某一个字符没有办法正确匹配的话，那么这两个string的common prefix就是截止到上一位也就是j
                    break;
                }
            }
            st = st.substring(0, size);
        }
        return st;
    }
}