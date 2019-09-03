/*1048. Longest String Chain
链接：https://leetcode.com/problems/longest-string-chain/
Medium: 

Given a list of words, each word consists of English lowercase letters.

Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.  For example, "abc" is a predecessor of "abac".

A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.

Return the longest possible length of a word chain with words chosen from the given list of words.

 

Example 1:

Input: ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: one of the longest word chain is "a","ba","bda","bdca".
*/

/*解题思路
这道题目给我们一组单词，问我们最长的单词串有多长。单词串的定义是 a->b 那么可以在a单词中
添加任意一个字母变成b单词。我们这道题用的是类似于dp的办法来做。我们与其从前往后，也就是由短的单词往长的单词找，不如长的单词往短的单词转化。每一次我们挪去长单词的一个字母，然后查看相应的单词有没有出现在hashmap中，如果有的话，说明这两个单词之间可以构成链表。此时我们需要更新短单词的值，使得从这个短单词出发可以找到最长的一个父串。我们每一次都检查一下被更新单词的链有多长，最后返回这个最长的链

*/

class Solution {
    public int longestStrChain(String[] words) {
        Arrays.sort(words, new Comparator<String>(){
            public int compare(String a, String b){
                return a.length()-b.length();
            }
        });
        
        HashMap<String, Integer> map = new HashMap<>();
        for(String word: words) map.put(word, 0);
        
        int res = 0;
        int n = words.length;
        for(int i= n-1; i>=0; i--){
            String word = words[i];
            
            for(int j = 0; j<word.length(); j++){
                String temp = word.substring(0,j)+word.substring(j+1, word.length());
                if(!map.containsKey(temp)) continue;
                int t = map.get(temp);
                map.put(temp, Math.max(t, map.get(word)+1));
                res = Math.max(res, map.get(temp));
            }
           
        }
        
        return res+1;
    }
}