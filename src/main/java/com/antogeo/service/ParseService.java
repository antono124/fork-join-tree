package com.antogeo.service;

import com.antogeo.pojo.Node;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

@Service
public class ParseService {

    public void parseTree(Node<String> node) {
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

    public void parseTreeInParallel(Node<String> node){
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