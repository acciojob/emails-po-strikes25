package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        if(!oldPassword.equals(getPassword()))
            return;

            // 1. It contains at least 8 characters
        else if (newPassword.length() < 8 ) return;

        // 2. It contains at least one uppercase letter
        boolean isUpperCase = false;
        // 3. It contains at least one lowercase letter
        boolean isLowerCase = false;
        // 4. It contains at least one digit
        boolean isDigit = false;
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        boolean isSpecialCharacter = false;
        for(int i =0; i < newPassword.length(); i++){
            char ch = newPassword.charAt(i);
            if(ch >= 'A' && ch <= 'Z')isUpperCase = true;
            if(ch >= 'a' && ch <= 'z')isLowerCase = true;
            if(ch >= '0' && ch <= '9')isDigit = true;
            if((ch < 'A' || ch > 'Z') &&(ch < '0' || ch > '9') && (ch < 'a' || ch > 'z') )isSpecialCharacter = true;
        }
        if(isUpperCase && isLowerCase && isDigit && isSpecialCharacter){
            setPassword(newPassword);
        }
    }
}
