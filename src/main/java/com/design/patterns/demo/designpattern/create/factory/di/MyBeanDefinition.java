package com.design.patterns.demo.designpattern.create.factory.di;

import java.util.List;

import lombok.Data;

/**
 * MyBeanDefinition类
 *
 * @author wangjixue
 * @date 6/28/22 11:34 PM
 */
@Data
public class MyBeanDefinition {
    private String id;
    private boolean isSingleton;
    private String className;
    private List<ConstructorArg> constructorArgs;
    private boolean lazyInit;

    public class ConstructorArg {
        private boolean isRef;
        private Class type;
        private Object arg;

        public boolean getIsRef() {
            return isRef;
        }

        public void setIsRef(boolean isRef) {
            this.isRef = isRef;
        }

        public Class getType() {
            return type;
        }

        public void setType(Class type) {
            this.type = type;
        }

        public Object getArg() {
            return arg;
        }

        public void setArg(Object arg) {
            this.arg = arg;
        }
    }
}
