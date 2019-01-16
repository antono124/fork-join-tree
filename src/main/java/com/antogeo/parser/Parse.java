package com.antogeo.parser;

import com.antogeo.pojo.Node;
import com.antogeo.service.TreeService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Parse {

    public static void main(String[] args){
        Node<String> root = TreeService.getRoot("1");

        long startTime, stopTime, elapsedTime;

        startTime = System.currentTimeMillis();

        parseTree(root);

        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("");
        System.out.println("Duration without parallelization : " + elapsedTime);

        startTime = System.currentTimeMillis();

        parseTreeInParallel(root);

        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("");
        System.out.println("Duration with parallelization : " + elapsedTime);

    }

    private static void parseTree(Node<String> node) {

        for(Node<String> child : node.getChildren()){
            parseTree(child);
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(node.getData() + " ");
    }

    private static void parseTreeInParallel(Node<String> node){

        final ForkJoinPool forkJoinPool = new ForkJoinPool(15);

        List<Node<String>> tree = new ArrayList<>();

        try {
            ParseTreeTask task = new ParseTreeTask(node);
            tree = forkJoinPool.invoke(task);
        } catch (final Exception e){
            forkJoinPool.shutdownNow();
            e.printStackTrace();
        } finally {
            forkJoinPool.shutdown();
        }

        printTree(tree.get(0));
    }

    private static void printTree(Node<String> node){
        for(Node<String> child : node.getChildren()){
            printTree(child);
        }
        System.out.print(node.getData() + " ");
    }

}