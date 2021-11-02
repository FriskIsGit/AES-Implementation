class Convert{

    protected static String intToHexString(int x){
        return Integer.toHexString(x);
    }
    protected static int hexStringToInt(String hex){
        try{
            return Integer.parseInt(hex, 16);
        }catch(NumberFormatException exc){
            exc.printStackTrace();
            return -1;
        }
    }
    protected static byte [] arrToByteArr(int [] arr){
        byte [] byteArr = new byte[arr.length];
        for(int i = 0; i<arr.length; i++){
            byteArr[i] = (byte)arr[i];
        }
        return byteArr;
    }
    protected static String [] arrToHexStringArr(int [] arr){
        String [] hexArr = new String[arr.length];
        for(int i = 0; i<arr.length; i++){
            hexArr[i] = intToHexString(arr[i]);
        }
        return hexArr;
    }
    protected static String [] arrToHexStringArr(byte [] arr){
        String [] hexArr = new String[arr.length];
        for(int i = 0; i<arr.length; i++){
            hexArr[i] = intToHexString(Byte.toUnsignedInt(arr[i]));
        }
        return hexArr;
    }

    protected static int byteToUnsigned(byte givenByte){
        return givenByte<0 ? 256+givenByte : givenByte;
    }
    protected static int[] unsignedByteToIndices(int uByte){
        //for example: 216/16 = 13.5, therefore {13,x}
        int[] result = new int[]{uByte/16, 0};
        //make a number out of 4 last bits
        for(int exp = 0; exp<4; exp++){
            double remainder = uByte/2D;
            uByte = uByte/2;
            if(remainder%1 != 0){
                result[1] += Math.pow(2,exp);
            }
        }
        return result;
    }
    // A - 10; B - 11; C - 12; D - 13; E - 14;  F - 15;
}
