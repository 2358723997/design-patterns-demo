package com.design.patterns.demo.designpattern.structure.flyweight.editor.v2;

import java.util.Objects;

import javafx.scene.text.Font;

/**
 * CharacterStyle类
 *
 * @author wangjixue
 * @date 8/8/22 11:21 PM
 */
public class CharacterStyle {
    private Font font;
    private int size;
    private int colorRGB;

    public CharacterStyle(Font font, int size, int colorRGB) {
        this.font = font;
        this.size = size;
        this.colorRGB = colorRGB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterStyle that = (CharacterStyle) o;
        return size == that.size &&
            colorRGB == that.colorRGB &&
            Objects.equals(font, that.font);
    }
}
