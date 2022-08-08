package com.design.patterns.demo.designpattern.structure.flyweight.editor.v1;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.text.Font;

/**
 * Editor类
 *
 * @author wangjixue
 * @date 8/8/22 11:16 PM
 */
public class Editor {
    private List<Character> chars = new ArrayList<>();

    public void appendCharacter(char c, Font font, int size, int colorRGB) {
        Character character = new Character(c, font, size, colorRGB);
        chars.add(character);
    }
}
