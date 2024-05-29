package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author arper
 */
public class CodeValidation {

    public boolean validationCode(long code) {

        String regex = "\\d+";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(String.valueOf(code));

        return matcher.matches();
    }
}
