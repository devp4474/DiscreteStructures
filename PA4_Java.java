import java.util.Random;

public class PA4_Java {   
   /* Perform one instance of the Monty Hall Problem
    *
    * should_switch: set to true if running an experiment where the contestant should
    *    switch their guess every time. Set to false if not
    *
    * Returns true if the contestant selected the door with the car behind it. Returns
    *    false otherwise.
    */
   public static boolean run_trial(boolean should_switch) {
      Random rand = new Random();
      int winningDoor = rand.nextInt(3);
      int chosenDoor = rand.nextInt(3);

      if (!should_switch) {
         return chosenDoor == winningDoor;
      } else {
         int revealedDoor = 0;
         while (revealedDoor == chosenDoor || revealedDoor == winningDoor) {
            revealedDoor++;
         }
         int newChosenDoor = 0;
         while (newChosenDoor == revealedDoor || newChosenDoor == chosenDoor) {
            newChosenDoor++;
         }
         return newChosenDoor == winningDoor;
      }
   }
   
   /* Execute an entire experiment (i.e., execute the specified number of trials)
    * and return the desired results
    * 
    * num_trials: number of trials to execute in this experiment
    * should_switch: set to true if running an experiment where the contestant should
    *    switch their guess every time. Set to false if not
    *
    * Returns the percentage of games won (i.e., number of wins / number of trials)
    */
   public static double run_experiment(int num_trials, boolean should_switch) {
      int switchWins = 0;
      int stayWins = 0;
      for (int i = 0; i < num_trials; i++) {
         boolean switchResult = run_trial(should_switch);
         boolean stayResult = run_trial(false);
         if (switchResult) {
            switchWins++;
         }
         if (stayResult) {
            stayWins++;
         }
      }
      double switchPercentage = (double) switchWins / num_trials;
      return switchPercentage;
   }
   
   /* This is a stub that you can use to test the rest of the program. The code in this
    * method will not be executed during grading, so you don't need to worry about user
    * input.
    */
   public static void main(String[] args) {
      int num_trials = 1000;
      boolean should_switch = false;
      double stayPercentage = run_experiment(num_trials, should_switch);
      System.out.println("Staying wins " + stayPercentage + "% of the time");

      should_switch = true; 
      double switchPercentage = run_experiment(num_trials, should_switch);
      System.out.println("Switching wins " + switchPercentage + "% of the time");
   }
}