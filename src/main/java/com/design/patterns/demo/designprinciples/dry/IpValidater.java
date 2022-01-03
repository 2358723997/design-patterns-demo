package com.design.patterns.demo.designprinciples.dry;

import org.apache.commons.lang3.StringUtils;

/**
 * IpValidater类
 *
 * 问题：在同一个项目代码中有下面两个函数：isValidIp() 和 checkIfIpValid()。尽管两个函数的命名不同，
 * 实现逻辑不同，但功能是相同的，都是用来判定 IP 地址是否合法的，此时是否违反DRY原则？
 *
 * 答案：这个例子中，尽管两段代码的实现逻辑不重复，但语义重复，也就是功能重复，我们认为它违反了 DRY 原则。
 * 我们应该在项目中，统一一种实现思路，所有用到判断IP地址是否合法的地方，都统一调用同一个函数。假设我们不
 * 统一实现思路，那有些地方调用了isValidIp()函数，有些地方又调用了checkIfIpValid()函数，这就会导致
 * 代码看起来很奇怪，相当于给代码“埋坑”，给不熟悉这部分代码的同事增加了阅读的难度。同事有可能研究了半天，
 * 觉得功能是一样的，但又有点疑惑，觉得是不是有更高深的考量，才定义了两个功能类似的函数，最终发现居然是代码
 * 设计的问题。
 * 除此之外，如果哪天项目中IP地址是否合法的判定规则改变了，比如:255.255.255.255不再被判定为合法的了，
 * 相应地，我们对isValidIp()的实现逻辑做了相应的修改，但却忘记了修改checkIfIpValid()函数。又或者，
 * 我们压根就不知道还存在一个功能相同的checkIfIpValid()函数，这样就会导致有些代码仍然使用老的IP地址
 * 判断逻辑，导致出现一些莫名其妙的 bug。
 *
 * @author wangjixue
 * @date 1/2/22 6:59 PM
 */
public class IpValidater {

    public boolean isValidIpAddress(String ipAddress) {
        if (StringUtils.isBlank(ipAddress)) return false;
        String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
            + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
            + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
            + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        return ipAddress.matches(regex);
    }


    public boolean checkIfIpValid(String ipAddress) {
        if (StringUtils.isBlank(ipAddress)) return false;
        String[] ipUnits = StringUtils.split(ipAddress, '.');
        if (ipUnits.length != 4) {
            return false;
        }
        for (int i = 0; i < 4; ++i) {
            int ipUnitIntValue;
            try {
                ipUnitIntValue = Integer.parseInt(ipUnits[i]);
            } catch (NumberFormatException e) {
                return false;
            }
            if (ipUnitIntValue < 0 || ipUnitIntValue > 255) {
                return false;
            }
            if (i == 0 && ipUnitIntValue == 0) {
                return false;
            }
        }
        return true;
    }
}
