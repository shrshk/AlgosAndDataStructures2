package forYoutube;

import java.util.Arrays;

public class SortedSquares {
    public int[] sortedSquares(int[] nums) {
        long startTime = System.nanoTime(); 
        int len = nums.length;
        int left=0,right=len-1, idx=len-1;
        int[] res = new int[len];
        while(left<=right) {
            int rightVal = Math.abs(nums[right]);
            int leftVal = Math.abs(nums[left]);
            if (leftVal>rightVal) {
                res[idx--] = leftVal * leftVal;
                left++;
            } else {
                res[idx--] = rightVal * rightVal;
                right--;
            }
        }
        long stopTime = System.nanoTime();
        System.out.println(stopTime - startTime);
        return res;
    }
    
    public static void main(String[] args) {		
		int len = 1000;
		int[] nums = new int[1000];
		Arrays.fill(nums, 0, len/2, -5);
		Arrays.fill(nums, (len/2)+1, len-1, 5);
		System.out.println(new SortedSquares().sortedSquares(nums));
	}
    
    
}
