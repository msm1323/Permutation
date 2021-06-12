/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

public class Permutation {

    public static void main(String[] args) {

        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();

        while (StdIn.isEmpty()) {
            randomizedQueue.enqueue(StdIn.readString());
        }

        Iterator<String> iterator = randomizedQueue.iterator();
        for (int i = 0; i < k; i++) {
            System.out.println(iterator.next());
        }
    }

}
