import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * #1 KeyExpansion
 * #2 Initial round key addition:
 *      - addRoundKey
 *      - subBytes (S_BOX)
 *      - shiftRows
 *      - mixColumns
 *      - addRoundKey
 * #3 Final round
 *      - subBytes (S_BOX)
 *      - shiftRows
 *      - addRoundKey
 */
class Main{

    public static int getRandom(){
        return (int)(Math.random()*(122-97+1))+97;
    }
    public static void main(String[] args)  {
        Converter con = new Converter();
        int [] arr = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
        String [] strArr = new String[]{"19","3d","e3","be","a0","f4","e2","2b","9a","c6","8d","2a","e9","f8","48","08"};
        con.display4by4(arr);
        //new Converter().display4by4(strArr);
        System.out.println(con.hexStringToInt("fc"));

        //getPass();
    }


    private static void getPass(){
        Scanner scan = new Scanner(System.in);
        String pass = scan.nextLine();
        System.out.println("Length: " + pass.length());
    }
}
