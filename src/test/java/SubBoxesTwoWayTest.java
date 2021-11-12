import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class SubBoxesTwoWayTest{
    @Test
    public void sub_to_inv(){
        int count = 0;
        for(int i = 0; i<Encryption.INT_S_BOX.length; i++){
            if(Decryption.INT_INVERSE_S_BOX[Encryption.INT_S_BOX[i]] == i){
                count++;
            }
        }
        assertEquals(Encryption.BYTE_S_BOX.length,count);
    }
    @Test
    public void inv_to_sub(){
        int count = 0;
        for(int i = 0; i<Decryption.INT_INVERSE_S_BOX.length; i++){
            if(Encryption.INT_S_BOX[Decryption.INT_INVERSE_S_BOX[i]] == i){
                count++;
            }
        }
        assertEquals(Decryption.INT_INVERSE_S_BOX.length,count);
    }
}
