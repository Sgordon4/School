package edu.iastate.cs228.hw01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author
 * Sean Gordon
 */
public class MyString
{
	private char[] chars;

	public MyString(char[] chars)
	{
		if(chars == null || chars.length == 0) throw new IllegalArgumentException();
		this.chars = chars;
		
	}

	//https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#length--
	public int length()
	{
		return chars.length;
	}
	
	//https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#charAt-int-
	public char charAt(int index)
	{
		return chars[index];
	}

	// https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#substring-int-int-
	public MyString substring(int begin, int end)
	{
		char[] substring = Arrays.copyOfRange(chars, begin, end);
		return new MyString(substring);
	}

	//It is ok to use
	//https://docs.oracle.com/javase/8/docs/api/java/lang/Character.html#toLowerCase-char-
	public MyString toLowerCase()
	{
		char[] nw = new char[chars.length];
		for(int i : chars) {
			nw[i] = Character.toLowerCase(chars[i]);
		}
		return new MyString(nw);	
	}

	
	//You can assume only positive values.
	//https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#valueOf-int-
	public static MyString valueOf(int i)
	{	
		int n = i;
		int length = (int)(Math.log10(n)+1);
		char[] chars = new char[length];		//Are we at the point of using queues?
		
		while(length > 0) {
			length--;
			chars[length] = (char)(n%10 + 48);
			n = n/10;
		}
		
		
		return new MyString(chars);
	}

	public char[] toCharArray()
	{
		return chars;
	}
}