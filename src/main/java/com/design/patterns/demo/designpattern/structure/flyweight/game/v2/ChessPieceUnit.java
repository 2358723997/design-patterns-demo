package com.design.patterns.demo.designpattern.structure.flyweight.game.v2;

import com.design.patterns.demo.designpattern.structure.flyweight.game.Color;

/**
 * ChessPieceUnit 享元类
 *
 * 为了记录每个房间当前的棋局情况，我们需要给每个房间都创建一个 ChessBoard 棋局对象。因为游戏大厅中有
 * 成千上万的房间（实际上，百万人同时在线的游戏大厅也有很多），那保存这么多棋局对象就会消耗大量的内存。
 *
 * 有没有什么办法来节省内存呢？
 *
 * 这个时候，享元模式就可以派上用场了。
 * 像刚刚的实现方式，在内存中会有大量的相似对象。这些相似对象的 id、text、color 都是相同的，
 * 唯独 positionX、positionY 不同。实际上，我们可以将棋子的 id、text、color 属性拆分出
 * 来，设计成独立的类，并且作为享元供多个棋盘复用。这样，棋盘只需要记录每个棋子的位置信息就可以了。
 *
 * @author wangjixue
 * @date 8/8/22 10:56 PM
 */
public class ChessPieceUnit {
    private int id;
    private String text;
    private Color color;

    public ChessPieceUnit(int id, String text, Color color) {
        this.id = id;
        this.text = text;
        this.color = color;
    }
}
