/*76. Minimum Window Substring
链接：https://leetcode.com/problems/minimum-window-substring/
Hard: 

Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:
Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
Note:

If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
*/

/*解题思路


*/


class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for(char c: t.toCharArray()){
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        
        int fast =0, slow = 0, num = t.length(), len = Integer.MAX_VALUE;
        String res = "";
        
        while(fast < s.length()){
            char c = s.charAt(fast);
            
            if(map.containsKey(c)){
                map.put(c, map.get(c)-1);
                if(map.get(c) >= 0) num--;
            }
         
            if(num == 0){
                char temp = s.charAt(slow);
                while((!map.containsKey(temp) || map.get(temp) <0) && slow < fast){
                    if(map.containsKey(temp)){
                        map.put(temp, map.get(temp)+1);
                        if(map.get(temp) >0) num++;
                    }
                    slow++;
                    temp = s.charAt(slow);
                }
                if(fast-slow+1 < len){
                    len = fast-slow+1;
                    res = s.substring(slow, fast+1);
                }
            }           
            
            fast++;
        }
        
        
        return res;
    }
}