/*387. First Unique Character in a String

链接：https://leetcode.com/problems/first-unique-character-in-a-string/submissions/
Easy: 
Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.
*/

/*解题思路
这道题目给我们一个字符串，这个字符串只有小写字母。问我们第一个出现且只出现过一次的元素是什么。比如说第一个例子，l是第一个出现的单独元素。第二个例子里，l和o都在后面又出现过，所以第一个出现的元素就是v。

这道题目因为都是小写字母，所以我们可以创造一个长度为26的数组。然后累计每一个元素出现的次数。然后再从头到尾遍历一次Input string，这样第一个在array[]当中只出现过一次的元素就是我们要找的元素。

*/
class Solution {
    public int firstUniqChar(String s) {
        int[] array = new int[26];
        for(int i=0; i< s.length(); i++){
            array[s.charAt(i)-'a']++;
        }
        
        for(int i=0; i< s.length(); i++){
            if(array[s.charAt(i)-'a'] == 1) return i;
        }
        return -1;
    }
}

/*这是第一次写的比较复杂的方法，使用了hashmap记录每一个元素与其对应的出现
的位置。如果某一个元素出现超过两次，则不管他。如果只出现了一次的话，我们
就拿它的位置和结果的位置进行比较*/
class Solution {
    public int firstUniqChar(String s) {
        Map<Character, List<Integer>> map = new HashMap<>();
        
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(!map.containsKey(c)) map.put(c, new LinkedList());
            map.get(c).add(i);
        }
        
        int idx = s.length();
        for(Map.Entry<Character, List<Integer>> entry: map.entrySet()){
            if(entry.getValue().size() > 1) continue;
            idx = Math.min(idx, entry.getValue().get(0));
        }
        
        return idx == s.length() ? -1 : idx;
    }
}