/*12. Integer to Roman
链接：https://leetcode.com/problems/integer-to-roman/
Medium: 
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9. 
X can be placed before L (50) and C (100) to make 40 and 90. 
C can be placed before D (500) and M (1000) to make 400 and 900.
Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.

Example 1:

Input: 3
Output: "III"
Example 2:

Input: 4
Output: "IV"
Example 3:

Input: 9
Output: "IX"
Example 4:

Input: 58
Output: "LVIII"
Explanation: L = 50, V = 5, III = 3.
Example 5:

Input: 1994
Output: "MCMXCIV"
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
*/

/*解题思路
这道题目让我们把普通的十进制数转换成罗马数字。主要的难点就是在遇到4,9,40,90,400,900
的时候，需要采用一个减法的操作。那这里直接用一个很鸡贼的方式，直接把这些数字也添加
进我们的数组中。那么我们只需要顺序遍历我们建立好的数组，每一次减掉相应位置的数字就可以了。

*/


//用hashmap 稍慢, 8ms
class Solution {
    public String intToRoman(int num) {
        
        int[] value = new int[]{1,4, 5,9, 10,40,50,90,100,400,500,900,1000};
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "I");
        map.put(4, "IV");
        map.put(5, "V");
        map.put(9, "IX");
        map.put(10, "X");
        map.put(40, "XL");
        map.put(50, "L");
        map.put(90, "XC");
        map.put(100, "C");
        map.put(400, "CD");
        map.put(500, "D");
        map.put(900, "CM");
        map.put(1000, "M");
       
        StringBuffer sb = new StringBuffer();
        for(int i=value.length-1; i>=0; i--){
            int val = value[i];
            if(num == 0) break;
            if(num/val !=0){
                int time = num/val;
             
                while(time-- > 0){
                    sb.append(map.get(val));
                    num-=(val);
                }
            }
            else{       
                continue;
            }
           
        }
        
        
        return sb.toString();
    }
}

//用双数组，较快， 3ms
class Solution {
	public String intToRoman(int num){
		if (num < 1 || num > 3999) return "";
		
		StringBuilder result = new StringBuilder();
		
		String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
		int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		
		int i = 0;
	            //iterate until the number becomes zero, NO NEED to go until the last element in roman array
		while (num >0) {
			while ( num >= values[i]) {
				num -= values[i];
				result.append(roman[i]);
			}
			i++;
		}
		return result.toString();
	}
}

//一开始没考虑到可以直接加入特殊数字，所以每一次都用了转换，所以会比较慢
class Solution {
    int[] list1 = {1000,500,100,50,10,5,1};
    String[] list2 = {"M","D","C","L","X","V","I"};
    public String intToRoman(int num) {
        StringBuffer sb = new StringBuffer();
        String n = String.valueOf(num);   
       
        for(int i=0; i<list1.length;i++){
    
            int divisor = list1[i];
            if(num == (num%divisor)) continue;
            char c = String.valueOf(num).charAt(0);

            if(c=='9'){
                sb.append(list2[i+1]);
                sb.append(list2[i-1]);
                num -= list1[i-1]- list1[i+1]; 
            } 
            else if(c=='4'){
                sb.append(list2[i]);
                sb.append(list2[i-1]);
                num -= list1[i-1]- list1[i];
            }else{
                int rep = num/divisor;
                for(int j=0; j<rep; j++){
                    sb.append(list2[i]);
                    num -= list1[i];
                }
            }
        }
        return sb.toString();
    }
}