package pl.info.rkluszczynski.java8.parrays;

import java.util.Arrays;
import java.util.Comparator;
import java.util.UUID;

public class P1ParallelSort extends DataClass {

    public static void main(String[] args) {
        P1ParallelSort mySort = new P1ParallelSort();
        int[] src = null;

        System.out.println("\nSerial sort:");
        src = mySort.getData();
        mySort.sortIt(src, false);

        System.out.println("\nParallel sort:");
        src = mySort.getData();
        mySort.sortIt(src, true);

        mySort.stringSortingExample();
    }

    private void stringSortingExample() {
        String[] arrayStr = new String[10]; // 4M
        Arrays.parallelSetAll(arrayStr, i -> UUID.randomUUID().toString());

        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                String str1 = (String) o1;
                String str2 = (String) o2;
                return str1.compareTo(str2);
            }
        };
        Arrays.parallelSort(arrayStr, comparator);

//        Arrays.parallelSort(arrayStr, (x, y) -> x.equalsIgnoreCase(y));
        Arrays.parallelSort(arrayStr, 2, 5, comparator);

        Arrays.spliterator(arrayStr).forEachRemaining(System.out::println);
    }

    public void sortIt(int[] src, boolean parallel) {
        try {
            System.out.println("--Array size: " + src.length);
            long start = System.currentTimeMillis();
            if (parallel == true) {
                Arrays.parallelSort(src);
            } else {
                Arrays.sort(src);
            }
            long end = System.currentTimeMillis();
            System.out.println("--Elapsed sort time: " + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
