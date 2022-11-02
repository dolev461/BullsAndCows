import java.util.Random;
import java.lang.String;
import java.lang.Math;

public class Game {
    /* Bulls & Cows game logic.
     * 
     * Randomize a 4 digits secret number which the user needs to guess.
     * In each guess there are:
     *   1) bulls (exact digit in the exact place).
     *   2) cows (exists digit not in the right place).  
     */
    
    /*
     * Restriction:
     *  Secret length has to be under 9.
     *  Otherwise, the most significant digit
     *  might be zero which is invalid.
     */
    private static final int secretLength = 4;
    
    private String secretStr = "";
    private boolean isOngoing = false;
    
    public Game() {
        /* Initializes a new game */
        
        Restart();
    }
    
    public void Restart() {
        /* Resets the game secret and status */
        
        newSecret();
        isOngoing = true;
    }
    
    public String Guess(String guessStr) {
        /* Calculates bulls and cows of a guess.
         * 
         * Args:
         *  guessStr: number with secret length digits.
         *  
         * Returns:
         *  String represents bulls and cows of the guess.
         */
        
        int bulls = 0;
        int cows = 0;
        
        for (int i=0; i<getSecretLength(); i++) {
            if (guessStr.charAt(i) == secretStr.charAt(i)) {
                bulls++;
            }
            else if (secretStr.contains(guessStr.subSequence(i, i+1))) {
                cows++;
            }
        }
        
        if (bulls == getSecretLength()) {
            isOngoing = false;
            return "Guess: " + guessStr + " - Success!!!";
        }
        
        return "Guess: " + guessStr + " - Bulls: " + bulls + " - Cows: " + cows + "\r\n";
    }
    
    public boolean getIsOngoing() {
        /* True while there is no right guess, false otherwise. */
        
        return isOngoing;
    }
    
    public int getSecretLength() {
        /* Returns the game secret length. */
        
        return secretLength;
    }
    
    private void newSecret() {
        /* Randomizes a secret length number with no repetitions.
         * 
         * Restriction:
         *  Secret length has to be under 9.
         *  Otherwise, the most significant digit
         *  might be zero which is invalid.
         */
        
        Random rnd = new Random();
        
        int[] digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int digitIndex = 0;
        int digit = 0;
        int number = 0;

        for (int i=0; i<secretLength; i++) {
            do {
                digitIndex = rnd.nextInt(digits.length);
                digit = digits[digitIndex];
            } while (digit == -1 || 
                    ((secretLength == (i+1)) && (digit == 0)));
            
            number += digit * (Math.pow(10, i));
            digits[digitIndex] = -1;
        }
        
        secretStr = String.valueOf(number);
    }
}
