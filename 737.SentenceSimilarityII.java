/*737. Sentence Similarity II
Medium: Union Find, DFS

Given two sentences words1, words2 (each represented as an array of strings), 
and a list of similar word pairs pairs, determine if two sentences are similar.

For example, 
words1 = ["great", "acting", "skills"] and 
words2 = ["fine", "drama", "talent"] are similar, 
if the similar word pairs are 
pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].

Note that the similarity relation is transitive. For example, if "great" and "good" 
are similar, and "fine" and "good" are similar, then "great" and "fine" are similar.

Similarity is also symmetric. For example, "great" and "fine" being similar is 
the same as "fine" and "great" being similar.

Also, a word is always similar with itself. For example, the sentences w
ords1 = ["great"], words2 = ["great"], pairs = [] are similar, even though 
there are no specified similar word pairs.

Finally, sentences can only be similar if they have the same number of words. 
So a sentence like words1 = ["great"] can never be similar to 
words2 = ["doubleplus","good"].

*/

/*解题思路: Union Find, BFS, DFS
还是一个经典的Union Find题目，将可以连接到一起的pair通过hashmap赋予相同的root. 
下面这种解法就是碉堡了的联合查找Union Find了，这种解法的核心是一个getRoot函数，
如果两个元素属于同一个群组的话，调用getRoot函数会返回相同的值。主要分为两部，第一步是建立群组关系，
suppose开始时每一个元素都是独立的个体，各自属于不同的群组。然后对于每一个给定的关系对，
我们对两个单词分别调用getRoot函数，找到二者的祖先结点，如果从未建立过联系的话，那么二者的祖先结
点时不同的，此时就要建立二者的关系。等所有的关系都建立好了以后，第二步就是验证两个任意的元素是否属
于同一个群组，就只需要比较二者的祖先结点都否相同。我们保存群组关系的数据结构，有时用数组，
有时用哈希map，看输入的数据类型，如果输入元素的整型数的话，用root数组就可以了，如果是像本题这种的
字符串的话，需要用哈希表来建立映射，建立每一个结点和其祖先结点的映射。注意这里的祖先结点不一定是最
终祖先结点，而最终祖先结点的映射一定是最终祖先结点，所以我们的getRoot函数的设计思路就是要找到最终
祖先结点，那么就是当结点和其映射结点相同时返回，否则继续循环，可以递归写，也可以迭代写，这无所谓。
注意这里第一行判空是相当于初始化，这个操作可以在外面写，就是要让初始时每个元素属于不同的群组，

BFS的解法，其实这道题的本质是无向连通图的问题，那么首先要做的就是建立这个连通图的数据结构，
对于每个结点来说，我们要记录所有和其相连的结点，所以我们建立每个结点和其所有相连结点集合之间的映射，
比如对于这三个相似对(a, b), (b, c)，和(c, d)，我们有如下的映射关系：
a -> {b}
b -> {a, c}
c -> {b, d}
d -> {c}

那么如果我们要验证a和d是否相似，就需要用到传递关系，a只能找到b，b可以找到a，c，为了不陷入死循环，
我们将访问过的结点加入一个集合visited，那么此时b只能去，c只能去d，那么说明a和d是相似的了。
那么我们用for循环来比较对应位置上的两个单词，如果二者相同，那么直接跳过去比较接下来的。
否则就建一个访问即可visited，建一个队列queue，然后把words1中的单词放入queue，
建一个布尔型变量succ，标记是否找到，然后就是传统的BFS遍历的写法了，从队列中取元素，
如果和其相连的结点中有words2中的对应单词，标记succ为true，并break掉。否则就将取出的结点加入
队列queue，并且遍历其所有相连结点，将其中未访问过的结点加入队列queue继续循环：
*/

class Solution {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        if(words1.length != words2.length) return false;
        Map<String, String> map = new HashMap<>();
        
        for(int i=0; i<words1.length; i++){
            String word1 = words1[i];
            String word2 = words2[i];
            if(word1.equals(word2)) map.put(word1, word2); //有可能出现某个单词同时出现在words1[]和words[]2中但是不在pairs<>中
        }
        
        for(List<String> pair : pairs){
            String word1 = pair.get(0);
            String word2 = pair.get(1);
          
            String root1 = helper(map, word1);
            String root2 = helper(map, word2);
            if(!root1.equals(root2)){
                map.put(root1, root2);
            }
        }
        
        //是否可以transite和出现的顺序相关，不支持乱序    
        for(int i=0; i<words1.length; i++){
            if(!map.containsKey(words1[i]) || !map.containsKey(words2[i])) return false;
            String root1 = helper(map, words1[i]);
            String root2 = helper(map, words2[i]);
            if(!root1.equals(root2)) return false;
        }
        return true;
    }
    
    public String helper(Map<String, String> map, String word){
        if(!map.containsKey(word)){
            map.put(word, word);
            return word;
        }
        while(!map.get(word).equals(word)){
            word = map.get(word);
        }
        return word;
    }
}