package br.com.tcp;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import br.com.banco.*;
import br.com.entidades.*;

public class TCPClient {
	 static Integer codigo;
	 static String placa;
	 static Integer tipo;
	 static Integer capacidade;
	 static String unpac;
	 static Scanner keyb = new Scanner(System.in);
	 static Veiculo veiculo;
	
	public static void main(String argv[]) throws Exception {
//		String sentence;
//		String modifiedSentence;
		String resp = new String();
		int opc=99;
		
		
		
//		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
//		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		do
		{
			
			System.out.println("Digite a operação que desjea realizar:");
			System.out.println("1-Adicionar");
			System.out.println("2-Alterar");
			System.out.println("3-Excluir");
			System.out.println("4-Consultar");
			System.out.println("5-Listar tipo");
			System.out.println("6-Localização total do veículo (todos os lugares)");
			System.out.println("7-Localização a partir de x hora");
			System.out.println("0-Sair");
			
			Socket clientSocket = new Socket("localhost", 2010);
			ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
			ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
			
			opc = keyb.nextInt();
			
			outToServer.writeObject(opc);
			switch (opc) {
			case 1: // adiciona
				Padrao();
				outToServer.writeObject(veiculo);
				resp = (String)inFromServer.readObject();
				System.out.println(resp);
				clientSocket.close();
				break;
			case 2: // altera
				Padrao();
				outToServer.writeObject(veiculo);
				resp = (String)inFromServer.readObject();
				System.out.println(resp);
				clientSocket.close();
				break;
			case 3: // deleta 
				System.out.println("Digite o código do veículo que deseja excluir:");
				outToServer.writeObject(keyb.nextInt());
				resp = (String)inFromServer.readObject();
				System.out.println(resp);
				clientSocket.close();
				break;
			case 4: // consulta
				System.out.println("Digite o código do veículo que deseja consultar:");
				outToServer.writeObject(keyb.nextInt());
				resp = (String)inFromServer.readObject();
				System.out.println(resp);
				clientSocket.close();
				break;
			case 5: // lista tipo
				System.out.println("Digite o tipo de veículo que deseja consultar:");
				outToServer.writeObject(keyb.nextInt());
				resp = (String)inFromServer.readObject();
				List<Veiculo> veiculos = (List<Veiculo>)inFromServer.readObject();
				System.out.println(resp);
				for (Veiculo veic : veiculos)
				{
					System.out.println("Código: " + veic.getCodigo() 
									+ "Placa: " + veic.getPlaca()
									+ "Capacidade: " + veic.getCapacidade() 
									+ "Unpac: " + veic.getUnpac());
				}
				clientSocket.close();
				break;
			case 6:
				System.out.println("Digite o código do veículo para checagem de todas as localizações:");
				outToServer.writeObject(keyb.nextInt());
				List<Posicao> posicoes = new ArrayList<>();
				posicoes = (List<Posicao>)inFromServer.readObject();
				for (Posicao pos : posicoes)
				{
					System.out.println("Data: " + pos.getDatahora() +
										"Latitude: " + pos.getLatitude() +
										"Longitude: " + pos.getLongitude());
				}
				clientSocket.close();
				break;
			case 7:
				System.out.println("Digite primeiramente o código do veículo:");
				outToServer.writeObject(keyb.nextInt());
				System.out.println("Digite o ano (AAAA):");
				int ano = keyb.nextInt();
				System.out.println("Digite o mês (MM):");
				int mes = keyb.nextInt();
				System.out.println("Digite o dia (DD)");
				int dia = keyb.nextInt();
				System.out.println("Digite a hora (HH):");
				int hora = keyb.nextInt();
				System.out.println("Digite os minutos (MM):");
				int min = keyb.nextInt();
				Calendar cal = Calendar.getInstance();
				cal.set(ano, mes, dia, hora, min);
				Date d = cal.getTime();
				outToServer.writeObject(d);
				List<Posicao> posicoes2 = new ArrayList<>();
				posicoes = (List<Posicao>)inFromServer.readObject();
				for (Posicao pos : posicoes2)
				{
					System.out.println("Data: " + pos.getDatahora() +
										"Latitude: " + pos.getLatitude() +
										"Longitude: " + pos.getLongitude());
				}
				
				break;
			default:
				clientSocket.close();
				break;
			}
//			sentence = inFromUser.readLine();
//			outToServer.writeObject(sentence + '\n');
//			modifiedSentence = (String)inFromServer.readObject();
//			System.out.println("FROM SERVER: " + modifiedSentence);
			clientSocket.close();
		}while(opc!=0);
		
		
		
	}
	
	public static void Padrao(){
		System.out.println("Digite o código do veículo: ");
		codigo = keyb.nextInt();
		System.out.println("Digite a placa do veículo: ");
		placa = keyb.next();
		System.out.println("Digite o tipo do veículo: ");
		tipo = keyb.nextInt();
		System.out.println("Digite a capacidade do veículo: ");
		capacidade = keyb.nextInt();
		System.out.println("Digite a unpac do veículo: ");
		unpac = keyb.next();
		
		veiculo = new Veiculo(codigo, placa, tipo, capacidade, unpac);
	}
	
}