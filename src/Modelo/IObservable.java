package Modelo;

public interface IObservable {
    public void addObserver(IObserver observer);
    public void removerObserver(IObserver observer);
    public void notifyAllObservers(String mensaje);
}
