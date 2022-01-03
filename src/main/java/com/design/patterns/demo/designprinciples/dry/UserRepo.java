package com.design.patterns.demo.designprinciples.dry;

import com.design.patterns.demo.designprinciples.dry.validation.EmailValidation;
import com.design.patterns.demo.designprinciples.dry.validation.PasswordValidation;

/**
 * UserRepo类
 *
 * @author wangjixue
 * @date 1/3/22 10:04 PM
 */
public class UserRepo {

    public boolean checkIfUserExisted(String email, String password) throws InvalidException {
        if(!EmailValidation.validate(email)){
            throw new InvalidException("email","");
        }

        if(!PasswordValidation.validate(password)){
            throw new InvalidException("password","");
        }
        //TODO query db to check if email&password exists.
        return false;
    }

    public User getUserByEmail(String email) throws InvalidException {
        if(!EmailValidation.validate(email)){
            throw new InvalidException("email","");
        }
        //TODO query db to get user by email.
        return null;
    }
}
