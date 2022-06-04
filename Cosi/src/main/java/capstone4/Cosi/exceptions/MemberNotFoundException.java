package capstone4.Cosi.exceptions;

import org.springframework.security.core.AuthenticationException;

public class MemberNotFoundException extends AuthenticationException{
    public MemberNotFoundException(String msg){
        super(msg);
    }
}
