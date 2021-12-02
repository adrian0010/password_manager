package pass_manager.password_manager;



import javax.crypto.*;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class InputDevice {

    public InputDevice() {
    }
    public Account encryptReadFirst() throws IOException{
        try {
            FileInputStream fileInputStream = new FileInputStream("allowed.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            AES.setKey("123");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, AES.getSecretKey());
            SealedObject sealedObject = (SealedObject) objectInputStream.readObject();
            if (sealedObject != null){
                Account account = (Account) sealedObject.getObject(cipher);
                return account;
            }
            fileInputStream.close();
            return null;
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | ClassNotFoundException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Account> encryptRead() throws IOException{
        try {
            FileInputStream fileInputStream = new FileInputStream("db.txt");
            AES.setKey("123");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, AES.getSecretKey());
            ArrayList<Account> accounts = new ArrayList<>();
            while (fileInputStream.available()!=0){
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                SealedObject sealedObject = (SealedObject) objectInputStream.readObject();
                if (sealedObject != null){
                    Account account1 = (Account) sealedObject.getObject(cipher);
                    accounts.add(account1);
                }
            }
            fileInputStream.close();
            return accounts;
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | ClassNotFoundException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<Password> encryptReadPasswords() throws IOException{
        try {
            FileInputStream fileInputStream = new FileInputStream("passwords.txt");
            AES.setKey("123");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, AES.getSecretKey());
            ArrayList<Password> passwords = new ArrayList<>();
            while (fileInputStream.available()!=0){
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                SealedObject sealedObject = (SealedObject) objectInputStream.readObject();
                if (sealedObject != null){
                    Password password = (Password) sealedObject.getObject(cipher);
                    passwords.add(password);
                }
            }
            fileInputStream.close();
            return passwords;
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | ClassNotFoundException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }


}
