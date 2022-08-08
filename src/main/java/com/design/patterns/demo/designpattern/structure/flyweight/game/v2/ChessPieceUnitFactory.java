package com.design.patterns.demo.designpattern.structure.flyweight.game.v2;

import java.util.HashMap;
import java.util.Map;

import com.design.patterns.demo.designpattern.structure.flyweight.game.Color;

/**
 * ChessPieceUnitFactory类
 *
 * @author wangjixue
 * @date 8/8/22 11:03 PM
 */
public class ChessPieceUnitFactory {
    private static final Map<Integer, ChessPieceUnit> CHESS_PIECE_UNIT_MAP = new HashMap<>();

    static {
        CHESS_PIECE_UNIT_MAP.put(1,new ChessPieceUnit(1,"車", Color.BLACK));
        CHESS_PIECE_UNIT_MAP.put(2,new ChessPieceUnit(2,"駡", Color.BLACK));
        // 其他棋子
    }

    public static ChessPieceUnit getChessPieceUnit(int chessPieceId){
        return CHESS_PIECE_UNIT_MAP.get(chessPieceId);
    }
}
