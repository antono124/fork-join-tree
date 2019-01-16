package com.antogeo.service;

import com.antogeo.pojo.Node;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
public class TreeService {

    @Value("${tree.file.path}")
    private String treeTxtPath;

    public Node<String> getRoot(String nodeName){
        Map<String, Node<String>> treeMap = readTree();
        return treeMap.get(nodeName);
    }

    private Map<String, Node<String>> readTree(){
        Map<String, Node<String>> nodes = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(treeTxtPath))){
            String line, nodeName, words[];
            List<Node<String>> children;
            while ((line = br.readLine()) != null){
                children = new ArrayList<>();
                words = line.split(" ");
                nodeName = words[0];
                for (int i = 1; i < words.length; i++) {
                    children.add(nodes.get(words[i]));
                }
                nodes.put(nodeName, new Node<>(nodeName, children));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return nodes;
    }

}
