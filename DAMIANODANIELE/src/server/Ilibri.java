package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Ilibri extends Remote {
	Libro inserisci(Libro l) throws RemoteException;
	Libro aggiorna(Libro l) throws RemoteException;
	void stampaTuttiLibri()throws RemoteException;
	void stampaLibroAutore(String autore) throws RemoteException;

}
