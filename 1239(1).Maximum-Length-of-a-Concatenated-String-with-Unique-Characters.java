/*1239. Maximum Length of a Concatenated String with Unique Characters
链接：https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
Medium:

Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.

Return the maximum possible length of s.

Example 1:
Input: arr = ["un","iq","ue"]
Output: 4
Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
Maximum length is 4.

Example 2:
Input: arr = ["cha","r","act","ers"]
Output: 6
Explanation: Possible solutions are "chaers" and "acters".

Example 3:
Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
Output: 26
 
Constraints:
1 <= arr.length <= 16
1 <= arr[i].length <= 26
arr[i] contains only lower case English letters.
*/

/*解题思路 bit manipulation + backtracking
题目给我们了一个string链表。让我们用这些string组合，并且找到组合长度最大，且没有重复
字符出现的string组合。这个string链表中的字符串可能本身就存在重复字符，所以这一点要注意。
这道题目有两个比较费时间的地方。第一个是两个字符串比较的时间。
第二个是每一次我们考虑新的字符串的时候，都要和前一步valid的所有字符串进行对比。
后来看了tag之后发现这是一个 Bit manipulation +backtrack的题目。那么我们就可以想到，首先
我们可以用32位的整数来代表每一个String中出现了什么字符。比如说， ab的话，我们第一位mark
成1代表a出现过，第二位mark成1代表b出现过。所以这个整数就变成了 "..000011"。
每一次两个字符串只要做一个("&")和运算 ，如果结果为0，说明两个字符串没有共同存在的元素。如果不为0,
说明起码有一位重合了。 假设没有重复元素的话，这两个字符串做一个("|")或运算，就相当于两个
String的元素合并在一起了。然后再带入下一层进行运行。

beats 95%

*/

class Solution {
    int lengthOfLongestString =0;
    
    public int maxLength(List<String> arr) {
        backtrack(arr, 0, 0);
        return lengthOfLongestString;
    }
    
    public void backtrack(List<String> arr, int pt, int binaryMarkerTotal){
        lengthOfLongestString = Math.max(lengthOfLongestString, countBits(binaryMarkerTotal));
        
        if(pt == arr.size()) return;
        
        for(int i=pt; i<arr.size(); i++){
        	//将字符串转化为二进制标记
            int binaryMarker = binaryMarkerOfString(arr.get(i));
            
            //这个说明当前字符串本身就存在重复字符，我们不能用这个
            if(countBits(binaryMarker) != arr.get(i).length()) continue;
           
            //如果这两个字符串没有共同元素，则带入下一层运算
            if((binaryMarker & binaryMarkerTotal) == 0){
                backtrack(arr, i+1, binaryMarker | binaryMarkerTotal);
            }
        }
    }
    //二进制标记字符串
    public int binaryMarkerOfString(String s){
        int binary =0;
        for(char c: s.toCharArray()){
            binary |= 1<<(c-'a');
        }
        return binary;
    }
    

    //计算string当中出现1的个数
    public int countBits(int n){
        int count = 0; 
        while (n > 0) { 
            count += n & 1; 
            n >>= 1; 
        } 
        return count; 
    } 
}