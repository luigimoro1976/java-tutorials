package com.luigimoro.java.turorial.java8.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * A thumbnail rule to calculate the number of thread to use in parallel context is:
 *
 *  numberOfThreads = numberOfCores / (1 - blockingRatio)
 *
 *  where blockingRatio exists in [0, 1]
 *
 */
public class StreamVsParallelStream {
    private static List<Double> doubleList = new ArrayList<>();

    static {
        DoubleStream.iterate(0, e -> e + 1).limit(1000).forEach(e -> doubleList.add(Math.random()));
    }

    public static void main(String[] args) {

        // the overhead introduced by tasks creation when using parallelism
        // in a NON blocking scenario leads to a performance deterioration
        compareStreamAndParallelStreamPerformances(false);

        // in a fully blocking scenario (e.g. the idle time is around 100%)
        // the advantages of parallel computing comes out
        compareStreamAndParallelStreamPerformances(true);

        // if the operation is completely blocking, you can further parallelize
        ForkJoinPool fjp = new ForkJoinPool(100);
        fjp.execute(() -> compareStreamAndParallelStreamPerformances(true));
        fjp.awaitQuiescence(60, TimeUnit.SECONDS);
    }

    private static void compareStreamAndParallelStreamPerformances(boolean useBlockingOperation) {
        System.out.println("----------------------------------------------");
        System.out.println(" PERFORMING A" + (useBlockingOperation ? " " : " *NON* ") + "BLOCKING TASK ON ELEMENTS");
        System.out.println("----------------------------------------------");

        Function<Double, Double> operationToPerform;
        if (useBlockingOperation) {
            operationToPerform = StreamVsParallelStream::aBlockingOperation;
        } else {
            operationToPerform = StreamVsParallelStream::aNonBlockingOperation;
        }

        long instant = System.nanoTime();
        doSomethingOnAStream(doubleList.parallelStream(), operationToPerform);
        long parallelDt = System.nanoTime() - instant;

        instant = System.nanoTime();
        doSomethingOnAStream(doubleList.stream(), operationToPerform);
        long serialDt = System.nanoTime() - instant;

        System.out.println("Elapsed time:");
        System.out.println(String.format("parallelStream(): %.3f ms", nanosToMillis(parallelDt)));
        System.out.println(String.format("stream(): %.3f ms", nanosToMillis(serialDt)));
        System.out.println(String.format("parallelStream()/stream(): %.3f", (double) parallelDt / serialDt));
        System.out.println();
    }

    public static <T, R> List<R> doSomethingOnAStream(Stream<T> stream, Function<T, R> transformation) {
        return stream.map(transformation).collect(Collectors.toList());
    }

    private static double aNonBlockingOperation(double d) {
        return Math.sqrt(Math.abs(d));
    }

    private static double aBlockingOperation(double d) {
        try {
            Thread.sleep(1);
            return Math.sqrt(Math.abs(d));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static double nanosToMillis(long nanos) {
        return (double) nanos / 1_000_000;
    }

}
