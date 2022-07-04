package com.design.patterns.demo.designpattern.create.prototype;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.design.patterns.demo.designpattern.create.prototype.domain.SearchWork;

/**
 * Demo1类
 *
 * 需求：假设数据库中存储了大约 10 万条“搜索关键词”信息，每条信息包含关键词、关键词被搜索的次数、
 * 信息最近被更新的时间等。系统 A 在启动的时候会加载这份数据到内存中，用于处理某些其他的业务需求。
 *
 * 为了方便快速地查找某个关键词对应的信息，我们给关键词建立一个散列表索引。如果你熟悉的是 Java
 * 语言，可以直接使用语言中提供的 HashMap 容器来实现。其中，HashMap 的 key 为搜索关键词，
 * value 为关键词详细信息（比如搜索次数）。我们只需要将数据从数据库中读取出来，放入 HashMap
 *就可以了。不过，我们还有另外一个系统 B，专门用来分析搜索日志，定期（比如间隔 10 分钟）批量地
 * 更新数据库中的数据，并且标记为新的数据版本。比如，在下面的示例图中，我们对 v2 版本的数据进行
 * 更新，得到 v3 版本的数据。这里我们假设只有更新和新添关键词，没有删除关键词的行为。
 *
 * 实现思路：
 * 我们只需要在系统 A 中，记录当前数据的版本Va对应的更新时间Ta，从数据库中捞出更新时间大于Ta的所有
 * 搜索关键词，也就是找出Va版本与最新版本数据的“差集”，然后针对差集中的每个关键词进行处理。如果它已
 * 经在散列表中存在了，我们就更新相应的搜索次数、更新时间等信息；如果它在散列表中不存在，我们就将它插入到散列表中。
 *
 * @author wangjixue
 * @date 7/4/22 11:16 PM
 */
public class Demo1 {

    private ConcurrentHashMap<String,SearchWork> currentKeyWords = new ConcurrentHashMap<>();

    private long lastUpdateTime = 0l;

    public void refresh(){
        // 从数据库中取出更新时间>lastUpdateTime的数据，放入到currentKeywords中
       List<SearchWork> toBeUpdatedSearchWorks = getSearchWorks(lastUpdateTime);
        long maxNewUpdatedTime = lastUpdateTime;
        for (SearchWork searchWork : toBeUpdatedSearchWorks) {

            if(searchWork.getLastUpdateTime() > maxNewUpdatedTime){
                maxNewUpdatedTime = searchWork.getLastUpdateTime();
            }

            if(currentKeyWords.containsKey(searchWork.getKeyWord())){
                currentKeyWords.replace(searchWork.getKeyWord(),searchWork);
            }else{
                currentKeyWords.put(searchWork.getKeyWord(),searchWork);
            }
        }

        lastUpdateTime = maxNewUpdatedTime;

    }

    private List<SearchWork> getSearchWorks(long lastUpdateTime) {
        //TODO 从数据库获取 > lastUpdateTime的数据
        return null;
    }

}
