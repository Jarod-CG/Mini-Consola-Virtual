package Modelo;

import java.util.ArrayList;

public abstract class AbstractObservable implements IObservable {
    private ArrayList<IObserver> observers;

    public AbstractObservable() {
        this.observers = new ArrayList<IObserver>();
    }
    
    public void addObserver(IObserver observer){
        observers.add(observer);
    }
    public void removerObserver(IObserver observer){
        observers.remove(observer);
    }
    public void notifyAllObservers(String mensaje){
        for (IObserver observer : observers) {
            observer.notifyObserver(mensaje);
        }
    }
    
    
}