import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class ValidatorTest {


     /** Fixture initialization (common initialization
      *  for all tests). **/
   @Before public void setUp() {
   }


     /** A test that always fails. **/
   @Test
   public void testValidate_1() {
      String[] premises = {"p^q", "q^r"};
      String conclusion = "p^r";
      Boolean expectedResult = true;
      Validator validator = new Validator();
      Boolean result = validator.validate(premises, conclusion);
      
      System.out.println("Expected Result: " + expectedResult);
      System.out.println("Result: " + result);
      assertEquals(expectedResult, result);
   }

   @Test
   public void testValidate_2() {
      String[] premises = {"p", "q"};
      String conclusion = "pVq";
      Boolean expectedResult = true;
      Validator validator = new Validator();
      Boolean result = validator.validate(premises, conclusion);
      assertEquals(expectedResult, result);
   
   }

   @Test
   public void testValidate_3() {
      String[] premises = {"~p", "pVq"};
      String conclusion = "q";
      Boolean expectedResult = true;
      Validator validator = new Validator();
      Boolean result = validator.validate(premises, conclusion);
      
      System.out.println("Expected Result: " + expectedResult);
      System.out.println("Result: " + result);
      assertEquals(expectedResult, result);
   }

   @Test
   public void testValidate_4() {
      String[] premises = {"p^q", "qVr"};
      String conclusion = "p^r";
      Boolean expectedResult = false;
      Validator validator = new Validator();
      Boolean result = validator.validate(premises, conclusion);
      
      System.out.println("Expected Result: " + expectedResult);
      System.out.println("Result: " + result);
      assertEquals(expectedResult, result);
   }
}