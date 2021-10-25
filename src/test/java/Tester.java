import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class Tester{
    // A - 10; B - 11; C - 12; D - 13; E - 14;  F - 15;
    @Test
    public void test1(){
        assertArrayEquals(new int[]{15, 15}, Convert.unsignedByteToIndices(255));
    }
    @Test
    public void test2(){
        assertArrayEquals(new int[]{10,7}, Convert.unsignedByteToIndices(167));
    }
    @Test
    public void test3(){
        assertArrayEquals(new int[]{7,13}, Convert.unsignedByteToIndices(125));
    }
    @Test
    public void test4(){
        assertArrayEquals(new int[]{1,10}, Convert.unsignedByteToIndices(26));
    }
    @Test
    public void hex16(){
        assertArrayEquals(new int[]{1,0}, Convert.unsignedByteToIndices(16));
    }
    @Test
    public void testF(){
        assertArrayEquals(new int[]{0,15}, Convert.unsignedByteToIndices(15));
    }
    @Test
    public void single(){
        assertArrayEquals(new int[]{0,1}, Convert.unsignedByteToIndices(1));
    }
    @Test
    public void upperBound(){
        assertArrayEquals(new int[]{15,15}, Convert.unsignedByteToIndices(255));
    }
    @Test
    public void lowerBound(){
        assertArrayEquals(new int[]{0,0}, Convert.unsignedByteToIndices(0));
    }



}
