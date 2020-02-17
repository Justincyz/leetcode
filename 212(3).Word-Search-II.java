/*212. Word Search II
Hard
DGiven a 2D board and a list of words from the dictionary, find all words in the 
board.

Each word must be constructed from letters of sequentially adjacent cell, where 
"adjacent" cells are those horizontally or vertically neighboring. The same letter 
cell may not be used more than once in a word.

 

Example:

Input: 
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
words = ["oath","pea","eat","rain"]

Output: ["eat","oath"]
*/

/*
两个关键步骤，
(1)用Trie Tree结构储存要查找的单词
(2)DFS + BackTracking查找matrix中每一个字母(注意是对每一个位置都做DFS)。
用字母作为root在Trie中看是否有可以reach的单词
*/

class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        TreeNode root =  buildTree(words);    
        List<String> res = new ArrayList<>();
        for(int i =0; i< board.length; i++){
            for(int j =0; j < board[0].length; j++){
                dfs(board, i, j, root, res);
            }
        }
        return res;     
    }
    
    public void dfs(char[][] board, int i, int j, TreeNode p, List<String> res){
        char c = board[i][j];
        if(c == '#' || p.next[c-'a'] == null) return;
        p = p.next[c-'a'];
        if(p.word != null){
            res.add(p.word);
            p.word = null;
        }
        
        board[i][j] = '#';
        if(i > 0) dfs(board, i-1, j, p, res);
        if(j > 0) dfs(board, i, j-1, p, res);
        if(i < board.length-1) dfs(board, i+1, j, p, res);
        if(j < board[0].length-1) dfs(board, i, j+1, p, res);
        board[i][j] = c;
    }
    
    public TreeNode buildTree(String[] words){
        TreeNode root = new TreeNode();
        
        for(String word : words){
            TreeNode p = root;
            for(int i=0; i<word.length(); i++){
                char c = word.charAt(i);
                int pos = c-'a';
                if(p.next[pos] == null){
                     p.next[pos] = new TreeNode();
                }
                
                p = p.next[pos];
            }
            p.word = word;
        }
        return root;
    }
}
    
class TreeNode{
    TreeNode[] next = new TreeNode[26];
    String word;
}

/*
时隔四个月又写一次
*/


class Solution {
    int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
    
    public List<String> findWords(char[][] board, String[] words) {
        Node root = new Node();
        for(String s: words){
            char[] array = s.toCharArray();
            Node dummy = root;
            for(char c: array){
                if(dummy.list[c-'a'] == null) dummy.list[c-'a'] = new Node();
                dummy = dummy.list[c-'a'];
            }
            dummy.hasWord = true;
            dummy.word = s;
        }
    
       
        List<String> res = new ArrayList<>();
        for(int i=0; i< board.length;i++){
            for(int j =0; j< board[0].length; j++){
                if(root.list[board[i][j]-'a'] !=null){
                    char c = board[i][j];
                    board[i][j] = '#';
                    dfs(board, i, j, res, root.list[c-'a']);
                    board[i][j] = c;
                }
            }         
        }

        return res;
    }
    
    public void dfs(char[][] board, int i, int j, List<String> res, Node root){
        if(root.hasWord == true){
            root.hasWord = false;
            res.add(root.word);
        }
        
        for(int[] dir: dirs){
            int x = i+dir[0];
            int y = j+dir[1];
            if(x<0 || y<0 || x>=board.length || y >= board[0].length  || board[x][y] == '#'|| root.list[board[x][y]-'a'] == null) continue;
            char c= board[x][y];
            board[x][y] = '#';
            dfs(board, x, y, res, root.list[c-'a']);
            board[x][y] = c;
        }  
    }
}


class Node{
    Node[] list = new Node[26];
    boolean hasWord = false;
    String word ="";
    public Node(){}
    
}