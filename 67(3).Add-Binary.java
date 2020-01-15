/*67. Add Binary

Easy
链接: https://leetcode.com/problems/add-binary/
Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:

Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"


*/

/*解题思路


*/

class Solution {
    public String addBinary(String a, String b) {
        StringBuffer sb = new StringBuffer();
        char[] l1 = a.toCharArray(), l2 = b.toCharArray();
        
        int i=l1.length-1, j = l2.length-1, carry = 0;
        
        while(i >= 0|| j >=0){
            if(i >=0 && j>=0){
                int t = l1[i]-'0' + l2[j]-'0'+carry;
                sb.append(t%2);
                carry = t/2;
                i--; j--;
            }else if(i >=0){
                int t = l1[i]-'0' +carry;
                sb.append(t%2);
                carry = t/2;
                i--;
            }else{
                int t = l2[j]-'0'+carry;
                sb.append(t%2);
                carry = t/2;
                j--;
            }
        }
        if(carry >0) sb.append(1);
        return sb.reverse().toString();
    }
}