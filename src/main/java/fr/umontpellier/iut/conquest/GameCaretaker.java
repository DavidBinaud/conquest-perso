package fr.umontpellier.iut.conquest;

import java.util.ArrayDeque;
import java.util.Deque;

class GameCaretaker {

    final Deque<GameMemento> mementos = new ArrayDeque<>();

    public GameMemento getMemento(){
        return mementos.pop();
    }

    public void addMemento(GameMemento memento){
        mementos.push(memento);
    }

    public boolean hasMemento(){
        return mementos.size() > 0;
    }
}
