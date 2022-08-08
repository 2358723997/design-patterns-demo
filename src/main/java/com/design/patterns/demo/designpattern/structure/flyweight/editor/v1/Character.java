package com.design.patterns.demo.designpattern.structure.flyweight.editor.v1;

import javafx.scene.text.Font;

/**
 * Character 文字类
 *
 * 需求背景：我们假设这个文本编辑器只实现了文字编辑功能，不包含图片、表格等复杂的编辑功能。
 * 对于简化之后的文本编辑器，我们要在内存中表示一个文本文件，只需要记录文字和格式两部分信息
 * 就可以了，其中，格式又包括文字的字体、大小、颜色等信息。
 *
 * 尽管在实际的文档编写中，我们一般都是按照文本类型（标题、正文）来设置文字的格式，标题是一种
 * 格式，正文是另一种格式等等。但是，从理论上讲，我们可以给文本文件中的每个文字都设置不同的格式。
 *
 * 为了实现如此灵活的格式设置，并且代码实现又不过于太复杂，我们把每个文字都当作一个独立的对象来看
 * 待，并且在其中包含它的格式信息。
 *
 *
 * @author wangjixue
 * @date 8/8/22 11:12 PM
 */
public class Character {
    private char c;

    private Font font;
    private int size;
    private int colorRGB;

    public Character(char c, Font font, int size, int colorRGB) {
        this.c = c;
        this.font = font;
        this.size = size;
        this.colorRGB = colorRGB;
    }
}
