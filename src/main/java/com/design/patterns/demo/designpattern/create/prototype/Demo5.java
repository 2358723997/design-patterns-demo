package com.design.patterns.demo.designpattern.create.prototype;

import java.util.HashMap;
import java.util.List;

import com.design.patterns.demo.designpattern.create.prototype.domain.SearchWork;

/**
 * Demo5类
 *
 * 不管采用哪种深拷贝，都要比浅拷贝耗时、耗内存空间。
 * 针对我们这个应用场景，有没有更快、更省内存的实现方式呢？
 *
 * 我们可以先采用浅拷贝的方式创建newKeywords。对于需要更新的SearchWord对象，
 * 我们再使用深度拷贝的方式创建一份新的对象，替换newKeywords中的老对象。毕竟需
 * 要更新的数据是很少的。这种方式即利用了浅拷贝节省时间、空间的优点，又能保证
 * currentKeywords中的中数据都是老版本的数据。
 *
 * @author wangjixue
 * @date 7/4/22 11:16 PM
 */
public class Demo5 {

    private HashMap<String, SearchWork> currentKeyWords = new HashMap<>();

    private long lastUpdateTime = 0l;

    public void refresh() {
        //原型模式 -- 浅拷贝，拷贝已有对象的数据，更新少量差值
        HashMap<String, SearchWork> newKeyWords = (HashMap<String, SearchWork>) currentKeyWords.clone();
        // 从数据库中取出更新时间>lastUpdateTime的数据，放入到newKeyWords中
        List<SearchWork> toBeUpdatedSearchWorks = getSearchWorks(lastUpdateTime);
        long maxNewUpdatedTime = lastUpdateTime;
        for (SearchWork searchWork : toBeUpdatedSearchWorks) {

            if (searchWork.getLastUpdateTime() > maxNewUpdatedTime) {
                maxNewUpdatedTime = searchWork.getLastUpdateTime();
            }

            if (newKeyWords.containsKey(searchWork.getKeyWord())) {
                newKeyWords.remove(searchWork.getKeyWord());
            }
            newKeyWords.put(searchWork.getKeyWord(), searchWork);

        }

        lastUpdateTime = maxNewUpdatedTime;
        currentKeyWords = newKeyWords;

    }

    private List<SearchWork> getSearchWorks(long lastUpdateTime) {
        //TODO 从数据库获取 > lastUpdateTime的数据
        return null;
    }

}
