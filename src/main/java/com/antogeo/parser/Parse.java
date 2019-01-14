package com.antogeo.parser;

import com.antogeo.pojo.Node;
import com.antogeo.service.TreeService;

public class Parse {

    static TreeService service = new TreeService();

    public static void main(String[] args){
        Node<String> root = service.getRoot("1");
        try {
            parseTree(root);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void parseTree(Node<String> node) throws InterruptedException{

        if(node.getChildren() != null)  {
            for(Node<String> child : node.getChildren()){
                parseTree(child);
                Thread.sleep(1000L);
            }
        }
        System.out.println(node.getData());
    }



}