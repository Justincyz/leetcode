/*1181. Before and After Puzzle
链接：https://leetcode.com/problems/before-and-after-puzzle/
Medium: 
Given a list of phrases, generate a list of Before and After puzzles.

A phrase is a string that consists of lowercase English letters and spaces only. No space appears in the start or the end of a phrase. There are no consecutive spaces in a phrase.

Before and After puzzles are phrases that are formed by merging two phrases where the last word of the first phrase is the same as the first word of the second phrase.

Return the Before and After puzzles that can be formed by every two phrases phrases[i] and phrases[j] where i != j. Note that the order of matching two phrases matters, we want to consider both orders.

You should return a list of distinct strings sorted lexicographically.

 

Example 1:

Input: phrases = ["writing code","code rocks"]
Output: ["writing code rocks"]
Example 2:

Input: phrases = ["mission statement",
                  "a quick bite to eat",
                  "a chip off the old block",
                  "chocolate bar",
                  "mission impossible",
                  "a man on a mission",
                  "block party",
                  "eat my words",
                  "bar of soap"]
Output: ["a chip off the old block party",
         "a man on a mission impossible",
         "a man on a mission statement",
         "a quick bite to eat my words",
         "chocolate bar of soap"]
Example 3:

Input: phrases = ["a","b","a"]
Output: ["a"]
*/

/*解题思路 HashMap + HashSet
这道题让我们找，如果两个句子首尾单词一样，则可以连接成为一个
新的句子。题目还是比较容易的，注意第三个例子，要避免单独一个 b 
和自己构成首尾相连的句子。最后要按照字母顺序输出句子

*/
class Solution {
    public List<String> beforeAndAfterPuzzles(String[] phrases) {
        List<String> res = new ArrayList<>();
        Map<String, List<Integer>> firstWord = new HashMap<>();
       
        for(int i=0; i< phrases.length; i++){
            String[] str = phrases[i].split(" ");
            if(!firstWord.containsKey(str[0])) firstWord.put(str[0], new ArrayList());
            firstWord.get(str[0]).add(i);
        }
        
        Set<String> set = new HashSet<>();
        for(int i=0; i< phrases.length; i++){
            String[] str = phrases[i].split(" ");
            if(!firstWord.containsKey(str[str.length-1])) continue;
            List<Integer> temp = firstWord.get(str[str.length-1]);
            
            for(Integer j : temp){
                if(j ==i){ //防止单个的元素，例如["a", "a"]自己和自己重复计算
                    continue;    
                } 
                str[str.length-1] = "";
                String store = String.join(" ", str) + phrases[j];
                set.add(store);
            }
        
        }
        res.addAll(set);
        Collections.sort(res);
        return res;
    }
}