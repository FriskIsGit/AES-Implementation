import java.awt.*;
import java.awt.event.KeyEvent;
import java.nio.charset.StandardCharsets;

enum Standard{
    BIT_128,BIT_192,BIT_256;
}

class Draft{
    public static void main(String[] args) throws InterruptedException, AWTException{
        Printer.printGalois();
        String pass = "password";
        String data = "secret message";
        byte [] roundKey = new byte[16];
        byte [] state = new byte[16];
        for(int i = 0; i<pass.length();i++){
            roundKey[i] = (byte) pass.charAt(i);
        }
        for(int i = 0; i<data.length();i++){
            state[i] = (byte) data.charAt(i);
        }
        byte [] result = Encryption.encryptData(state,roundKey);
        System.out.println("Encrypted state");
        Printer.formatPrintArr(result,4,4);
        System.out.println(Convert.arrToString(result));
        for(int i = 0; i<pass.length();i++){
            roundKey[i] = (byte) pass.charAt(i);
        }
        byte[] finalResult = Decryption.decryptData(result,Decryption.keyScheduleList(roundKey));
        System.out.println("Decrypted state");
        Printer.formatPrintArr(finalResult,4,4);
        System.out.println(Convert.arrToString(finalResult));


    }
}