/*6. ZigZag Conversion
链接：https://leetcode.com/problems/zigzag-conversion/
Medium: 
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);
Example 1:

Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"
Example 2:

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:

P     I    N
A   L S  I G
Y A   H R
P     I
*/

/*解题思路
对每一行创建一个arraylist，然后按顺序遍历就好了。注意开头的时候让
goingDown为false, 因为当curRow首次触及0的时候会自动转变方向。
接下来就是如果触底就转方向就好了

*/

class Solution {
    public String convert(String s, int numRows) {
        if(numRows == 1) return s;
        
        List<StringBuffer> rows = new ArrayList<>();
        for(int i=0; i< Math.min(numRows, s.length());i++){
            rows.add(new StringBuffer());
        }
        
        int curRow = 0;
        boolean goingDown = false;
        
        for(char c: s.toCharArray()){
            rows.get(curRow).append(c);
            if(curRow == 0 || curRow == numRows -1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }
        
        StringBuffer sb = new StringBuffer();
        for(StringBuffer row : rows) sb.append(row);
        return sb.toString();
    }
}

//跟上面一样的，只是用了character list而已
class Solution {
    public String convert(String s, int numRows) {
         if(numRows == 1) return s;
        List<List<Character>> list = new ArrayList<>();
        for(int i=0; i< numRows; i++) list.add(new ArrayList());
        
        int curLevel =0;
        boolean goingDown = false;
        for(char c: s.toCharArray()){
            list.get(curLevel).add(c);
            if(curLevel == numRows-1 || curLevel ==0) goingDown = !goingDown;
            curLevel += goingDown? 1: -1;
        }
        StringBuffer sb = new StringBuffer();
        for(List<Character> t: list){
           // StringBuffer p = new StringBuffer();
            for(Character c: t){
                sb.append(c);
             //   p.append(c);
            }
           // System.out.println(p);
        }
        
        return sb.toString();
    }
}