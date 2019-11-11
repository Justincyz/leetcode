/*1087. Brace Expansion
链接：https://leetcode.com/problems/brace-expansion/
Medium: 
A string S represents a list of words.

Each letter in the word has 1 or more options.  If there is one option, the letter is represented as is.  If there is more than one option, then curly braces delimit the options.  For example, "{a,b,c}" represents options ["a", "b", "c"].

For example, "{a,b,c}d{e,f}" represents the list ["ade", "adf", "bde", "bdf", "cde", "cdf"].

Return all words that can be formed in this manner, in lexicographical order.

 

Example 1:

Input: "{a,b}c{d,e}f"
Output: ["acdf","acef","bcdf","bcef"]
Example 2:

Input: "abcd"
Output: ["abcd"]
*/

/*解题思路
先用stack把每一组(有括号的后边加一个!作为标记，没有括号的就是直接一个string)。
然后存在list当中，再用backtracking做就完了。一开始看到自己写的
这么长有点心虚，后来发现大家都写的这么长，所以无所谓了。

*/
class Solution {
    public String[] expand(String S) {
        List<String> list = new ArrayList<>();
        Stack<Character> st = new Stack();
        for(int i=0; i< S.length(); i++){
            char c = S.charAt(i);
            if(c == '{'){
                StringBuffer sb = new StringBuffer();
                while(!st.isEmpty()){
                    sb.append(st.pop());
                }
                if(sb.length() != 0){
                    list.add(sb.reverse().toString());
                }
                st.push(c);
            }else if(c == '}'){
                StringBuffer sb = new StringBuffer();
                while(!st.isEmpty() && st.peek() != '{'){
                    sb.append(st.pop());
                }
                st.pop();
                list.add(sb.reverse().append("!").toString());
            }else if(c == ','){
                continue;
            }else{
                st.push(c);
            }
        }
        StringBuffer sb = new StringBuffer();
        while(!st.isEmpty() && st.peek() != '{'){
            sb.append(st.pop());
        }
        if(sb.length() !=0)
            list.add(sb.reverse().toString());
        List<String> res = new ArrayList<>();
        construct(list, res, new StringBuffer(), 0);
        Collections.sort(res);
        String[] myArray = res.toArray(new String[0]);
        return myArray;
    }
    
    public void construct(List<String> list, List<String> res, StringBuffer sb , int idx){
        if(idx == list.size()){
            String r = new String(sb);
            res.add(r);
            return;
        }
        String cur = list.get(idx);

        int len = sb.length();
        if(cur.charAt(cur.length()-1) != '!'){
            construct(list, res, sb.append(cur), idx+1);
        }else{
            for(int i=0; i<cur.length()-1; i++){
                sb.append(cur.charAt(i));
                construct(list, res, sb, idx+1);
                sb.setLength(len);
            }
        }
    }
}