import java.util.*;

class Decryption extends Encryption{
    protected final static int[] INT_INVERSE_S_BOX = new int[]{
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
    protected final static byte[] INVERSE_S_BOX = new byte[]{
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
    protected static final int[] INVERSE_MIX_COL = new int[]{14,11,13,9, 9,14,11,13, 13,9,14,11, 11,13,9,14};

    private static final int MOD = 283;
    private static final int MOD_LENGTH = 9;
    private static final List<Integer> NINES_POWERS = Arrays.asList(0,3);
    private static final List<Integer> ELEVENS_POWERS = Arrays.asList(0,1,3);
    private static final List<Integer> THIRTEENS_POWERS = Arrays.asList(0,2,3);
    private static final List<Integer> FOURTEENS_POWERS = Arrays.asList(1,2,3);

    private static final int[] gfp9 = {0, 9, 18, 27, 36, 45, 54, 63, 72, 65, 90, 83, 108, 101, 126, 119, 144, 153, 130, 139, 180, 189, 166, 175, 216, 209, 202, 195, 252, 245, 238, 231, 59, 50, 41, 32, 31, 22, 13, 4, 115, 122, 97, 104, 87, 94, 69, 76, 171, 162, 185, 176, 143, 134, 157, 148, 227, 234, 241, 248, 199, 206, 213, 220, 118, 127, 100, 109, 82, 91, 64, 73, 62, 55, 44, 37, 26, 19, 8, 1, 230, 239, 244, 253, 194, 203, 208, 217, 174, 167, 188, 181, 138, 131, 152, 145, 77, 68, 95, 86, 105, 96, 123, 114, 5, 12, 23, 30, 33, 40, 51, 58, 221, 212, 207, 198, 249, 240, 235, 226, 149, 156, 135, 142, 177, 184, 163, 170, 236, 229, 254, 247, 200, 193, 218, 211, 164, 173, 182, 191, 128, 137, 146, 155, 124, 117, 110, 103, 88, 81, 74, 67, 52, 61, 38, 47, 16, 25, 2, 11, 215, 222, 197, 204, 243, 250, 225, 232, 159, 150, 141, 132, 187, 178, 169, 160, 71, 78, 85, 92, 99, 106, 113, 120, 15, 6, 29, 20, 43, 34, 57, 48, 154, 147, 136, 129, 190, 183, 172, 165, 210, 219, 192, 201, 246, 255, 228, 237, 10, 3, 24, 17, 46, 39, 60, 53, 66, 75, 80, 89, 102, 111, 116, 125, 161, 168, 179, 186, 133, 140, 151, 158, 233, 224, 251, 242, 205, 196, 223, 214, 49, 56, 35, 42, 21, 28, 7, 14, 121, 112, 107, 98, 93, 84, 79, 70};
    private static final int[] gfp11 = {0, 11, 22, 29, 44, 39, 58, 49, 88, 83, 78, 69, 116, 127, 98, 105, 176, 187, 166, 173, 156, 151, 138, 129, 232, 227, 254, 245, 196, 207, 210, 217, 123, 112, 109, 102, 87, 92, 65, 74, 35, 40, 53, 62, 15, 4, 25, 18, 203, 192, 221, 214, 231, 236, 241, 250, 147, 152, 133, 142, 191, 180, 169, 162, 246, 253, 224, 235, 218, 209, 204, 199, 174, 165, 184, 179, 130, 137, 148, 159, 70, 77, 80, 91, 106, 97, 124, 119, 30, 21, 8, 3, 50, 57, 36, 47, 141, 134, 155, 144, 161, 170, 183, 188, 213, 222, 195, 200, 249, 242, 239, 228, 61, 54, 43, 32, 17, 26, 7, 12, 101, 110, 115, 120, 73, 66, 95, 84, 247, 252, 225, 234, 219, 208, 205, 198, 175, 164, 185, 178, 131, 136, 149, 158, 71, 76, 81, 90, 107, 96, 125, 118, 31, 20, 9, 2, 51, 56, 37, 46, 140, 135, 154, 145, 160, 171, 182, 189, 212, 223, 194, 201, 248, 243, 238, 229, 60, 55, 42, 33, 16, 27, 6, 13, 100, 111, 114, 121, 72, 67, 94, 85, 1, 10, 23, 28, 45, 38, 59, 48, 89, 82, 79, 68, 117, 126, 99, 104, 177, 186, 167, 172, 157, 150, 139, 128, 233, 226, 255, 244, 197, 206, 211, 216, 122, 113, 108, 103, 86, 93, 64, 75, 34, 41, 52, 63, 14, 5, 24, 19, 202, 193, 220, 215, 230, 237, 240, 251, 146, 153, 132, 143, 190, 181, 168, 163};
    private static final int[] gfp13 = {0, 13, 26, 23, 52, 57, 46, 35, 104, 101, 114, 127, 92, 81, 70, 75, 208, 221, 202, 199, 228, 233, 254, 243, 184, 181, 162, 175, 140, 129, 150, 155, 187, 182, 161, 172, 143, 130, 149, 152, 211, 222, 201, 196, 231, 234, 253, 240, 107, 102, 113, 124, 95, 82, 69, 72, 3, 14, 25, 20, 55, 58, 45, 32, 109, 96, 119, 122, 89, 84, 67, 78, 5, 8, 31, 18, 49, 60, 43, 38, 189, 176, 167, 170, 137, 132, 147, 158, 213, 216, 207, 194, 225, 236, 251, 246, 214, 219, 204, 193, 226, 239, 248, 245, 190, 179, 164, 169, 138, 135, 144, 157, 6, 11, 28, 17, 50, 63, 40, 37, 110, 99, 116, 121, 90, 87, 64, 77, 218, 215, 192, 205, 238, 227, 244, 249, 178, 191, 168, 165, 134, 139, 156, 145, 10, 7, 16, 29, 62, 51, 36, 41, 98, 111, 120, 117, 86, 91, 76, 65, 97, 108, 123, 118, 85, 88, 79, 66, 9, 4, 19, 30, 61, 48, 39, 42, 177, 188, 171, 166, 133, 136, 159, 146, 217, 212, 195, 206, 237, 224, 247, 250, 183, 186, 173, 160, 131, 142, 153, 148, 223, 210, 197, 200, 235, 230, 241, 252, 103, 106, 125, 112, 83, 94, 73, 68, 15, 2, 21, 24, 59, 54, 33, 44, 12, 1, 22, 27, 56, 53, 34, 47, 100, 105, 126, 115, 80, 93, 74, 71, 220, 209, 198, 203, 232, 229, 242, 255, 180, 185, 174, 163, 128, 141, 154, 151};
    private static final int[] gfp14 = {0, 14, 28, 18, 56, 54, 36, 42, 112, 126, 108, 98, 72, 70, 84, 90, 224, 238, 252, 242, 216, 214, 196, 202, 144, 158, 140, 130, 168, 166, 180, 186, 219, 213, 199, 201, 227, 237, 255, 241, 171, 165, 183, 185, 147, 157, 143, 129, 59, 53, 39, 41, 3, 13, 31, 17, 75, 69, 87, 89, 115, 125, 111, 97, 173, 163, 177, 191, 149, 155, 137, 135, 221, 211, 193, 207, 229, 235, 249, 247, 77, 67, 81, 95, 117, 123, 105, 103, 61, 51, 33, 47, 5, 11, 25, 23, 118, 120, 106, 100, 78, 64, 82, 92, 6, 8, 26, 20, 62, 48, 34, 44, 150, 152, 138, 132, 174, 160, 178, 188, 230, 232, 250, 244, 222, 208, 194, 204, 65, 79, 93, 83, 121, 119, 101, 107, 49, 63, 45, 35, 9, 7, 21, 27, 161, 175, 189, 179, 153, 151, 133, 139, 209, 223, 205, 195, 233, 231, 245, 251, 154, 148, 134, 136, 162, 172, 190, 176, 234, 228, 246, 248, 210, 220, 206, 192, 122, 116, 102, 104, 66, 76, 94, 80, 10, 4, 22, 24, 50, 60, 46, 32, 236, 226, 240, 254, 212, 218, 200, 198, 156, 146, 128, 142, 164, 170, 184, 182, 12, 2, 16, 30, 52, 58, 40, 38, 124, 114, 96, 110, 68, 74, 88, 86, 55, 57, 43, 37, 15, 1, 19, 29, 71, 73, 91, 85, 127, 113, 99, 109, 215, 217, 203, 197, 239, 225, 243, 253, 167, 169, 187, 181, 159, 145, 131, 141};

    public static byte[] decryptData(byte[] state, byte[] password){
        return decryptData(state, keyScheduleList(password));
    }

    public static byte[] decryptData(byte[] state, List<byte[]> roundKeys){
        state = ensureSize(state, 16);
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

    protected static byte[] inverseMixColumns(byte[] arr){
        byte[] computedArr = new byte[arr.length];
        for(int col = 0; col<4; col++){
            //inv galois field ???
            int galoisCell = 0;
            for(int row = 0; row<4; row++){
                boolean firstEntry = true;
                byte singleCellResult = -1;
                for(int i = col; i<16; i+=4, galoisCell++){
                    if(firstEntry){
                        singleCellResult = inverseMultiplyByLookup(arr[i], INVERSE_MIX_COL[galoisCell]);
                        firstEntry = false;
                    }
                    else{
                        singleCellResult ^= inverseMultiplyByLookup(arr[i], INVERSE_MIX_COL[galoisCell]);
                    }
                }
                computedArr[row*4 + col] = singleCellResult;
            }
        }
        return computedArr;
    }

    private static byte inverseMultiplyByLookup(byte b, int times){
        switch (times){
            case 9:
                return (byte) gfp9[Byte.toUnsignedInt(b)];
            case 11:
                return (byte) gfp11[Byte.toUnsignedInt(b)];
            case 13:
                return (byte) gfp13[Byte.toUnsignedInt(b)];
            case 14:
                return (byte) gfp14[Byte.toUnsignedInt(b)];
        }
        return -1;
    }

    protected static void inverseSubBytes(byte[] state){
        for(int i = 0;i<state.length; i++){
            state[i] = INVERSE_S_BOX[Byte.toUnsignedInt(state[i])];
        }
    }
    protected static void inverseShiftRows(byte[] state){
        for(int shifts = 1; shifts<4; shifts++){
            shiftRight(state, shifts, shifts);
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
    private static void inverseRotVertically(byte[] columnArr){
        if(columnArr.length!=4)
            return;
        byte temp = columnArr[3];
        columnArr[3] = columnArr[2];
        columnArr[2] = columnArr[1];
        columnArr[1] = columnArr[0];
        columnArr[0] = temp;
    }
    public static List<byte[]> keyScheduleList(byte[] key){
        key = ensureSize(key, 16);
        List<byte[]> roundKeys = new ArrayList<>(11);
        roundKeys.add(key);
        for(int i = 0; i<ROUNDS; i++){
            roundKeys.add(Encryption.keySchedule(roundKeys.get(i), i));
        }
        return roundKeys;
    }

    protected static byte multiplyLarge(byte myByte, final int times){
        List<Integer> powersList = getPowers(myByte);
        switch (times){
            //1001
            case 9:
                HashMap<Integer, Boolean> oddsAt9 = getOddsMap(powersList, NINES_POWERS);
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
                int sum = currentPower1 + currentPower2;
                if(odds.containsKey(sum)){
                    odds.put(sum, !odds.get(sum));
                }else{
                    odds.put(sum,true);
                }
            }
        }
        return odds;
    }

    protected static int translateToNumber(HashMap<Integer, Boolean> odds){
        final int[] num = {0};
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
        for(int i = str.length()-1, power = 0; i>-1; i--, power++){
            if(str.charAt(i)=='1'){
                powersList.add(power);
            }
        }
        return powersList;
    }
    private static int countBits(int number){
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
