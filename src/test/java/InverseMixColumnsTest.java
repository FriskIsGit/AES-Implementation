import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class InverseMixColumnsTest{
    @Test
    public void encryptWayMixColumns(){
        int [] state1 = new int[]{0xd4, 0xe0, 0xb8, 0x1e, 0xbf, 0xb4, 0x41, 0x27, 0x5d, 0x52, 0x11, 0x98, 0x30, 0xae, 0xf1, 0xe5};
        int [] afterState = new int[]{0x04, 0xe0, 0x48, 0x28, 0x66, 0xcb, 0xf8,0x6, 0x81, 0x19, 0xd3, 0x26, 0xe5, 0x9a, 0x7a, 0x4c};
        Printer.formatPrintArr(Convert.arrToHexStringArr(state1),4,4);
        System.out.println();
        byte[] result = Encryption.mixColumns(Convert.arrToByteArr(state1));
        Printer.formatPrintArr(Convert.arrToHexStringArr(result),4,4);
        assertArrayEquals(Convert.arrToByteArr(afterState),result);
    }
    @Test
    public void reverseMix(){
        int [] reversed = new int[]{0xd4, 0xe0, 0xb8, 0x1e, 0xbf, 0xb4, 0x41, 0x27, 0x5d, 0x52, 0x11, 0x98, 0x30, 0xae, 0xf1, 0xe5};
        byte [] initState = new byte[]{4, -32, 72, 40, 102, -53, -8, 6, -127, 25, -45, 38, -27, -102, 122, 76};

        byte [] result = Decryption.inverseMixColumns(initState);
        Printer.formatPrintArr(result,4,4);
        assertArrayEquals(Convert.arrToByteArr(reversed),result);
    }
    @Test
    public void tutDotProduct(){

        List<Integer> powersList1 = Decryption.getPowers((byte)214);
        List<Integer> powersList2 = Decryption.getPowers((byte)54);
        HashMap<Integer,Boolean> map = Decryption.getOddsMap(powersList1,powersList2);
        int num = Decryption.translateToNumber(map);
        byte reduced = Decryption.reduce(num);

        assertEquals(Integer.parseInt("10001000",2),Byte.toUnsignedInt(reduced));
    }
    @Test
    public void singleMultiplication(){
        byte firstCalc = Decryption.multiplyLarge((byte)4,14);
        byte secondCalc = Decryption.multiplyLarge((byte)0x66,11);
        byte thirdCalc = Decryption.multiplyLarge((byte)0x81,13);
        byte fourthCalc = Decryption.multiplyLarge((byte)0xe5,9);
        byte result = (byte) (firstCalc^secondCalc^thirdCalc^fourthCalc);
        //56 = 0x04 * 14

        assertEquals(0xd4,Byte.toUnsignedInt(result));
    }
}
