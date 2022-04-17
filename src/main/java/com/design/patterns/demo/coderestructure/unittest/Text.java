package com.design.patterns.demo.coderestructure.unittest;

/**
 * Text类
 *
 * @author wangjixue
 * @date 4/17/22 5:34 PM
 */
public class Text {

    private String content;

    public Text(String content) {
        this.content = content;
    }

    /**
     * 将字符串转化成数字，忽略字符串中的首尾空格；
     * 如果字符串中包含除首尾空格之外的非数字字符，则返回null。
     *
     * @return
     */
    public Integer toNumber(){
        if(content == null || content.isEmpty()){
            return null;
        }
        return Integer.parseInt(content);
    }
}
