/*604. Design Compressed String Iterator
链接：https://leetcode.com/problems/design-compressed-string-iterator/
Easy: 
Design and implement a data structure for a compressed string iterator. It should support the following operations: next and hasNext.

The given compressed string will be in the form of each letter followed by a positive integer representing the number of this letter existing in the original uncompressed string.

next() - if the original string still has uncompressed characters, return the next letter; Otherwise return a white space.
hasNext() - Judge whether there is any letter needs to be uncompressed.

Note:
Please remember to RESET your class variables declared in StringIterator, as static/class variables are persisted across multiple test cases. Please see here for more details.

Example:

StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");

iterator.next(); // return 'L'
iterator.next(); // return 'e'
iterator.next(); // return 'e'
iterator.next(); // return 't'
iterator.next(); // return 'C'
iterator.next(); // return 'o'
iterator.next(); // return 'd'
iterator.hasNext(); // return true
iterator.next(); // return 'e'
iterator.hasNext(); // return false
iterator.next(); // return ' '
*/

/*解题思路
这道题其实没有什么太难的，唯一要注意的地方的就是可能数字
会大于一位，所以每一次要读全所有的数字。

*/
class StringIterator {
    String s;
    int begin = 0, end =0, count = 0;
    public StringIterator(String compressedString) {
        s = compressedString;
    }
    
    public char next() {
        if(!hasNext()) return ' ';
        if(count == 0){
            begin = end;
            end++;
            while(end < s.length() && Character.isDigit(s.charAt(end))){
                end++;
            }
            count = Integer.valueOf(s.substring(begin+1, end));
        }
        count--;
        return s.charAt(begin);
    }
    
    public boolean hasNext() {
        if(end == s.length() && count == 0) return false;
        else return true;
    }
}

/**
 * Your StringIterator object will be instantiated and called as such:
 * StringIterator obj = new StringIterator(compressedString);
 * char param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */