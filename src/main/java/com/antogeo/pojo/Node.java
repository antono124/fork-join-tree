package com.antogeo.pojo;

import java.util.List;

public class Node<T> {

    private List<Node<T>> children;

    private T data;

    public Node(T data, List<Node<T>> children) {
        this.children = children;
        this.data = data;
    }
    public List<Node<T>> getChildren() {
        return children;
    }

    public T getData() {
        return data;
    }


}
