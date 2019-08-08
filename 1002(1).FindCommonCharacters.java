/*1002. Find Common Characters
Easy: 
Given an array A of strings made only from lowercase letters, 
return a list of all characters that show up in all strings within the 
list (including duplicates).  For example, if a character occurs 3 
times in all strings but not 4 times, you need to include that character 
three times in the final answer.

You may return the answer in any order.

Example 1:
Input: ["bella","label","roller"]
Output: ["e","l","l"]

Example 2:
Input: ["cool","lock","cook"]
Output: ["c","o"]
*/

/*解题思路
题目要我们找到出现在所有string中的char, 如果同一个char出现了多次那就把所有的次数都输出出来。
因为只有小写字母，所以新建一个长度为26的int[] array就可以了。然后每一次两个string比较，先用
一个临时array记录新string的字符出现次数，然后遍历两个array, 每一位取小值(相当于去重)。
最后输出不为0的位置代表的char就可以了

*/

class Solution {
    public List<String> commonChars(String[] A) {
        int[] list = new int[26];
        Arrays.fill(list, Integer.MAX_VALUE);
        
        for(int i=0; i<A.length; i++){
            String s = A[i];
            int[] temp = new int[26];
            for(int j=0; j<s.length(); j++){
                char c = s.charAt(j);
                temp[c-'a']++;
            }
            for(int k=0; k<26; k++){
                list[k] = Math.min(temp[k], list[k]);
            }
        }
        List<String> res = new ArrayList<>();
        for(int i=0; i<26; i++){
            if(list[i] == 0) continue;
            while(list[i]!=0){
                res.add(String.valueOf((char)(i+'a')));
                list[i]--;
            }
        }
        return res;
    }
}