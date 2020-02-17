/*249. Group Shifted Strings 
链接：
Medium: 
Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
Return:

[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
 
要按照字符串顺序返回结果
Note: For the return value, each inner list's elements must follow the lexicographic order.
*/

/*解题思路
这道题再做的时候已经被锁住了,只能凭记忆来写了
这道题让我们重组偏移字符串，所谓偏移字符串，就是一个字符串的每个字符按照字母顺序
表偏移相同量得到的另一个字符串，两者互为偏移字符串，注意相同字符串是偏移字符串的
一种特殊情况，因为偏移量为0。现在给了我们一堆长度不同的字符串，让我们把互为偏移字
符串的归并到一起。
肯定要通过映射的关系，找到每一个对应的偏移量和拥有这个偏移量的字符串的。
我们可以更加巧妙的利用偏移字符串的特点，那就是字符串的每个字母和首字符的相对距离都是相等的，比如abc和efg互为偏移，对于abc来说，b和a的距离是1，c和a的距离是2，对于efg来说，f和e的距离是1，g和e的距离是2。再来看一个例子，az和yx，z和a的距离是25，x和y的距离也是25(直接相减是-1，这就是要加26然后取余的原因)，那么这样的话，所有互为偏移的字符串都有个unique的距离差，我们根据这个来建立映射就可以很好的进行单词分组了，

*/
public class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str :strings) {
            String tmp = helper(str);
            List<String> list = map.containsKey(tmp) ? map.get(tmp) : new ArrayList<String>();
            list.add(str);
            map.put(tmp, list);
        }
        Iterator<String> it = map.keySet().iterator();
        List<List<String>> result = new ArrayList<List<String>>();
        while (it.hasNext()) {
            List<String> ll = map.get(it.next());
            Collections.sort(ll);
            result.add(ll);
        }
        return result;
    }
    private String helper(String str) {
        char[] chars = str.toCharArray();
        int tmp = (int)chars[0] - (int)'0';
        //防止str是单个的字符
        String result = String.valueOf(0) + ",";
        for (int i = 1; i < chars.length; i++) {
            int num = (int)chars[i] - (int)'0';
            num = num - tmp;
            num += num > 0 ? 0 : 26;
            result = result + String.valueOf(num) + ",";
        }
        return result;
    }
}