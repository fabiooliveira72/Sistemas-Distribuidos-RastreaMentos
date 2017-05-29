package br.com.tcp;

//import java.io.BufferedReader;
//import java.io.DataOutputStream;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
//import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import br.com.banco.Operacoes;
import br.com.entidades.Veiculo;

public class TCPServer {
	private static final Logger logger = Logger.getLogger(TCPServer.class.getName());
	static FileHandler fh;
	// TODO Auto-generated method stub
	public static void main(String argv[]) throws Exception {
//		String clientSentence;
//		String capitalizedSentence;
		ServerSocket welcomeSocket = new ServerSocket(2010);
		Veiculo veiculo;
//		Boolean check = true;
		int codigo;

		Operacoes.beginReplica();
		
		try {  

	        // This block configure the logger with handler and formatter  
	        fh = new FileHandler("C:/test/MyLogFile.log");  
	        logger.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);  

	        // the following statement is used to log any messages  
//	        logger.info("My first log");  
	        
	        logger.info("Sistema iniciado em: " + new Date());

	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
		
        
		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			ObjectInputStream inFromClient = new ObjectInputStream(connectionSocket.getInputStream());
			ObjectOutputStream outToClient = new ObjectOutputStream(connectionSocket.getOutputStream());
//			BufferedReader inFromClient =
//					new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
//			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			
			int opc = (Integer)inFromClient.readObject();
			System.out.println("Opcção selecionada:" + opc);
			switch (opc) {
			case 1: // adiciona
				veiculo = (Veiculo)inFromClient.readObject();
				try {
					Operacoes.adicionaVeiculo(veiculo);
					System.out.println("To adicionando veículo");
					logger.info(connectionSocket.getInetAddress() + " adicionou veículo com código: " + veiculo.getCodigo() + " na base de dados.");
					outToClient.writeObject("Veículo adicionado com sucesso!");
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
					System.out.println("Não deu pra adicionar veículo");
					logger.info(connectionSocket.getInetAddress() + " tentou adicionar veículo já existente. Msg SQLException:"
							+ e.getMessage());
					outToClient.writeObject("Veículo informado já existe.");
				}
				break;
			case 2: // altera
				veiculo = (Veiculo)inFromClient.readObject();
				try {
					Operacoes.alteraVeiculo(veiculo);
					System.out.println("To alterando veículo");
					logger.info(connectionSocket.getInetAddress() + " alterou veículo com código: " + veiculo.getCodigo() + " na base de dados.");
					outToClient.writeObject("Veículo alterado com sucesso!");
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
					logger.info(connectionSocket.getInetAddress() + " tentou alterar veículo não existente. Msg SQLException:"
							+ e.getMessage());
					outToClient.writeObject("Veículo informado não existe.");
				}
				break;
			case 3: // deleta
				codigo = (Integer)inFromClient.readObject();
				try {
					Operacoes.deletaVeiculo(codigo);
					System.out.println("To deletando veículo");
					logger.info(connectionSocket.getInetAddress() + " deletou veículo com código: " + codigo + " na base de dados.");
					outToClient.writeObject("Veículo excluído com sucesso!");
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					logger.info(connectionSocket.getInetAddress() + " tentou deletar veículo não existente. Msg SQLException:"
							+ e.getMessage());
					outToClient.writeObject("Veículo informado não existe.");
				}
				break;
			case 4: // consulta 
				codigo = (Integer)inFromClient.readObject();
				try {
					veiculo = Operacoes.consultaVeiculo(codigo);
					System.out.println("Consultando veículo");
					logger.info(connectionSocket.getInetAddress() + " consultou veículo com código:" +veiculo.getCodigo() + " na base de dados.");
					outToClient.writeObject("Código: " + veiculo.getCodigo() + " Placa: " + veiculo.getPlaca() + " Tipo: " + veiculo.getTipo()
					+ " Capacidade: " + veiculo.getCapacidade() + " Unpac: " + veiculo.getUnpac());
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					logger.info(connectionSocket.getInetAddress() + " tentou consultar veículo não existente. Msg SQLException:"
							+ e.getMessage());
					outToClient.writeObject("Veículo informado não existe.");
				}
				break;
			case 5: // lista tipo
				System.out.println("Entrei na listagem de tipo");
				List<Veiculo> veiculos = new ArrayList<>();
				int tipo = (Integer)inFromClient.readObject(); // salvo o tipo numa variável para salvar no log
				veiculos = Operacoes.listaTipo(tipo);
				outToClient.writeObject("Lista com veículos do tipo: " + tipo);
				outToClient.writeObject(veiculos);
				logger.info("Cliente com ip: " + connectionSocket.getInetAddress() + " listou veículos do tipo: " + tipo);
				break;
			case 6:
				break;
			case 7:
				break;
			case 0:
				System.out.println("Conexão encerrada!");
				logger.info("Cliente com ip: " + connectionSocket.getInetAddress() + " encerrou a conexão!" );
				break;
			default:
				break;
			}
			
//			clientSentence = (String)inFromClient.readObject();
//			System.out.println("Received: " + clientSentence);
//			capitalizedSentence = clientSentence.toUpperCase() + '\n';
//			outToClient.writeObject(capitalizedSentence);
//			logger.info("Alteração teste" + connectionSocket.getInetAddress());
		}
		
	}
}
