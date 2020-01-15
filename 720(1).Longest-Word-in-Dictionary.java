/*720. Longest Word in Dictionary
链接：https://leetcode.com/problems/longest-word-in-dictionary/
Easy: 
Given a list of strings words representing an English Dictionary, find the longest word in words that can be built one character at a time by other words in words. If there is more than one possible answer, return the longest word with the smallest lexicographical order.

If there is no answer, return the empty string.
Example 1:
Input: 
words = ["w","wo","wor","worl", "world"]
Output: "world"
Explanation: 
The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
Example 2:
Input: 
words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
Output: "apple"
Explanation: 
Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".
Note:

All the strings in the input will only contain lowercase letters.
The length of words will be in the range [1, 1000].
The length of words[i] will be in the range [1, 30].
*/

/*解题思路
这道题挺有意思的，问我们 a->ab->abc 这样都出现在words中的单词最长的那一个
有多长，如果结果在同样长度不止一个，找到lexiorder较小的那一个单词。我们先给
words来排个序，根据长度来排序。如果长度为1或者当前单词的前一个形式出现在了
我们的hashset中，那么我们进去if 条件来判断，如果当前单词长度比结果长，
则替换掉结果。如果长度一样，那么我们找lexi-order小的那一个单词。记得还要将
当前单词加入到结果中，以便后续查找。

*/
class Solution {
    public String longestWord(String[] words) {
        Set<String> set = new HashSet<>();
        //Arrays.sort(words, (a, b) -> a.length() - b.length());
        Arrays.sort(words,new Comparator<String>(){
            public int compare(String a, String b){
                return a.length() - b.length();
            }
        });
        String res = "";
        for(int i =0; i< words.length; i++){
            String s = words[i];
            if(s.length()==1 || set.contains(s.substring(0, s.length()-1))){
                set.add(s);
                if(res.length()==s.length()){
                    if(res.compareTo(s) > 0) res = s;
                }else{
                    res = s;
                }
            }
            
        }
        return res;
    }
}


//一开始写的，用了dfs, 因为是从后面往前面找，速度比较慢。
class Solution {
    public String longestWord(String[] words) {
        Set<String> set = new HashSet<>();
        for(String s: words) set.add(s);
        
        String res = "";
        for(int i = words.length-1; i>=0; i--){
            String s = words[i];
           
            if(dfs(s, set)){
                if(s.length() == res.length()){
                    if(s.compareTo(res) < 0) res = s;
                }else if(s.length() > res.length()){
                    res = s;
                }
            }
        }
        return res;
    }
    
    public boolean dfs(String s, Set<String> set){
        if(s.length()==1) return true;
        
        String next = s.substring(0, s.length()-1);
        if(set.contains(next)){
            return dfs(next, set);
        }else{
            return false;
        }
    }
}