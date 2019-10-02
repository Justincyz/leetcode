/*127. Word Ladder
Medium
链接: 

Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

*/

/*解题思路
这道题目就是用bfs来解就好了，把所有的字母转换看成一张图

*/

class Solution {
    int shortest = Integer.MAX_VALUE;
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>();
        List<String> graph = new ArrayList<>();
        Set<String> visited = new HashSet();
        
        for(String word : wordList) set.add(word);
        if (!set.contains(endWord)) return 0;
           
        graph.add(beginWord);
        visited.add(beginWord);
        helper(endWord, set, visited, graph, 1);
        return shortest == Integer.MAX_VALUE? 0: shortest;
    }
    
    public void helper(String e, Set<String> wordList, Set<String> visited, List<String> graph, int time){
        if(graph.size() == 0) return;
       
        List<String> newGraph = new ArrayList<>();
        
        for(String g : graph){
            StringBuffer sb = new StringBuffer(g);
            for(int i=0; i< g.length(); i++){
                char c = g.charAt(i);
                for(char j='a'; j< 'z'; j++){
                    sb.setCharAt(i, j);
                    if(j == c) continue;
                    if(!visited.contains(sb.toString()) && wordList.contains(sb.toString())){
                        if(sb.toString().equals(e)){
                            shortest =  time+1;
                            return;
                        }
                        newGraph.add(sb.toString());
                        visited.add(sb.toString());
                    }
                    sb.setCharAt(i,c);
                }
            }
        }
        helper(e, wordList, visited, newGraph, time+1);
    }
}