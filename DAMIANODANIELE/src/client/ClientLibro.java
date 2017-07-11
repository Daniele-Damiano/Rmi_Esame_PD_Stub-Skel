package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import server.Ilibri;
import server.Libro;

public class ClientLibro {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try{
			System.setSecurityManager(new SecurityManager());
			Ilibri ib = (Ilibri)Naming.lookup("rmi://localhost/server");
			
			
			//Modificata Condizione while tolto 1 e messo true 
			while(true){
				System.out.println("1 per Inserire");
				System.out.println("2 per Aggiornare");
				int menu = sc.nextInt();
				if(menu == 1){
					System.out.println("Inserisci Titolo");
					String titolo = sc.next();
					System.out.println("Inserisci Autore");
					String autore = sc.next();
					System.out.println("Inserisci Prezzo");
					int prezzo = sc.nextInt();
					Libro l = new Libro(titolo, autore, prezzo);
					ib.inserisci(l);
				}
				if(menu == 2){
					System.out.println("Inserisci titolo");
					String titolo = sc.next();
					System.out.println("Inserisci Prezzo");
					int prezzo = sc.nextInt();
					Libro m = new Libro(titolo, null, prezzo);
					ib.aggiorna(m);
				}
				if(menu == 0){
					System.exit(0);
				}
				
			}
			
		}catch(RemoteException|MalformedURLException|NotBoundException e){
			System.out.println(e.getMessage());
		}
		sc.close();
		
	}

}
