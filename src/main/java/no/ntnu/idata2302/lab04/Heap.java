package no.ntnu.idata2302.lab04;

import java.util.ArrayList;

public class Heap {

    public static Heap fromValues(int... values) {
        var heap = new Heap();
        for (var each : values) {
            heap.insert(each);
        }
        return heap;
    }

    private ArrayList<Integer> array;

    public Heap() {
        array = new ArrayList<Integer>();
        array.add(0);
    }

    public void insert(Integer k) {
        array.add(k);
        int i = array.size() - 1;
        while (i > 1 && array.get(parentOf(i)) > array.get(i)) {
            swap(i, parentOf(i));
            i = parentOf(i);
        }
    }


    public int takeMinimum() {
        if (array.size() <= 1) {
            throw new RuntimeException("Heap is empty");
        }
        int min = array.get(1);
        int last = array.remove(array.size() - 1);
        if (array.size() == 1) {
            return min;
        }
        array.set(1, last);
        int i = 1;
        while (true) {
            int left = leftChildOf(i);
            int right = rightChildOf(i);
            int smallest = i;

            if (left < array.size() && array.get(left) < array.get(smallest)) {
                smallest = left;
            }
            if (right < array.size() && array.get(right) < array.get(smallest)) {
                smallest = right;
            }
            if (smallest != i) {
                swap(i, smallest);
                i = smallest;
            } else {
                break;
            }
        }
        return min;
    }

    public void decreaseKey(int i, int k) {
        if (i <= 0 || i >= array.size()) {
            throw new RuntimeException("Index out of bounds");
        }

        int current = array.get(i);
        if (k >= current) {
            throw new RuntimeException("New key must be smaller than current value");
        }

        array.set(i, k);
        while (i > 1 && array.get(parentOf(i)) > array.get(i)) {
            swap(i, parentOf(i));
            i = parentOf(i);
        }
    }

    private int parentOf(int index) {
        return index / 2;
    }

    private int leftChildOf(int index) {
        return index * 2;
    }

    private int rightChildOf(int index) {
        return index * 2 + 1;
    }

    void swap(int pos1, int pos2) {
        int temp = array.get(pos1);
        array.set(pos1, array.get(pos2));
        array.set(pos2, temp);
    }
}
