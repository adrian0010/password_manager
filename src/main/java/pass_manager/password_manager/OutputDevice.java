package pass_manager.password_manager;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class OutputDevice {

    public void write( Account account) throws IOException {
        FileOutputStream f_out = new FileOutputStream("db.txt", true);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(f_out);
        objectOutputStream.writeObject(account);
        objectOutputStream.close();
        f_out.close();
    }

    public void encryptWrite(Account account) throws IOException{
        try {
            FileOutputStream f_out = new FileOutputStream("allowed.txt",true);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(f_out);
            AES.setKey("123");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, AES.getSecretKey());
            SealedObject sealedObject = new SealedObject(account,cipher);
            objectOutputStream.writeObject(sealedObject);
            objectOutputStream.close();
            f_out.close();
        } catch (IllegalBlockSizeException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    public void encryptWritePassword(Password password) throws IOException{
        try {
            FileOutputStream f_out = new FileOutputStream("passwords.txt",true);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(f_out);
            AES.setKey("123");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, AES.getSecretKey());
            SealedObject sealedObject = new SealedObject(password,cipher);
            objectOutputStream.writeObject(sealedObject);
            objectOutputStream.close();
            f_out.close();
        } catch (IllegalBlockSizeException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }
    public void emptyFile(FileOutputStream file) throws IOException {
        OutputStream os = null;
        try {
            os = file;
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }
    public void deletePassword() throws IOException{
        FileOutputStream f_out = new FileOutputStream("passwords.txt");
        emptyFile(f_out);
    }
}