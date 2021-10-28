import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class SubBytesTest{
    @Test
    public void replaceBytes(){
        byte [] expected = new byte[]{
                (byte)0xd4, (byte)0xe0, (byte)0xb8,(byte)0x1e,
                (byte)0x27,(byte)0xbf,(byte)0xb4,(byte)0x41,
                (byte)0x11,(byte)0x98,(byte)0x5d,(byte)0x52,
                (byte)0xae,(byte)0xf1,(byte)0xe5,(byte)0x30};

        byte [] state = new byte[]{
                (byte)0x19, (byte)0xa0, (byte)0x9a,(byte)0xe9,
                (byte)0x3d,(byte)0xf4,(byte)0xc6,(byte)0xf8,
                (byte)0xe3,(byte)0xe2,(byte)0x8d,(byte)0x48,
                (byte)0xbe,(byte)0x2b,(byte)0x2a,(byte)0x08};
        long start = System.nanoTime();
        Main.subBytes(state);
        long end = System.nanoTime();
        System.out.println("time: " +(end-start));
        assertArrayEquals(expected,state);
    }
    @Test
    public void customTest(){
        byte [] elements_32 = new byte[]{
                (byte)0xd4, (byte)0xe0, (byte)0xb8,(byte)0x1e,
                (byte)0x27, (byte)0xbf, (byte)0xb4,(byte)0x41,
                (byte)0x11, (byte)0x98, (byte)0x5d,(byte)0x52,
                (byte)0xae, (byte)0xf1, (byte)0xe5,(byte)0x30,
                (byte)0x19, (byte)0xa0, (byte)0x9a,(byte)0xe9,
                (byte)0x3d, (byte)0xf4, (byte)0xc6,(byte)0xf8,
                (byte)0xe3, (byte)0xe2, (byte)0x8d,(byte)0x48,
                (byte)0xbe, (byte)0x2b, (byte)0x2a,(byte)0x08};

        String kungFu = "Try to look cool";
        System.out.println(kungFu.length());
        byte [] bytes = kungFu.getBytes(StandardCharsets.UTF_8);
        Main.subBytes(bytes);



    }
}
