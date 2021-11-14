import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
public class KeyScheduleTest{

    @Test
    public void test4by4_rcon0(){
        String [] expectedStrArr = new String[]{"a0","88","23","2a","fa","54","a3","6c","fe","2c","39","76","17","b1","39","05"};
        byte [] expectedArr = Convert.arrToByteArr(expectedStrArr);

        byte [] cipherKey0 = new byte[]{43, 40, -85, 9, 126, -82, -9, -49, 21, -46, 21, 79, 22, -90, -120, 60};
        byte [] roundKey0 = Encryption.keySchedule(cipherKey0,0);
        assertArrayEquals(expectedArr,roundKey0);
    }
    @Test
    public void test4by4_rcon1(){
        String [] expectedStrArr = new String[]{"f2","7a","59","73","c2","96","35","59","95","b9","80","f6","f2","43","7a","7f"};
        byte [] expectedArr = Convert.arrToByteArr(expectedStrArr);

        byte [] cipherKey1 = {-96, -120, 35, 42, -6, 84, -93, 108, -2, 44, 57, 118, 23, -79, 57, 5};
        byte [] roundKey1 = Encryption.keySchedule(cipherKey1,1);
        assertArrayEquals(expectedArr,roundKey1);
    }
    @Test
    public void test4by4_rcon9(){
        byte [] nextKey = {-96, -120, 35, 42, -6, 84, -93, 108, -2, 44, 57, 118, 23, -79, 57, 5};
        for(int round = 1; round<10; round++){
            nextKey = Encryption.keySchedule(nextKey,round);
        }
        String [] expectedHex = new String[]{"d0","c9","e1","b6","14","ee","3f","63","f9","25","0c","0c","a8","89","c8","a6"};
        assertArrayEquals(Convert.arrToByteArr(expectedHex),nextKey);
    }

}

