/*151. Reverse Words in a String
Medium
链接: https://leetcode.com/problems/reverse-words-in-a-string/

Given an input string, reverse the string word by word.

Example 1:
Input: "the sky is blue"
Output: "blue is sky the"

Example 2:
Input: "  hello world!  "
Output: "world! hello"
Explanation: Your reversed string should not contain leading or trailing spaces.

Example 3:
Input: "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.

*/

/*解题思路
这道题目让我们把string反过来，题目本身不难，唯一要注意的是中间的空格可能
会有不止一个。所以在便利string 数组的时候如果string为空直接跳过。
最后trim()这个方法是将string前后的空位全部去掉 " sss  " ->"sss"

*/
class Solution {
    public String reverseWords(String s) {
        String[] input = s.split(" ");
        StringBuffer sb = new StringBuffer();
        
        for(int i=input.length-1; i>=0; i--){
            if(input[i].equals("")) continue;
            sb.append(input[i]+" ");    
        } 
        
        return sb.toString().trim();
    }
}