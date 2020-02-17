/*211. Add and Search Word - Data structure design
Medium
Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing 
only letters a-z or .. A . means it can represent any one letter.

Example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
*/

/*
还是经典的Trie Tree设计。唯一的难点就是，当遇到.的时候就遍历整个TrieNode[] 看看有没有返回true的
*/
//2/5/2020 自己的更新版本，更加快了

class WordDictionary {
    Node root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new Node();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        char[] list = word.toCharArray();
        Node dummy = root;
        for(char c: list){
            if(dummy.array[c-'a'] == null){
                dummy.array[c-'a'] = new Node();
            }
            dummy = dummy.array[c-'a'];
        }
        dummy.word = word;
        dummy.hasWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        char[] list = word.toCharArray();
        Node dummy = root;
        return dfs(list, dummy, 0);
    }
    
    public boolean dfs(char[] list, Node dummy, int idx){
        if(dummy == null) return false;
        if(idx == list.length){
            //一开始这里又一次判断错误, 不是idx == list.length-1
            return dummy.hasWord;
        }
        char c = list[idx];
        if(c == '.'){
            Node[] array = dummy.array;
            for(int i=0; i< 26; i++){
                if(dfs(list, array[i], idx+1)) return true;
            }
        }else{
            return dfs(list, dummy.array[c-'a'], idx+1);
        }
        
        return false;
    }
    
    public class Node{
        Node[] array = new Node[26];
        String word = "";
        boolean hasWord = false;
        public Node(){}
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */





//第一次版本
public class WordDictionary {
    class TrieNode {
        TrieNode[] child = new TrieNode[26];
        boolean isWord = false;
    }
    TrieNode root = new TrieNode();
    public void addWord(String word) {
        TrieNode p = root;
        for (char c : word.toCharArray()) {
            if (p.child[c - 'a'] == null) p.child[c - 'a'] = new TrieNode();
            p = p.child[c - 'a'];
        }
        p.isWord = true;
    }

    public boolean search(String word) {
        return helper(word, 0, root);
    }
    
    private boolean helper(String s, int index, TrieNode p) {
        if (index >= s.length()) return p.isWord;
        char c = s.charAt(index);
        if (c == '.') {
            for (int i = 0; i < p.child.length; i++)
                if (p.child[i] != null && helper(s, index + 1, p.child[i]))
                    return true;
            return false;
        } else return (p.child[c - 'a'] != null && helper(s, index + 1, p.child[c - 'a']));
    }
}