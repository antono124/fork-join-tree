package com.antogeo.parser;

import com.antogeo.pojo.Node;
import com.antogeo.service.TreeService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Parse {

    static TreeService service = new TreeService();

    public static void main(String[] args){
        Node<String> root = service.getRoot("1");

        parseTree(root);

        parseTreeInParallel(root);

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

        System.out.println("START PARALLEL");

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