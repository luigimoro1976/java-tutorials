package com.luigimoro.java.turorial.java8.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StreamVsParallelstream {

    List<Double> a;
    List<Double> b;

    public StreamVsParallelstream() {
        a = new ArrayList();
        b = new ArrayList();

        Random random = new Random();

        for (int i = 0; i < 10000000; i++) {

            a.add(random.nextDouble());
            b.add(random.nextDouble());
        }
    }

    public void sort(List<Double> a) {
        a.stream().map((x) -> Math.sqrt(Math.abs(x))).collect(Collectors.toList());
    }

    public void parallelSort(List<Double> a) {
        b.parallelStream().map((x) -> Math.sqrt(Math.abs(x))).collect(Collectors.toList());
    }

    public static void main(String[] args) {

        StreamVsParallelstream streamVsParallelstream = new StreamVsParallelstream();


        long time0 = System.nanoTime();
        streamVsParallelstream.parallelSort(streamVsParallelstream.b);


        long time1 = System.nanoTime();

        streamVsParallelstream.sort(streamVsParallelstream.a);

        long time2 = System.nanoTime();

        long delta1 = (time1 - time0);
        long delta2 = (time2 - time1);

        System.out.println(String.format("Time a = %d, time b = %d", delta1, delta2));

        System.out.println(String.format("Ratio parallel vs non parallel : %s", ((double)delta1/delta2)));

    }

}
