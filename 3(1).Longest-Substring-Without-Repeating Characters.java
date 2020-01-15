/*3. Longest Substring Without Repeating Characters
Medium
链接: https://leetcode.com/problems/longest-substring-without-repeating-characters/
Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.


*/

/*解题思路
题目问我们最长不重复的子串。一道滑动窗口的题目，还有进阶的题目;
比如说340： 	Longest Substring with At Most K Distinct Characters    
比较简单，利用hashset记录已经遍历过的元素
*/

class Solution {
    public int lengthOfLongestSubstring(String s) {
        char[] list = s.toCharArray();
        Set<Character> set = new HashSet<>();
        int res =0,  l=0;
        for(int r=0; r<list.length; r++){
            char c = list[r];
            if(set.contains(c)){
                res = Math.max(res, r-l);
                while(l < r && s.charAt(l) !=c){
                    set.remove(s.charAt(l));
                    l++;    
                } 
                l++;
               
            }else{
                set.add(c);
            }
        }
        res = Math.max(res,  set.size());
        return res;
    }
}