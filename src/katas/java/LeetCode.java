/**
 * Solutions to LeetCode problems
 *
 * Tests are in Clojure and put in the module `katas.clj.java-ctci-test`.
 **/
package katas.java;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.LinkedList;

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

}
