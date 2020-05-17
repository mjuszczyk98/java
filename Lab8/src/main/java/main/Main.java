package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class Main {

	public static void main(String[] args) {
		System.out.print("Menu:\n0 - Wyjscie\n1 - Zaszyfruj\n2 - Odszyfruj\n");
		int selected = -1;
		Scanner in = new Scanner(System.in);
		while(true) {
			String file;
			String key;
			System.out.println("Proszę tryb");
			String ret = in.nextLine();
			selected = Integer.parseInt(ret);
			if(selected == 0) {
				break;
			} else {
				System.out.println("Proszę podać nazwę pliku");
				file = in.nextLine();
				System.out.println("Proszę podać klucz");
				key = in.nextLine();
				if(selected == 1) {
					Crypto.encrypt(key, file);
				} else if(selected == 2) {
					Crypto.decrypt(key, file);
				}
			}
		}
	}

}

class Crypto {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";
    private static final String DEFAULT_KEY = "Example16bytekey";
    private static final int SIZE = 16;
 
    public static void encrypt(String key, String file) {
		File f = new File(file);
        doCrypto(Cipher.ENCRYPT_MODE, key, f);
    }
 
    public static void decrypt(String key, String file) {
		File f = new File(file);
        doCrypto(Cipher.DECRYPT_MODE, key, f);
    }
 
    private static void doCrypto(int cipherMode, String key, File file) {
        try {
			if(key.length() != SIZE) {
				key = DEFAULT_KEY;
			}
            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(cipherMode, secretKey);
             
            FileInputStream inputStream = new FileInputStream(file);
            byte[] input = new byte[(int) file.length()];
            inputStream.read(input);
            inputStream.close();
             
            byte[] output = cipher.doFinal(input);
             
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(output);
            outputStream.close();
             
        } catch (Exception ex) {
        }
    }
}
