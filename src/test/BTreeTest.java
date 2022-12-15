package test;

import org.junit.Test;

import structures.BTree;

public class BTreeTest {
    
    @Test
    public void removeTest() {
        BTree<Integer> bTree = new BTree<Integer>(2);
        bTree.add(1);

        bTree.add(7);
        bTree.add(4);
        bTree.add(8);

        bTree.add(3);
        bTree.add(6);
        bTree.add(5);

        System.out.println(bTree);

        System.out.println(bTree.remove(4));
        
        System.out.println(bTree);
    }

    @Test
    public void rebalanceTest() {
        BTree<Integer> bTree = new BTree<>(1);
        bTree.add(2);
        bTree.add(3);
        bTree.add(4);
        bTree.add(5);
        bTree.add(6);
        bTree.add(7);
        bTree.add(8);
        bTree.add(9);
        bTree.add(10);
        System.out.println(bTree);
        bTree.rebalance();
        System.out.println(bTree);
    }
}
 