package com.antogeo.pojo;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {

    private List<Node<T>> children = new ArrayList<Node<T>>();

    private T data;

    public Node(T data) {
        this.data = data;
    }

    public Node(T data, List<Node<T>> children) {
        this.children = children;
        this.data = data;
    }

    public boolean isLeaf() {
        return this.children.size() == 0;
    }

    public void addChild(T data) {
        Node<T> child = new Node<T>(data);
        this.children.add(child);
    }

    public void addChild(Node<T> child) {
        this.children.add(child);
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public void setChildren(List<Node<T>> children) {
        this.children = children;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
