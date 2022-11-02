import javax.swing.JOptionPane;

public class BullsAndCows {
    /* Main class interacts with user and the game. */
    
    private static boolean isNumeric(String str){
        /* Checks if a given string matches a number like pattern.
         * 
         * Args:
         *  str: Given string to check.
         */
        
        return str != null && str.matches("[1-9.][0-9.]*");
    }
    
    private static boolean isRepetitive(String str) {
        /* Checks if a string has duplications in it.
         * 
         * Args:
         *  str: Given string to check.
         */
        
        for (int i=0; i<str.length(); i++) {
            if (str.lastIndexOf(str.charAt(i)) != i) {
                return true;
            }
        }
        
        return false;
    }
    
    private static boolean validateGuess(String guessStr, Game game) {
        /* Validates the guess matches the game formats.
         * 
         * 1) Numeric value.
         * 2) Secret length.
         * 3) No repetitions.
         * 
         * Args:
         *  guessStr: A guess to validate.
         *  game: Game instance to validate formats.
         *  
         * Displays:
         *  Message dialog with a proper info text.
         * 
         * Returns:
         *  True if the guess is in game formats, false otherwise.
         */
        
        boolean isValidGuess = true;
        
        if (!isNumeric(guessStr)) {
            /* Not a valid number, try again */
            JOptionPane.showMessageDialog(null, "Please insert a number.");        
            isValidGuess = false;
        }
        else if (guessStr.length() != game.getSecretLength()) {
            /* Number not in range */
            JOptionPane.showMessageDialog(null, "Please enter a " + game.getSecretLength() + " digits number.");        
            isValidGuess = false;
        }
        else if (isRepetitive(guessStr)) {
            /* Repetitive guess */
            JOptionPane.showMessageDialog(null, "Please enter a number without repetitions.");        
            isValidGuess = false;
        }
        
        return isValidGuess;
    }

    public static void main(String[] args) {
        String guessStr = "", bullsAndCows = "";
        int guess = 0;
        Game game = new Game();
        
        while (game.getIsOngoing()) {
            guessStr = JOptionPane.showInputDialog(bullsAndCows + "Guess a number!");
            
            if (guessStr == null) {
                /* Pressed 'cancel' or 'X' */
                JOptionPane.showMessageDialog(null, "Bye bye :(");
                break;
            }
            else if (!validateGuess(guessStr, game)) {
                continue;
            }
            
            bullsAndCows = bullsAndCows + game.Guess(guessStr);
            
            if (!game.getIsOngoing()) {
                JOptionPane.showMessageDialog(null, bullsAndCows);
                if (JOptionPane.showConfirmDialog(null, "Keep Playing?") == 0) {
                    bullsAndCows = "";
                    game.Restart();                    
                }
            }
        }
        
    }
}
