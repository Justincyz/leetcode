/*1119. Remove Vowels from a String
链接：https://leetcode.com/problems/remove-vowels-from-a-string/
Easy: 
Given a string S, remove the vowels 'a', 'e', 'i', 'o', and 'u' from it, and return the new string.

 

Example 1:

Input: "leetcodeisacommunityforcoders"
Output: "ltcdscmmntyfrcdrs"
Example 2:

Input: "aeiou"
Output: ""
*/

/*解题思路
very straight forward

*/
class Solution {
    public String removeVowels(String S) {
        StringBuffer sb = new StringBuffer();
        for(int i=0; i< S.length(); i++){
            char c = S.charAt(i);
            switch(c){
                case 'a': continue;
                case 'e': continue;
                case 'i': continue;
                case 'o': continue;
                case 'u': continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }
}