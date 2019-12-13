package fr.umontpellier.iut.conquest;

import java.util.ArrayDeque;
import java.util.Deque;

public class GameCaretaker {

    final Deque<GameMemento> mementos = new ArrayDeque<>();

    public GameMemento getMemento(){
        GameMemento gameMemento = mementos.pop();
        return gameMemento;
    }

    public void addMemento(GameMemento memento){
        mementos.push(memento);
    }

    public boolean hasMemento(){
        return mementos.size() > 0;
    }
}
