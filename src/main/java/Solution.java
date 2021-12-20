class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int count = 0;
        int start = 0;
        while (count < n) {
            int current = start;
            int pre = nums[start];
            do {
                int next = (current + k) % n;
                int temp = nums[next];
                nums[next] = pre;
                count++;
                pre = temp;
                current = next;
            } while (start != current);
            start++;
        }
    }

    public static void main(String[] args) {
        new Solution().rotate(new int[]{1,2,3,4,5,6,7}, 3);
    }
}