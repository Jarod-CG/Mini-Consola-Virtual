package Modelo;

import java.util.ArrayList;

public abstract class AbstractObservable implements IObservable {
    public ArrayList<IObserver> observers;

    public AbstractObservable() {
        this.observers = new ArrayList<IObserver>();
    }
    
    public abstract void addObserver(IObserver observer);
    
    public void removerObserver(IObserver observer){
        observers.remove(observer);
    }
    public void notifyAllObservers(String mensaje){
        for (IObserver observer : observers) {
            observer.notifyObserver(mensaje);
        }
    }
    
    
}