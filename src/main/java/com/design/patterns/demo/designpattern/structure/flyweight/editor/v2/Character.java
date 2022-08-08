package com.design.patterns.demo.designpattern.structure.flyweight.editor.v2;


/**
 * Character 文字类
 *
 * 如何利用享元模式来优化文本编辑器的内存占用？
 *
 * @author wangjixue
 * @date 8/8/22 11:12 PM
 */
public class Character {
    private char c;

    private CharacterStyle style;

    public Character(char c, CharacterStyle style) {
        this.c = c;
        this.style = style;
    }
}
