/*953. Verifying an Alien Dictionary
Easy: 
In an alien language, surprisingly they also use english lowercase letters, 
but possibly in a different order. The order of the alphabet is some 
permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of 
the alphabet, return true if and only if the given words are sorted
 lexicographicaly in this alien language.

Example 1:
Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is 
sorted.

Example 2:
Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1],
 hence the sequence is unsorted.

Example 3:
Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is 
shorter (in size.) According to lexicographical rules "apple" > "app", 
because 'l' > '∅', where '∅' is defined as the blank character which is 
less than any other character (More info).
*/

/*解题思路
这道题目虽然是一个easy的题目，但是题目本身比较长。题意如下，给出一组单词和一个字母顺顺序表(26)。
这个字母顺序表是乱序排放的，我们要比较单词是不是按照这个字母顺序表代表的顺序排放的。
两个同样前缀的单词，短的要放前面。其实题目本身不难，注意细节就可以了。

*/

class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        if(words.length ==0 || words.length ==1) return true;
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0; i<order.length(); i++){
            map.put(order.charAt(i), i);
        }
        
        for(int i=1; i<words.length; i++){
            char[] w1 = words[i-1].toCharArray();
            char[] w2 = words[i].toCharArray();
            int j;
            for(j=0; j<w1.length && j< w2.length;j++){
                if(w1[j] == w2[j]) continue;
                if(map.get(w1[j]) < map.get(w2[j])) break;
                else return false;
            }
            if(j == w1.length || j == w2.length){
                if(w1.length < w2.length) continue;
                else return false;
            }
        }
        
        return true;
    }
}