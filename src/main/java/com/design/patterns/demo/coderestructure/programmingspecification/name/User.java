package com.design.patterns.demo.coderestructure.programmingspecification.name;

import org.apache.commons.lang3.StringUtils;

/**
 * User类
 *
 * @author wangjixue
 * @date 4/19/22 10:24 PM
 */
public class User {
    private String userName;
    private String name;//借助User对象上下文简化命名

    private String password;

    public void uploadUserAvatarImageToAliyun(String userAvatarImageUri);
    public void uploadUserAvatarImageToAliyun(String imageUri); // 借助函数上下文简化命名

    /**
     * 密码校验
     *
     * 对于逻辑比较复杂的代码或者比较长的函数，如果不好提炼、不好拆分成小的函数调用，
     * 那我们可以借助总结性的注释来让代码结构更清晰、更有条理。
     *
     * @param password
     * @return
     */
    public boolean isValidPasword(String password){
        // check if password is null or empty
        if(StringUtils.isBlank(password)){
            return false;
        }

        // check if the length of password is between 4 and 64
        int length = password.length();
        if(length < 4 || length > 64){
            return false;
        }

        // check if password contains only a~z,0~9,A~Z
        for (int i = 0; i < length; i++) {
            char c = password.charAt(i);
            if(!((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')){
                return false;
            }
        }

        return true;
    }
}
