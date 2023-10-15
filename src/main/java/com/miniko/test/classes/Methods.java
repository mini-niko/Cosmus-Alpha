package com.miniko.test.classes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


//Métodos utilitários
public class Methods {
    //Verifica se uma string é um email
    public boolean isValidEmail(String email) {
        String padrao = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(padrao);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}
