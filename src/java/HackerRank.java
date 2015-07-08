
package katas;

import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import java.util.TreeMap;

public class HackerRank {

    public static List<String> subWords(String word) {
        ArrayList<String> list = new ArrayList<String>();
        if (word.length() < 2) return list;
        StringBuilder sb = new StringBuilder();
        for (int i=0; i < word.length(); i++)
            sb.append(word.charAt(i));
        for (int i=0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            sb.deleteCharAt(i);
            list.add(sb.toString());
            sb.insert(i, c);
        }
        return list;
    }

    public static int longest_chain(String[] w) {
        TreeMap<String, Integer> map = new TreeMap<String, Integer>();
        for (int i=0; i < w.length; i++) map.put(w[i], w[i].length());
        int maxD = 0; // max distance
        while (!map.isEmpty()) {
            // get longest word from collection
            String word = map.pollLastEntry().getKey();
            if (word.length() <= maxD) return maxD;

            // depth-first search all possible chains starting from
            // current word
            Stack<String> st = new Stack<String>();
            st.add(word);
            int dist = 0;
            while (!st.isEmpty()) {
                String x = st.pop();
                int   dx = word.length() - x.length();
                if (dx > dist) dist = dx;
                for (String v: subWords(x)) {
                    if (map.containsKey(v)) {
                        st.add(v);
                        map.remove(v);
                    }
                }
            }
            if (dist >= maxD) maxD = dist;
        }
        return maxD+1;
    }
}
