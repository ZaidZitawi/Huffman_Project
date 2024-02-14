package com.example.algo_project2;

public class Node {
    private char data;
    private int freq;
    private String huffmanCode;
    private Node left;
    private Node right;

    public Node(char data, int freq, Node left, Node right) {
        this.data = data;
        this.freq = freq;
        this.left = left;
        this.right = right;
        if (left == null && right == null)
            this.huffmanCode = "";
    }
    public Node(char data, int freq)
    {
        this.data = data;
        this.freq = freq;
        left = right = null;
    }

    public boolean isLeaf()
    {
        return this.left == null && this.right == null;
    }

    public String getHuffmanCode() { return this.huffmanCode; }

    public void setHuffmanCode(String huffmanCode) { this.huffmanCode = huffmanCode; }

    public char getData() {
        return data;
    }

    public void setData(char data) {
        this.data = data;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{ " + "("+ data +")"+ " F:"+ freq + ", huffmanCode=" + huffmanCode + "\n, left=" + left + "\t\t, right=" + right + '}';
    }
}
