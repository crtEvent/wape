package crtevn.webapp.model;

import java.util.regex.Pattern;

public class Member {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{5,20}$");

    private String email;
    private String password;

    public Member(String email, String password) {
        if (!validEmail(email))
            throw new IllegalArgumentException("이메일이 유효하지 않습니다.");
        if (!validPassword(password))
            throw new IllegalArgumentException("비밀번호가 유효하지 않습니다.");

        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public boolean authenticate(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }

    private boolean validEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    private boolean validPassword(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }
}
