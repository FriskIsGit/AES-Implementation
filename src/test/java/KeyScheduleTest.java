import org.junit.Test;

import java.util.Arrays;

public class KeyScheduleTest{
    @Test
    public void testSingleBlock(){
        //32 columns (words)
        byte [] cipher = {
            43,  40,  -85,   9, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            126,-82,   -9, -49, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            21, -46,   21,  79, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            22, -90, -120,  60, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        Main.keySchedule(cipher);
        System.out.println(Arrays.toString(Convert.arrToHexStringArr(cipher)));
    }
}
