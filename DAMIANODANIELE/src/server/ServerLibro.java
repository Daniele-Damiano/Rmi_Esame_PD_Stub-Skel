package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.Vector;

public class ServerLibro extends UnicastRemoteObject implements Ilibri {

	private static final long serialVersionUID = 2L;
	private static Vector<Libro>memLibro;

	//Dichiarato Serverlibro con modificatore static
	private static ServerLibro sl;

	protected ServerLibro() throws RemoteException {
		memLibro = new Vector<>();
	}



	public synchronized Libro inserisci(Libro l) throws RemoteException {
		memLibro.add(l);
		return l;
	}

	//Modifica al metodo Aggiorna cambiata iterazione l con  memLibro nel ciclo for
	public synchronized Libro aggiorna(Libro l) throws RemoteException {
		for(Libro r: memLibro){
			if(r.getPrezzo() == l.getPrezzo() && r.getTitolo().equals(l.getTitolo())){
				r.setTitolo(l.getTitolo());
				r.setAutore(l.getAutore());
				System.out.println("Modifica ok");
				return l;
			}
			if(r.getPrezzo() != l.getPrezzo() && r.getTitolo() != l.getTitolo()){
				System.out.println("Modifica Non Ok");
				return l;
			}
		}
		return null;
	}


	public synchronized void stampaTuttiLibri() throws RemoteException {
		for(int i = 0; i<memLibro.size(); i++){
			System.out.println(memLibro.get(i));
		}

	}


	public synchronized void stampaLibroAutore(String autore) throws RemoteException {
		for(Libro l: memLibro){
			if(l.getAutore().equals(autore))
				System.out.println(l.getTitolo()+ l.getAutore()+ l.getPrezzo());
		}

	}

	public static void main(String[] args) {
		System.setSecurityManager(new SecurityManager());
		Scanner sc = new Scanner(System.in);
		try{
			sl = new ServerLibro();
			Naming.rebind("server", sl);
		}catch(RemoteException |MalformedURLException e){
			System.out.println(e.getMessage());
		}


		//Modifica nella condizione del while al posto di 1 messo true
		//Gestita eccezione RemoteException ai metodi stampaLibroAutore() e StampaTuttiiLibri()
		while(true){
			System.out.println("1 Per Stampa Tutti i Libri");
			System.out.println("2 Per Stampa Librio Per Autore");
			int menu = sc.nextInt();
			if(menu == 1){
				try {
					sl.stampaTuttiLibri();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}if(menu == 2){
				try {
					System.out.println("Inserisci Autore");
					String autore = sc.next();
					sl.stampaLibroAutore(autore);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(menu == 0){
				System.exit(0);
				sc.close();
			}

		}

	}


}
