package com.antogeo.parser;

import com.antogeo.pojo.Node;
import com.antogeo.service.TreeService;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Parse {

    static TreeService service = new TreeService();

    public static void main(String[] args){
        Node<String> root = service.getRoot("1");
        try {
            parseTree(root);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        parseTreeInParallel(root);

    }

    public static void parseTree(Node<String> node) throws InterruptedException{

        for(Node<String> child : node.getChildren()){
            parseTree(child);
            Thread.sleep(1000L);
        }
        System.out.println(node.getData());
    }


    private static void parseTreeInParallel(Node<String> node){

        final ForkJoinPool forkJoinPool = new ForkJoinPool(15);

        ParseTreeTask task = new ParseTreeTask(node);
        try {
            List<Node<String>> tree = forkJoinPool.invoke(task);


        } catch (final Exception e){
            forkJoinPool.shutdownNow();
            e.printStackTrace();
        } finally {
            forkJoinPool.shutdown();
        }
    }

}