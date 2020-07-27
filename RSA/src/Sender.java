import java.math.BigInteger;

public class Sender 
{
	/**Instance variables**/
	private BigInteger M;
	private BigInteger e;
	private String message;
	
	//Constructor
	public Sender(PublicKey pPuK, String pMessage) 
	{
		this.M = pPuK.getM();
		this.e = pPuK.getE();
		this.message = pMessage;
	}

	//Encryption
	public byte[] encryption(byte[] pClearText) //0 <= pClearText < M
	{
		return (new BigInteger(pClearText)).modPow(e, M).toByteArray();
	}
	
	/**Accessor functions**/
	
	//Gets message
	public String getMessage()
	{
		return this.message;
	}
}