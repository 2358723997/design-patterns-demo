package com.design.patterns.demo.coderestructure.demo.performancecounters.v3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.EmailSender;
import com.design.patterns.demo.coderestructure.demo.performancecounters.v2.RequestStat;

/**
 * EmailViewer类
 *
 * @author wangjixue
 * @date 6/14/22 11:41 PM
 */
public class EmailViewer implements StatViewer{
    private EmailSender sender;
    private List<String> toAddressList = new ArrayList<>();

    public EmailViewer() {
        sender = new EmailSender();
    }

    public EmailViewer(List<String> toAddressList) {
        this.toAddressList = toAddressList;
        sender = new EmailSender();
    }

    public EmailViewer(EmailSender sender) {
        this.sender = sender;
    }

    public void addToAddress(String toAddress){
        toAddressList.add(toAddress);
    }

    @Override
    public void output(Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMills) {
        // TODO 第3个代码逻辑：将统计数据格式化为html格式，并且发送邮件；
    }
}
