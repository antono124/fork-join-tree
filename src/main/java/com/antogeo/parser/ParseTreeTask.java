package com.antogeo.parser;

import com.antogeo.pojo.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class ParseTreeTask extends RecursiveTask<List<Node<String>>>{

    private final Node<String> currentNode;


    public ParseTreeTask(Node<String> currentNode) {
        this.currentNode = currentNode;
    }

    @Override
    protected List<Node<String>> compute() {

        final List<ParseTreeTask> tasks = new ArrayList<>();
        List<Node<String>> nodes = new ArrayList<>();

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(currentNode.getChildren() != null)  {
            for(Node<String> child : currentNode.getChildren()){
                ParseTreeTask task = new ParseTreeTask(child);
                task.fork();
                tasks.add(task);
            }
        }

        for(final ParseTreeTask parseTreeTask : tasks){
            nodes.addAll((Collection<? extends Node<String>>) parseTreeTask.join());
        }

        return nodes;
    }
}
