package com.design.patterns.demo.designpattern.structure.flyweight.game.v1;

import java.util.HashMap;
import java.util.Map;

import com.design.patterns.demo.designpattern.structure.flyweight.game.Color;

/**
 * ChessBoard 棋局类
 *
 * @author wangjixue
 * @date 8/8/22 10:50 PM
 */
public class ChessBoard {
    private Map<Integer,ChessPiece> chessPieceMap = new HashMap<>();

    public ChessBoard() {
        init();
    }

    private void init(){
        chessPieceMap.put(1,new ChessPiece(1,"車", Color.BLACK,0,0));
        chessPieceMap.put(2,new ChessPiece(2,"駡", Color.BLACK,0,1));
        // 其他棋子
    }

    public void move(int chessPieceId, int toPositionX, int toPositionY){
        //TODO 待实现
    }
}
