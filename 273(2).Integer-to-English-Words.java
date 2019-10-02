/*273. Integer to English Words
链接：
Hard: 
Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

Example 1:

Input: 123
Output: "One Hundred Twenty Three"
Example 2:

Input: 12345
Output: "Twelve Thousand Three Hundred Forty Five"
Example 3:

Input: 1234567
Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
Example 4:

Input: 1234567891
Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
*/

/*解题思路
这道题让我们把一个整型数转为用英文单词描述。题目中给足了提示，首先告诉我们要3个一组的进行
处理，而且题目中限定了输入数字范围为0到231 - 1之间，最高只能到billion位，3个一组也只需处
理四组即可，那么我们需要些一个处理三个一组数字的函数，我们需要把1到19的英文单词都列出来，放
到一个数组里，还要把20,30，... 到90的英文单词列出来放到另一个数组里，然后我们需要用写技巧，
比如一个三位数n，百位数表示为n/100，后两位数一起表示为n%100，十位数表示为n%100/10，个
位数表示为n%10，然后我们看后两位数是否小于20，小于的话直接从数组中取出单词，如果大于等于20的
话，则分别将十位和个位数字的单词从两个数组中取出来。然后再来处理百位上的数字，还要记得加上
Hundred。主函数中调用四次这个帮助函数，然后中间要插入"Thousand", "Million", "Billion"到
对应的位置，最后check一下末尾是否有空格，把空格都删掉，返回的时候检查下输入是否为0，是的话要返回'Zero'

*/


class Solution {
    String[] LESS_THAN_TWENTY = {"","One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen","Eighteen", "Nineteen", "Twenty"};
    String[] TWENTY_TO_HUND = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety","Hundred"};
    String[] THOUSANDS = {"", "Thousand","Million","Billion"};
    
    public String numberToWords(int num) {
        if(num == 0) return "Zero";
        
        String res = "";
        int index =0;
        while(num > 0){
            if(num%1000 != 0){        
                res = THOUSANDS[index]+" " +res;
                res = helper(num%1000)+" " + res;
            } 
            index++;
            num/=1000;         
        }
        return res.trim();
    }
    
    public String helper(int num){
        String str = "";

        int val = num%100;

        if(val <=20){
            str += LESS_THAN_TWENTY[val]+" ";    
        }
        else if(val <100){
            str += TWENTY_TO_HUND[val/10] +" ";
            str += LESS_THAN_TWENTY[num%10] +" ";
        }
        
        num/=100;
        if(num >0){
            str = TWENTY_TO_HUND[10] +" "+ str;
            str = LESS_THAN_TWENTY[num]+" " + str;
        }

        return str.trim();
    }
}


/*上下两种方法区别就是Helper()*/


class Solution {
    String[] LESS_THAN_TWENTY = {"","One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen","Eighteen", "Nineteen", "Twenty"};
    String[] TWENTY_TO_HUND = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety","Hundred"};
    String[] THOUSANDS = {"", "Thousand","Million","Billion"};
    
    public String numberToWords(int num) {
        if(num == 0) return "Zero";
        
        String res = "";
        int index =0;
        while(num > 0){
            if(num%1000 != 0){        
                res = THOUSANDS[index]+" " +res;
                res = helper(num%1000)+" " + res;
            } 
            index++;
            num/=1000;         
        }
        return res.trim();
    }
    
    public String helper(int num){
        String str = "";

        int val = num%100;

        if(val <=20){
            str += LESS_THAN_TWENTY[val]+" ";    
        }
        else if(val <100){
            str += TWENTY_TO_HUND[val/10] +" ";
            str += LESS_THAN_TWENTY[num%10] +" ";
        }
        
        num/=100;
        if(num >0){
            str = TWENTY_TO_HUND[10] +" "+ str;
            str = LESS_THAN_TWENTY[num]+" " + str;
        }

        return str.trim();
    }
}


