import org.junit.Test;
import static org.junit.Assert.*;
public class MixColumnsTest{
    byte byteRow0Col0 = (byte)0xd4;
    byte byteRow1Col0 = (byte)0xbf;
    byte byteRow2Col0 = (byte)0x5d;
    byte byteRow3Col0 = (byte)0x30;

    @Test
    public void testRow0Col0(){
        byte result1 = Encryption.multiply(byteRow0Col0,2);
        byte result2 = Encryption.multiply(byteRow1Col0,3);
        byte result3 = Encryption.multiply(byteRow2Col0,1);
        byte result4 = Encryption.multiply(byteRow3Col0,1);
        assertEquals((byte)0x4,result1^result2^result3^result4);
    }
    @Test
    public void testRow1Col0(){
        byte result1 = Encryption.multiply(byteRow0Col0,1);
        byte result2 = Encryption.multiply(byteRow1Col0,2);
        byte result3 = Encryption.multiply(byteRow2Col0,3);
        byte result4 = Encryption.multiply(byteRow3Col0,1);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
        assertEquals((byte)0x66,result1^result2^result3^result4);
    }
    @Test
    public void testRow2Col0(){
        byte result1 = Encryption.multiply(byteRow0Col0,1);
        byte result2 = Encryption.multiply(byteRow1Col0,1);
        byte result3 = Encryption.multiply(byteRow2Col0,2);
        byte result4 = Encryption.multiply(byteRow3Col0,3);
        assertEquals((byte)0x81,result1^result2^result3^result4);
    }
    @Test
    public void testRow3Col0(){
        byte result1 = Encryption.multiply(byteRow0Col0,3);
        byte result2 = Encryption.multiply(byteRow1Col0,1);
        byte result3 = Encryption.multiply(byteRow2Col0,1);
        byte result4 = Encryption.multiply(byteRow3Col0,2);
        assertEquals((byte)0xe5,result1^result2^result3^result4);
    }

    byte byteRow0Col1 = (byte)0xe0;
    byte byteRow1Col1 = (byte)0xb4;
    byte byteRow2Col1 = (byte)0x52;
    byte byteRow3Col1 = (byte)0xae;

    @Test
    public void testRow0Col1(){
        byte result1 = Encryption.multiply(byteRow0Col1,2);
        byte result2 = Encryption.multiply(byteRow1Col1,3);
        byte result3 = Encryption.multiply(byteRow2Col1,1);
        byte result4 = Encryption.multiply(byteRow3Col1,1);
        assertEquals((byte)0xe0,result1^result2^result3^result4);
    }
    @Test
    public void testRow1Col1(){
        byte result1 = Encryption.multiply(byteRow0Col1,1);
        byte result2 = Encryption.multiply(byteRow1Col1,2);
        byte result3 = Encryption.multiply(byteRow2Col1,3);
        byte result4 = Encryption.multiply(byteRow3Col1,1);
        assertEquals((byte)0xcb,result1^result2^result3^result4);
    }
    @Test
    public void testRow2Col1(){
        byte result1 = Encryption.multiply(byteRow0Col1,1);
        byte result2 = Encryption.multiply(byteRow1Col1,1);
        byte result3 = Encryption.multiply(byteRow2Col1,2);
        byte result4 = Encryption.multiply(byteRow3Col1,3);
        assertEquals((byte)0x19,result1^result2^result3^result4);
    }
    @Test
    public void testRow3Col1(){
        byte result1 = Encryption.multiply(byteRow0Col1,3);
        byte result2 = Encryption.multiply(byteRow1Col1,1);
        byte result3 = Encryption.multiply(byteRow2Col1,1);
        byte result4 = Encryption.multiply(byteRow3Col1,2);
        assertEquals((byte)0x9a,result1^result2^result3^result4);
    }

    @Test
    public void entireArrayTransformation(){
        byte [] expected = new byte[]{(byte)0x4, (byte)0xe0, (byte)0x48,(byte)0x28,(byte)0x66,(byte)0xcb,(byte)0xf8,(byte)0x6,(byte)0x81,(byte)0x19,(byte)0xd3,(byte)0x26,(byte)0xe5,(byte)0x9a,(byte)0x7a,(byte)0x4c};
        assertArrayEquals(expected, Encryption.mixColumns(new byte[]{-44, -32, -72, 30, -65, -76, 65, 39, 93, 82, 17, -104, 48, -82, -15, -27}));
    }
}
