import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class READMETest{
    @Test
    public void encrypt_and_decrypt(){
        byte[] data = "what".getBytes();
        byte[] password = "defense".getBytes();
        byte[] encryptedBytes = Encryption.encryptData(data, password);

        byte[] decryptedBytes = Decryption.decryptData(encryptedBytes, password);

        assertTrue(new String(decryptedBytes).startsWith("what"));
        assert new String(decryptedBytes).startsWith("what");
    }
}
