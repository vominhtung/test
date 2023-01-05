package Customer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidPhone {

    private static final String REGEX= "^0[987326]\\d]{8,9}$";

    public ValidPhone(){}
public boolean validate(String regex){
        return Pattern.matches(REGEX,regex);
}



}

