package pass_manager.password_manager;

import org.apache.commons.lang3.RandomStringUtils;

public class PasswordGenerator {
    public static String password_generator() {
        return RandomStringUtils.random(32, 0, 20, true, true, "qw32rfHIJk9iQ8Ud7h0X".toCharArray());
    }
}
