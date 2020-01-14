package fr.umontpellier.iut.conquest.strategies;

import fr.umontpellier.iut.conquest.Board;
import fr.umontpellier.iut.conquest.Move;
import fr.umontpellier.iut.conquest.Player;

public class Minmax implements Strategy {

    private int level;

    public Minmax(int level) {
        this.level = level;
    }

    @Override
    public Move getMove(Board board, Player player) {

        Move bestMove = null;
        int bestValue = Integer.MIN_VALUE;

        for (Move move : board.getValidMoves(player)) {
            int value = minMax(level, player, board, true,Integer.MIN_VALUE,Integer.MAX_VALUE);

            if (bestMove == null) {
                bestValue = value;
                bestMove = move;
            } else if (value > bestValue) {
                bestValue = value;
                bestMove = move;
            }
        }
        return bestMove;
    }

    public int minMax(int lvl, Player player, Board board,boolean maximizingPlayer,int alpha,int beta) {
        Player otherPlayer = player.getGame().getOtherPlayer(player);

        if (lvl == 0) {
            return board.getNbPawns(player) - board.getNbPawns(otherPlayer);
        }


        if (maximizingPlayer) {
            int score = Integer.MIN_VALUE;
            for (Move move : board.getValidMoves(player)) {
                Board board1 = new Board(board);
                board1.movePawn(move);
                score = Math.max(score, minMax(lvl - 1, otherPlayer, board1, false,alpha,beta));
                if (alpha >= score){
                    return score;
                }
                alpha = Math.max(alpha,score);
            }
            return score;
        }
        else{
            int score = Integer.MAX_VALUE;
            for (Move move : board.getValidMoves(player)) {
                Board board1 = new Board(board);
                board1.movePawn(move);
                score = Math.min(score,minMax(lvl - 1, otherPlayer, board1,true,alpha,beta));
                if (score >= beta){
                    return score;
                }
                beta = Math.min(beta,score);
            }
            return score;
        }

    }
}
