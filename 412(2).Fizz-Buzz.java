/*412. Fizz Buzz
链接：
Easy: 
Write a program that outputs the string representation of numbers from 1 to n.

But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.

Example:

n = 15,

Return:
[
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
]
*/

/*解题思路
简单的一道题

*/
class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();
        for(int i=1; i<=n; i++){
            if(i%3 ==0 && i%5 ==0){
                res.add("FizzBuzz");
            }else if( i%3 == 0){
                res.add("Fizz");
            }else if( i%5 == 0){
                res.add("Buzz");
            }else{
                res.add(String.valueOf(i));
            }
        }
        return res;
    }
}