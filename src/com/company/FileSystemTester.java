package com.company;

public class FileSystemTester {

    public static void main(String[] args) {

        FileSystem ao = new FileSystem();
        String filename = ao.openFile();
        System.out.println(filename);

    }

}