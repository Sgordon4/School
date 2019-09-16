package edu.iastate.cs228.hw01;

import java.math.BigInteger;

/**
 * @author Y. Daniel Liang
 * Sean Gordon
 * 
 * Changes applied for cs228.hw01:
 * => The original equals method implementation is changed.
 * => Removed one of the methods.
 * => Check:
 *    https://www.mathsisfun.com/algebra/rational-numbers-operations.html
 *    https://www.mathsisfun.com/greatest-common-factor.html  
 */

/*
 * Rational.java: Define a rational number and its associated operations such as
 * add, subtract, multiply, and divide.
 */

@SuppressWarnings("serial")
public class Rational2 extends Number implements Comparable<Rational2>
{
	// Data fields for numerator and denominator
	private BigInteger numerator = new BigInteger("0");
	private BigInteger denominator = new BigInteger("1");

	/** Default constructor */
	public Rational2()
	{
		this(new BigInteger("0"), new BigInteger("1"));
	}

	/** Construct a rational with specified numerator and denominator */
	public Rational2(BigInteger numerator, BigInteger denominator)
	{
		BigInteger gcd = numerator.gcd(denominator);
		this.numerator = ((denominator.intValue() > 0) ? new BigInteger("1") : new BigInteger("-1")).multiply(numerator).divide(gcd);
		this.denominator = denominator.abs().divide(gcd);
	}

	/** Find GCD of two numbers */
	private BigInteger gcd(BigInteger n, BigInteger d)
	{
		BigInteger n1 = n.abs();
		BigInteger n2 = d.abs();

		BigInteger gcd = new BigInteger("1");

		for (int k = 1; k <= n1.intValue() && k <= n2.intValue(); k++)
		{
			if (n1.mod(new BigInteger(""+k)).intValue() == 0 && n2.mod(new BigInteger(""+k)).intValue() == 0)
				gcd = new BigInteger(""+k);
		}
		return gcd;
	}

	/** Return numerator */
	public BigInteger getNumerator()
	{
		return numerator;
	}

	/** Return denominator */
	public BigInteger getDenominator()
	{
		return denominator;
	}

	/** Add a rational number to this rational */
	public Rational2 add(Rational2 secondRational)
	{
		BigInteger n = numerator.multiply(secondRational.getDenominator()).add(denominator.multiply(secondRational.getNumerator()));
		BigInteger d = denominator.multiply(secondRational.getDenominator());
		return new Rational2(n, d);
	}

	/** Subtract a rational number from this rational */
	public Rational2 subtract(Rational2 secondRational)
	{
		BigInteger n = numerator.multiply(secondRational.getDenominator()).subtract(denominator.multiply(secondRational.getNumerator()));
		BigInteger d = denominator.multiply(secondRational.getDenominator());
		return new Rational2(n, d);
	}

	/** Multiply a rational number to this rational */
	public Rational2 multiply(Rational2 secondRational)
	{
		BigInteger n = numerator.multiply(secondRational.getNumerator());
		BigInteger d = denominator.multiply(secondRational.getDenominator());
		return new Rational2(n, d);
	}

	/** Divide a rational number from this rational */
	public Rational2 divide(Rational2 secondRational)
	{
		BigInteger n = numerator.multiply(secondRational.getDenominator());
		BigInteger d = denominator.multiply(secondRational.getNumerator());
		return new Rational2(n, d);
	}

	@Override
	public String toString()
	{
		if (denominator.intValue() == 1)
			return numerator + "";
		else
			return numerator + "/" + denominator;
	}

	/** Override the equals method in the Object class */
	public boolean equals(Object parm1)
	{
		if(this == parm1) return true;
		if((parm1 == null) || (parm1.getClass()!=this.getClass())) return false;
			
		if ((this.subtract((Rational2) (parm1))).getNumerator().intValue() == 0)
			return true;
		else
			return false;
	}

	/** Override the abstract intValue method in java.lang.Number */
	public int intValue()
	{
		return (int) doubleValue();
	}

	/** Override the abstract floatValue method in java.lang.Number */
	public float floatValue()
	{
		return (float) doubleValue();
	}

	/** Override the doubleValue method in java.lang.Number */
	public double doubleValue()
	{
		return numerator.doubleValue() * 1.0 / denominator.doubleValue();
	}

	/** Override the abstract longValue method in java.lang.Number */
	public long longValue()
	{
		return (long) doubleValue();
	}

	@Override
	public int compareTo(Rational2 o)
	{
		if (this.subtract(o).getNumerator().intValue() > 0)
			return 1;
		else if (this.subtract(o).getNumerator().intValue() < 0)
			return -1;
		else
			return 0;
	}
}
