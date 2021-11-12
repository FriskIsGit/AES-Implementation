import org.junit.Test;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class FullEncryptionTest{
    final static int TESTS = 2_000_000;
    final static int ROUNDS = 10;
    static List<Long> times = new LinkedList<>();

    protected static byte[] encryptData(byte[] state, byte[] roundKey){
        Encryption.addRoundKey(state,roundKey);
        for(int round = 1; round<ROUNDS; round++){
            Encryption.subBytes(state);
            Encryption.shiftRows(state);
            state = Encryption.mixColumns(state);

            long st = System.nanoTime();
            roundKey = Encryption.keySchedule(roundKey,round-1);
            long en = System.nanoTime();
            times.add(en - st);

            Encryption.addRoundKey(state,roundKey);
        }
        Encryption.subBytes(state);
        Encryption.shiftRows(state);
        roundKey = Encryption.keySchedule(roundKey,ROUNDS-1);
        Encryption.addRoundKey(state,roundKey);
        return state;
    }

    public void benchmarkPerformance(){

        int [] initialState = new int[]{0x32,0x88,0x31,0xe0,0x43,0x5a,0x31,0x37,0xf6,0x30,0x98,0x07,0xa8,0x8d,0xa2,0x34};
        byte [] state = Convert.arrToByteArr(initialState);
        byte [] roundKey = new byte[]{43, 40, -85, 9, 126, -82, -9, -49, 21, -46, 21, 79, 22, -90, -120, 60};

        for(int i = 0;i<TESTS; i++) FullEncryptionTest.encryptData(state, roundKey);

        //int count = 0;
        BigInteger sum = new BigInteger("0");
        for(long timeStamp : times){
            //if(timeStamp!=0){
                //count++;
            sum = sum.add(new BigInteger(String.valueOf(timeStamp)));
            //}
        }
        BigInteger divisor = new BigInteger(String.valueOf(times.size()));
        BigInteger result = sum.divide(divisor);
        System.out.println("Average in nano: " + result + " ;Timestamps: " + times.size());

    }
    @Test
    public void _10RoundsTest(){
        int [] expectedState = new int[]{0x39,0x02,0xdc,0x19,0x25,0xdc,0x11,0x6a,0x84,0x09,0x85,0x0b,0x1d,0xfb,0x97,0x32};
        int [] initialState = new int[]{0x32,0x88,0x31,0xe0,0x43,0x5a,0x31,0x37,0xf6,0x30,0x98,0x07,0xa8,0x8d,0xa2,0x34};

        byte [] state = Convert.arrToByteArr(initialState);
        byte [] roundKey = new byte[]{43, 40, -85, 9, 126, -82, -9, -49, 21, -46, 21, 79, 22, -90, -120, 60};
        state = Encryption.encryptData(state,roundKey);
        System.out.println("Final");
        Printer.formatPrintArr(expectedState,4,4);
        assertArrayEquals(Convert.arrToByteArr(expectedState),state);
    }
    @Test
    public void stringTextTest(){
        String plainText = "Two One Nine Two";
        String key = "Thats my Kung Fu";
        System.out.println("Plain len: " + plainText.length());
        System.out.println("Key len: " + key.length());
        byte [] state = plainText.getBytes(StandardCharsets.UTF_8);
        byte [] roundKey = key.getBytes(StandardCharsets.UTF_8);
        state = Encryption.encryptData(state,roundKey);

        Printer.formatPrintArr(state,4,4);
        System.out.println("Final");
        Printer.formatPrintArr(Convert.arrToHexStringArr(state),4,4);
        System.out.println(Convert.arrToString(state));
        //assertArrayEquals(Convert.arrToByteArr(expectedState),state);
    }
}
