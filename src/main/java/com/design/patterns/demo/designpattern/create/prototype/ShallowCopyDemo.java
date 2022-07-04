package com.design.patterns.demo.designpattern.create.prototype;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.design.patterns.demo.designpattern.create.prototype.domain.SearchWork;

/**
 * ShallowCopyDemo类
 *
 * Demo2中newKeywords构建的成本比较高。我们需要将这10万条数据从数据库中读出，
 * 然后计算哈希值，构建newKeywords。这个过程显然是比较耗时。为了提高效率，原型
 * 模式就派上用场了。
 *
 * 我们拷贝currentKeywords数据到newKeywords中，然后从数据库中只捞出新增或者
 * 有更新的关键词，更新到newKeywords中。而相对于10万条数据来说，每次新增或者更
 * 新的关键词个数是比较少的，所以，这种策略大大提高了数据更新的效率。
 *
 *
 * @author wangjixue
 * @date 7/4/22 11:16 PM
 */
public class ShallowCopyDemo {

    private HashMap<String,SearchWork> currentKeyWords = new HashMap<>();

    private long lastUpdateTime = 0l;

    public void refresh(){
        //原型模式 -- 浅拷贝，拷贝已有对象的数据，更新少量差值
        HashMap<String,SearchWork> newKeyWords = (HashMap<String,SearchWork>) currentKeyWords.clone();
        // 从数据库中取出更新时间>lastUpdateTime的数据，放入到currentKeywords中
       List<SearchWork> toBeUpdatedSearchWorks = getSearchWorks(lastUpdateTime);
        long maxNewUpdatedTime = lastUpdateTime;
        for (SearchWork searchWork : toBeUpdatedSearchWorks) {

            if(searchWork.getLastUpdateTime() > maxNewUpdatedTime){
                maxNewUpdatedTime = searchWork.getLastUpdateTime();
            }

            if(newKeyWords.containsKey(searchWork.getKeyWord())){
                SearchWork oldSearchWork = newKeyWords.get(searchWork.getKeyWord());
                oldSearchWork.setCount(searchWork.getCount());
                oldSearchWork.setLastUpdateTime(searchWork.getLastUpdateTime());
            }else{
                newKeyWords.put(searchWork.getKeyWord(),searchWork);
            }
        }

        lastUpdateTime = maxNewUpdatedTime;
        currentKeyWords = newKeyWords;

    }

    private List<SearchWork> getSearchWorks(long lastUpdateTime) {
        //TODO 从数据库获取 > lastUpdateTime的数据
        return null;
    }

}
