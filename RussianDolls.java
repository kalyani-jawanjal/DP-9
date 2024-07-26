import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class RussianDolls {
    public int maxEnvelopes(int[][] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums, new Comparator<int[]>(){
            public int compare(int[] arr1, int[] arr2) {
                if(arr1[0] != arr2[0]) {
                    return arr1[0] - arr2[0];
                } else {
                    return arr2[1] - arr1[1];
                }
            }
        });

        int[] secondNums = new int[nums.length];
        for(int i=0; i<nums.length; i++) {
            secondNums[i] = nums[i][1];
        }

        return LIS(secondNums);
    }
    private int LIS(int[] secondNums) {
        if(secondNums == null || secondNums.length == 0) {
            return -1;
        }

        List<Integer> list = new ArrayList<>();
        list.add(secondNums[0]);

        for(int i=1; i<secondNums.length; i++) {
            if(secondNums[i] > list.get(list.size()-1)) {
                list.add(secondNums[i]);
                continue;
            }

            int index = binarySearch(list, 0, list.size()-1, secondNums[i]);
            list.set(index, secondNums[i]);
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
