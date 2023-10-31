// Dev Patel COMP 3240 Programming Assignment 2
public class Assignment2_Java {
    /* This method should accept the number to test and the maximum number of iterations
     * to try before halting execution. If num is NOT magic (or the maximum number
     * of iterations has been reached), return (-1 * num) (i.e., the negative of num).
     * If num IS magic, return the number of iterations it took to reduce num to 1.
     *
     * Remember that a number is magic if it can be reduced to 1 by dividing it by 2 if
     * it is even or multiplying it by 3 and adding 1 if it is odd.
     */
    public static int IsMagic(int num, int max_iterations) {
        int iterationsDone = 0;
        while (num != 1 && iterationsDone < max_iterations) {
            if (num % 2 == 0) {
                num = num / 2;
            } else {
                num = (num * 3) + 1;
            }
            iterationsDone++;
        }
        if (num == 1) {
            return iterationsDone;
        }
        else {
            return -1 * num;
        }
    }

    /* This method should be used to check if each number in the range [start, stop]
     * is a magic number. If all numbers in the range are magic, return the number of
     * iterations that it took to reduce the number passed into "stop" to 1. If you
     * find a number that is NOT magic, this method should return the negative of
     * that number.
     */
    public static int TestRange(int start, int stop, int max_iterations) {
        int totalIterations = 0;
        for (int i = start; i <= stop; i++) {
            int result = IsMagic(i, max_iterations);
            if (result < 0) {
                return result;
            }
            // if all numbers are magic then return number of iterations for number passed into stop to be reduced to 1
            // since stop is last number in range, result will be number of iterations for stop to be reduced to 1
            else {
                totalIterations = result;
            }
        }
        return totalIterations;
    }

    public static void main(String[] args) {
        int start = 5;
        int stop = 20;
        int max_iterations = 500;

        int result = TestRange(start, stop, max_iterations);
        System.out.println(result);
    }
}