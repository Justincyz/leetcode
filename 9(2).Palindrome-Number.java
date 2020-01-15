/*9. Palindrome Number
Easy
链接: https://leetcode.com/problems/palindrome-number/
Determine whether an integer is a palindrome. An integer is a palindrome 
when it reads the same backward as forward.

Example 1:

Input: 121
Output: true
Example 2:

Input: -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it 
becomes 121-. Therefore it is not a palindrome.
Example 3:

Input: 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.

*/

/*解题思路
这道题目最简单的办法就是直接把整数转化成字符串。直接用两个指着进行对比就好了。
有一个follow up就是说如果不可以转化成字符串的话应该怎么办？那么我们就用一个
新数字来和原来的数字进行比较，因为如果是轴对称的两个数字的话，那么长度除以二也是
轴对称的，比如说34543， 那么我们只要比到中间，就是345从左读和345从右读如果是一样的话
那么就说明是轴对称的。所以while loop的结束语句是 x> res，也就是说不需要要完全的
将原来数字翻转过来。注意最后是返回x == rev 或者 x == rev/10

*/

class Solution {
    public boolean isPalindrome(int x) {
        if (x<0 || (x!=0 && x%10==0)) return false;
        int rev = 0;
        while (x>rev){
            
            rev = rev*10 + x%10;
            x = x/10;
        }
  
        return (x==rev || x==rev/10);
    }
}

class Solution {
    public boolean isPalindrome(int x) {
        String list = String.valueOf(x);
        int l = 0, r = list.length()-1;
        while(l < r){
            if(list.charAt(l) == list.charAt(r)){
                l++;
                r--;
            }else
                return false;
        }
        return true;
    }
}