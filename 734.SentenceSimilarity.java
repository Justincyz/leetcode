/*734. Sentence Similarity
Easy, HashMap

Given two sentences words1, words2 (each represented as an array of strings), and a
list of similar word pairs pairs, determine if two sentences are similar.

For example, "great acting skills" and "fine drama talent" are similar, if 
the similar word pairs are pairs = [["great", "fine"], ["acting","drama"], 
["skills","talent"]].

Note that the similarity relation is not transitive. For example, if "great" 
and "fine" are similar, and "fine" and "good" are similar, "great" and "good" 
are not necessarily similar.

However, similarity is symmetric. For example, "great" and "fine" being similar 
is the same as "fine" and "great" being similar.
Also, a word is always similar with itself. For example, the sentences
 words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though 
 there are no specified similar word pairs.

Finally, sentences can only be similar if they have the same number of words. 
So a sentence like words1 = ["great"] can never be similar to 
words2 = ["doubleplus","good"].
*/

/*解题思路
最重要的就是注意细节。可以用hashSet来代替hashmap来把pair变得扁平化。通过构造string = "word1"+"#"+"word2"
来构造唯一的string。然后最后遍历数组，在hashset中查找是否有一样的string。
*/



class Solution {
    public boolean areSentencesSimilar(String[] words1, String[] words2, List<List<String>> pairs) {
        if(words1.length != words2.length) return false;
        HashMap<String, ArrayList<String>> map = new HashMap<>();

        for(int i=0; i<words1.length; i++){
            String word1 = words1[i];
            String word2 = words2[i];
            if(!map.containsKey(word1)) map.put(word1, new ArrayList());
            if(!map.containsKey(word2)) map.put(word2, new ArrayList());
            map.get(word1).add(word1);
            map.get(word2).add(word2);
        }
        
        for(List<String> pair: pairs){
            if(!map.containsKey(pair.get(0))) map.put(pair.get(0), new ArrayList());
            if(!map.containsKey(pair.get(1))) map.put(pair.get(1), new ArrayList());
            map.get(pair.get(0)).add(pair.get(1));
            map.get(pair.get(1)).add(pair.get(0));
        }

        for(int i=0; i<words1.length; i++){
            String s1 = words1[i];
            String s2 = words2[i];
            if(s1.equals(s2)) continue;
            List<String> temp = map.get(s1);
            boolean flag = false;
            for(String s: temp){
                if(s.equals(s2)){
                    flag = true;
                    continue;
                } 
            }
            if(!flag) return false;
            
        }
        return true;
    }
}

//使用hashSet, 加快速度
class Solution {
    public boolean areSentencesSimilar(String[] words1, String[] words2, List<List<String>> pairs) {
        
        if(words1 == null && words2 == null || words1.length == 0 && words2.length == 0){
            return true;
        }
        if(words1.length != words2.length){
            return false;
        }
        
        HashSet<String> set = new HashSet<>();
        for(int i = 0; i < pairs.size(); i++){
            set.add(pairs.get(i).get(0) + '#' + pairs.get(i).get(1));
        }
        
        for(int i = 0 ; i < words1.length; i++){
            if(!((words1[i].equals(words2[i])) ||
                 (set.contains(words1[i] + '#' + words2[i])) || 
                 (set.contains(words2[i] + '#' + words1[i])))){
                return false;        
            }
        }
        return true;
    }
}