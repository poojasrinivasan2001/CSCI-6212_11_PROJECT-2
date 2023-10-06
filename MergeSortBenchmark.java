package heap_q8;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class MergeSortBenchmark {
    public static void main(String[] args) {
        // Different values of n
        int[] nValues = {100, 1000, 10000, 100000,1000000};

        // Creating a list of arrays
        List<int[]> arrayOfLists = new ArrayList<>();
        for (int n : nValues) {
            arrayOfLists.add(generateSortedRandomArray(n));
        }

        for (int[] array : arrayOfLists) {
            long startTime = System.currentTimeMillis();
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            for (int element : array) {
                minHeap.offer(element);
            }

            List<Integer> mergedSequence = new ArrayList<>();
            while (minHeap.size() > 1) {
                int min1 = minHeap.poll();
                int min2 = minHeap.poll();
                int mergedElement = min1 + min2;
                mergedSequence.add(mergedElement);
                minHeap.offer(mergedElement);
            }

            long endTime = System.currentTimeMillis();
            int totalMergedCost = mergedSequence.stream().mapToInt(Integer::intValue).sum();
            System.out.println("Cost = " + totalMergedCost);
            double executionTimeInSeconds = (endTime - startTime)/1000.0; // Convert to seconds
            System.out.println("n = " + array.length + ", Time Taken: " + executionTimeInSeconds + " seconds");
        }
    }

    // Function to generate a sorted random array
    public static int[] generateSortedRandomArray(int size) {
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(901) + 100; // Generates random numbers between 100 and 1000
        }
        Arrays.sort(arr);
        return arr;
    }
}
