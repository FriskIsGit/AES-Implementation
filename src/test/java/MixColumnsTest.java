import org.junit.Test;
import static org.junit.Assert.*;
public class MixColumnsTest{
    byte byteR0C0 = (byte)0xd4;
    byte byteR1C0 = (byte)0xbf;
    byte byteR2C0 = (byte)0x5d;
    byte byteR3C0 = (byte)0x30;
    @Test
    public void testR0C0(){
        byte result1 = Main.multiply(byteR0C0,2);
        byte result2 = Main.multiply(byteR1C0,3);
        byte result3 = Main.multiply(byteR2C0,1);
        byte result4 = Main.multiply(byteR3C0,1);
        assertEquals((byte)0x4,result1^result2^result3^result4);
    }
    @Test
    public void testR1C0(){
        byte result1 = Main.multiply(byteR0C0,1);
        byte result2 = Main.multiply(byteR1C0,2);
        byte result3 = Main.multiply(byteR2C0,3);
        byte result4 = Main.multiply(byteR3C0,1);
        assertEquals((byte)0x66,result1^result2^result3^result4);
    }
    @Test
    public void testR2C0(){
        byte result1 = Main.multiply(byteR0C0,1);
        byte result2 = Main.multiply(byteR1C0,1);
        byte result3 = Main.multiply(byteR2C0,2);
        byte result4 = Main.multiply(byteR3C0,3);
        assertEquals((byte)0x81,result1^result2^result3^result4);
    }
    @Test
    public void testR3C0(){
        byte result1 = Main.multiply(byteR0C0,3);
        byte result2 = Main.multiply(byteR1C0,1);
        byte result3 = Main.multiply(byteR2C0,1);
        byte result4 = Main.multiply(byteR3C0,2);
        assertEquals((byte)0xe5,result1^result2^result3^result4);
    }
}
