package com.example.algo_project2;
import java.util.Arrays;

public class Heap {
    private Node[] heap;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public Heap() {
        heap = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    // Add a node to the heap
    public void add(Node node) {
        ensureCapacity();
        heap[size] = node;
        size++;
        heapifyUp();
    }

    // Remove and return the minimum node from the heap
    public Node poll() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        Node minNode = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown();

        return minNode;
    }

    private void heapifyUp() {
        int index = size - 1;

        while (hasParent(index) && getParent(index).getFreq() > heap[index].getFreq()) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    private void heapifyDown() {
        int index = 0;

        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);

            if (hasRightChild(index) && getRightChild(index).getFreq() < getLeftChild(index).getFreq()) {
                smallerChildIndex = getRightChildIndex(index);
            }

            if (heap[index].getFreq() < heap[smallerChildIndex].getFreq()) {
                break;
            } else {
                swap(index, smallerChildIndex);
            }

            index = smallerChildIndex;
        }
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (size == heap.length) {
            heap = Arrays.copyOf(heap, heap.length * 2);
        }
    }

    private boolean hasParent(int index) {
        return index > 0;
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private Node getParent(int index) {
        return heap[getParentIndex(index)];
    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    private Node getLeftChild(int index) {
        return heap[getLeftChildIndex(index)];
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    private Node getRightChild(int index) {
        return heap[getRightChildIndex(index)];
    }

    private void swap(int index1, int index2) {
        Node temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    public Node[] getHeap() {
        return heap;
    }

    public void setHeap(Node[] heap) {
        this.heap = heap;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    //    public static void main(String[] args) {
//        Heap minHeap = new MinHeap();
//
//        Node node1 = new Node('a', 5);
//        Node node2 = new Node('b', 3);
//        Node node3 = new Node('c', 8);
//        Node node4 = new Node('d', 1);
//
//        minHeap.add(node1);
//        minHeap.add(node2);
//        minHeap.add(node3);
//        minHeap.add(node4);
//
//        Node minNode = minHeap.poll();
//        System.out.println("Min node: data = " + minNode.data + ", freq = " + minNode.freq);
//    }
}


//
//public class Heap {
//    private Node [] Data;
//    private int size;
//    private int MaxSize;
//    private static final int FRONT = 1;
//
//    //use constructor to initialize heapData array
//    public Heap(int MaxSize)  {
//        this.MaxSize = MaxSize;
//        this.size = 0;
//        Data = new Node[this.MaxSize + 1];
//        Data[0] = new Node('0', 0);
//    }
//
//    // create getParentPos() method that returns parent position for the node
//    private int getParentPosition(int position)  {
//        return position / 2;
//    }
//
//    // create getLeftChildPosition() method that returns the position of left child
//    private int getLeftChildPosition(int position)  {
//        return (2 * position);
//    }
//
//    // create getRightChildPosition() method that returns the position of right child
//    private int getRightChildPosition(int position)  {
//        return (2 * position) + 1;
//    }
//
//    // checks whether the given node is leaf or not
//    private boolean checkLeaf(int position)  {
//        if (position >= (size / 2) && position <= size) {
//            return true;
//        }
//        return false;
//    }
//
//    // create swapNodes() method that perform swapping of the given nodes of the heap
//    // firstNode and secondNode are the positions of the nodes
//    private void swap(int firstNode, int secondNode)  {
//        Node temp;
//        temp = Data[firstNode];
//        Data[firstNode] = Data[secondNode];
//        Data[secondNode] = temp;
//    }
//
//    // create minHeapify() method to heapify the node for maintaining the heap property
//    private void minHeapify(int position)  {
//
//        //check whether the given node is non-leaf and greater than its right and left child
//        if (!checkLeaf(position)) {
//            if (Data[position].getFreq() > Data[getLeftChildPosition(position)].getFreq() || Data[position].getFreq() >Data[getRightChildPosition(position)].getFreq()) {
//
//                // swap with left child and then heapify the left child
//                if (Data[getLeftChildPosition(position)].getFreq() <Data[getRightChildPosition(position)].getFreq()) {
//                    swap(position, getLeftChildPosition(position));
//                    minHeapify(getLeftChildPosition(position));
//                }
//
//                // Swap with the right child and heapify the right child
//                else {
//                    swap(position, getRightChildPosition(position));
//                    minHeapify(getRightChildPosition(position));
//                }
//            }
//        }
//    }
//
//    // create insertNode() method to insert element in the heap
//    public void insertNode(Node data)  {
//        if (size >= MaxSize) {
//            return;
//        }
//        Data[++size] = data;
//        int current = size;
//
//        while (Data[current].getFreq() <Data[getParentPosition(current)].getFreq()) {
//            swap(current, getParentPosition(current));
//            current = getParentPosition(current);
//        }
//    }
//
//    // crreatedisplayHeap() method to print the data of the heap
//    public void displayHeap()  {
//        System.out.println("PARENT NODE" + "\t" + "LEFT CHILD NODE" + "\t" + "RIGHT CHILD NODE");
//        for (int k = 1; k <= size / 2; k++) {
//            System.out.print(" " + Data[k] + "\t\t" + Data[2 * k] + "\t\t" + Data[2 * k + 1]);
//            System.out.println();
//        }
//    }
//
//    // create designMinHeap() method to construct min heap
//    public void designMinHeap()  {
//        for (int position = (size / 2); position >= 1; position--) {
//            minHeapify(position);
//        }
//    }
//    public Node removeRoot()  {
//        Node popElement = Data[FRONT];
//        Data[FRONT] = Data[size--];
//        minHeapify(FRONT);
//        return popElement;
//    }
//
//
//
//    public Node[] getData() {
//        return Data;
//    }
//
//    public void setData(Node[] data) {
//        Data = data;
//    }
//
//    public int getSize() {
//        return size;
//    }
//
//    public void setSize(int size) {
//        this.size = size;
//    }
//
//    public int getMaxSize() {
//        return MaxSize;
//    }
//
//    public void setMaxSize(int maxSize) {
//        MaxSize = maxSize;
//    }
//}