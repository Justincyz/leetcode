/*49. Group Anagrams
Medium: 
Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]

Note:

All inputs will be in lowercase.
The order of your output does not matter.

*/

/*解题思路
题目要求我们吧所有用同一组字符组成的string归类。直接用HashMap+ sort做就可以了。
我看到有的面试会问，如果不能用hashMap怎么办。我认为可以用sort + Trie tree的形式
储存也可以。

*/

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for(String s : strs){
            char[] c = s.toCharArray();
            Arrays.sort(c);
            String temp = new String(c);
            if(!map.containsKey(temp)) map.put(temp, new ArrayList());
            map.get(temp).add(s);
        }
        List<List<String>> res = new ArrayList<>();
        for(Map.Entry<String, List<String>> entry: map.entrySet()){
            res.add(entry.getValue());
        }
        return res;
    }
}