class Converter{

    protected String intToHexString(int x){
        return Integer.toHexString(x);
    }
    protected int hexStringToInt(String hex){
        try{
            return Integer.parseInt(hex, 16);
        }catch(NumberFormatException exc){
            exc.printStackTrace();
        }
        return -1;
    }
    protected String [] intArrHexStringArr(int [] arr){
        String [] hexArr = new String[arr.length];
        for(int i = 0; i<arr.length; i++){
            hexArr[i] = intToHexString(arr[i]);
        }
        return hexArr;
    }
    protected void display4by4(int [] arr){
        Converter con = new Converter();
        int len = arr.length;
        int beg = 0;
        for(int times = 0; times<len/4; times++){
            for (int index = beg; index < len; index += 4){
                System.out.print(con.intToHexString(arr[index]) + " ");
            }
            System.out.println();
            beg++;
        }
    }
    protected void display4by4(String [] arr){
        int len = arr.length;
        int beg = 0;
        for(int times = 0; times<len; times++){
            for (int index = beg; index < len * len; index += 4){
                System.out.print(index);
            }
        }
    }
}
