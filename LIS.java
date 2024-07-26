import java.util.ArrayList;
import java.util.List;

class LIS {
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);

        for(int i=1; i<nums.length; i++) {
            if(nums[i] > list.get(list.size()-1)) {
                list.add(nums[i]);
            } else {
                int index = binarySearch(list, 0, list.size()-1, nums[i]);
                list.set(index, nums[i]);
            }
        }

        return list.size();
    }
    private int binarySearch(List<Integer> list, int low, int high, int target) {
        while(low <= high) {
            int mid = low + (high - low)/2;

            if(list.get(mid) == target) {
                return mid;
            } else if(list.get(mid) < target) {
                low = mid+1;
            } else {
                high = mid-1;
            }
        }

        return low;
    }
}
