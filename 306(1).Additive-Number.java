/*306. Additive Number
链接：https://leetcode.com/problems/additive-number/
Medium: 
Additive number is a string whose digits can form additive sequence.

A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.

Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.

Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

Example 1:
Input: "112358"
Output: true
Explanation: The digits can form an additive sequence: 1, 1, 2, 3, 5, 8. 
             1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8

Example 2:
Input: "199100199"
Output: true
Explanation: The additive sequence is: 1, 99, 100, 199. 
             1 + 99 = 100, 99 + 100 = 199
 
Constraints:
num consists only of digits '0'-'9'.
1 <= num.length <= 35
*/

/*解题思路  backtrack
这道题目让我们验证一个由0-9构成的字符串，至少含有三个数字，除去前两个数外，每个
数字都是前面两个数字的和，题目中给了许多例子，也限定了一些不合法的情况，比如两
位数以上不能以0开头等等，让我们来判断一个数是否是加法数。目光犀利的童鞋应该一眼
就能看出来，这尼玛不就是斐波那契数组么，跟另一道 Split Array into Fibonacci 
Sequence 简直不要太像啊。只不过那道题要返回一种组合方式，而这道题只是问能否拆成
斐波那契数列。

我们直接用backtrack来解决这个题目就好了。 定义的方程backtrack中，p (previous)代表前一位数，
pp(previous-previous)代表再前面一位数。
首先我们要保证最后再验证的时候有三位数。那么就用一个flag代表p位和pp
位有存入值。这样如果遇到第三位的时候，此时flag就会保持true. 否则backtrack
存在只将string拆成两个部分的可能，如果此时pos == num.length()则为false。

接下来就是第二个问题，因为样例中会有大于Integer.MAX_VALUE的值，那么就有
两个办法来解决。第一个是改用Long来存数值。第二个办法就是每次都一位一位的遍历相加
p和pp两个string。最后和cur进行对比看是否一致。我选择了第二个办法因为这样子
更加稳妥一些。
做出来是0ms beats 100%
*/


class Solution {
    public boolean isAdditiveNumber(String num) {
        if(num.length() <3) return false;
        return backtrack(num, 0, "","", false);
    }
    
    public boolean backtrack(String num, int pos, String p, String pp, boolean flag){
        if(pos == num.length()){
            if(flag) return true; //flag说明前面存在 a+b = c的情况，而不是单纯的 a+b然后就到了pos == num.length
            else return false;
        }
        
        for(int i=pos+1; i<=num.length() ; i++){
            String cur = num.substring(pos, i);
            if(cur.length()>1 && cur.charAt(0) == '0') continue;
            if(pp.equals("")){                    
                if(backtrack(num, i, p, cur, false)) return true;
            }
            else if(p.equals("")){
                if(backtrack(num, i, cur, pp, false)) return true;
            }
            else{
                if(check(p, pp, cur)){// p+pp = cur
                    if(backtrack(num, i,  cur, p, true)) return true;
                }
            }
        }
        return false;
    }
    
    public boolean check(String p, String pp, String cur){
        int t1 = p.length()-1, t2 = pp.length()-1,carry =0;
        StringBuffer sb = new StringBuffer();
        while(t1 >=0 || t2 >=0){
            int sum = carry;
            if(t1 >=0) sum+=p.charAt(t1--)-'0';
                
            if(t2 >=0) sum+=pp.charAt(t2--)-'0';
                
            carry = sum/10;
            sb.append(sum%=10);
        }
        if(carry !=0) sb.append(carry);
        return sb.reverse().toString().equals(cur);
    }
}