import org.junit.Test;

import java.util.List;
import static org.junit.Assert.*;

public class FullDecryptionTest{
    private static final int ROUNDS = 10;

    private static byte[] decryptData(byte[] state, List<byte[]> roundKeys){
        Encryption.addRoundKey(state, roundKeys.get(ROUNDS));
        Decryption.inverseShiftRows(state);
        Decryption.inverseSubBytes(state);
        for (int round = 1; round < ROUNDS; round++){
            Encryption.addRoundKey(state, roundKeys.get(ROUNDS-round));
            state = Decryption.inverseMixColumns(state);
            Decryption.inverseShiftRows(state);
            Decryption.inverseSubBytes(state);
        }
        Encryption.addRoundKey(state, roundKeys.get(0));
        return state;
    }

    @Test
    public void test1(){
        int[] expected = new int[]{0x32,0x88,0x31,0xe0,0x43,0x5a,0x31,0x37,0xf6,0x30,0x98,0x07,0xa8,0x8d,0xa2,0x34};
        int[] initial  = new int[]{0x39,0x02,0xdc,0x19,0x25,0xdc,0x11,0x6a,0x84,0x09,0x85,0x0b,0x1d,0xfb,0x97,0x32};

        byte[] state = Convert.arrToByteArr(initial);

        byte[] roundKey = new byte[]{43, 40, -85, 9, 126, -82, -9, -49, 21, -46, 21, 79, 22, -90, -120, 60};
        List<byte[]> list = Decryption.keyScheduleList(roundKey);

        assertArrayEquals(Convert.arrToByteArr(expected), decryptData(state,list));
    }
}
