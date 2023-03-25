package expression_calc_2;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternFinder{
    private String s, regex;

    public PatternFinder(String s, String regex) {
        this.s = s;
        this.regex = regex;
    }

    public boolean isEqual() {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        
        if(matcher.find()) {
            return true;    
        }
        return false;

        
        
    }

    public String patternGroup() {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        
        if(matcher.find()) {
            return matcher.group();    
        }
        
        return "a expressão8 não bateu";

       
        
    }

}