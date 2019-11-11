/*1170. Compare Strings by Frequency of the Smallest Character
链接：https://leetcode.com/problems/compare-strings-by-frequency-of-the-smallest-character/
Easy: 
Let's define a function f(s) over a non-empty string s, which calculates the frequency of the smallest character in s. For example, if s = "dcce" then f(s) = 2 because the smallest character is "c" and its frequency is 2.

Now, given string arrays queries and words, return an integer array answer, where each answer[i] is the number of words such that f(queries[i]) < f(W), where W is a word in words.

 

Example 1:

Input: queries = ["cbd"], words = ["zaaaz"]
Output: [1]
Explanation: On the first query we have f("cbd") = 1, f("zaaaz") = 3 so f("cbd") < f("zaaaz").
Example 2:

Input: queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
Output: [1,2]
Explanation: On the first query only f("bbb") < f("aaaa"). On the second query both f("aaa") and f("aaaa") are both > f("cc").
*/

/*解题思路
这道题目说的好像很复杂，让我们找到queries中每个单词最小字母出现的次数，
同时在words中找每个单词最小字母出现的次数，对每个queries最小字母出现次数，
在words中找到所有比他大的值有多少。听起来很拗口。实际上是这样的。
两个整型数组，在对于A中的每一位，我们计算B中比这一位大的数字有多少个。
题目给出了每个单词长度不超过12， 所以我们只需要最多十二位的数组，来
记录1-12之间出现数字的次数。


*/
class Solution {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] res = new int[queries.length];
        int[] w = new int[12];

        //计算相对应位置出现的次数。
        for(String word: words){
            w[getFreq(word)]++;    
        }
        
        //w[i]代表的是比i大的数字总共有多少。
        for(int i=9; i>=0; i--){
            w[i] =w[i]+ w[i+1];
        }
        
        //我们找到所有比f大的个数，也就是w[f+1]代表比f大个数的总和
        for(int i=0; i<queries.length; i++){
            int f = getFreq(queries[i]);
            res[i] = w[f+1];
        }
      
        
        return res;
    }
    
    public int getFreq(String s){
        
        int[] res = new int[26];
        for(char c : s.toCharArray()){
            res[c-'a']++;
        }
        for(int i=0; i<26; i++){
            if(res[i] !=0){
                return res[i];
            }
        }
        return 0;
        
    }
}