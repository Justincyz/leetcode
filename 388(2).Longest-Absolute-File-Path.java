/*388. Longest Absolute File Path
链接：https://leetcode.com/problems/longest-absolute-file-path/
Medium: 
Suppose we abstract our file system by a string in the following manner:

The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:

dir
    subdir1
    subdir2
        file.ext
The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.

The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:

dir
    subdir1
        file1.ext
        subsubdir1
    subdir2
        subsubdir2
            file2.ext
The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.

We are interested in finding the longest (number of characters) absolute path to a file within our file system. For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).

Given a string representing the file system in the above format, return the length of the longest absolute path to file in the abstracted file system. If there is no file in the system, return 0.

Note:
The name of a file contains at least a . and an extension.
The name of a directory or sub-directory will not contain a ..
Time complexity required: O(n) where n is the size of the input string.

Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.
*/

/*解题思路
这道题给了我们一个字符串代表一个目录结构。让我们找到某一个最长的文件路径。要注意的是，最长绝对文件路径不一定是要最深的路径。 这个最长指的是名字最长，就像上面给出来的例子一样。同时，如果一个子目录
当中压根没有文件，那么整个子目录都不算。
字符串当中有\n代表下一层级，\t 的个数代表第几层级

题目要求我们在O(N)的时间复杂度下完成。我们可以用stack来做这个。
首先stack的高度代表了目录的层级，stack的元素是每一个层级累计
到目前为止的路径名字长度。这样我们就很容易可以计算出来最长的文件路径
有多长了。

*/
class Solution {
   public int lengthLongestPath(String input) {
        Stack<Integer> stack = new Stack<>();
        int maxLen = 0;
        stack.push(0);
        for(String s:input.split("\n")) {
            int level = s.lastIndexOf("\t")+1;
            while(stack.size() >= level) stack.pop();
            //这里加一指的是每一个元素后面要加上一个反斜杠"\"
            int newLen = stack.peek() + s.length() - level + 1;
            stack.push(newLen);

            if(s.contains(".")) maxLen = Math.max(maxLen, newLen-1);
        }

        return maxLen;

    }
}


//这个方法是第一次写的，差不多

class Solution {
    public int lengthLongestPath(String input) {
        if(!input.contains(".")) return 0; //如果一个目录当中没有文件，那么无论怎样都不算
        String[] list = input.split("\n");

        int maxLen = 0;
        Stack<Node> fileStructure = new Stack();
        
        for(String dirOrFile : list){         
            int depthOfFile = dirOrFile.lastIndexOf("\t")+1;
            int len = dirOrFile.length() - depthOfFile+1;

            while(fileStructure.size() > depthOfFile){
                fileStructure.pop();
            } 
            
            if(!fileStructure.isEmpty()){
                len+= fileStructure.peek().length;
            } 
            
            Node node = new Node(depthOfFile, len);
            fileStructure.push(node);
            if(dirOrFile.contains(".")){
                maxLen = Math.max(maxLen, len);
            }
                
        }
        return maxLen-1;
    }
}

class Node{
    int depthOfFile =0;
    int length = 0;
    public Node(int depthOfFile, int length){
        this.depthOfFile = depthOfFile;
        this.length = length;
    }
}