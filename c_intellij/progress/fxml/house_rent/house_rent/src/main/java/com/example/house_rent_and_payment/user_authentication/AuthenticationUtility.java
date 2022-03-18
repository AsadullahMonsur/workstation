package com.example.house_rent_and_payment.user_authentication;

import javafx.scene.image.Image;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthenticationUtility {
    public static boolean password_validity_check(String password) {
        String pattern_format = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{6,10})";
        Pattern pattern = Pattern.compile(pattern_format);
        Matcher matcher = pattern.matcher(password);


        return (matcher.matches() && (password.length()>5) && (!(password.contains(" "))));
    }

    public static boolean mail_validity_check(String mail) {
       boolean result = mail.length()<1 ||
                        mail.length()>50 ||
                        (!email_pattern_check(mail));
       // if true not a valid one
       return  result;
    }

    public static boolean email_pattern_check(String email){
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }

    public static boolean general_input_pattern_check(String input){
       boolean first_check = input.equals("");
       boolean second_check = input.contains(" ");

       String pattern_format_01 = "[^a-zA-Z0-9]";
       Pattern pattern_01 = Pattern.compile(pattern_format_01);
       Matcher matcher_01 = pattern_01.matcher(input);
       boolean third_check = matcher_01.find();

       String pattern_format_02 = "[a-zA-Z]";
       Pattern pattern_02 = Pattern.compile(pattern_format_02);
       Matcher matcher_02 = pattern_02.matcher(input);
       boolean fourth_check = matcher_02.find();

        return (!first_check) && (!second_check) && (!third_check) && (fourth_check);
    }

    public static String hidden_string(int length){
      String value = "";

      for (int i=0;i<length;i++){
        value = value+"*";
      }

      return value;
    }
}
