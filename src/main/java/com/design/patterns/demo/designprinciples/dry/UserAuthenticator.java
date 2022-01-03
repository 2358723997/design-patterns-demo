package com.design.patterns.demo.designprinciples.dry;

import org.apache.commons.lang3.StringUtils;

/**
 * UserAuthenticator类
 *
 * 问题：isValidUserName() 和 isValidPassword() 两个函数是否违反了DRY原则？
 *
 * 答案：尽管两个函数的从代码实现逻辑上看起来是重复的，但是从语义上并不重复。所谓“语义不重复”指的是：
 * 从功能上来看，这两个函数干的是完全不重复的两件事情，一个是校验用户名，另一个是校验密码。尽管在目前
 * 的设计中，两个校验逻辑是完全一样的，但如果将两个函数的合并，那就会存在潜在的问题。在未来的某一天，如
 * 果我们修改了密码的校验逻辑，比如，允许密码包含大写字符，允许密码的长度为 8 到 64 个字符，那这个时候
 * ，isValidUserName() 和 isValidPassword() 的实现逻辑就会不相同。我们就要把合并后的函数，重新拆
 * 成合并前的那两个函数。
 *
 * 尽管代码的实现逻辑是相同的，但语义不同，我们判定它并不违反 DRY 原则。对于包含重复代码的问题，我们可以
 * 通过抽象成更细粒度函数的方式来解决。比如将校验只包含 a~z、0~9、dot 的逻辑封装成
 * boolean onlyContains(String str, String charlist); 函数。
 *
 * @author wangjixue
 * @date 1/3/22 9:42 PM
 */
public class UserAuthenticator {
    public void authenticate(String userName, String password) throws InvalidException {
        if (!isValidUsername(userName)) {
            throw new InvalidException("userName", "User name" + userName + "is not valid.");
        }

        if (!isValidPassword(password)) {
            throw new InvalidException("password", "Password" + password + "is not valid.");
        }
    }

    private boolean isValidPassword(String password) {
        // check not null, not empty
        if (StringUtils.isBlank(password)) {
            return false;
        }
        // check length: 4~64
        int length = password.length();
        if (length < 4 || length > 64) {
            return false;
        }
        // contains only lowcase characters
        if (!StringUtils.isAllLowerCase(password)) {
            return false;
        }
        // contains only a~z,0~9,dot
        for (int i = 0; i < length; i++) {
            char c = password.charAt(i);
            if (!(c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '.') {
                return false;
            }
        }

        return true;
    }

    private boolean isValidUsername(String userName) {
        // check not null, not empty
        if (StringUtils.isBlank(userName)) {
            return false;
        }
        // check length: 4~64
        int length = userName.length();
        if (length < 4 || length > 64) {
            return false;
        }
        // contains only lowcase characters
        if (!StringUtils.isAllLowerCase(userName)) {
            return false;
        }
        // contains only a~z,0~9,dot
        for (int i = 0; i < length; i++) {
            char c = userName.charAt(i);
            if (!(c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '.') {
                return false;
            }
        }

        return true;
    }
}
