/*1347. Minimum Number of Steps to Make Two Strings Anagram
链接：https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram/
Medium: 
Given two equal-size strings s and t. In one step you can choose any character of t and replace it with another character.

Return the minimum number of steps to make t an anagram of s.

An Anagram of a string is a string that contains the same characters with a different (or the same) ordering.

 

Example 1:

Input: s = "bab", t = "aba"
Output: 1
Explanation: Replace the first 'a' in t with b, t = "bba" which is anagram of s.
Example 2:

Input: s = "leetcode", t = "practice"
Output: 5
Explanation: Replace 'p', 'r', 'a', 'i' and 'c' from t with proper characters to make t anagram of s.
Example 3:

Input: s = "anagram", t = "mangaar"
Output: 0
Explanation: "anagram" and "mangaar" are anagrams. 
Example 4:

Input: s = "xxyyzz", t = "xxyyzz"
Output: 0
Example 5:

Input: s = "friend", t = "family"
Output: 4
*/

/*解题思路
检查从字符串A转到字符串B需要改变几个字母。首先保证了两个字符串
长度相同。
很简单，计算一下A中多了几个就可以了
*/


class Solution {
    public int minSteps(String s, String t) {
        int[] l1 = new int[26];
        int[] l2 = new int[26];
        for(int i=0; i<s.length(); i++){
            l1[s.charAt(i)-'a']++;
            l2[t.charAt(i)-'a']++;
        }
        
        for(int i=0; i<l1.length; i++){
            l1[i] -= l2[i];
        }
        int res =0;
        
        for(int i: l1) res+=i>0?i:0;
        return res;
    }
}
//可以进一步简化

class Solution {
    public int minSteps(String s, String t) {
        int[] list = new int[26];
       
        for(int i=0; i<s.length(); i++){
            list[s.charAt(i)-'a']++;
            list[t.charAt(i)-'a']--;
        }
        
        int res =0;
        
        for(int i: list) res+=i>0?i:0;
        
        return res;
    }
}