/*208. Implement Trie (Prefix Tree)
Medium
链接: https://leetcode.com/problems/implement-trie-prefix-tree/
Implement a trie with insert, search, and startsWith methods.

Example:
Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // returns true
trie.search("app");     // returns false
trie.startsWith("app"); // returns true
trie.insert("app");   
trie.search("app");     // returns true
Note:

You may assume that all inputs are consist of lowercase letters a-z.
*/

/*
没啥可说的，设计 Trie Tree. 这道题只需要支持 a-z, 如果是有乱码的话就需要用到hashmap了
*/

class Trie {
    Node root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new Node();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node dummy = root;
        for(char c: word.toCharArray()){
            if(dummy.child[c-'a'] == null) dummy.child[c-'a'] = new Node();
            dummy = dummy.child[c-'a'];   
        }
        dummy.hasWord = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node dummy = root;
        for(char c: word.toCharArray()){
            if(dummy.child[c-'a'] == null) return false;
            dummy = dummy.child[c-'a'];   
        }
        return dummy.hasWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node dummy = root;
        for(char c: prefix.toCharArray()){
            if(dummy.child[c-'a'] == null) return false;
            dummy = dummy.child[c-'a'];   
        }
        return true;
    }
    
    class Node{
        boolean hasWord = false;
        Node[] child = new Node[26];
        
        public Node(){}
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

