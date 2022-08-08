package com.design.patterns.demo.designpattern.structure.flyweight.editor.v2;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.text.Font;

/**
 * CharacterStyleFactory类
 *
 * @author wangjixue
 * @date 8/8/22 11:24 PM
 */
public class CharacterStyleFactory {
    private static final List<CharacterStyle> CHARACTER_STYLES = new ArrayList<>();

    public static CharacterStyle getStyle(Font font, int size, int colorRGB){
        CharacterStyle characterStyle = new CharacterStyle(font, size, colorRGB);
        for (CharacterStyle style : CHARACTER_STYLES) {
            if(characterStyle.equals(style)){
                return style;
            }
        }
        CHARACTER_STYLES.add(characterStyle);
        return characterStyle;
    }
}
