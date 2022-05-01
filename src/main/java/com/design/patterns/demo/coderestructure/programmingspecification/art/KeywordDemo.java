package com.design.patterns.demo.coderestructure.programmingspecification.art;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * KeywordDemo类
 *
 * 编程规范之移除过深的嵌套层次
 * @author wangjixue
 * @date 5/1/22 2:13 PM
 */
public class KeywordDemo {
    //案例一：去掉多余的 if 或 else 语句
    public List<String> matchStrings(List<String> list, String word) {
        List<String> matchedList = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            for (String string : list) {
                if (string != null) {
                    if (string.contains(word)) { // 跟上面的if 合并在一起。
                        matchedList.add(string);
                    }
                }
            }
        }
        return matchedList;
    }

    //案例二：使用编程语言提供的 continue、break、return 关键字，提前退出嵌套。

    //重构前代码。
    public List<String> matchStrings_old2(List<String> list, String word) {
        List<String> matchedList = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            for (String string : list) {
                if (string != null && string.contains(word)) {
                    matchedList.add(string);
                }
            }
        }
        return matchedList;
    }

    //重构后代码。
    public List<String> matchStrings_new(List<String> list, String word) {
        List<String> matchedList = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            for (String string : list) {
                if (string == null || !(string.contains(word))) {
                    continue; // 使用continue提前退出循环。
                }
                matchedList.add(string);
            }
        }
        return matchedList;
    }

    //案例三：调整执行顺序来减少嵌套
    //重构前代码
    public List<String> matchStrings_old3(List<String> list, String word) {
        List<String> matchedList = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            for (String string : list) {
                if (string == null || !(string.contains(word))) {
                    continue; // 使用continue提前退出循环。
                }
                matchedList.add(string);
            }
        }
        return matchedList;
    }

    //重构后代码
    public List<String> matchStrings_new3(List<String> list, String word) {
        if (list == null || list.isEmpty()) { // 先判空
            return Collections.emptyList();
        }
        List<String> matchedList = new ArrayList<>();

        for (String string : list) {
            if (string == null || !(string.contains(word))) {
                continue; // 使用continue提前退出循环。
            }
            matchedList.add(string);
        }

        return matchedList;
    }

    //案例四：将部分代码封装成函数
    //重构前代码
    public List<String> appendSalts(List<String> passwords){
        if(passwords == null && passwords.isEmpty()){
            return Collections.emptyList();
        }

        List<String> passwordSalts = new ArrayList();
        for (String password : passwords) {
            if(password == null){
                continue;
            }
            if(password.length() < 8){
                //执行长度 < 8的逻辑
            }else{
                //执行长度 > 8的逻辑
            }
        }
        return passwords;
    }

    //重构后的代码
    public List<String> appendSalts_new(List<String> passwords){
        if(passwords == null && passwords.isEmpty()){
            return Collections.emptyList();
        }

        List<String> passwordSalts = new ArrayList();
        for (String password : passwords) {
            //将代码封装成函数
            passwordSalts.add(appendSalt(password));
        }
        return passwords;
    }

    private String appendSalt(String password) {
        if (password.length() < 8) {
            //执行长度 < 8的逻辑
        } else {
            //执行长度 > 8的逻辑
        }
        return password;
    }


}
