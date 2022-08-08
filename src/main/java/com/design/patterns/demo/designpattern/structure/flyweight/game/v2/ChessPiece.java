package com.design.patterns.demo.designpattern.structure.flyweight.game.v2;

import com.design.patterns.demo.designpattern.structure.flyweight.game.Color;

/**
 * ChessPiece 棋子类
 *
 * 需求：假设我们在开发一个棋牌游戏（比如象棋）。一个游戏厅中有成千上万个“房间”，每个房间对应一个棋局。
 * 棋局要保存每个棋子的数据，比如：棋子类型（将、相、士、炮等）、棋子颜色（红方、黑方）、棋子在棋局中的
 * 位置。
 *
 * 利用这些数据，我们就能显示一个完整的棋盘给玩家。具体的代码如下所示。其中，ChessPiece 类表示棋子，
 * ChessBoard 类表示一个棋局，里面保存了象棋中 30 个棋子的信息。
 *
 * @author wangjixue
 * @date 8/8/22 10:44 PM
 */
public class ChessPiece {
    private ChessPieceUnit chessPieceUnit;
    private int positionX;
    private int positionY;

    public ChessPiece(ChessPieceUnit chessPieceUnit, int positionX, int positionY) {
        this.chessPieceUnit = chessPieceUnit;
        this.positionX = positionX;
        this.positionY = positionY;
    }
}
