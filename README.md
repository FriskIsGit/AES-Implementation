# AES implementation
### Advanced Encryption Algorithm (Rijndael)

- [x] two-way process of encryption and decryption with the use of a key
- [x] a sequence of characters serves as the key
- [x] encrypting data to safely store it
#### AES standards: 
- key lengths: 128, 192, 256 bits
- constant block size: 128 bits

#### Usage

```java
class Main{
    public static void main(String[] args){
        byte[] data = "what".getBytes();
        byte[] password = "defense".getBytes();

        byte[] encryptedBytes = Encryption.encryptData(data, password);

        byte[] decryptedBytes = Decryption.decryptData(encryptedBytes, password);
        assert new String(decryptedBytes).startsWith("what");
    }
}
```

#### sources:
- https://formaestudio.com/rijndaelinspector/archivos/Rijndael_Animation_v4_eng-html5.html
- https://crypto.stackexchange.com/questions/2402/how-to-solve-mixcolumns

![image](mixColumns.png)
![image](standards.png)

