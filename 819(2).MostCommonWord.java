/* 819. Most Common Word
Easy: 
Given a paragraph and a list of banned words, return the most frequent 
word that is not in the list of banned words.  It is guaranteed there 
is at least one word that isn't banned, and that the answer is unique.

Words in the list of banned words are given in lowercase, and free of 
punctuation.  Words in the paragraph are not case sensitive.  The answer
 is in lowercase.

 

Example:

Input: 
paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
banned = ["hit"]
Output: "ball"
Explanation: 
"hit" occurs 3 times, but it is a banned word.
"ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph. 
Note that words in the paragraph are not case sensitive,
that punctuation is ignored (even if adjacent to words, such as "ball,"), 
and that "hit" isn't the answer even though it occurs more because it is banned.

*/

/*解题思路
题目要求我们输出给定句子中出现频率最高的单词，且此单词不能出现在另外一个banned的List中。
没有什么特别的难点，我们直接用hashmap储存每个单词和它出现的次数，同时检查这个但是是否在
banned list中。没有必要等到所有单词都遍历完再找出现频率最高的，我们可以一边遍历一边查找。

*/

class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        if(paragraph.length() == 0) return "";
        Set<String> set = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();
        set.addAll(Arrays.asList(banned));
        
        String res ="";
        int occur = 0;
        for(int i=0; i< paragraph.length(); i++){
            StringBuffer sb = new StringBuffer();
            while(i<paragraph.length() && Character.isLetter(paragraph.charAt(i))){
                sb.append(paragraph.charAt(i));
                i++;
            }
            
            String s = sb.toString();
            s = s.toLowerCase();
            if(s.length() >0 && !set.contains(s)){
                map.put(s, map.getOrDefault(s, 0)+1);
                if(occur < map.get(s)){
                    occur = map.get(s);
                    res = s;
                }
            }
        }
        return res;
    }
}


//简洁但是比较慢的做法
class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        HashMap<String, Integer> map = new HashMap<>();
        Set<String> ban = new HashSet<>();
        
        ban.addAll(Arrays.asList(banned));
        String[] para = paragraph.split("\\W+");
        int max=0;
        String res = "";
        for(int i=0; i< para.length;i++){
            String temp = para[i].toLowerCase();    
            if(ban.contains(temp)) continue;
            map.put(temp, map.getOrDefault(temp, 0)+1);  
            if(map.get(temp) > max){
                res = temp;
                max = map.get(temp);
            }           
        }
        
        return res;
    }
}