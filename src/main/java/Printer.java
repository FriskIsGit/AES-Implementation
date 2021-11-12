class Printer{
    protected static void formatPrintArr(byte [] arr, int rows, int cols){
        int index = 0;
        for(int i = 0; i<rows; i++){
            for (int j = 0; j < cols; j++, index++){
                System.out.print(arr[index] + ", ");
            }
            System.out.println();
        }
    }
    protected static void formatPrintArr(String [] arr, int rows, int cols){
        int index = 0;
        for(int i = 0; i<rows; i++){
            for (int j = 0; j < cols; j++, index++){
                System.out.print(arr[index] + ", ");
            }
            System.out.println();
        }
    }

    protected static void formatPrintArr(int [] arr, int rows, int cols){
        int index = 0;
        for(int i = 0; i<rows; i++){
            for (int j = 0; j < cols; j++, index++){
                System.out.print(arr[index] + ", ");
            }
            System.out.println();
        }
    }
    protected static void printGalois(){
        formatPrintArr(Encryption.GALOIS,4,4);
    }
    protected static void printInvGalois(){
        formatPrintArr(Decryption.INVERSE_MIX_COL,4,4);
    }
}
