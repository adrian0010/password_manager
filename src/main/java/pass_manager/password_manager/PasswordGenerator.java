package pass_manager.password_manager;

import java.util.Random;

public class PasswordGenerator {
    public static String password_generator(int length) {
        // Create a random number generator
        Random rng = new Random();

        // Generate a random string of uppercase, lowercase, and digits of the specified length
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int r = rng.nextInt(4);
            if (r == 0) {
                // Generate a random uppercase letter
                password.append((char) (rng.nextInt(26) + 'A'));
            } else if (r == 1) {
                // Generate a random lowercase letter
                password.append((char) (rng.nextInt(26) + 'a'));
            } else if (r == 2) {
                // Generate a random digit
                password.append((char) (rng.nextInt(10) + '0'));
            } else {
                // Generate a random special character
                password.append((char) (rng.nextInt(15) + 33));
            }
        }
        return password.toString();
    }
}
