/*17. Letter Combinations of a Phone Number
Medium
链接: https://leetcode.com/problems/letter-combinations-of-a-phone-number/
Given a string containing digits from 2-9 inclusive, return all possible 
letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given 
below. Note that 1 does not map to any letters.

//此处应该有图片，就是电话号码的拨盘上面每个数字有对应的几个字母字母，

Example:
Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
*/

/*解题思路
简单的dfs

*/

class Solution {
    List<String> list;
    String[] chara = {"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    public List<String> letterCombinations(String digits) {
        list = new ArrayList<>();
        if(digits.length() == 0) return list;
        helper(digits, 0, "");
        return list;
    }
    
    public void helper(String digits, int idx, String s){
        if(idx == digits.length()){
            list.add(s);
            return;
        }
        
        int num = digits.charAt(idx)-'0';
        String st = chara[num-2];
        for(int i=0; i< st.length(); i++){
            helper(digits, idx+1, s+st.charAt(i)+"");
        }
    }
}