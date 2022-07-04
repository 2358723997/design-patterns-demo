package com.design.patterns.demo.designpattern.create.prototype;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.design.patterns.demo.designpattern.create.prototype.domain.SearchWork;

/**
 * Demo2类
 *
 * 在Demo1的基础上增加特殊要求：
 *
 * 任何时刻，系统A中的所有数据都必须是同一个版本的，要么都是版本a，要么都是版本b，
 * 不能有的是版本a，有的是版本b。那刚刚的更新方式就不能满足这个要求了。除此之外，
 * 我们还要求：在更新内存数据的时候，系统A不能处于不可用状态，也就是不能停机更新数据。
 *
 * 实现思路：
 *
 * 我们把正在使用的数据的版本定义为“服务版本”，当我们要更新内存中的数据的时候，我们并
 * 不是直接在服务版本（假设是版本a数据）上更新，而是重新创建另一个版本数据（假设是版本
 * b数据），等新的版本数据建好之后，再一次性地将服务版本从版本a切换到版本b。这样既保证
 * 了数据一直可用，又避免了中间状态的存在。
 *
 * @author wangjixue
 * @date 7/4/22 11:16 PM
 */
public class Demo2 {

    private ConcurrentHashMap<String, SearchWork> currentKeyWords = new ConcurrentHashMap<>();

    public void refresh() {
        ConcurrentHashMap<String, SearchWork> newKeyWords = new ConcurrentHashMap<>();

        // 从数据库中取出所有数据，放入到newKeyWords中
        List<SearchWork> toBeUpdatedSearchWorks = getSearchWorks();
        for (SearchWork searchWork : toBeUpdatedSearchWorks) {

            newKeyWords.put(searchWork.getKeyWord(), searchWork);

        }
        currentKeyWords = newKeyWords;

    }

    private List<SearchWork> getSearchWorks() {
        //TODO 从数据库获取所有数据
        return null;
    }

}
