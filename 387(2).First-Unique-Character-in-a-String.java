/*387. First Unique Character in a String

链接：https://leetcode.com/problems/first-unique-character-in-a-string/submissions/
Easy: 
Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.
*/

/*解题思路


*/
class Solution {
    public int firstUniqChar(String s) {
        int[] array = new int[26];
        for(int i=0; i< s.length(); i++){
            array[s.charAt(i)-'a']++;
        }
        
        for(int i=0; i< s.length(); i++){
            if(array[s.charAt(i)-'a'] == 1) return i;
        }
        return -1;
    }
}