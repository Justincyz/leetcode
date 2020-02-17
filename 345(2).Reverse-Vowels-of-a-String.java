/*345. Reverse Vowels of a String
链接：https://leetcode.com/problems/reverse-vowels-of-a-string/
Easy: 
Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:

Input: "hello"
Output: "holle"
Example 2:

Input: "leetcode"
Output: "leotcede"
*/

/*解题思路
大小写都包括

*/


class Solution {
    public String reverseVowels(String s) {
        char[] list = s.toCharArray();
        Set<Character> set = new HashSet<>();
        set.add('a'); set.add('e');set.add('i');set.add('o');set.add('u');
        set.add('A'); set.add('E');set.add('I');set.add('O');set.add('U');
        int l = 0, r = s.length()-1;
        while(l < r){
            while(!set.contains(list[l]) && l < r){
                l++;
            }
            while(!set.contains(list[r]) && l < r){
                r--;
            }
            char c = list[l];
            list[l] = list[r];
            list[r] = c;
            l++;
            r--;
        }
        
        return new String(list);
    }
}




class Solution {
    public String reverseVowels(String s) {
        int start = 0, end = s.length()-1;
        char[] list = s.toCharArray();
        
        while(start < end){
            char c1 = list[start];
            char c2 = list[end];
            if((check(c1) && check(c2))){
                swap(list, start, end);
                start++;
                end--;
            }else if(check(c1)) end--;
            else if(check(c2)) start++;
            else{
                start++;
                end--;
            }
        }
        
        return String.valueOf(list);
    }
    
    public void swap(char[] list, int i, int j){
        char c = list[i];
        list[i] = list[j];
        list[j] =c ;
    }
     public boolean check(char c){
        if(c == 'a' || c== 'e' || c== 'i' || c == 'o' || c=='u' || c== 'A' || c=='E'|| c=='I' || c== 'O' || c== 'U'){
            return true;
        }
         return false;
    }
   
}