import java.util.Scanner;

public class AffineCipher 
{
	/**Class variables**/
	private static final int MOD_NUM = 128;
	
	/**Instance variables**/
	private String cleartext;
	private int a;
	private int b;
	private String ciphertext;
	
	//Constructor
	public AffineCipher(String pCleartext, int pA, int pB, String pCiphertext) 
	{
		this.cleartext = pCleartext;
		this.a = pA;
		this.b = pB;
		this.ciphertext = pCiphertext;
	}
	
	//Encryption
	public void encryption(String pCleartext)
	{	
		int temp;
		
		for(int i = 0; i < pCleartext.length(); ++i)
		{
			temp = (int) pCleartext.charAt(i);
			ciphertext += (char) (((temp * a) + b) % MOD_NUM);
		}
	}
	
	//Decryption
	public void decryption(String pCiphertext)
	{
		if(gcd(a, MOD_NUM) != 1)
		{
			System.out.println("No multiplicative inverse exists " + a + " mod " + MOD_NUM);
			System.exit(0);
		}
		else
		{
			int temp;
			int inverse = findMultiplicativeInverse(a, MOD_NUM);
			
			for(int i = 0; i < pCiphertext.length(); ++i)
			{
				temp = (int) pCiphertext.charAt(i);
				temp = temp - b;
				
				while(temp < 0)
				{
					temp = temp + MOD_NUM;
				}
				
				cleartext += (char) ((temp * inverse) % MOD_NUM);
			}
		}		
	}
	
	//Euclidean Algorithm
	public int gcd(int pA, int pB)
	{
		int temp1 = pA;
		int temp2 = pB;
		
		if(temp1 < 0)
		{
			temp1 = temp1 * -1;
		}
		else if(temp2 < 0)
		{
			temp2 = temp2 * -1;
		}
		
		while(true)
		{
			if(temp2 == 0)
			{
				return temp1;
			}
			temp1 = (temp1 % temp2);
			
			if(temp1 == 0)
			{
				return temp2;
			}
			temp2 = (temp2 % temp1);
		}
	}
	
	//Iterative method to find multiplicative inverse
	public int findMultiplicativeInverse(int pA, int pM)
	{
		int a = pA;
		int m = pM;
		
		while(a < 0)
		{
			a = a + m;
		}
		
		a = a % m;
		
		for(int x = 1; x < m; ++x)
		{
			if((a * x) % m == 1)
			{
				return x;
			}
		}
		return 0;	
	}

	//Main
	public static void main(String[] args) 
	{
		String encryptionDecryptionChoice = "";
		String encryptionDecryptionText = "";
		int encryptionDecryptionA;
		int encryptionDecryptionB;
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Encryption or Decryption? Enter E or D.");
		encryptionDecryptionChoice = scanner.next();
		
		if(encryptionDecryptionChoice.charAt(0) == 'E')
		{
			System.out.println("Enter text to encrypt:");
			encryptionDecryptionText = scanner.next();
			
			System.out.println("Enter a:");
			encryptionDecryptionA = scanner.nextInt();
			
			System.out.println("Enter b:");
			encryptionDecryptionB = scanner.nextInt();
			
			AffineCipher ac = new AffineCipher(encryptionDecryptionText, encryptionDecryptionA, encryptionDecryptionB, "");
			
			ac.encryption(ac.getCleartext());
			
			System.out.println("Ciphertext: " + ac.getCiphertext());
		}
		else if(encryptionDecryptionChoice.charAt(0) == 'D')
		{
			System.out.println("Enter text to decrypt:");
			encryptionDecryptionText = scanner.next();
			
			System.out.println("Enter a:");
			encryptionDecryptionA = scanner.nextInt();
			
			System.out.println("Enter b:");
			encryptionDecryptionB = scanner.nextInt();
			
			AffineCipher ac = new AffineCipher("", encryptionDecryptionA, encryptionDecryptionB, encryptionDecryptionText);
			
			ac.decryption(ac.getCiphertext());
			
			System.out.println("Cleartext: " + ac.getCleartext());
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
	
	//Gets multiplicative key
	public int getA()
	{
		return a;
	}
	
	//Gets additive key
	public int getB()
	{
		return b;
	}
	
	//Gets ciphertext
	public String getCiphertext()
	{
		return ciphertext;
	}
}