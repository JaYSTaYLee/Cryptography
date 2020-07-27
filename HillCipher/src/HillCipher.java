import java.util.Scanner;

public class HillCipher 
{	
	/**Class variables**/
	private static final int MOD_NUM = 128;
	
	/**Instance variables**/
	private String cleartext;
	private int[][] keyMatrix;
	private int determinant;
	private int multiplicativeInverse;
	private int[][] adjointMatrix;
	private int[][] inverseMatrix;
	private String ciphertext;
	
	//Constructor
	public HillCipher(String pCleartext, int[][] pKeyMatrix, String pCiphertext) 
	{
		this.cleartext = pCleartext;
		this.keyMatrix = pKeyMatrix;
		this.determinant = 0;
		this.multiplicativeInverse = 0;
		this.adjointMatrix = new int[2][2];
		this.inverseMatrix = new int[2][2];
		this.ciphertext = pCiphertext;
	}
	
	//Finds determinant of a 2x2 matrix
	public void findDeterminant()
	{
		determinant = (keyMatrix[0][0] * keyMatrix[1][1]) - (keyMatrix[0][1] * keyMatrix[1][0]);
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
		
	//Iterative method to find multiplicative inverse modulo a number
	public void findMultiplicativeInverse()
	{
		int a = determinant;
		int m = MOD_NUM;
		
		while(a < 0)
		{
			a = a + m;
		}
		
		a = a % m;
		
		for(int x = 1; x < m; ++x)
		{
			if((a * x) % m == 1)
			{
				multiplicativeInverse = x;
				return;
			}
		}
		multiplicativeInverse = 0;
		return;	
	}
	
	//Finds adjoint matrix of a 2x2 Matrix
	public void findAdjointMatrix()
	{
		adjointMatrix[0][0] = keyMatrix[1][1];
		adjointMatrix[0][1] = keyMatrix[0][1] * (-1);
		adjointMatrix[1][0] = keyMatrix[1][0] * (-1);
		adjointMatrix[1][1] = keyMatrix[0][0];
	}
	
	//Finds inverse matrix of a 2x2 Matrix
	public void findInverseMatrix()
	{	
		for(int i = 0; i < adjointMatrix.length; ++i)
		{
			for(int j = 0; j < adjointMatrix[i].length; ++j)
			{
				while(adjointMatrix[i][j] < 0)
				{
					adjointMatrix[i][j] = adjointMatrix[i][j] + MOD_NUM;
				}
				
				inverseMatrix[i][j] = (adjointMatrix[i][j] * getMultiplicativeInverse()) % MOD_NUM;
			}
		}
	}
	
	//Converts a string/char to an int
	public static int[] convertStringToIntArray(String pText)
	{
		int[] intArray = new int[pText.length()];
		
		int temp;
		
		for(int i = 0; i < pText.length(); ++i)
		{
			temp = (int) pText.charAt(i);
			intArray[i] = temp  % MOD_NUM;
		}
		return intArray;
	}
	
	//Encryption
	public void encryption(String pMessage)
	{
		int[] messageArray = convertStringToIntArray(pMessage);
		int[] cipherArray = new int[2];
		
		for(int i = 0; i < keyMatrix[0].length; ++i)
		{			
			for(int j = 0; j < keyMatrix[1].length; ++j)
			{
				cipherArray[i] += keyMatrix[i][j] * messageArray[j];
			}
		}
		for(int i = 0; i < cipherArray.length; ++i)
		{
			ciphertext += (char) (cipherArray[i] % MOD_NUM);
		}
	}
	
	//Decryption
	public void decryption(String pMessage)
	{
		int[] messageArray = convertStringToIntArray(pMessage);
		int[] clearArray = new int[2];
		
		for(int i = 0; i < inverseMatrix[0].length; ++i)
		{			
			for(int j = 0; j < inverseMatrix[1].length; ++j)
			{
				clearArray[i] += inverseMatrix[i][j] * messageArray[j];
			}
		}
		for(int i = 0; i < clearArray.length; ++i)
		{
			cleartext += (char) (clearArray[i] % MOD_NUM);
		}	
	}
	
	//Prints matrix
	public static void printMatrix(int[] pMatrix)
	{
		for(int i = 0; i < pMatrix.length; ++i)
		{
			System.out.print(pMatrix[i] + " ");
		}
	}
	
	//Prints 2x2 matrix
	public static void printMatrix(int[][] pMatrix)
	{
		for(int i = 0; i < pMatrix.length; ++i)
		{
			System.out.print("[");
			for(int j = 0; j < pMatrix[i].length; ++j)
			{
				System.out.print(" " + pMatrix[i][j]);
			}
			System.out.println(" ]");
		}
	}
	
	public static void main(String[] args) 
	{	
		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter("\\n");
		
		String message;
		
		System.out.println("Enter message to encrypt: ");
		message = scanner.nextLine();
		
		//Hill Cipher setup
		int[][] key = {{2, 3}, {1, 5}};
		
		HillCipher hc = new HillCipher("", key, "");
		
		System.out.println("Key Matrix:");
		printMatrix(hc.getKeyMatrix());
		
		hc.findDeterminant();
		System.out.println("Determinant: " + hc.getDeterminant());
		if(hc.gcd(hc.getDeterminant(), MOD_NUM) != 1)
		{
			System.out.println("No multi inverse exists");
			System.exit(0);
		}
		
		hc.findMultiplicativeInverse();
		System.out.println("Multiplicative Inverse of " + hc.getDeterminant() + " modulo " +  MOD_NUM + ": " + hc.getMultiplicativeInverse());
		
		hc.findAdjointMatrix();
		System.out.println("Adjoint Matrix:");
		printMatrix(hc.getAdjointMatrix());
		
		hc.findInverseMatrix();
		System.out.println("Inverse Matrix:");
		printMatrix(hc.getInverseMatrix());
		
		//String str1 = "abcdm ";
		//String str2 = "hKrW";
		
		String text = "";
		int i = 0;
		
		System.out.println("Encrypting: " + message);
		while(i < message.length() - 1)
		{
			text += message.substring(i, i + 2);
			hc.encryption(text);
			text = "";
			i += 2;
		}
		
		System.out.println("Ciphertext: " + hc.getCiphertext());
		
		text = "";
		int j = 0;
		
		System.out.println("Decrypting: " + hc.getCiphertext());
		while(j < hc.getCiphertext().length() - 1)
		{
			text += hc.getCiphertext().substring(j, j + 2);
			hc.decryption(text);
			text = "";
			j += 2;
		}
		
		System.out.println("Cleartext: " + hc.getCleartext());	
		
		scanner.close();
	}
	
	/**Accessor methods**/

	//Gets cleartext
	public String getCleartext()
	{
		return cleartext;
	}
	
	//Gets key matrix
	public int[][] getKeyMatrix()
	{
		return keyMatrix;
	}
	
	//Gets determinant
	public int getDeterminant()
	{
		return determinant;
	}
	
	//Gets multiplicative inverse
	public int getMultiplicativeInverse()
	{
		return multiplicativeInverse;
	}
	
	//Gets adjoint matrix
	public int[][] getAdjointMatrix()
	{
		return adjointMatrix;
	}
	
	//Gets inverse matrix
	public int[][] getInverseMatrix()
	{
		return inverseMatrix;
	}	
	
	//Gets ciphertext
	public String getCiphertext()
	{
		return ciphertext;
	}
}