/*126. Word Ladder II
链接：https://leetcode.com/problems/word-ladder-ii/
Hard: 
Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return an empty list if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output:
[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: []

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
*/

/*解题思路
这道题目是一道很经典的题目，是word ladder I的follow up的问题。这道题和I的区别
主要是让我们输出所有最短的路径。直接在代码里面标注吧。
主要我们要证明，为什么按照下述方法到达的是最短节点。
我们对所有遍历过的节点都添加到我们的map当中了，假设第n层有某一个单词X可以变化
到n之前某一层的某一个单词Y, 那么假设这条路径成立并且可以到达最终的
endWord，但是这一条路径一定比原来的直接从Y到达endWord更长，因为其中路径长度
增加了从X->Y这一步，所以肯定不是最短的路径了，直接pass掉就好。

*/
class Solution {
    List<List<String>> res;
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        res = new ArrayList<>();
        //unvisted代表的是还没有被遍历过的节点有哪一些
        Set<String> unvisited = new HashSet<>(wordList);
        //当前的节点可以连接到下一层的哪一些节点
        Map<String, List<String>> map = new HashMap<>();
        //当前层的节点有哪一些
        Set<String> cur = new HashSet<>();
        //第一层只有起始单词
        cur.add(beginWord);
        
        //检查是否可以直达endWord
        boolean find = false;
        
        while(!find && !cur.isEmpty()){
            //先将当前层的所有节点从未遍历的节点中去掉
            unvisited.removeAll(cur);
            //为当前层简历一个hashset
            Set<String> next = new HashSet<>();

            for(String s : cur){
                //为当前节点在map中创建新的arraylist
                map.put(s, new ArrayList());
                char[] list = s.toCharArray();
                
                //对每一个位置改变一次字符
                for(int i=0; i< list.length; i++){
                    char t = list[i];
                    for(char c ='a'; c<='z'; c++){
                        list[i] = c;
                        String nxtStr = new String(list);
                        //必须要是没有被遍历过才可以
                        if(unvisited.contains(nxtStr)){
                            map.get(s).add(nxtStr);
                            next.add(nxtStr);
                            if(nxtStr.equals(endWord)) find = true;
                        }
                    }
                    list[i] = t; 
                }
            }
            cur = next;
        }
        
        if(!find) return res;

        /*我们现在构建的树中，只要从startWord开始查找，做dfs，找到的endWord
        之间的路径，就一定是最短的路径*/
        List<String> carry = new ArrayList<>();
        carry.add(beginWord);
        dfs(beginWord, endWord, carry, map);
        return res;
    }
    
    public void dfs(String beginWord, String endWord, List<String> carry, Map<String, List<String>> map){
        if(beginWord.equals(endWord)){
            List<String> t = new ArrayList<>(carry);
           
            res.add(t);
        }
        //不一定所有的都是通路
        if(!map.containsKey(beginWord)) return;
        List<String> next = map.get(beginWord);
        
        //dfs+backtrack
        for(String s: next){
            int size = carry.size();
            carry.add(s);
            dfs(s, endWord, carry, map);
            carry.remove(size);
        }
    }
}