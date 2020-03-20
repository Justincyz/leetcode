/*318. Maximum Product of Word Lengths
链接：https://leetcode.com/problems/maximum-product-of-word-lengths/
Medium: 
Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

Example 1:

Input: ["abcw","baz","foo","bar","xtfn","abcdef"]
Output: 16 
Explanation: The two words can be "abcw", "xtfn".
Example 2:

Input: ["a","ab","abc","d","cd","bcd","abcd"]
Output: 4 
Explanation: The two words can be "ab", "cd".
Example 3:

Input: ["a","aa","aaa","aaaa"]
Output: 0 
Explanation: No such pair of words.
*/

/*解题思路
这道题给我们了一个单词数组，让我们求两个没有相同字母的单词的长度之积的最大值。
用mask 来做，因为题目中说都是小写字母，那么只有26位，一个整型数int有32位，
我们可以用后26位来对应26个字母，若为1，说明该对应位置的字母出现过，那么每个单
词的都可由一个int数字表示，两个单词没有共同字母的条件是这两个int数想与为0

*/
class Solution {
    public int maxProduct(String[] words) {
     
        int[] array = new int[words.length];
        for(int i=0; i< words.length; i++){
            String word = words[i];
            int mask =0;
            for(char c: word.toCharArray()){
                //将二进制整数正确的位置设为1
                mask |=(1 << (c-'a'));
            }
            array[i] = mask;
        }
    
        int max =0;
        for(int i=0; i<words.length; i++){
            int m1 = array[i];
            for(int j = i+1; j< words.length; j++){
                int m2 = array[j];
                if((m1 & m2) == 0) max = Math.max(max, words[i].length()*words[j].length());
            }
        }
        return max;
    }
}

//可以再简化一点点
class Solution {
    public int maxProduct(String[] words) {
     
        int[] array = new int[words.length];
        int max =0;
        for(int i=0; i< words.length; i++){
            String word = words[i];
            int mask =0;
            for(char c: word.toCharArray()){
                //将二进制整数正确的位置设为1
                mask |=(1 << (c-'a'));
            }
            array[i] = mask;
            for(int j = 0; j< i; j++){
                int m = array[j];
                if((mask & m) == 0) max = Math.max(max, words[i].length()*words[j].length());
            }
        }
    
        return max;
    }
}

//这是一开始想到的办法，后来改进了，将一个26位的数组改成一个整数。
class Solution {
    public int maxProduct(String[] words) {
     
        Map<String, int[]> map = new HashMap<>();
        for(String word: words){
            int[] array = new int[26];
            for(char c: word.toCharArray()){
                array[c-'a']++;
            }
            map.put(word, array);
        }
        
        int max =0;
        for(int i=0; i<words.length; i++){
            int[] w1 = map.get(words[i]);
            for(int j = i+1; j< words.length; j++){
                int[] w2 = map.get(words[j]);
                boolean flag = false;
                for(int k=0; k<26; k++){
                    if(w1[k]!=0 && w2[k] != 0){
                        flag = true;
                        break;
                    }
                }
                
                if(!flag) max = Math.max(max, words[i].length()*words[j].length());
            }
        }
        return max;
    }
}