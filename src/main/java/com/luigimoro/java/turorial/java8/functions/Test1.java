package com.luigimoro.java.turorial.java8.functions;

import java.io.File;
import java.util.Arrays;

public class Test1 {

    public static void main(String[] args) {

        File[] folderList = new File(".").listFiles(File::isDirectory);

        int[] a = {1,2,3,4,};

//        Arrays.stream(a).forEach((int x) -> x +1) ;


    }

}
