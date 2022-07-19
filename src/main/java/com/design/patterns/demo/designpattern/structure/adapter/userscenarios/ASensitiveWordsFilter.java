package com.design.patterns.demo.designpattern.structure.adapter.userscenarios;

import java.util.ArrayList;
import java.util.List;

/**
 * ASensitiveWordsFilter类
 *
 * 使用场景二：统一多个类的接口设计
 *
 * 某个功能的实现依赖多个外部系统（或者说类）。通过适配器模式，将它们的接口适配为统一的接口定义，然后我们就可以使用多态的特性来复用代码逻辑。
 *
 * @author wangjixue
 * @date 7/17/22 5:53 PM
 */
//A敏感词过滤系统提供的接口
public class ASensitiveWordsFilter {
    public String filterSexyWords(String text) {
        return null;
    }

    public String filterPoliticalWords(String text) {
        return null;
    }
}

//B敏感词过滤系统提供的接口
class BSensitiveWordsFilter {
    public String filter(String text) {
        return null;
    }
}

//C敏感词过滤系统提供的接口
class CSensitiveWordsFilter {
    public String filter(String text, String mask) {
        return null;
    }
}

// 未使用适配器模式之前的代码：代码的可测试性、扩展性不好
class RiskManagement {
    private ASensitiveWordsFilter aFilter;
    private BSensitiveWordsFilter bFilter;
    private CSensitiveWordsFilter cFilter;

    public RiskManagement(ASensitiveWordsFilter aFilter, BSensitiveWordsFilter bFilter, CSensitiveWordsFilter cFilter) {
        this.aFilter = aFilter;
        this.bFilter = bFilter;
        this.cFilter = cFilter;
    }

    public String filterSensitiveWords(String text){
        String maskedText = aFilter.filterSexyWords(text);
        aFilter.filterPoliticalWords(maskedText);
        bFilter.filter(text);
        cFilter.filter(maskedText,"***");
        return maskedText;
    }

}
//使用适配器模式进行改造
interface ISensitiveWordsFilter{ //统一接口定义
    String filter(String text);
}

class ASensitiveWordsFilterAdaptor implements ISensitiveWordsFilter{
    private ASensitiveWordsFilter filter;

    public ASensitiveWordsFilterAdaptor(ASensitiveWordsFilter filter) {
        this.filter = filter;
    }

    @Override
    public String filter(String text) {
        String maskedText = filter.filterPoliticalWords(text);
        maskedText = filter.filterSexyWords(maskedText);
        return maskedText;
    }
}
//TODO 补充BSensitiveWordsFilterAdaptor、CSensitiveWordsFilterAdaptor

class RiskManagementA{
    private List<ISensitiveWordsFilter> filterList = new ArrayList<>();

    public void addISensitiveWordsFilter(ISensitiveWordsFilter filter){
        filterList.add(filter);
    }

    //TODO 参数设计的比较巧妙
    public String filterSensitiveWords(String text){
        String markedText = text;
        for (ISensitiveWordsFilter filter : filterList) {
            markedText = filter.filter(markedText);
        }
        return markedText;
    }
}

