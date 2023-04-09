package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
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
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character

        // Adding Base Cases :
        if(!oldPassword.equals(getPassword()))
            return;
        else if (newPassword.length() < 8 )
            return;

        if(oldPassword.equals(getPassword()) && newPassword.length() >= 8) {
            boolean isUpperCase = false;
            boolean isLowerCase = false;
            boolean isDigit = false;
            boolean isSpecialCharacter = false;

            for(int i = 0 ; i < newPassword.length() ; i++) {
                char ch = newPassword.charAt(i);
                if(ch >= 'A' && ch <= 'Z')
                    isUpperCase = true;
                else if(ch >= 'a' && ch <= 'z')
                    isLowerCase = true;
                else if(ch >= '0' && ch <= '9')
                    isDigit = true;
                else isSpecialCharacter = true;
            }

            if(isUpperCase && isLowerCase && isDigit && isSpecialCharacter)
                setPassword(newPassword);
        }
    }
}
