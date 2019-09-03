/*639. Decode Ways II
Hard: 

A message containing letters from A-Z is being encoded to numbers using the following mapping way:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the numbers from 1 to 9.

Given the encoded message containing digits and the character '*', return the total number of ways to decode it.

Also, since the answer may be very large, you should return the output mod 109 + 7.

Example 1:
Input: "*"
Output: 9
Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".
Example 2:
Input: "1*"
Output: 9 + 9 = 18
Note:
The length of the input string will fit in range [1, 105].
The input string will only contain the character '*' and digits '0' - '9'.
*/

/*解题思路
这道题目是根据九章算术DP章节学习到的，有任何疑惑可以参考九章视频。
这道题目实际上复杂的点就是要分很多种不同的情况，具体的可以看comment
*/

class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        long[] dp = new long[n+1];
        dp[0] =1;
        
        for(int i=1; i<=n; i++){
            dp[i] = dp[i-1];//首先这一位继承上一位的所有组合方式。
            /*
			这里我们先考虑第i-1位自己。dp[i] = dp[i-1]*s.charAt(i-1)。注意
			如果是第i-1位的话，那么就是和前面的dp[i-1]位相乘，dp[i-1]代表
			的是截止到 i-2位substring可以组合的形式
            */
            dp[i] *= checkChar(s.charAt(i-1));
            if(i>1){
            	/*我们这里考虑的是第i-1和第i-2位作为一个整体。同样，因为我们
				考虑了两位，所以是结果和dp[i-2]相乘。也就是截止到i-3位之前所有
				substring的组合方式相乘
				*/
                dp[i] += (dp[i-2]*checkChar2(s.charAt(i-2), s.charAt(i-1)));
            }

            dp[i] %= 1000000007;
        }
        
        return (int)dp[n];
    }
    
    public long checkChar(char a){
        if(a == '0') return 0;
        else if(a =='*') return 9;
        else return 1;
    }
    
    public long checkChar2(char a, char b){
        if(a == '1'){
            if(b >= '0' && b <= '9') return 1; // 10 - 19, 一种解
            else return 9;  // 1*, 九种解
        }
        else if(a == '2'){
            if(b>='0' && b <= '6') return 1;  //20-26，一种解
            else if(b>='7' && b <= '9') return 0; //27-29 无解
            else return 6;  // 2*, 六种解
        }
        else if(a == '*'){
            if(b >='0' && b <= '6') return 2;  //*0 - *6, 两种解，因为* 要不等于一，要不等于2
            else if(b >='7' && b <= '9') return 1; // 此时*只能等于1
            else return 15; // 可以组合成10-26 所以是15种
        } 
        //如果开头是其他数字的话，直接返回0，因为不能是30多，40多这样
        else{
            return 0;
        }
    }
}