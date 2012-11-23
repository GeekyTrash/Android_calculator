package com.chrisconley.simplecalculator;

/*********************************************************************
*	Calculator
**********************************************************************
* CHRIS CONLEY
*********************************************************************/

import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;



public class CalculatorMainActivity extends Activity {

	
	public EditText mathText;		//the edit text window
	public String theMath = "";     //current string to be displayed in math Text
	public boolean opstate=false; 	//false = operand 1, true = operand 2




	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculator_main);
		mathText = (EditText) findViewById(R.id.mathText);
		mathText.setKeyListener(null);	// make the edit text so you cant click it
		// create the buttons and attatch them to the layout buttons
		final Button bzero = (Button) findViewById(R.id.zero);
		final Button bone = (Button) findViewById(R.id.one);
		final Button btwo = (Button) findViewById(R.id.two);
		final Button bthree = (Button) findViewById(R.id.three);
		final Button bfour = (Button) findViewById(R.id.four);
		final Button bfive = (Button) findViewById(R.id.five);
		final Button bsix = (Button) findViewById(R.id.six);
		final Button bseven = (Button) findViewById(R.id.seven);
		final Button beight = (Button) findViewById(R.id.eight);
		final Button bnine = (Button) findViewById(R.id.nine);
		final Button bclear = (Button) findViewById(R.id.clear);
		final Button badd = (Button) findViewById(R.id.add);
		final Button bsubtract = (Button) findViewById(R.id.subtract);
		final Button bmultiply = (Button) findViewById(R.id.multiply);
		final Button bdivide = (Button) findViewById(R.id.divide);
		final Button bequals = (Button) findViewById(R.id.equals);

		/* 
		 * Listeners for the buttons, one for each button
		 * the value of the button is added to the string 'theMath'
		 * and the updateMathText is called which updates mathText
		 */ 
		bzero.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				theMath+="0";			// add value onto the string
				updateMathText();}});	// update string
		
		bone.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				theMath+="1";
				updateMathText();}});
		btwo.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				theMath+="2";
				updateMathText();}});
		bthree.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				theMath+="3";
				updateMathText();}});
		bfour.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				theMath+="4";
				updateMathText();}});
		bfive.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				theMath+="5";
				updateMathText();}});
		bsix.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				theMath+="6";
				updateMathText();}});
		bseven.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				theMath+="7";
				updateMathText();}});
		beight.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				theMath+="8";
				updateMathText();}});
		bnine.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				theMath+="9";
				updateMathText();}});   

		/*
		 * The next 4 functions are for the arithmetic, their value is 
		 * added to the string 'theMath' with a leading and following
		 * space that is used to split the string when it is parsed
		 * it then calls updateMathText to update mathText
		 * 
		 */
		badd.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				theMath+=" + ";
				updateMathText();}});

		// subtraction
		bsubtract.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				theMath+=" - ";
				
				updateMathText();}});

		// multiplication
		bmultiply.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				theMath+=" * ";
				updateMathText();}});

		// division
		bdivide.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
						theMath+=" / ";
				updateMathText();}});

		/*
		 * clears the MathText text view, erases the string 'theMath'
		 * then updates the text view
		 */
		bclear.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				theMath ="";		// sets theMath to be blank
				updateMathText();}}); 


		/*
		 * When pressed the entire string theMath will be passed into the
		 * ExpressionParser function which will evaluate the string.
		 * It returns a string which is set to be 'theMath' and that is
		 * set as the text of the MathText
		 */
		bequals .setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				theMath = ExpressionParser(theMath);
				updateMathText();}});
	}

	/*
	 * The heavy lifter of the Activity, a string is passed in and it is
	 * split into a string array by white spaces. It checks to make sure
	 * there are a proper number of operators and operands, that they are
	 * in the correct order, and no one destroys the universe by dividing 
	 * by zero. It returns a string of the final answer of the arithmetic 
	 * statement
	 */
	public String ExpressionParser(String exp){
		 String[] tokens = exp.split("[ ]+");	// split the input string by its white space
		 String operand1 = tokens[0];			// set the first operand as the first value
		 if (intCheck(operand1)==false){		// check that it doesnt start with an operator
			 return "Error: Cannot start a statement with an operator";
		 }
		 int length = tokens.length;
		 // make sure that there are the correct number of tokens for a correct statement
		 if (length%2==0){			
			 return "Error: incorrent format";
		 }
		 String operator;
		 String operand2;
		 int result;
		 int i =1;			//keep current place in the parsing of string array
		 String spool="";	//used to print the remainder of the string
	     while (i<length) {
	         operator = tokens[i];	// should always assign an operator
	         operand2 = tokens[i+1];// should always assign an integer
	         // makes sure there are not two side by side operators
	         if (intCheck(operand1)==false || intCheck(operand2)==false){
	        	 return "Error: can not have 2 operators side by side";
	         }
	         // depending on the value of operator is does the correct arithmetic
	         if (operator.equals("+")){
	        	 result = Integer.parseInt(operand1) + Integer.parseInt(operand2);
	         }
	         else if (operator.equals("-")){
	        	 result = Integer.parseInt(operand1) - Integer.parseInt(operand2);
	         }
	         else if (operator.equals("*")){
	        	 result = Integer.parseInt(operand1) * Integer.parseInt(operand2);
	         }
	         else if(operator.equals("/")){
	        	 if (operand2.equals("0")){	// makes sure we dont divide by zero
	        		 return "Error: Can not divide by 0";
	        	 }
	        	 // this will round down
	        	 result = (int) (Integer.parseInt(operand1)*1.0 / Integer.parseInt(operand2));
	         }
	      // just a catch all the off chance something instead of an operator is entered
	      // for example if someone entered a value from an onscreen keyboard   
	         else{	
	        	 return "Error";
	         }
	         // loops through the remainder of the string array so we can show the remaining work
	         for (int j = i+2;j<length;j++){
	        	 spool.concat(" "+tokens[j]);
	         }
	         // set the mathText to show the math as it happens
	         mathText.setText(Integer.toString(result).concat(spool));
	         operand1=Integer.toString(result);	// operand becomes the result for further math
	         i=i+2;	// incrememnt i by two since we took one operand and one operator from the array
	         
	         
	     }
		return operand1; // returns the answer
	}
	
	/*
	 * Used to check to make sure a string is numeric.
	 * It's purpose is to stop errors from arising if
	 * an operator is placed in a spot a numeric operand
	 * should be in. 
	 * 
	 * If the passed in string can be parsed as an integer it
	 * returns true, otherwise it returns false
	 */
	public boolean intCheck(String s) {
		  try {
		    Integer.parseInt(s);
		    return true;
		  }
		  catch (NumberFormatException e) {
		    // s is not numeric
		    return false;
		  }
		}
		
		

	/*
	 *  updates the mathText edit text box to display the
	 *  value of the string theMath
	 */
	public void updateMathText(){
		mathText.setText(theMath);
	}

}
