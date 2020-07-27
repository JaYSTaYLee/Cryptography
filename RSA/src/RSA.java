import java.util.Scanner;

public class RSA 
{
	/**Instance variables**/
	private KeyGenerator KG;
	private PublicKey PuK;
	private PrivateKey PrK;

	//Constructor
	public RSA() 
	{
		this.KG = new KeyGenerator();
		this.PuK = new PublicKey(KG);
		this.PrK = new PrivateKey(KG);
	}
	
	/**Accessor functions**/
	
	//Gets public key
	public PublicKey getPublicKey()
	{
		return PuK;	
	}
	
	//Gets private key
	public PrivateKey getPrivateKey()
	{
		return PrK;	
	}

	//Converts a string to bytes
	private static String bytesToString(byte[] bytes)
    {
        String str = "";
        
        for (byte b : bytes)
        {
            str += Byte.toString(b);
        }
        return str;
    }
	
	//Main
	public static void main(String[] args)
	{	
		RSA rsa = new RSA();
		
		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter("\\n");
		
		String message = "";
		
		System.out.println("Enter text to encrypt:");
		message = scanner.nextLine();
		
		Sender s = new Sender(rsa.getPublicKey(), message);
		System.out.println();
		System.out.println("Public Key (" + rsa.getPublicKey().getE() + ", " + rsa.getPublicKey().getM() + ")");
		System.out.println("Private Key (" + rsa.getPrivateKey().getD() + ", " + rsa.getPrivateKey().getM() + ")\n");
		
		System.out.println("Text to be encrypted: " + s.getMessage());
		System.out.println("Text in bytes: " + bytesToString(s.getMessage().getBytes()));
		System.out.println("Ciphertext : " + bytesToString(s.encryption(s.getMessage().getBytes())));
		
		byte[] encryptedText = s.encryption(s.getMessage().getBytes());
		
		Receiver r = new Receiver(rsa.getPrivateKey(), encryptedText);
		
		System.out.println("Cleartext : " + new String(r.decryption(encryptedText)));
		
		scanner.close();
	}
}