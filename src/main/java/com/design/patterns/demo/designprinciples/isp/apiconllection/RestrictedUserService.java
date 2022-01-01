package com.design.patterns.demo.designprinciples.isp.apiconllection;

/**
 * RestrictedUserService接口
 *
 * @author wangjixue
 * @date 12/26/21 4:37 PM
 */
public interface RestrictedUserService {

    boolean deleteUserByCellphone(String cellphone);

    boolean deleteUserById(long id);
}
