package cliente.tcp;

import Entidades.Posicao;
import Entidades.Veiculo;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


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
			
			System.out.println("Digite a operação que deseja realizar:");
			System.out.println("1-Adicionar");
			System.out.println("2-Alterar");
			System.out.println("3-Excluir");
			System.out.println("4-Consultar");
			System.out.println("5-Listar tipo");
			System.out.println("6-Localização dos Veiculos");
			System.out.println("0-Sair");
			
			Socket clientSocket = new Socket("34.211.122.0", 2010);
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
                                System.out.println("Digite primeiramente o código do veículo:");
                                outToServer.writeObject(keyb.nextInt());
                                String datahora = "";
                                System.out.println("Digite (1) para informar Data Hora (0) para não informar Data Hora:");
                                int opcao = keyb.nextInt();
                                if(opcao == 1){
                                    System.out.println("Digite o ano (AAAA):");
                                    datahora += keyb.next();
                                    System.out.println("Digite o mês (MM):");
                                    datahora += "-"+keyb.next();
                                    System.out.println("Digite o dia (DD)");
                                    datahora += "-"+keyb.next();
                                    System.out.println("Digite a hora (HH):");
                                    datahora += "-"+keyb.next();
                                    System.out.println("Digite os minutos (MM):");
                                    datahora += "-"+keyb.next();
                                    System.out.println("Digite os segundos (SS):");
                                    datahora += "-"+keyb.next();                                    
                                }
                                outToServer.writeObject(datahora);
                                List<Posicao> posicoes2 = new ArrayList<>();
                                posicoes2 = (List<Posicao>)inFromServer.readObject();
                                for (Posicao pos : posicoes2)
                                {
                                        System.out.println("Data: " + pos.getDatahora() +
                                                                                "Latitude: " + pos.getLatitude() +
                                                                                "Longitude: " + pos.getLongitude());
                                }
                                
				clientSocket.close();
				break;
			default:
				clientSocket.close();
				break;
			}
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