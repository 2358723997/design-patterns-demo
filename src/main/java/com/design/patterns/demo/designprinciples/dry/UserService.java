package com.design.patterns.demo.designprinciples.dry;


/**
 * UserService类
 *
 * 问题：代码执行重复是否违反DRY原则？
 * 答案：违反DRY原则，首先解释重复执行最明显的一个地方，就是在login()函数中，email的校验逻辑被执行了两次。
 * 一次是在调用checkIfUserExisted()函数的时候，另一次是调用getUserByEmail()函数的时候。这个问题解决
 * 起来比较简单，我们只需要将校验逻辑从UserRepo中移除，统一放到UserService中就可以了。
 *
 * 除此之外，代码中还有一处比较隐蔽的执行重复，login()函数并不需要调用checkIfUserExisted()函数，只需要
 * 调用一次getUserByEmail()函数，从数据库中获取到用户的email、password等信息，然后跟用户输入的email、
 * password信息做对比，依次判断是否登录成功。
 *
 * 实际上，这样的优化是很有必要的。因为checkIfUserExisted()函数和getUserByEmail()函数都需要查询数据库，
 * 而数据库这类的 I/O 操作是比较耗时的。我们在写代码的时候，应当尽量减少这类 I/O 操作。
 *
 *
 * @author wangjixue
 * @date 1/3/22 10:04 PM
 */
public class UserService {
    private UserRepo userRepo;//通过依赖注入或者IOC框架注入

    public User login(String email, String password) throws AuthenticationFailureException, InvalidException {
        boolean existed = userRepo.checkIfUserExisted(email, password);
        if(!existed){
        throw new AuthenticationFailureException();
        }
        User user = userRepo.getUserByEmail(email);
        return user;
    }
}
