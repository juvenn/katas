/**
 * Solutions to LeetCode problems
 *
 * Tests are in Clojure and put in the module `katas.clj.java-ctci-test`.
 **/
package katas.java;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode {
    /**
     * Sort three colors
     *
     *  Given an array with n objects colored red, white or blue, sort
     *  them so that objects of the same color are adjacent, with the
     *  colors in the order red, white and blue. Here, we will use the
     *  integers 0, 1, and 2 to represent the color red, white, and
     *  blue respectively.
     **/
    public static void sortColors(int[] nums) {
        int[] count = new int[3];
        for (int i=0; i < nums.length; i++) count[nums[i]]++;
        int accum = 0;
        for (int j=0; j < 3; j++) {
            for (int i=accum; i < accum + count[j]; i++) nums[i] = j;
            accum += count[j];
        }
    }

    /**
     * Sort three colors followup: take only one-pass and constant space
     * Use 3-way quick sort
     **/
    public static void sortColors3Way(int[] nums) {
        int lo = 0,
            i  = 0,
            hi = nums.length - 1;
        while (i <= hi) {
            if (nums[i] < 1) {
                int tmp = nums[lo];
                nums[lo++] = nums[i];
                nums[i++]  = tmp;
            } else if (nums[i] > 1) {
                int tmp = nums[hi];
                nums[hi--] = nums[i];
                nums[i] = tmp;
            } else { i++; }
        }
    }


    /**
     * Convert a string into zig-zag one
     **/
    public static String zigZagConvert(String s, int numRows) {
        // we divide the string into smallest repeat groups
        if (numRows < 2) return s;
        int widthGroup = numRows * 2 - 2;
        int numGroup = s.length() / widthGroup;
        StringBuilder sb = new StringBuilder();
        for (int j=0; j < numRows; j++) {
            for (int i=0; i <= numGroup; i++) {
                int start = widthGroup * i;
                if (start + j < s.length())
                    sb.append(s.charAt(start + j));
                int index2 = start + numRows - 1 + numRows - 1 - j;
                if (j > 0 && j < numRows-1 && index2 < s.length())
                    sb.append(s.charAt(index2));
            }
        }
        return sb.toString();
    }

    /**
     * ZigZag Conversion: accumulate chars in rows
     **/ 
    public static String zigZagConvertRows(String s, int numRows) {
        if (numRows < 2) return s;
        boolean inc = true;
        int     row = 0;
        StringBuilder[] sb = new StringBuilder[numRows];
        // accumulate rows
        for (int i=0; i < s.length(); i++) {
            if (sb[row] == null) sb[row] = new StringBuilder();
            sb[row].append(s.charAt(i));
            if      (row == 0)           inc = true;
            else if (row == numRows - 1) inc = false;
            if   (inc) row++;
            else       row--;
        }
        for (int i=1; i < numRows; i++)
            for (int j=0; j < sb[i].length(); j++)
                sb[0].append(sb[i].charAt(j));
        return sb[0].toString();
    }


    /**
     * Given an array of integers, find two numbers such that they add
     * up to a specific target number.
     *
     * The function twoSum should return indices of the two numbers
     * such that they add up to the target, where index1 must be less
     * than index2. Please note that your returned answers (both
     * index1 and index2) are not zero-based.
     *
     * Input: [2, 7, 11, 15], 9
     * Output: 1, 2
     **/
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i=0; i < nums.length-1; i++)
            for (int j=i+1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i + 1;
                    result[1] = j + 1;
                    return result;
                }
            }
        return null;
    }

    public static int[] twoSumLinear(int[] nums, int target) {
        Hashtable<Integer, Integer> table = new Hashtable<Integer,Integer>();
        for (int i=0; i < nums.length; i++) table.put(nums[i], i);
        int[] result = new int[2];
        for (int i=0; i < nums.length; i++) {
            int     val = target - nums[i];
            Integer j   = table.get(val);
            if (j != null && i != j) {
                result[0] = (i < j) ? i+1 : j+1;
                result[1] = (i < j) ? j+1 : i+1;
                return result;
            }
        }
        return null;
    }

    /**
     * Reverse digits in integer
     *
     * E.g.: 123 => 321, -123 => -321
     **/
    public static int reverseInteger(int x) {
        int quot  = x;
        int accum = 0;
        while (quot / 10 != 0) {
            accum = accum * 10 + quot % 10;
            quot  = quot / 10;
        }
        // check integer overflow
        int bound = Integer.MAX_VALUE / 10;
        if (accum > bound || accum < - bound) return 0;

        accum = accum * 10 + quot;
        return accum;
    }

    /**
     * Palindrome number, Determine whether an integer is a
     * palindrome. Do this without extra space.
     **/
    public static boolean isPalindrome(int x) {
        if (x < 0) return false;
        int quot = x;
        int rev  = 0; // reverse integer
        while (quot / 10 != 0) {
            rev  = rev * 10 + quot % 10;
            quot = quot / 10;
        }
        // check integer overflow
        int bound = Integer.MAX_VALUE / 10;
        if (rev > bound) return false;

        rev = rev * 10 + quot;
        return rev == x;
    }

    public static boolean isPalindrome2(int x) {
        if (x < 0) return false;
        int div = 1;
        while (x / div >= 10) {
            div *= 10;
        }
        while (x != 0) {
            if (x % 10 != x / div) return false;
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }

    /**
     * Count and Say
     * 
     * The count-and-say sequence is the sequence of integers beginning as
     * follows: 1, 11, 21, 1211, 111221, ...
     * 
     * 1 is read off as "one 1" or 11.  11 is read off as "two 1s" or
     * 21.  21 is read off as "one 2, then one 1" or 1211.
     * 
     * Given an integer n, generate the nth sequence.
     * 
     * Note: The sequence of integers will be represented as a string.
     **/
    public static String countAndSay(int n) {
        assert(n > 0);
        StringBuilder seq = new StringBuilder("1");
        for (int i=1; i < n; i++)
            seq = nextSay(seq);
        return seq.toString();
    }

    public static StringBuilder nextSay(StringBuilder seq) {
        if (seq.length() == 0) return seq;
        int          cnt = 1;
        char           c = seq.charAt(0);
        StringBuilder sb = new StringBuilder();
        for (int i=1; i < seq.length(); i++) {
            if ('0' > seq.charAt(i) || '9' < seq.charAt(i))
                throw new IllegalArgumentException();
            if (seq.charAt(i) == c) cnt++;
            else {
                sb.append(cnt);
                sb.append(c);
                cnt = 1;
                c   = seq.charAt(i);
            }
        }
        // append last group
        sb.append(cnt);
        sb.append(c);
        return sb;
    }

    /**
     * Minimum window substring
     *
     * Given a string S and a string T, find the minimum window in S
     * which will contain all the characters in T in complexity O(n).
     * 
     * For example, S = "ADOBECODEBANC" T = "ABC"
     * 
     * Minimum window is "BANC".
     * 
     * Note:
     *
     * If there is no such window in S that covers all
     * characters in T, return the emtpy string "".
     * 
     * If there are multiple such windows, you are guaranteed that
     * there will always be only one unique minimum window in S.
     **/
    public static String minWindow(String s, String t) {
        if (t.length() == 0) return "";
        int[] cnt = new int[256]; // assume chars are in ASCII.
        for (int i = 0; i < t.length(); i++) cnt[t.charAt(i)]++;

        int[] found   = new int[256];
        int min_width = s.length() + 1;
        int min_lo    = 0,
            min_hi    = min_width;
        int count     = 0;
        for (int lo=0,  hi=0; hi < s.length(); hi++) {
            if (cnt[s.charAt(hi)] == 0) continue;
            found[s.charAt(hi)]++;
            if (found[s.charAt(hi)] <= cnt[s.charAt(hi)]) count++;

            if (count == t.length()) {
                while (cnt[s.charAt(lo)] == 0 ||
                       found[s.charAt(lo)] > cnt[s.charAt(lo)]) {
                    found[s.charAt(lo)]--;
                    lo++;
                }

                int width = hi - lo;
                if (width <= min_width) {
                    min_width = width;
                    min_lo = lo;
                    min_hi = hi;
                }
            }
        }
        return (min_width > s.length()) ? "" : s.substring(min_lo, min_hi+1);
    }

    /**
     * Reverse Linked List
     * Reverse a singly linked list.
     **/
    public static ListNode reverseList(ListNode head) {
        ListNode nav = head;
        ListNode   x = null;
        while (nav != null) {
            ListNode tmp = new ListNode(nav.val);
            tmp.next = x;
            x        = tmp;
            nav      = nav.next;
        }
        return x;
    }

    public static ListNode reverseListRecur(ListNode head) {
        return reverseListRecur2(head, null);
    }

    public static ListNode reverseListRecur2(ListNode nav, ListNode res) {
        if (nav == null) return res;
        ListNode x = new ListNode(nav.val);
        x.next = res;
        return reverseListRecur2(nav.next, x);
    }


    /**
     * Number of Islands
     *
     * Given a 2d grid map of '1's (land) and '0's (water), count the
     * number of islands. An island is surrounded by water and is
     * formed by connecting adjacent lands horizontally or
     * vertically. You may assume all four edges of the grid are all
     * surrounded by water.
     *
     * 11110
     * 11010     => 1
     * 11000
     * 00000
     * 
     * 11000
     * 11000     => 3
     * 00100
     * 00011
     **/
    public static int numIslands(char[][] grid) {
        if (grid.length == 0) return 0;
        int wid = grid[0].length;
        int hei = grid.length;
        int size = wid * hei;
        int islands = 0;
        boolean[] marked = new boolean[size]; // is cell walked
        for (int i=0; i < size; i++) {
            if (marked[i]) continue;
            int lands = 0; // number of lands connected
            // queue of land neighbors
            LinkedList<Integer> q = new LinkedList<Integer>();
            q.add(i);
            while (!q.isEmpty()) {
                int x = q.remove();
                if (marked[x]) continue;
                marked[x] = true;
                if (grid[x / wid][x % wid] != '1') continue;
                lands++;

                // possible neighbors: x - wid, x - 1, x + 1, x + wid;
                ArrayList<Integer> nbs = new ArrayList<Integer>();
                if (x - wid >= 0)
                    nbs.add(x - wid);
                if ((x - 1) > 0 && ((x - 1) % wid + 1 != wid))
                    nbs.add(x - 1);
                if ((x + 1) % wid != 0)
                    nbs.add(x + 1);
                if (x + wid < size)
                    nbs.add(x + wid);
                for (int y: nbs)
                    if (!marked[y]) q.add(y);
            }
            if (lands > 0) islands++;
        }
        return islands;
    }

    /**
     * Maximum Subarray
     *
     * Find the contiguous subarray within an array (containing at
     * least one number) which has the largest sum.
     *
     * Example: [-2,1,-3,4,-1,2,1,-5,4]
     * the maximum sub array [4,-1,2,1], the sum is 6.
     
     [8 -19 5 -4 20]
  nums[i] sum_val max_val max_lo max_hi
   8       8       8       0      0    
  -19     -11      8       0      0    
   5      -6       8       0      0

     **/
    public static int maxSubArray(int[] nums) {
        if (nums.length == 0) return 0;
        int max_val = nums[0];
        int sum_val = nums[0];
        for (int i=1; i < nums.length; i++) {
            sum_val = (sum_val > 0) ? sum_val + nums[i] : nums[i];
            if (max_val > sum_val && max_val > nums[i]) continue;
            if (sum_val >= max_val && sum_val > nums[i]) {
                max_val = sum_val;
                continue;
            }
            sum_val = nums[i];
            max_val = nums[i];
        }
        return max_val;
    }

    // this version keeps index range of best consecutive sum.
    public static int maxSubArray2(int[] nums) {
        if (nums.length == 0) return 0;
        int max_val = nums[0];  // max sum
        int max_lo  = 0,
            max_hi  = 0;        // the index range of max_val
        int sum_val = nums[0];  // next best consecutive sum
        int sum_lo  = 0;
        for (int i=1; i < nums.length; i++) {
            // if sum is negative, it will not contribute to further
            // sum, so we drop previous sum, and start anew.
            if   (sum_val > 0) sum_val += nums[i];
            else {
                sum_val = nums[i];
                sum_lo  = i;
            }
            if (max_val > sum_val && max_val > nums[i])
                continue;
            if (sum_val >= max_val && sum_val > nums[i]) {
                max_val = sum_val;
                max_lo  = sum_lo;
                max_hi  = i; // max_lo remains
                continue;
            }
            // else nums[i] is the maximum
            max_val = nums[i];
            max_lo  = i;
            max_hi  = i;
            sum_val = nums[i];
        }
        return max_val;
    }

    /**
     * Maximum Subarray, divide and conquer approach
     **/
    public static int maxSubArray3(int[] nums) {
        return 0;
    }

    /**
     * Dungeon Game
     **/
    public static int calculateMinHP(int[][] dungeon) {
        // Dynamic programming: calculate minimum hp required
        // backwardly from target cell. Observe that,
        // 1) to enter next cell, it must has at least 1 point,
        // 2) there're only two path from [i j] to [i+1 j+1].
        int M = dungeon.length;
        int N = dungeon[0].length;
        int[][] hp = new int[M][N]; // minimum hp required to enter [i,j]
        hp[M-1][N-1] = Math.max(1, 1 - dungeon[M-1][N-1]);
        for (int i=M-2; i >= 0; i--)
            hp[i][N-1] = Math.max(1, hp[i+1][N-1] - dungeon[i][N-1]);
        for (int j=N-2; j >= 0; j--)
            hp[M-1][j] = Math.max(1, hp[M-1][j+1] - dungeon[M-1][j]);
        for (int i=M-2; i >= 0; i--)
            for (int j=N-2; j >= 0; j--)
                hp[i][j] = Math.max((Math.min(hp[i+1][j], hp[i][j+1])) - dungeon[i][j],
                               1);
        return hp[0][0];
    }

    /**
     * Convert sorted array to height-balanced binary search tree
     **/
    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) return null;
        return buildTree(nums, 0, nums.length - 1);
    }

    private static TreeNode buildTree(int[] a, int lo, int hi) {
        if (lo > hi)  return null;
        if (lo == hi) return new TreeNode(a[lo]);
        int mid = (hi + lo + 1) / 2;
        TreeNode node = new TreeNode(a[mid]);
        node.left  = buildTree(a, lo, mid-1);
        node.right = buildTree(a, mid+1, hi);
        return node;
    }

    /**
     * Sorted List to Binary Search Tree
     *
     * Given a singly linked list where elements are sorted in
     * ascending order, convert it to a height balanced BST.
     **/
    public static TreeNode sortedListToBST(ListNode head) {
        int n = 0;
        for (ListNode x = head; x != null; x = x.next) n++;
        // Keep head in array of one ListNode, so it is possible
        // to move the head forward in outer context within method.
        ListNode[] nodes = {head};
        return buildBalancedTree(nodes, 0, n-1);
    }

    public static TreeNode buildBalancedTree(ListNode[] nodes,
                                             int lo, int hi) {
        if (lo > hi) return null;
        int mid = (hi + lo) / 2;
        TreeNode left = buildBalancedTree(nodes, lo, mid-1);
        // nodes[0] has moved forward after left tree being built
        TreeNode parent = new TreeNode(nodes[0].val);
        parent.left = left;
        nodes[0] = nodes[0].next;
        parent.right = buildBalancedTree(nodes, mid+1, hi);
        return parent;
    }

    /**
     * This solution uses red black tree method to balance the height.
     **/
    public static TreeNode sortedListToBST2(ListNode head) {
        Stack<Parent> st = new Stack<Parent>(); // stack of ancestors
        while (head != null) {
            promote(st, new TreeNode(head.val));
            head = head.next;
        }
        Parent p = null;
        while (!st.isEmpty()) p = st.pop();
        if (p == null) return null;
        else           return p.node;
    }

    private static class Parent {
        public boolean inRed;
        public TreeNode node;
        public Parent(TreeNode x, boolean red) {
            inRed = red;
            node  = x;
        }

        public String toString() {
            return String.format("%d[%b]", node.val, inRed);
        }
    }

    /**
     * Promote a node and its ancestors
     **/
    private static void promote(Stack<Parent> st, TreeNode x) {
        if (st.isEmpty()) {
            st.push(new Parent(x, false));
            return;
        }
        Parent p = st.pop();
        if (!p.inRed) {
            // p is not an ancestor of x any more
            // stop promoting
            p.node.right = x.left;
            x.left = p.node;
            if (!st.isEmpty()) st.peek().node.right = x;
            st.push(new Parent(x, true));
        } else {
            p.node.right = x;
            promote(st, p.node); // further promote
            st.push(new Parent(x, false));
        }
    }

    /**
     * Invert binary tree
     *
     *      4                 4
     *    /   \             /   \
     *   2     7     =>    7     2
     *  / \   / \         / \   / \
     * 1   3 6   9       9   6 3   1
     **/
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = invertTree(root.left);
        root.left     = invertTree(root.right);
        root.right    = left;
        return root;
    }

    /**
     * Number of 1 bits, or hamming weight
     * Assume n is unsigned integer
     **/
    public static int hammingWeight(int n) {
        int quot  = n;
        int count = 0;
        // count the sign bit and convert it to positive integer
        if (quot < 0) {
            quot += Integer.MAX_VALUE + 1;
            count = 1; // count the sign bit
        }
        while (quot != 0) {
            int rem = quot % 2;
            if (rem == 1) count++;
            quot /= 2;
        }
        return count;
    }

    /**
     * Clone graph
     *
     * Clone unidrected graph, where each graph node contains `label` and
     * `neighbors`. Assume graph nodes are labeled uniquely.
     **/
    public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        HashMap<Integer, UndirectedGraphNode> map = new HashMap<Integer, UndirectedGraphNode>();
        Stack<UndirectedGraphNode> st = new Stack<UndirectedGraphNode>();

        UndirectedGraphNode root = new UndirectedGraphNode(node.label);
        map.put(root.label, root);
        st.push(node);
        while (!st.isEmpty()) {
            UndirectedGraphNode x = st.pop();
            UndirectedGraphNode y = map.get(x.label);
            for (UndirectedGraphNode x1 : x.neighbors) {
                UndirectedGraphNode y1 = map.get(x1.label);
                if (y1 == null) {
                    y1 = new UndirectedGraphNode(x1.label);
                    map.put(y1.label, y1);
                    st.push(x1);
                }
                y.neighbors.add(y1);
            }
        }
        return root;
    }

    /**
     * Rectangle area
     **/
    public static int computeArea(int A, int B, int C, int D,
                                  int E, int F, int G, int H) {
        int area = (D - B) * (C - A) + (G - E) * (H - F);
        int dx   = crossLength(A, C, E, G);
        if (dx <= 0) return area;
        int dy   = crossLength(B, D, F, H);
        if (dy <= 0) return area;
        return area - dx * dy;
    }

    /**
     * Cross length of two lines
     * Given end points of two lines, compute length of crossed line,
     * assume points are given in increasing order.
     **/
    private static int crossLength(int A1, int A2, int B1, int B2) {
        if (B1 < A1) {
            if      (B2 <= A1) return 0;
            else if (B2 >= A2) return A2 - A1;
            else               return B2 - A1;
        } else {
            if      (B1 >= A2) return 0;
            else if (B2 <= A2) return B2 - B1;
            else               return A2 - B1;
        }
    }

    /**
     * Find first missing positive integer
     * Given an unsorted array of integers, find the first missing positive
     * integer.
     **/
    public static int findFirstMissingPositive(int[] nums) {
        // move the non-positive to right using the quick sort method
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            if (nums[lo] > 0) lo++;
            else if (nums[hi] <= 0) hi--;
            else {
                int tmp = nums[lo];
                nums[lo++] = nums[hi];
                nums[hi--] = tmp;
            }
        }
        if (lo == 0) return 1;

        // all positive are left now, it ends at lo - 1;
        int length = lo;
        // traverse positives and mark i-th element as negative if i
        // appeared in array
        for (int i=0; i < length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (index < length && nums[index] > 0)
                nums[index] = - nums[index];
        }
        for (int i=0; i < length; i++) {
            if (nums[i] > 0) return i+1;
        }
        return length + 1;

        /** Hint
         *  This solution mark the appeared element as negative, there's
         *  another solution: move element to its value index.
         **/
    }

    /**
     * String to integer (atoi)
     *
     **/
    public static int myAtoi(String str) {
        int lo = 0;
        while (lo < str.length() && Character.isWhitespace(str.charAt(lo)))
            lo++;
        boolean pos = true; // positive or negative
        if      (lo >= str.length())    return 0; // no digit at all
        if      (str.charAt(lo) == '+') lo++;
        else if (str.charAt(lo) == '-') {
            pos = false;
            lo++;
        }

        // Note: MAX_INT will overflow to MIN_INT
        int num = 0;
        for (int i=lo; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') return pos ? num : -num;
            int d = c - '0';

            // detecting overflow
            if (num > 214748364 || (num == 214748364 && d >= 8))
                return pos ? 2147483647 : -2147483648;
            num = num * 10 + d;
        }

        return pos ? num : -num;
    }

    /**
     * Repeated DNA Sequences
     *
     * Find all the 10-letter-long sequences that occur more than once
     * in a DNA molecule.
     **/
    public static List<String> findRepeatedDnaSequences(String s) {
        int N = s.length();
        int k = 10;
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (int i=0; i <= N-k; i++) {
            String sub = s.substring(i, i+k);
            int    cnt = map.getOrDefault(sub, 0);
            map.put(sub, cnt+1);
        }
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            int    cnt = entry.getValue();
            if (cnt > 1) list.add(key);
        }
        return list;
    }

    /**
     * Reverse bits of a given 32 bits unsigned integer.
     *
     * For example, given input 43261596 (represented in binary as
     * 00000010100101000001111010011100), return 964176192
     * (represented in binary as 00111001011110000010100101000000).
     */
    public static int reverseBits(int n) {
        // you need treat n as an unsigned value

        // Drop the last bit and reverse the left 31 bits at first,
        // then make offset according to last bit.
        int quot;
        if (n >= 0) quot = n / 2;
        else        quot = (n + 1 + 2147483647) / 2 + 1073741824;
        int acc = 0;
        for (int i=0; i < 31; i++) {
            acc = acc * 2 + quot % 2;
            quot /= 2;
        }
        if (n % 2 != 0) acc += -2147483648;
        return acc;
    }

    /**
     * Basic Calculator
     * 
     * Implement a basic calculator to evaluate a simple expression
     * string.  The expression string may contain open ( and closing
     * parentheses ), the plus + or minus sign -, non-negative
     * integers and empty spaces. You may assume that the given
     * expression is always valid.
     * Some examples:
     *
     *     "1 + 1" = 2
     *     " 2-1 + 2 " = 3
     *     "(1+(4+5+2)-3)+(6+8)" = 23
     **/
    public static int calculate(String s) {
        // stack to keep both operand and operator
        Stack<Integer> st = new Stack<Integer>();
        int number = 0;
        int  accum = 0; // accumulator
        int   sign = 1; // previous sign
        for (int i=0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                number = number * 10 + c - '0';
            } else if (c == '+' || c == '-') {
                accum += sign * number;
                number = 0;
                sign   = (c == '+') ? 1 : -1;
            } else if (c == '(') {
                st.push(accum);
                st.push(sign);
                sign  = 1;
                accum = 0;
            } else if (c == ')') {
                accum += sign * number;
                accum *= st.pop();      // sign before the corresponding '('
                accum += st.pop();      // accumulator before '('
                number = 0;
                sign   = 1;
            }
        }
        if (number != 0) accum += sign * number;
        return accum;
        // hint another solution: (1+(4+5+2)-3)+(8+6) =>
        // sum += 1 + 4 + 5 + 2 + (-3) + 8 + 6
    }

    public static int calculate2(String s) {
        Stack<Integer>   opr = new Stack<Integer>();   // operands
        Stack<Character> ops = new Stack<Character>(); // operators

        for (int i=0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                // accumulate consecutive digits as number
                int accum = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    accum = accum * 10 + (s.charAt(i) - '0');
                    i++;
                }

                if (!ops.isEmpty() && !opr.isEmpty()) {
                    char op = ops.pop();
                    int   a = opr.pop();
                    if      (op == '+') accum = a + accum;
                    else if (op == '-') accum = a - accum;
                    else {
                        opr.push(a);
                        ops.push('(');
                    }
                }
                opr.push(accum);
            }
            if (i >= s.length()) break;
            char c = s.charAt(i);
            switch (c) {
            case '+': ops.push(c); break;
            case '-': ops.push(c); break;
            case '(': ops.push(c); break;
            case ')':
                ops.pop(); // drop the corresponding `(`
                int accum = opr.pop();
                if (!ops.isEmpty() && !opr.isEmpty()) {
                    char op = ops.pop();
                    int   a = opr.pop();
                    if      (op == '+') accum = a + accum;
                    else if (op == '-') accum = a - accum;
                    else {
                        opr.push(a);
                        ops.push('(');
                    }
                }
                opr.push(accum);
                break;
            }
        }
        assert (ops.isEmpty());
        return opr.isEmpty() ? 0 : opr.pop();
    }

    /**
     * Remove Duplicates from Sorted Array
     *
     * Given a sorted array, remove the duplicates in place such that
     * each element appear only once and return the new length.  Do
     * not allocate extra space for another array, you must do this in
     * place with constant memory.
     **/
    public static int removeDuplicates(int[] nums){
        if (nums.length == 0) return 0;
        int hi = 0;
        for (int i=1; i < nums.length; i++)
            if (nums[i] > nums[hi])
                nums[++hi] = nums[i];
        return hi + 1;
    }
}
