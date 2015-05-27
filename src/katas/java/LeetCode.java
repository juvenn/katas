/**
 * Solutions to LeetCode problems
 *
 * Tests are in Clojure and put in the module `katas.clj.java-ctci-test`.
 **/
package katas.java;
import java.util.Hashtable;

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
}
