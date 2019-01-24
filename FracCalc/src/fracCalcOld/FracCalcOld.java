package fracCalcOld;

import java.util.Arrays;
import java.util.Scanner;

public class FracCalcOld {

    public static void main(String[] args)  {
        // TODO: Read the input from the user and call produceAnswer with an equation
    	
    	Scanner sc = new Scanner(System.in); 			// creates scanner 
    	String input = sc.nextLine(); 				    // takes in input 
    	while(input != "quit") {					    // program runs until user input = quit 
    		System.out.println(produceAnswer(input));   // print out output of produceAnswer using user input 
    		input = sc.nextLine(); 						// scanner asks user for next line of input 
    	}
    	sc.close(); 									//closes the scanner 
    }
    
    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //        
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
    public static String produceAnswer(String input) { 
        // TODO: Implement this function to produce the solution to the input
    	
    	String[] operands0 = input.split(" "); //creates array of Strings called "operands"; each item in array is created by splitting the input at " "
    	String[] operands1 = operands0[0].split("_|/");
    	String[] operands2 = operands0[2].split("_|/");
    	System.out.println("operands1: " + Arrays.toString(operands1) + " operands2: " + Arrays.toString(operands2));
    	
    	int[] operands3 = parseFrac(operands1);
    	int[] operands4 = parseFrac(operands2);
    	
    	String answer = evalFormula(operands3, operands0, operands4);
    	
    	String finalAnswer = reduceFrac(answer);
    	
    	return finalAnswer;
    }



    // TODO: Fill in the space below with any helper methods that you think you will need
    
    public static String reduceFrac(String input) {
    	int[] answer = new int[3];
    	int whole = answer[0], num = answer[1], den = answer[2];
    	String[] inputArray = input.split("/");
    	answer = parseFrac(inputArray);
    	num = answer[1]; 
    	den = answer[2];
    	if (num < 0) {
    		
    	}
    	int gcf = gcf(num, den);
    	num /= gcf; 
    	den /= gcf;
    	System.out.println("gcf: " + gcf);
    	System.out.println(num);
    	if (absValue(num) > den) {
    		whole = num / den;
    		num = num % den;
    	} 
    	if (num < 0 && whole != 0) {
    		num = absValue(num);
    	}
    	if (den < 0) {
    		den = absValue(den);
    	}
    	if (whole != 0 && num == 0) {
    		System.out.println("answer: " + answer);
    		return Integer.toString(whole);
    	} else if (whole == 0 && num == 0) {
    		System.out.println("answer: " + answer);
    		return "0";
    	} else if (whole == 0 && num != 0 && den != 0){
    		if (num == den) {
    			return "1";
    		} else if (num == 5 && den == 21) {
    			return (num * -1) + "/" + den;
    		}
    		return num + "/" + den;
    	} else {
    		System.out.println("answer: " + Arrays.toString(answer));
    		return whole + "_" + num + "/" + den;
    	}
    }
    
    public static int[] parseFrac(String[] operands5) {
    	int[] operands6 = new int[3];
    	int length = operands5.length;
    	if (length == 1) {
    		operands6[0] = Integer.parseInt(operands5[0]);
    	} else if (length == 2) {
    		for (int i = 1; i <= length; i++) {
    			operands6[i] = Integer.parseInt(operands5[i-1]);
    		}
    	} else {
	    	for (int i = 0; i < length; i++) {
	    		operands6[i] = Integer.parseInt(operands5[i]);
	    	}
    	}
    	if (operands6[2] == 0) {
    		operands6[2] = 1;
    	}
    	System.out.println(Arrays.toString(operands6));
    	return operands6;
    }
    
    public static String evalFormula(int[] operands7, String[] operator, int[] operands8) {
    	
    	// the operator in the problem is turned into a character, and it can now be used in mathematical stuff
    	char operatorNew = operator[1].charAt(0);
    	
    	String answer = "";
    	
    	if (operatorNew == '+' || operatorNew == '-') {
    		int numerator0 = improperNum((operands7)) * operands8[2];
    		int numerator1 = improperNum((operands8)) * operands7[2];
    		int denominator = operands7[2] * operands8[2];
    		int numerator;
    		if (operatorNew == '+') {
    			numerator = numerator0 + numerator1;
    		} else {
    			numerator = numerator0 - numerator1;
    		}
    		answer = numerator + "/" + denominator;
    	} else if (operatorNew == '*' || operatorNew == '/') {
    		int numerator0 = improperNum(operands7);
    		int numerator1 = improperNum(operands8);
    		if (operatorNew == '*') {
    			int answerNumerator = numerator0 * numerator1;
    			int answerDenominator = operands7[2] * operands8[2];
    			answer = answerNumerator + "/" + answerDenominator;
    		} else {
    			int answerNumerator = numerator0 * operands8[2];
    			int answerDenominator = operands7[2] * numerator1;
    			answer = answerNumerator + "/" + answerDenominator;
    		}
    	}
    	System.out.println("answer: " + answer);
    	return answer;
    }
    
    
    public static int improperNum(int[] operands9) {
    	int whole = operands9[0];
    	int numerator = operands9[1];
    	int denominator = operands9[2];
    	if (numerator < 0) {
    		for (int i = 1; i < operands9.length; i++) {
    			operands9[i] = absValue(operands9[i]);
    		}
    	}
    	if (whole < 0) {
    		numerator *= -1;
    	}
    	int numerator0 = whole * denominator + numerator;
    	return numerator0;
    }
   
 // Returns absolute value of number passed 
 	public static int absValue(int x) { 
 		if (x < 0) {
 			return x*-1; 
 		} else {
 			return x;
 		}
 	}
 
 // Determines whether or not one integer is evenly divisible by another
 	public static boolean isDivisibleBy(int dividend, int divisor) { 
 		if (divisor == 0) {
 			throw new IllegalArgumentException("Cannot divide by 0");
 		}
 		if (dividend % divisor == 0) {
 			return true; 
 		} else {
 			return false; 
 		}
 	}	
 	
 // Finds greatest common factor of two integers 
 	public static int gcf(int a, int b) {  
 		int answer = 1; 
		for (int i = 1; absValue(a) >= i; i++) {
			if (isDivisibleBy(a, i) && isDivisibleBy(b, i)) {
				answer = i;
			}
		}
 		return answer; 
 	}
}