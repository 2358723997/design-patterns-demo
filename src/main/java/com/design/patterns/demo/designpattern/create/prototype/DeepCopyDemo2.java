package com.design.patterns.demo.designpattern.create.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.design.patterns.demo.designpattern.create.prototype.domain.SearchWork;

/**
 * DeepCopyDemo1类
 *
 * 在浅拷贝Demo中我们通过调用HashMap上的clone()浅拷贝方法来实现原型模式。
 * 当我们通过newKeywords更新SearchWord对象的时候（比如，更新“设计模式”
 * 这个搜索关键词的访问次数），newKeywords和currentKeywords因为指向相同
 * 的一组SearchWord对象，就会导致currentKeywords中指向的SearchWord，
 * 有的是老版本的，有的是新版本的，就没法满足我们之前的需求：currentKeywords
 * 中的数据在任何时刻都是同一个版本的，不存在介于老版本与新版本之间的中间状态。
 *
 * 将浅拷贝替换为深拷贝来解决。具体来说：newKeywords不仅仅复制currentKeywords的索引，
 * 还把SearchWord对象也复制一份出来，这样newKeywords和currentKeywords就指向不同的
 * SearchWord对象，也就不存在更新newKeywords的数据会导致currentKeywords的数据也被
 * 更新的问题了。
 *
 * 第二种方法：先将对象序列化，然后再反序列化成新的对象。具体的示例代码如下所示：
 *
 *
 *
 *
 * @author wangjixue
 * @date 7/4/22 11:16 PM
 */
public class DeepCopyDemo2 {

    private HashMap<String,SearchWork> currentKeyWords = new HashMap<>();

    private long lastUpdateTime = 0l;

    public void refresh() throws IOException, ClassNotFoundException {
        //原型模式 -- 深拷贝

        HashMap<String,SearchWork> newKeyWords = (HashMap<String,SearchWork>)deepCopy(currentKeyWords);

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

    private Object deepCopy(Object object) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(outputStream);
        oo.writeObject(object);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(inputStream);
        return oi.readObject();
    }

    private List<SearchWork> getSearchWorks(long lastUpdateTime) {
        //TODO 从数据库获取 > lastUpdateTime的数据
        return null;
    }

}
