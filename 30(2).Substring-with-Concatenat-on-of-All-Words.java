/*30. Substring with Concatenation of All Words
链接：https://leetcode.com/problems/substring-with-concatenation-of-all-words/
Hard: 
You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

 

Example 1:

Input:
  s = "barfoothefoobarman",
  words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.
Example 2:

Input:
  s = "wordgoodgoodgoodbestword",
  words = ["word","good","best","word"]
Output: []
*/

/*解题思路
https://leetcode.wang/leetCode-30-Substring-with-Concatenation-of-All-Words.html
这是O(n)的做法，比我用的O(n^2)快很多，但是比较复杂

*/


//常规O(N^2)的解法，比较慢
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> list = new ArrayList<>();
        if(s.length() == 0 || words.length == 0) return list;
        
        int p = 0, len = words[0].length(), total = words.length;
        if(s.length() < len*words.length) return list;
        
        Map<String, Integer> map = new HashMap<>();
        for(String word: words) map.put(word, map.getOrDefault(word, 0)+1);

        while(p <=s.length()){
            if((p+words.length*len) >s.length()) break;
            Map<String, Integer> temp = new HashMap<>(map);
           
            int tp = p, t = total;
            while(tp+len <=s.length() && temp.containsKey(s.substring(tp,tp+len))){
                String subStr = s.substring(tp,tp+len);
                temp.put(subStr, temp.get(subStr)-1);
                if(temp.get(subStr) == -1) break;
                tp +=len;
                t--;
            }
         
            if(t==0){
                list.add(p);
            }

            p++;
        }
        return list;
    }
}