public class PA3_Java {
   /* Multiplies matrix 1 by matrix 2
    */
   public static float[][] matrix_mult(float[][] mat1, float[][] mat2) {
      float[][] result = new float[mat1.length][mat2.length];
      for (int i = 0; i < mat1.length; i++) {
         for (int j = 0; j < mat2.length; j++) {
            for (int k = 0; k < mat1.length; k++) {
               result[i][j] += mat1[i][k] * mat2[k][j];
            }
         }
      }
      return result;
   }
   
   /* Create the transition matrix from the given observation points
    */
   public static float[][] calc_transition_matrix(String observation_string) {
      float[][] transition_matrix = new float[2][2];
      int dCount = 0, rCount = 0;
      for (int i = 0; i < observation_string.length(); i++) {
         char curr = observation_string.charAt(i);
         char next = observation_string.charAt(i + 1);
         if (curr == 'D') {
            dCount++;
            if (next == 'D') {
               transition_matrix[0][0]++;
            } else if (next == 'R') {
               transition_matrix[0][1]++;
            }
         } else if (curr == 'R') {
            rCount++;
            if (next == 'D') {
               transition_matrix[1][0]++;
            } else if (next == 'R') {
               transition_matrix[1][1]++;
            }
         }
      }
      if (transition_matrix[0][0] == 0 || transition_matrix[0][1] == 0 || transition_matrix[1][0] == 0 || transition_matrix[1][1] == 0) {
         System.out.println("Error: Invalid observation string");
         System.exit(1);
      }

      float dTotal = transition_matrix[0][0] + transition_matrix[0][1];
      float rTotal = transition_matrix[1][0] + transition_matrix[1][1];
      transition_matrix[0][0] = transition_matrix[0][0] / dTotal;
      transition_matrix[0][1] = transition_matrix[0][1] / dTotal;
      transition_matrix[1][0] = transition_matrix[1][0] / rTotal;
      transition_matrix[1][1] = transition_matrix[1][1] / rTotal;

      return transition_matrix;
   }
   
   /* Generates the forecast for the next 7 days given the transition matrix and the current weather
      The forecast should be a 2x7 matrix where each row is a forecast for a day
    */
   public static float[][] generate_forecast(float[][] transition_matrix, char curr_weather) {
      float[][] forecast = new float[7][2];
      int currIndex = (currWeather == 'D') ? 0 : 1;
      forecast[0][currIndex] = 1;
      for (int i = 1; i < forecast.length; i++) {
         forecast[i] = matrix_mult(forecast[i - 1], transition_matrix);
      }
      return forecast;
   }
   
   /* Generates the climate prediction (i.e., steady state vector) given the transition matrix, current 
	  weather, and precision
    */
   public static float[] generate_climate_prediction(float[][] transition_matrix, char curr_weather, float precision) {
      float[] steady_state = new float[2];
      int initial_index;
      if (curr_weather == 'D') {
         initial_index = 0;
      } else {
         initial_index = 1;
      }
      float[] curr_vector = new float[]{0, 0};
      curr_vector[initial_index] = 1;
      float[] prev_vector;
      do {
         prev_vector = curr_vector;
         curr_vector = matrix_mult(transition_matrix, curr_vector);
      } while (Math.abs(curr_vector[0] - prev_vector[0]) > precision || Math.abs(curr_vector[1] - prev_vector[1]) > precision);
      return steady_state;
   }
   
   /* Print the forecasted weather predictions 
    */
   public static void print_predictions(float[][] forecast) {
      // Print first line
      System.out.println("[[" + forecast[0][0] + "," + forecast[0][1] + "],");
      
      // Print middle 5 lines
      for (int i = 1; i < forecast.length - 1; i++) {
         System.out.println(" [" + forecast[i][0] + "," + forecast[i][1] + "],");
      }
       
      // Print the last line
      System.out.println(" [" + forecast[6][0] + "," + forecast[6][1] + "]]");
   }
   
   /* Print the steady state vector containing the climate prediction
    */
   public static void print_steady_state(float[] steady_state) {
      System.out.println(steady_state[0]);
      System.out.println(steady_state[1]);
   }
   
   public static void main(String[] args) {
      String observation = "DDRRRDRDDD";
      float precision = 0.01f;

      float[][] transition_matrix = calc_transition_matrix(observation);
      char curr_weather = observation.charAt(observation.length() - 1);
      float[] steady_state = generate_climate_prediction(transition_matrix, curr_weather, precision);
      print_steady_state(steady_state);
   }
}