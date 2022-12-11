package test;

import org.junit.Test;

import helper.classes.BTree;

public class BTreeTest {
    
    @Test
    public void treeTest() {
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
}
