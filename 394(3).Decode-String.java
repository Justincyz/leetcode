/*394. Decode String
Medium:

Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string 
inside the square brackets is being repeated exactly k times. Note 
that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white 
spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain 
any digits and that digits are only for those repeat numbers, k. For 
example, there won't be input like 3a or 2[4].

Examples:
s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
*/

/*解题思路
第二个办法是我做的，第一个是别人写的，思路大同小异。这道题有两个注意点，第一个还是说小心嵌套的关系。
第二个就是找连续的数字，比如说 12[a]，那么12要作为整体来考虑，所以每次要读全了。
前两个都是迭代的做法，第三个是递归的做法。很巧妙。还是摘抄一段说明: "把一个括号中的所有内容看做一个
整体，一次递归函数返回一对括号中解码后的字符串。给定的编码字符串实际上只有四种字符，数字，字母，左括号，
和右括号。那么我们开始用一个变量i从0开始遍历到字符串的末尾，由于左括号都是跟在数字后面，所以首先遇到的
字符只能是数字或者字母，如果是字母，直接存入结果中，如果是数字，循环读入所有的数字，并正确转换，那么下一
位非数字的字符一定是左括号，指针右移跳过左括号，对之后的内容调用递归函数求解，注意我们循环的停止条件是
遍历到末尾和遇到右括号，由于递归调用的函数返回了子括号里解码后的字符串，而我们之前把次数也已经求出来了，
那么循环添加到结果中即可"


*/

class Solution {
    public String decodeString(String s) {
        if(s==null)
            return s;
        Stack<Integer> digit = new Stack<>();
        Stack<String> characters = new Stack<>();
        String str = "";
        int i=0;
        while(i<s.length()){
            if(Character.isDigit(s.charAt(i))){
                int c = 0;
                while(Character.isDigit(s.charAt(i))){
                    c = 10*c+(s.charAt(i)-'0');
                    i++;
                }
                digit.push(c);
            }
            else if(s.charAt(i)=='['){
                characters.push(str);
                str = "";
                i++;
            }
            else if(s.charAt(i)==']'){
                StringBuilder temp = new StringBuilder (characters.pop());
                int count = digit.pop();
                for(int j=0;j<count;j++)
                    temp.append(str);
                str = temp.toString();
                i++;
            }
            else
                str += s.charAt(i++);
        }
        return str;
    }
}


//1ms
class Solution {
    public String decodeString(String s) {
        StringBuffer sb = new StringBuffer();
        Stack<String> stack = new Stack();
        
        char[] list = s.toCharArray();
        String number = ""; //可能出现两位数甚至三位数
        for(char c: list){
            if( c== '['){
                stack.push(number);
                stack.push(Character.toString(c));
                number = "";
            }
            else if(c == ']'){
                String str = "", str1 ="";
                while(!stack.isEmpty() && !stack.peek().equals("[")){
                    str = str+stack.pop();
                }
                stack.pop(); //pop 掉多余的 "["
                int repeat = Integer.valueOf(stack.pop());
                
                while(--repeat>=0){
                    str1 += str;
                }
             
                stack.push(str1);
            }else{
                if(Character.isDigit(c)) number+=(Character.toString(c));
                else stack.push(Character.toString(c));
            }
        }
        while(!stack.isEmpty()) sb.append(stack.pop());
        return sb.reverse().toString();
    }
}

//递归求解
class Solution {
    int pos =0;
    public String decodeString(String s) {
        return decode(s);
    }

    public String decode(String s){
        String res = "";
        int n = s.length();
        while(pos < n && s.charAt(pos) !=']'){
            if(s.charAt(pos) < '0' || s.charAt(pos)>'9'){
                res+=s.charAt(pos);
                pos++; //skip the '['
            }else{
                int cnt = 0;
                
                while(s.charAt(pos) >='0' && s.charAt(pos) <='9'){
                    cnt =(cnt*10) + (s.charAt(pos++)-'0');
                }
                pos++; 
                
                String follow = decode(s);
                pos++;
                
                while(cnt-- >0) res+=follow;
            }
        }
        return res;
    }
}

// 2019/9/26 做法
class Solution {
    public String decodeString(String s) {
        Deque<String> q = new LinkedList();
        char[] str = s.toCharArray();
        StringBuffer sb = new StringBuffer();
        String nums = "";
        for(int i=0; i<str.length; i++){
            if(!String.valueOf(str[i]).equals("]")){
                if(String.valueOf(str[i]).equals("[")){
                    q.addLast(nums);
                    String c= String.valueOf(str[i]);
                    q.addLast(c);
                    nums = "";
                }else if('0' <= str[i] && str[i]<='9'){
                    //数字有可能大于一位
                    nums += String.valueOf(str[i]);
                }else{
                    //平常的字母
                    q.addLast(String.valueOf(str[i]));
                }
                
            }
            else{
                StringBuffer temp = new StringBuffer();
                String t = "";
                while(!q.isEmpty() && !q.peekLast().equals("[")){          
                    t=q.removeLast()+t;
                }
                q.removeLast();
                int num = Integer.valueOf(q.removeLast());
               
                while(num--> 0) temp.append(t);
                
                //如果q是空的的话，直接添加到结果中
                if(q.isEmpty()){
                    sb.append(temp);
                } 
                else q.addLast(temp.toString());
            }
        }
        
        while(!q.isEmpty()) sb.append(q.removeFirst());
        return sb.toString();
    }
}