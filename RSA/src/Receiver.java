import java.math.BigInteger;

public class Receiver 
{
	/**Instance variables**/
	private BigInteger M;
	private BigInteger d;
	private byte[] message;
	
	//Constructor
	public Receiver(PrivateKey pPrK, byte[] pMessage) 
	{
		this.M = pPrK.getM();
		this.d = pPrK.getD();
		this.message = pMessage;
	}

	//Decryption
	public byte[] decryption(byte[] pCipherText) //0 <= pCipherText < M
	{
		return (new BigInteger(pCipherText)).modPow(d, M).toByteArray();
	}
	
	/**Accessor functions**/
	
	//Gets message
	public byte[] getMessage()
	{
		return this.message;
	}
}