package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author arper
 */
public class NumberValidation {

    public boolean validatePhoneNumbers(String phoneNumber) {

        String regex = "^\\d{10}$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(String.valueOf(phoneNumber));

        return matcher.matches();
    }
}
