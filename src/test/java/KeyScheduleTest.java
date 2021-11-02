import org.junit.Test;

import java.util.Arrays;
import static org.junit.Assert.assertArrayEquals;
public class KeyScheduleTest{
    @Test
    public void test32Columns(){
        //32 columns (words)
        byte [] cipher = {
            43,  40,  -85,   9, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            126,-82,   -9, -49, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            21, -46,   21,  79, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            22, -90, -120,  60, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        Main.keySchedule(cipher);
        System.out.println(Arrays.toString(Convert.arrToHexStringArr(cipher)));
    }
    @Test
    public void test4by4(){
        byte [] cipherKey = new byte[]{43, 40, -85, 9, 126, -82, -9, -49, 21, -46, 21, 79, 22, -90, -120, 60};
        byte [] roundKey = Main.keySchedule(cipherKey);
        String [] expectedStrArr = new String[]{"a0","88","23","2a","fa","54","a3","6c","fe","2c","39","76","17","b1","39","05"};
        byte [] expectedArr = new byte[16];
        for(int i = 0; i<expectedStrArr.length; i++){
            expectedArr[i] = (byte)Convert.hexStringToInt(expectedStrArr[i]);
        }
        assertArrayEquals(expectedArr,roundKey);
    }
}
