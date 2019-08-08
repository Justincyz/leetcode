/*208. Implement Trie (Prefix Tree)
Medium
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

    /** Initialize your data structure here. */
    Node root;
    public Trie() {
        root = new Node();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node temp = root;
        for(char c: word.toCharArray()){
            if(temp.array[c-'a'] == null) temp.array[c-'a'] = new Node();
            temp = temp.array[c-'a'];
            temp.str = word;
        }
        temp.end = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node temp = root;
        for(char c: word.toCharArray()){
            if(temp.array[c-'a'] == null) return false;
            temp = temp.array[c-'a'];
            temp.str = word;
        }
        return temp.end;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node temp = root;
        for(char c: prefix.toCharArray()){
            if(temp.array[c-'a'] == null) return false;
            temp = temp.array[c-'a'];
            temp.str = prefix;
        }
        return temp.str.length() !=0;
    }
}

class Node{
    Node[] array;
    String str;
    boolean end;

    public Node(){
        array = new Node[26];
        str = "";
        end = false;
    }
}

