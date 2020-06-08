/*383. Ransom Note
Easy
链接: https://leetcode.com/problems/ransom-note/
Given an arbitrary ransom note string and another string containing letters from all the magazines, write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.

Each letter in the magazine string can only be used once in your ransom note.


Note:
You may assume that both strings contain only lowercase letters.

canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true
*/


/*解题思路
这道题目没有什么特别的，相当于给我们两个string, 问第一个字符串当中的所有字符是不是被第二个字符串所囊括了。
我们用hashmap把第一个字符串当中的字符储存起来，然后在第二个字符串当中相应的减少就可以了。如果某一个字符在第一个字符串当中并没有出现，或者出现的次数少于在第二个字符串当中出现的次数，那么就直接返回false;
*/

class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();
        for(char c: magazine.toCharArray()){
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        
        for(char c: ransomNote.toCharArray()){
            if(!map.containsKey(c) || map.get(c) == 0){
                return false;
            }else{
                map.put(c, map.get(c)-1);
            }
        }
        return true;
    }
}