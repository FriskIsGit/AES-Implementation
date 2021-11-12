import java.util.*;

public class Decryption extends Encryption{
    final static int[] INT_INVERSE_S_BOX = new int[]{
            0x52, 0x09, 0x6a, 0xd5, 0x30, 0x36, 0xa5, 0x38, 0xbf, 0x40, 0xa3, 0x9e, 0x81, 0xf3, 0xd7, 0xfb,
            0x7c, 0xe3, 0x39, 0x82, 0x9b, 0x2f, 0xff, 0x87, 0x34, 0x8e, 0x43, 0x44, 0xc4, 0xde, 0xe9, 0xcb,
            0x54, 0x7b, 0x94, 0x32, 0xa6, 0xc2, 0x23, 0x3d, 0xee, 0x4c, 0x95, 0x0b, 0x42, 0xfa, 0xc3, 0x4e,
            0x08, 0x2e, 0xa1, 0x66, 0x28, 0xd9, 0x24, 0xb2, 0x76, 0x5b, 0xa2, 0x49, 0x6d, 0x8b, 0xd1, 0x25,
            0x72, 0xf8, 0xf6, 0x64, 0x86, 0x68, 0x98, 0x16, 0xd4, 0xa4, 0x5c, 0xcc, 0x5d, 0x65, 0xb6, 0x92,
            0x6c, 0x70, 0x48, 0x50, 0xfd, 0xed, 0xb9, 0xda, 0x5e, 0x15, 0x46, 0x57, 0xa7, 0x8d, 0x9d, 0x84,
            0x90, 0xd8, 0xab, 0x00, 0x8c, 0xbc, 0xd3, 0x0a, 0xf7, 0xe4, 0x58, 0x05, 0xb8, 0xb3, 0x45, 0x06,
            0xd0, 0x2c, 0x1e, 0x8f, 0xca, 0x3f, 0x0f, 0x02, 0xc1, 0xaf, 0xbd, 0x03, 0x01, 0x13, 0x8a, 0x6b,
            0x3a, 0x91, 0x11, 0x41, 0x4f, 0x67, 0xdc, 0xea, 0x97, 0xf2, 0xcf, 0xce, 0xf0, 0xb4, 0xe6, 0x73,
            0x96, 0xac, 0x74, 0x22, 0xe7, 0xad, 0x35, 0x85, 0xe2, 0xf9, 0x37, 0xe8, 0x1c, 0x75, 0xdf, 0x6e,
            0x47, 0xf1, 0x1a, 0x71, 0x1d, 0x29, 0xc5, 0x89, 0x6f, 0xb7, 0x62, 0x0e, 0xaa, 0x18, 0xbe, 0x1b,
            0xfc, 0x56, 0x3e, 0x4b, 0xc6, 0xd2, 0x79, 0x20, 0x9a, 0xdb, 0xc0, 0xfe, 0x78, 0xcd, 0x5a, 0xf4,
            0x1f, 0xdd, 0xa8, 0x33, 0x88, 0x07, 0xc7, 0x31, 0xb1, 0x12, 0x10, 0x59, 0x27, 0x80, 0xec, 0x5f,
            0x60, 0x51, 0x7f, 0xa9, 0x19, 0xb5, 0x4a, 0x0d, 0x2d, 0xe5, 0x7a, 0x9f, 0x93, 0xc9, 0x9c, 0xef,
            0xa0, 0xe0, 0x3b, 0x4d, 0xae, 0x2a, 0xf5, 0xb0, 0xc8, 0xeb, 0xbb, 0x3c, 0x83, 0x53, 0x99, 0x61,
            0x17, 0x2b, 0x04, 0x7e, 0xba, 0x77, 0xd6, 0x26, 0xe1, 0x69, 0x14, 0x63, 0x55, 0x21, 0x0c, 0x7d};
    final static byte[] INVERSE_S_BOX = new byte[]{
            82, 9, 106, -43, 48, 54, -91, 56, -65, 64, -93, -98, -127, -13, -41, -5,
            124, -29, 57, -126, -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, -23, -53,
            84, 123, -108, 50, -90, -62, 35, 61, -18, 76, -107, 11, 66, -6, -61, 78,
            8, 46, -95, 102, 40, -39, 36, -78, 118, 91, -94, 73, 109, -117, -47, 37,
            114, -8, -10, 100, -122, 104, -104, 22, -44, -92, 92, -52, 93, 101, -74, -110,
            108, 112, 72, 80, -3, -19, -71, -38, 94, 21, 70, 87, -89, -115, -99, -124,
            -112, -40, -85, 0, -116, -68, -45, 10, -9, -28, 88, 5, -72, -77, 69, 6,
            -48, 44, 30, -113, -54, 63, 15, 2, -63, -81, -67, 3, 1, 19, -118, 107,
            58, -111, 17, 65, 79, 103, -36, -22, -105, -14, -49, -50, -16, -76, -26, 115,
            -106, -84, 116, 34, -25, -83, 53, -123, -30, -7, 55, -24, 28, 117, -33, 110,
            71, -15, 26, 113, 29, 41, -59, -119, 111, -73, 98, 14, -86, 24, -66, 27,
            -4, 86, 62, 75, -58, -46, 121, 32, -102, -37, -64, -2, 120, -51, 90, -12,
            31, -35, -88, 51, -120, 7, -57, 49, -79, 18, 16, 89, 39, -128, -20, 95,
            96, 81, 127, -87, 25, -75, 74, 13, 45, -27, 122, -97, -109, -55, -100, -17,
            -96, -32, 59, 77, -82, 42, -11, -80, -56, -21, -69, 60, -125, 83, -103, 97,
            23, 43, 4, 126, -70, 119, -42, 38, -31, 105, 20, 99, 85, 33, 12, 125};
    final static int [] INVERSE_MIX_COL = new int[]{14,11,13,9, 9,14,11,13, 13,9,14,11, 11,13,9,14};
    final static int MOD = 283;
    final static int MOD_LENGTH = 9;
    final static List<Integer> NINES_POWERS = Arrays.asList(0,3);
    final static List<Integer> ELEVENS_POWERS = Arrays.asList(0,1,3);
    final static List<Integer> THIRTEENS_POWERS = Arrays.asList(0,2,3);
    final static List<Integer> FOURTEENS_POWERS = Arrays.asList(1,2,3);
    public static void main(String[] args) {

    }

    protected static byte[] decryptData(byte [] state, List<byte[]> roundKeys){
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

    protected static byte []  inverseMixColumns(byte [] arr){
        byte [] computedArr = new byte[arr.length];
        for(int col = 0; col<4; col++){
            //inv galois field ???
            int galoisCell = 0;
            for(int row = 0; row<4; row++){
                boolean firstEntry = true;
                byte singleCellResult = -1;
                for(int i = col; i<16; i+=4, galoisCell++){
                    if(firstEntry){
                        singleCellResult = multiplyLarge(arr[i], INVERSE_MIX_COL[galoisCell]);
                        firstEntry = false;
                    }
                    else{
                        singleCellResult ^= multiplyLarge(arr[i], INVERSE_MIX_COL[galoisCell]);
                    }
                }
                computedArr[row*4 + col] = singleCellResult;
            }
        }
        return computedArr;
    }

    protected static void inverseSubBytes(byte [] state){
        for(int i = 0;i<state.length; i++){
            state[i] = INVERSE_S_BOX[Byte.toUnsignedInt(state[i])];
        }
    }
    protected static void inverseShiftRows(byte [] state){
        for(int shifts = 1; shifts<4;shifts++){
            shiftRight(state,shifts,shifts);
        }
    }
    private static void shiftRight(byte[] arr, int shifts, int row){
        row *= 4;
        byte temp;
        switch (shifts){
            case 1:
                //==shiftLeft by 3
                temp = arr[row + 3];
                arr[row + 3] = arr[row + 2];
                arr[row + 2] = arr[row + 1];
                arr[row + 1] = arr[row];
                arr[row] = temp;
                break;
            case 2:
                temp = arr[row];
                arr[row] = arr[row + 2];
                arr[row + 2] = temp;
                temp = arr[row + 1];
                arr[row + 1] = arr[row + 3];
                arr[row + 3] = temp;
                break;
            case 3:
                //==shiftLeft by 1
                temp = arr[row];
                arr[row] = arr[row + 1];
                arr[row + 1] = arr[row + 2];
                arr[row + 2] = arr[row + 3];
                arr[row + 3] = temp;
                break;
        }
    }
    protected static void inverseRotVertically(byte [] columnArr){
        if(columnArr.length!=4) return;
        byte temp = columnArr[3];
        columnArr[3] = columnArr[2];
        columnArr[2] = columnArr[1];
        columnArr[1] = columnArr[0];
        columnArr[0] = temp;
    }
    protected static List<byte[]> keyScheduleList(byte [] key){
        List<byte[]> roundKeys = new ArrayList<>(11);
        roundKeys.add(key);
        for(int i = 0; i<ROUNDS;i++){
            roundKeys.add(Encryption.keySchedule(roundKeys.get(i),i));
        }
        return roundKeys;
    }

    protected static byte multiplyLarge(byte myByte, final int times){

        List<Integer> powersList = getPowers(myByte);
        switch (times){
            //1001
            case 9:
                HashMap<Integer,Boolean> oddsAt9 = getOddsMap(powersList, NINES_POWERS);
                int result1 = translateToNumber(oddsAt9);
                return reduce(result1);
            //1011
            case 11:
                HashMap<Integer,Boolean> oddsAt11 = getOddsMap(powersList, ELEVENS_POWERS);
                int result2 = translateToNumber(oddsAt11);
                return reduce(result2);
            //1101
            case 13:
                HashMap<Integer,Boolean> oddsAt13 = getOddsMap(powersList, THIRTEENS_POWERS);
                int result3 = translateToNumber(oddsAt13);
                return reduce(result3);

            //1110
            case 14:
                HashMap<Integer,Boolean> oddsAt14 = getOddsMap(powersList, FOURTEENS_POWERS);
                int result4 = translateToNumber(oddsAt14);
                return reduce(result4);
        }
        return myByte;
    }

    protected static HashMap<Integer, Boolean> getOddsMap(List<Integer> list1, List<Integer> list2){
        HashMap<Integer,Boolean> odds = new HashMap<>();
        for(int currentPower1 : list1){
            for(int currentPower2 : list2){
                int sum = currentPower1+currentPower2;
                if(odds.containsKey(sum)){
                    odds.put(sum,!odds.get(sum));
                }else{
                    odds.put(sum,true);
                }
            }
        }
        return odds;
    }

    protected static int translateToNumber(HashMap<Integer, Boolean> odds){
        final int [] num = {0};
        odds.forEach((key,val)->{
            if(val){
                num[0] += Math.pow(2,key);
            }
        });
        return num[0];
    }

    protected static List<Integer> getPowers(byte myByte){
        List<Integer> powersList = new ArrayList<>();
        String str = Integer.toBinaryString(Byte.toUnsignedInt(myByte));
        for(int i = str.length()-1, power = 0; i>-1;i--,power++){
            if(str.charAt(i)=='1'){
                powersList.add(power);
            }
        }
        return powersList;
    }
    protected static int countBits(int number){
        return (int)(Math.log(number) / Math.log(2) + 1);
    }
    protected static byte reduce(int num){

        int toCompare = MOD<<Decryption.countBits(num)-MOD_LENGTH;
        while(num>255){
            num = toCompare^num;
            toCompare = toCompare>>Math.abs(Decryption.countBits(num)-Decryption.countBits(toCompare));
        }
        return bt(num);
    }


}
