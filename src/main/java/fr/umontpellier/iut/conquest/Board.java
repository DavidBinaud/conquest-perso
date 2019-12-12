package fr.umontpellier.iut.conquest;

import java.util.ArrayList;
import java.util.List;

/**
 * Modélise un plateau.
 */
public class Board {
    /**
     * Tableau des pions.
     */
    private Pawn[][] field;

    /**
     * Constructeur.
     *
     * @param size : la taille du plateau.
     */
    public Board(int size) {
        field = new Pawn[size][size];
    }

    /**
     * Constructeur pour Test.
     *
     * @param field : plateau prédéfini.
     */
    public Board(Pawn[][] field) {
        this.field = field;
    }

    /**
     * Les méthodes suivantes sont utilisées pour les tests automatiques. Il ne faut pas les utiliser.
     */
    public Pawn[][] getField() {
        return field;
    }

    /**
     * Retourne la taille du plateau.
     */
    public int getSize() {
        return field.length;
    }

    /**
     * Affiche le plateau.
     */
    public String toString() {
        int size = field.length;
        StringBuilder b = new StringBuilder();
        for (int r = -1; r < size; r++) {
            for (int c = -1; c < size; c++) {
                if (r == -1 && c == -1) {
                    b.append("_");
                } else if (r == -1) {
                    b.append("_").append(c);
                } else if (c == -1) {
                    b.append(r).append("|");
                } else if (field[r][c] == null) {
                    b.append("_ ");
                } else if (field[r][c].getPlayer().getColor() == 1) {
                    b.append("X ");
                } else {
                    b.append("O ");
                }
            }
            b.append("\n");
        }
        b.append("---");
        return b.toString();
    }

    /**
     * Initialise le plateau avec les pions de départ.
     * Rappel :
     * - player1 commence le jeu avec un pion en haut à gauche (0,0) et un pion en bas à droite.
     * - player2 commence le jeu avec un pion en haut à droite et un pion en bas à gauche.
     */
    public void initField(Player player1, Player player2) {
        //player1
        field[0][0] = new Pawn(player1);
        field[getSize() - 1][getSize() - 1] = new Pawn(player1);

        //player2
        field[0][getSize() - 1] = new Pawn(player2);
        field[getSize() - 1][0] = new Pawn(player2);

    }

    /**
     * Vérifie si un coup est valide.
     * Rappel :
     * - Les coordonnées du coup doivent être dans le plateau.
     * - Le pion bougé doit appartenir au joueur.
     * - La case d'arrivée doit être libre.
     * - La distance entre la case d'arrivée est au plus 2.
     */
    public boolean isValid(Move move, Player player) {
        //Vérification des coordonées de la position de départ
        if (move.getRow1() >= getSize() || move.getRow1() < 0 || move.getColumn1() < 0 || move.getColumn1() >= getSize()) {
            return false;
        }
        //Vérification des coordonées de la position d'arrivée
        if (move.getRow2() >= getSize() || move.getRow2() < 0 || move.getColumn2() < 0 || move.getColumn2() >= getSize()) {
            return false;
        }
        //Vérification de l'existance du pion
        Pawn playedPawn = field[move.getRow1()][move.getColumn1()];
        if (playedPawn == null){
            return false;
        }
        //Vérification de l'appartenance du pion au joueur passé en paramètres
        if (!playedPawn.getPlayer().equals(player)){
            return false;
        }

        //Vérification que la case d'arrivée est libre
        if (field[move.getRow2()][move.getColumn2()] != null) {
            return false;
        }

        //Vérification de la distance entre les deux cases
        if (Math.abs(move.getRow2() - move.getRow1()) > 2 || Math.abs(move.getColumn2() - move.getColumn1()) > 2) {
            return false;
        }
        return true;
    }

    /**
     * Déplace un pion.
     *
     * @param move : un coup valide.
     *             Rappel :
     *             - Si le pion se déplace à distance 1 alors il se duplique pour remplir la case d'arrivée et la case de départ.
     *             - Si le pion se déplace à distance 2 alors il ne se duplique pas : la case de départ est maintenant vide et la case d'arrivée remplie.
     *             - Dans tous les cas, une fois que le pion est déplacé, tous les pions se trouvant dans les cases adjacentes à sa case d'arrivée prennent sa couleur.
     */
    public void movePawn(Move move) {
        if (Math.abs(move.getColumn2() - move.getColumn1()) <= 1 && Math.abs(move.getRow2() - move.getRow1()) <= 1){
            field[move.getRow2()][move.getColumn2()] = new Pawn(field[move.getRow1()][move.getColumn1()].getPlayer());
        }else{
            field[move.getRow2()][move.getColumn2()] = field[move.getRow1()][move.getColumn1()];
            field[move.getRow1()][move.getColumn1()] = null;
        }



    }

    /**
     * Retourne la liste de tous les coups valides de player.
     * S'il n'y a de coup valide, retourne une liste vide.
     */
    public List<Move> getValidMoves(Player player) {
        List<Move> validMoves = new ArrayList<>();

        return validMoves;
    }

    /**
     * Retourne la liste de tous les pions d'un joueur.
     */
    public int getNbPawns(Player player) {
        int count = 0;
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                if (field[i][j].getPlayer().equals(player)){
                    count++;
                }
            }
        }
        return count;
    }
}
