/* 71. Simplify Path
Medium: 

Given an absolute path for a file (Unix-style), simplify it. Or 
in other words, convert it to the canonical path.

In a UNIX-style file system, a period . refers to the current 
directory. Furthermore, a double period .. moves the directory up 
a level. For more information, see: Absolute path vs relative path 
in Linux/Unix

Note that the returned canonical path must always begin with a slash 
/, and there must be only a single slash / between two directory names.
 The last directory name (if it exists) must not end with a trailing /. 
 Also, the canonical path must be the shortest string representing the 
 absolute path.

Example 1:
Input: "/home/"
Output: "/home"
Explanation: Note that there is no trailing slash after the last directory name.

Example 2:
Input: "/../"
Output: "/"
Explanation: Going one level up from the root directory is a no-op, as the root 
level is the highest level you can go.

Example 3:
Input: "/home//foo/"
Output: "/home/foo"
Explanation: In the canonical path, multiple consecutive slashes are replaced 
by a single one.

Example 4:
Input: "/a/./b/../../c/"
Output: "/c"

Example 5:
Input: "/a/../../b/../c//.//"
Output: "/c"

Example 6:
Input: "/a//b////c/d//././/.."
Output: "/a/b/c"
*/

/*解题思路， Stack+Queue, Deque
这道题目不难但是还是有很多坑的。
1. 计数的时候要用stack, 这样在遇到 ".."的时候才能正常pop()上一个directory
2. 要注意如果最后stack为空的时候，直接返回一个 "/". 不然最后悔返回一个空集
3. 要把stack的东西倒到queue里面，因为我们之后用 stringBuffer 粘贴的时候是先
遇到root dir, 再是各个子层级的 dir。如果是直接从stack中取的话结果会是反过来的。
也不可以直接call stringbuffer.reverse(), 否则会连文件名都reverse过来。


当然这道题也可以直接用Deque来做。双向链表
*/

class Solution {
    public String simplifyPath(String path) {
        String[] list = path.split("/");
        StringBuffer sb = new StringBuffer();

        Queue<String> q = new LinkedList();
        Stack<String> st = new Stack();
        for(String c: list){
            if(c.equals("..")){
                if(st.isEmpty()) continue;
                st.pop();
            }else if(c.equals(".") || c.equals("/") || c.equals("")){
                continue;
            } 
            else{st.push(c);} 
            
        }
        if(st.isEmpty()) return "/";
        for(String i: st) q.add(i);
     
        while(!q.isEmpty()){
            sb.append("/");
            sb.append(q.poll());
        }
        return sb.toString();
    }
}

//这是用deque来做的
class Solution {
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0) {
            return "";
        }
        Deque<String> queue = new LinkedList<>();
        String[] dirs = path.split("/");
        for (String dir: dirs) {
            dir = dir.trim();
            if (!dir.equals("") && !dir.equals(".")) {
                if (dir.equals("..")) {
                    if (queue.size() > 0) {
                        queue.removeLast();
                    }
                } else {
                    queue.add(dir);
                }
            }
        }
        
        if (queue.size() == 0) {
            return "/";
        }
        
        StringBuilder ans = new StringBuilder();
        while (queue.size() > 0) {
            ans.append("/").append(queue.remove());
        }
        
        return ans.toString();
    }
}