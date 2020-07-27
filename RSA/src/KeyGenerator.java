import java.math.BigInteger;
import java.util.Random;

public class KeyGenerator 
{
	/**Instance variables**/
	private BigInteger p; //Prime 1 (choose large prime)
	private BigInteger q; //Prime 2 (choose large prime)
	private BigInteger M; //Modulus (M = p * q)
	private BigInteger o; //Phi (o = (p - 1) * (q - 1))
	private BigInteger e; //Number (Choose 1 < e < (o - 1), so that GCF(e, o) = 1. Guarantees e has multiplicative inverse mod o)
	private BigInteger d; //Multiplicative inverse (d mod o, calculated using the Extended Euclidean Algorithm)
	private final int BIT_LENGTH = 1024; //Bit length of 1024
	private Random randNum; //Generates random primes

	//Constructor
	public KeyGenerator() 
	{
		this.randNum = new Random();
		this.p = BigInteger.probablePrime(BIT_LENGTH, randNum); //Calculates prime that can generate 1024 bit length
		this.q = BigInteger.probablePrime(BIT_LENGTH, randNum); //Calculates prime that can generate 1024 bit length
		this.M = p.multiply(q);
		this.o = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        this.e = BigInteger.probablePrime(BIT_LENGTH / 2, randNum);
        while(o.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(o) < 0)
        {
            e.add(BigInteger.ONE);
        }
		this.d = e.modInverse(o);
	}

	/**Accessor methods**/
	
	//Gets p
	public BigInteger getP()
	{
		return this.p;
	}
	
	//Gets q
	public BigInteger getQ()
	{
		return this.q;
	}
	
	//Gets M
	public BigInteger getM()
	{
		return this.M;
	}
	
	//Gets o
	public BigInteger getO()
	{
		return this.o;
	}
	
	//Gets e
	public BigInteger getE()
	{
		return this.e;
	}
	
	//Gets d
	public BigInteger getD()
	{
		return this.d;
	}
}