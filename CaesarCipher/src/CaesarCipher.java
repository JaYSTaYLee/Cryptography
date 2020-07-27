import java.util.Scanner;

public class CaesarCipher 
{	
	/**Class variables**/
	private static final int MOD_NUM = 128;
		
	/**Instance variables**/
	private String cleartext;
	private int key;
	private String ciphertext;
	
	//Constructor
	public CaesarCipher(String pCleartext, int pKey, String pCiphertext) 
	{
		this.cleartext = pCleartext;
		this.key = pKey;
		this.ciphertext = pCiphertext;
	}
	
	//Encryption
	public void encryption(String pCleartext)
	{	
		int temp;
		
		for(int i = 0; i < pCleartext.length(); ++i)
		{
			temp = (int) pCleartext.charAt(i);
			ciphertext += (char) ((temp + key) % MOD_NUM);
		}	
	}
	
	//Decryption
	public void decryption(String pCiphertext)
	{	
		int temp;
		
		for(int i = 0; i < pCiphertext.length(); ++i)
		{
			temp = (int) pCiphertext.charAt(i);
			
			temp = temp - key;
			
			while(temp < 0)
			{
				temp = temp + MOD_NUM;
			}
			
			cleartext += (char) (temp % MOD_NUM);
		}
	}
	
	//Main
	public static void main(String[] args) 
	{
		String encryptionDecryptionChoice = "";
		String encryptionDecryptionText = "";
		int encryptionDecryptionKey;
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Encryption or Decryption? Enter E or D.");
		encryptionDecryptionChoice = scanner.next();
		
		if(encryptionDecryptionChoice.charAt(0) == 'E')
		{
			System.out.println("Enter text to encrypt:");
			encryptionDecryptionText = scanner.next();
			
			System.out.println("Enter a key:");
			encryptionDecryptionKey = scanner.nextInt();
			
			CaesarCipher cc = new CaesarCipher(encryptionDecryptionText, encryptionDecryptionKey, "");
			
			cc.encryption(cc.getCleartext());
			
			System.out.println(cc.getCiphertext());
		}
		else if(encryptionDecryptionChoice.charAt(0) == 'D')
		{
			System.out.println("Enter text to decrypt:");
			encryptionDecryptionText = scanner.next();
			
			System.out.println("Enter a key:");
			encryptionDecryptionKey = scanner.nextInt();
			
			CaesarCipher cc = new CaesarCipher("", encryptionDecryptionKey, encryptionDecryptionText);
			
			cc.decryption(cc.getCiphertext());
			
			System.out.println(cc.getCleartext());
		}
		else
		{
			System.out.println("Please enter E or D.");
		}
		scanner.close();
	}
	
	/**Accessor functions**/
	
	//Gets cleartext
	public String getCleartext()
	{
		return cleartext;
	}
	
	//Gets key
	public int getKey()
	{
		return key;
	}
	
	//Gets ciphertext
	public String getCiphertext()
	{
		return ciphertext;
	}
}