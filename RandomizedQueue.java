/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;


public class RandomizedQueue<Item> implements Iterable<Item> {

    private class Node {
        Item item;
        Node next;
    }

    private Node first, last;
    private int count;

    // construct an empty randomized queue
    public RandomizedQueue() {

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the randomized queue
    public int size() {
        return count;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("argument cannot be null");
        }
        if (isEmpty()) {
            Node node = new Node();
            node.item = item;
            node.next = null;
            first = node;
            last = node;
        }
        else {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            if (isEmpty()) first = last;
            else oldLast.next = last;
        }
        count++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("RandomizedQueue is empty!");
        }
        RandomizedQueueIterator iterator = new RandomizedQueueIterator();
        Item item;
        item = iterator.current.item;

        if (iterator.current.equals(first)) {
            first = first.next;
        }
        else {
            Node prevRemoved = first;
            while (!prevRemoved.next.equals(iterator.current)) {
                prevRemoved = prevRemoved.next;
            }
            prevRemoved.next = iterator.current.next;
            if (iterator.current.equals(last)) {
                last = prevRemoved;
            }
        }

        count--;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("RandomizedQueue is empty!");
        }
        RandomizedQueueIterator iterator = new RandomizedQueueIterator();
        return iterator.next();
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private Node current;
        private int idN;
        private final int[] order;

        public RandomizedQueueIterator() {
            order = StdRandom.permutation(count);
            if(!isEmpty()) {
                current = first;
                idN = 0;
                if (order[idN] != 0) {
                    for (int i = 0; i < order[idN]; i++) {
                        current = current.next;
                    }
                }
            }
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException("no items to return");
            }
            Item item = current.item;
            current = first;
            idN++;
            if (idN == count) {
                current = null;
            }
            else if (order[idN] != 0) {
                for (int i = 0; i < order[idN]; i++) {
                    current = current.next;
                }
            }
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("you cannot remove");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        System.out.println("randomizedQueue.isEmpty()? - " + randomizedQueue.isEmpty());
        randomizedQueue.enqueue("ddd");
        randomizedQueue.enqueue("fff");
        randomizedQueue.enqueue("eee");
        randomizedQueue.enqueue("ooo");
        System.out.println("RandomizedQueue 1:");
        for (String el : randomizedQueue) {
            System.out.println(el);
        }
        System.out.println("RandomizedQueue 2:");
        for (String el : randomizedQueue) {
            System.out.println(el);
        }
        System.out.println("RandomizedQueue 3:");
        for (String el : randomizedQueue) {
            System.out.println(el);
        }
        System.out.println("size = " + randomizedQueue.size());
        System.out.println("dequeue!");
        System.out.println("randomizedQueue.dequeue() = " + randomizedQueue.dequeue());
        System.out.println("RandomizedQueue after dequeue 1:");
        for (String el : randomizedQueue) {
            System.out.println(el);
        }
        System.out.println("RandomizedQueue after dequeue 2:");
        for (String el : randomizedQueue) {
            System.out.println(el);
        }
        System.out.println("RandomizedQueue after dequeue 3:");
        for (String el : randomizedQueue) {
            System.out.println(el);
        }
        System.out.println("size after dequeue = " + randomizedQueue.size());
        System.out.println("randomizedQueue.sample() = " + randomizedQueue.sample());
        System.out.println("size = " + randomizedQueue.size());
    }

}
