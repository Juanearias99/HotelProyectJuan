package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author arper
 */
public class DateValidation {

    public boolean validateDate(String date) {

        String regex = "\\d{4}/\\d{2}/\\d{2}";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(date);

        return matcher.matches();
    }
}
