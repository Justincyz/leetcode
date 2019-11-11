/*616. Add Bold Tag in String
链接：https://leetcode.com/problems/add-bold-tag-in-string/
Medium: 
Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in dict. If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag. Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.
Example 1:
Input: 
s = "abcxyz123"
dict = ["abc","123"]
Output:
"<b>abc</b>xyz<b>123</b>"
Example 2:
Input: 
s = "aaabbcc"
dict = ["aaa","aab","bc"]
Output:
"<b>aaabbc</b>c"
*/

/*解题思路
这道题目是 758. Bold Words in String 的follow - up, 在这里我们为了节省空间，
对每一个需要被放大的字段我们先用一个区间存下来，然后Merge interval,
最后直接在区间两端标注就好了

*/
class Solution {
    public String addBoldTag(String s, String[] dict) { 
        StringBuffer sb = new StringBuffer();
        List<Node> list = new ArrayList<>();
        
        for (String str : dict) {
        	int index = -1;
        	//循环找str在 S中所有可能出现的位置
        	index = s.indexOf(str, index);
        	while (index != -1) {
        		list.add(new Node(index, index + str.length()));
        		index +=1;
        		index = s.indexOf(str, index);
        	}
        }
  		
        list = merge(list);  
        
        int start = 0;
        for(Node cur : list){

            sb.append(s.substring(start, cur.start));
            sb.append("<b>");
            sb.append(s.substring(cur.start, cur.end));
            sb.append("</b>");
            start = cur.end;           
        }
        if (start < s.length()) {
        	sb.append(s.substring(start));
        }

        return sb.toString();
    }
    
    public List<Node> merge(List<Node> list){
        if(list.size() <=1) return list;
        Collections.sort(list, (a, b) -> a.start-b.start);
       // Node prev = list.get(0);
        List<Node> res = new ArrayList<>();
       
        int start = list.get(0).start;
        int end = list.get(0).end;
    
        for (Node i : list) {
            if (i.start <= end) {
                end = Math.max(end, i.end);
            } else {
                res.add(new Node(start, end));
                start = i.start;
                end = i.end;
            }
        }
        res.add(new Node(start, end));
        return res;
    }
    
    class Node{
        int start;
        int end;
        public Node(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
}