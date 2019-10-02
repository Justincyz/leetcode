/*168. Excel Sheet Column Title
链接：
Easy: 
Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
    ...
Example 1:

Input: 1
Output: "A"
Example 2:

Input: 28
Output: "AB"
Example 3:

Input: 701
Output: "ZY"
*/

/*解题思路


*/

class Solution {
    public String convertToTitle(int n) {
        StringBuffer sb = new StringBuffer();
        
        int pos =0;
        while(n >0){
            int cur = n%26;
            n = n/26;//直接除
            if(cur == 0){//
                n-=1;//这一步是关键，否则的话当n = 26时，n = n/26 = 1, 还会循环一遍
                sb.append('Z'); 
            }else
                sb.append((char)(cur+'A'-1));  
        }
        
        return sb.reverse().toString();
    }
}