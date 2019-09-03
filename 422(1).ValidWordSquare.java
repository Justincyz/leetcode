/*422. Valid Word Square
Easy: 
Given a sequence of words, check whether it forms a valid word square.
A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).

Note:
The number of words given is at least 1 and does not exceed 500.
Word length will be at least 1 and does not exceed 500.
Each word contains only lowercase English alphabet a-z.

Example 1:
Input:
[
  "abcd",
  "bnrt",
  "crmy",
  "dtye"
]

Output:
true

Explanation:
The first row and first column both read "abcd".
The second row and second column both read "bnrt".
The third row and third column both read "crmy".
The fourth row and fourth column both read "dtye".

Therefore, it is a valid word square.

Example 2:
Input:
[
  "abcd",
  "bnrt",
  "crm",
  "dt"
]

Output:
true

Explanation:
The first row and first column both read "abcd".
The second row and second column both read "bnrt".
The third row and third column both read "crm".
The fourth row and fourth column both read "dt".

Therefore, it is a valid word square.

Example 3:
Input:
[
  "ball",
  "area",
  "read",
  "lady"
]

Output:
false

Explanation:
The third row reads "read" while the third column reads "lead".

Therefore, it is NOT a valid word square.
*/

/*解题思路
这道题目虽然是一道easy的题目，但是想做到最优解还是比较麻烦的，就在这里放一个简单
解好了，第一个着重于每个字符的判断，同时判断是否两个字符串可以匹配。第二个直接用
stringbuffer将竖行的string先保存下来(如果有空格也保存)，比如下面这个例子。最后一次性比较
[
  a b c d,
  b n r t,
  c r,
  d t m
]

*/

class Solution {
    public boolean validWordSquare(List<String> words) {
        if(words==null || words.size()==0){
            return true;
        }
        
        int n = words.size();
        for(int i=0;i<n;i++){
            for(int j=0;j<words.get(i).length();j++){
                //j>=n ->上下无法匹配，words.get(j).length()<=i ->左右无法匹配
                if(j>=n || words.get(j).length()<=i || words.get(j).charAt(i) != words.get(i).charAt(j)){
                    return false;
                }
            }
        }
        
        return true;
    }
}


class Solution {
    public boolean validWordSquare(List<String> words) {
        int start =0;
        for(String s: words){
            int i = 0;
            StringBuffer sb = new StringBuffer();
            while(i < words.size()){
                if(start<words.get(i).length()){
                    sb.append(words.get(i).charAt(start));
                }else{
                    sb.append(" ");
                }
                i++;
            }
            //trim()是用来去掉尾部的空格
            if(!s.equals(sb.toString().trim())){
                return false;
            } 
            start++;
        }
        
        return true;
    }
}