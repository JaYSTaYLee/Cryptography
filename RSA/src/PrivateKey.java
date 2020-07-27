import java.math.BigInteger;

public class PrivateKey 
{
	/**Instance variables**/
	private BigInteger M;
	private BigInteger d;
	
	//Constructor
	public PrivateKey(KeyGenerator KG) 
	{
		this.M = KG.getM();
		this.d = KG.getD();
	}
	
	/**Accessor functions**/
	
	//Gets m
	public BigInteger getM()
	{
		return this.M;
	}
	
	//Gets d
	public BigInteger getD()
	{
		return this.d;
	}
}
