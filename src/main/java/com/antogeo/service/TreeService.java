package com.antogeo.service;

import com.antogeo.pojo.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TreeService {

    public static Node<String> getRoot(String nodeName){
        Map<String, Node<String>> treeMap = readTree();
        return treeMap.get(nodeName);
    }

    private static Map<String, Node<String>> readTree(){
        Map<String, Node<String>> nodes = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\George\\Desktop\\Projects\\fork-join-tree\\tree.txt"))){
            String line, nodeName, words[];
            List<Node<String>> children;
            while ((line = br.readLine()) != null){
                children = new ArrayList<>();
                words = line.split(" ");
                nodeName = words[0];
                for (int i = 1; i < words.length; i++) {
                    children.add(nodes.get(words[i]));
                }
                nodes.put(nodeName, new Node<String>(nodeName, children));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return nodes;
    }

}
