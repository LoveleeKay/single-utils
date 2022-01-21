import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

class Solution {
    public int thirdMax(int[] nums) {
        Set<Integer> set = new TreeSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int size = set.size();
        if (set.size() >= 3) {
            return new ArrayList<>(set).get(size - 3);
        } else {
            return new ArrayList<>(set).get(size - 1);
        }
    }
}