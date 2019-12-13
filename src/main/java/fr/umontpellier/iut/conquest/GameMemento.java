package fr.umontpellier.iut.conquest;

public class GameMemento {
    private Board board;


    public GameMemento(Board board) {
        this.board = new Board(board.getSize());
        for (int i = 0; i < this.board.getSize(); i++) {
            for (int j = 0; j < this.board.getSize(); j++) {
                if (board.getField()[i][j] != null) {
                    this.board.getField()[i][j] = new Pawn(board.getField()[i][j].getPlayer());
                }
            }
        }
    }

    public Board getBoard() {
        return board;
    }

}
