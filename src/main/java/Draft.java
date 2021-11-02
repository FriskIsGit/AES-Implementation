import java.awt.*;

enum Standard{
    BIT_128,BIT_192,BIT_256;
}

class Draft{
    public static void main(String[] args) throws InterruptedException{
//        for(int i = 0; i<28;i++){
//            System.out.print("0,");
//        }
        Toolkit kit = java.awt.Toolkit.getDefaultToolkit();
        for(int i =0; i<100; i++) {
            Thread.sleep(0);
            kit.beep();
        }
        //System.out.println(Convert.intToHexString(Convert.hexStringToInt("ab")^Convert.hexStringToInt("88")));


    }
}