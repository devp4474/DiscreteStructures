package com.gradescope.validator;
import java.util.Hashtable;

// Dev Patel
// COMP 3240 Section 001 Programming Assignment 1

//import com.gradescope.validator.Evaluator;
// Notes on how to use evaluator():
// Call the evaluator with Evaluator.evaluate(<premise>, <variable_dict>). "premise"
// is a single string defining the premise or conclusion to test. "variable_dict" is a
// Hashtable<Character, Boolean>() object with the variable name as the key and true/false
// as the value. 
// The only valid operators for premise strings are '^' (and), 'V' (or--CAPITAL v),'~' (not),
// and '>' (implies), and you can use parentheses to override the order of operations as usual.
// All variables should be lowercase letters and each should only be one character long. Finally,
// do not include spaces in the string.
// For example, if you want to test the premise 'p implies q or not r', you should use 'p>qV~r' as
// your premise string.
public class Validator {
// All of the logic to complete this assignment should be written in this function.
// This method accepts two things: An array of strings called premiseList and a 
// single string called conclusion. These strings should be formatted according to 
// the structure definded above. Also, this needs to return a boolean variable: true if
// the argument form is valid, and false if it is not valid.
   public boolean validate(String[] premiseList, String conclusion) {
      // making list of all the variables in argument
      
      char[] variables = new char[26];
      int variableCount = 0;
      
      for (String premise : premiseList) {
         for (int i = 0; i < premise.length(); i++) {
            char c = premise.charAt(i);
            if (Character.isLowerCase(c)) {
               boolean found = false;
               for (int j = 0; j < variableCount; j++) {
                  if (variables[j] == c) {
                     found = true;
                     break;
                  }
               }
               if (!found) {
                  variables[variableCount++] = c;
               }
            }
         }
      }
      // adding character from conclusion into variables list
      for (int i = 0; i < conclusion.length(); i++) {
         char c = conclusion.charAt(i);
         if (Character.isLowerCase(c)) {
            boolean found = false;
            for (int j = 0; j < variableCount; j++) {
               if (variables[j] == c) {
                  found = true;
                  break;
               }
            }
            if (!found) {
               variables[variableCount++] = c;
            }
         }
      }
   
      // to generate all possible truth assignments for variables
      for (int i = 0; i < (1 << variableCount); i++) {
         Hashtable<Character, Boolean> variableAssignments = new Hashtable<Character, Boolean>();
         for (int j = 0; j < variableCount; j++) {
            variableAssignments.put(variables[j], (i & (1 << j)) != 0);
         }
      
         boolean conclusionTrue = false;
         try {
            conclusionTrue = Evaluator.evaluate(conclusion, variableAssignments);
         } catch (Exception e) {
            return false;
         }
      
      // checking validiity of argument forms. (premises all true and conclusion false then invalid)
         boolean allPremisesTrue = true;
         for (String premise : premiseList) {
            boolean premiseIsTrue = false;
            try {
               premiseIsTrue = Evaluator.evaluate(premise, variableAssignments);
            } catch (Exception e) {
               return false;
            }
            if (!premiseIsTrue) {
               allPremisesTrue = false;
               break;
            }
         }
      
         if (allPremisesTrue && !conclusionTrue) {
            return false;
         }
      }
      return true;
   }
}
