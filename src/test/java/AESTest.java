import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class AESTest{
    @Test
    public void rounds10test(){
        int [] initialState = new int[]{0x32,0x88,0x31,0xe0,0x43,0x5a,0x31,0x37,0xf6,0x30,0x98,0x07,0xa8,0x8d,0xa2,0x34};
        byte [] state = Convert.arrToByteArr(initialState);

        byte [] roundKey = new byte[]{43, 40, -85, 9, 126, -82, -9, -49, 21, -46, 21, 79, 22, -90, -120, 60};
        state = Main.encryptData(state,roundKey);
        Printer.formatPrintArr(Convert.arrToHexStringArr(state),4,4);
        System.out.println();
        int [] expectedState = new int[]{0x39,0x02,0xdc,0x19,0x25,0xdc,0x11,0x6a,0x84,0x09,0x85,0x0b,0x1d,0xfb,0x97,0x32};
        Printer.formatPrintArr(expectedState,4,4);
        System.out.println(Convert.arrToString(state));
        assertArrayEquals(Convert.arrToByteArr(expectedState),state);
    }
}
