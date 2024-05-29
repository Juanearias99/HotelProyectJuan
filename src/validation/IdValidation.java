package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author arper
 */
public class IdValidation {

    public boolean validateIdentification(long id) {

        String regex = "^\\d{7,10}$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(String.valueOf(id));

        return matcher.matches();
    }
}
