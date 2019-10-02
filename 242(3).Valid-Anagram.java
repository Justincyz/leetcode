/*242. Valid Anagram
链接：https://leetcode.com/problems/valid-anagram/
Easy: 
Given two strings s and t , write a function to determine if t is an anagram of s.

Example 1:

Input: s = "anagram", t = "nagaram"
Output: true
Example 2:

Input: s = "rat", t = "car"
Output: false
Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?


*/

/*解题思路
这道题目因为给出的都是小写字母才可以用int[], 否则的话还是用hashmap靠谱一些

*/
class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;
        int[] a = new int[26];
        for(int i=0; i< s.length(); i++){
            a[s.charAt(i)-'a']++;
            a[t.charAt(i)-'a']--;
        }
        
        for(int i=0; i< 26; i++){
           if(a[i] !=0) return false;
        }
        return true;
    }
}