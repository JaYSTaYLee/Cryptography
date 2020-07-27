import java.math.BigInteger;

public class PublicKey 
{
	/**Instance variables**/
	private BigInteger M;
	private BigInteger e;
	
	//Constructor
	public PublicKey(KeyGenerator KG) 
	{
		this.M = KG.getM();
		this.e = KG.getE();
	}
	
	/**Accessor functions**/
	
	//Gets m
	public BigInteger getM()
	{
		return this.M;
	}
	
	//Gets e
	public BigInteger getE()
	{
		return this.e;
	}
}
