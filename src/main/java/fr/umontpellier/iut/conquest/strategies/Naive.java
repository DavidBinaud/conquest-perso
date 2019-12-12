package fr.umontpellier.iut.conquest.strategies;

import fr.umontpellier.iut.conquest.Board;
import fr.umontpellier.iut.conquest.Move;
import fr.umontpellier.iut.conquest.Player;

public class Naive implements Strategy {

    @Override
    public Move getMove(Board board, Player player) {
        return board.getValidMoves(player).get(0);
    }
}
