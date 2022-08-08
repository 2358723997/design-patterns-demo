package com.design.patterns.demo.designpattern.structure.flyweight.editor.v2;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.text.Font;

/**
 * Editor类
 *
 * 在文本编辑器中，我们每敲一个文字，都会调用 Editor 类中的 appendCharacter() 方法，
 * 创建一个新的 Character 对象，保存到 chars 数组中。如果一个文本文件中，有上万、十几万、
 * 几十万的文字，那我们就要在内存中存储这么多 Character 对象。
 *
 * 那有没有办法可以节省一点内存呢？
 *
 * 实际上，在一个文本文件中，用到的字体格式不会太多，毕竟不大可能有人把每个文字都设置成不同的格式。
 * 所以，对于字体格式，我们可以将它设计成享元，让不同的文字共享使用。按照这个设计思路，我们对上面的
 * 代码进行重构。重构后的代码如下所示：
 *
 *
 * @author wangjixue
 * @date 8/8/22 11:16 PM
 */
public class Editor {
    private List<Character> chars = new ArrayList<>();

    public void appendCharacter(char c, Font font, int size, int colorRGB) {
        Character character = new Character(c, CharacterStyleFactory.getStyle(font, size, colorRGB));
        chars.add(character);
    }
}
