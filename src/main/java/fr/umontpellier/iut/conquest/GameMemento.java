package fr.umontpellier.iut.conquest;

public class GameMemento {
    private final Board board;


    public GameMemento(Board board) {
        this.board = new Board(board);
    }

    public Board getBoard() {
        return board;
    }

}
