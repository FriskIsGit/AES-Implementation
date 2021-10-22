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
    protected static String [] intArrHexStringArr(int [] arr){
        String [] hexArr = new String[arr.length];
        for(int i = 0; i<arr.length; i++){
            hexArr[i] = intToHexString(arr[i]);
        }
        return hexArr;
    }
    /*protected static void display4by4(int [] arr,int high){
        int len = arr.length;
        int beg = 0;
        for(int times = 0; times<len/high; times++){
            for (int index = beg; index < len; index += high){
                System.out.print(intToHexString(arr[index]) + " ");
            }
            System.out.println();
            beg++;
        }
    }
    protected static void display4by4(String [] arr){
        int len = arr.length;
        int beg = 0;
        for(int times = 0; times<len; times++){
            for (int index = beg; index < len * len; index += 4){
                System.out.print(index);
            }
        }
    }*/
    protected static int byteToUnsignedByte(byte givenByte){
        return givenByte<0 ? 256+givenByte : givenByte;
    }
    protected static int[] unsignedByteToIndices(int uByte){
        int [] result = new int[2];
        boolean [] bits = new boolean[4];
        result[0] = uByte/16;

        double remainder;
        for(int i = 0; i<4; i++){
            remainder = uByte/2D;
            uByte = uByte/2;
            if(remainder%1!=0) bits[i] = true;
        }
        //make a number from 4 bits
        for(int exponent = 0; exponent<4; exponent++){
            if(bits[exponent]){
                result[1] += Math.pow(2,exponent);
            }
        }
        return result;
    }
    // A - 10; B - 11; C - 12; D - 13; E - 14;  F - 15;
}
