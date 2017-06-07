package br.com.tcp;

//import java.io.BufferedReader;
//import java.io.DataOutputStream;
import Banco.Operacoes;
import Entidades.Posicao;
import Entidades.Veiculo;
//import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TCPServer {
	// TODO Auto-generated method stub
	public static void main(String argv[]) throws Exception {
//		String clientSentence;
//		String capitalizedSentence;
		ServerSocket welcomeSocket = new ServerSocket(2010);
		Veiculo veiculo;
//		Boolean check = true;
		int codigo;
		List<Posicao> posicoes;
		Date d;

	        System.out.println("Sistema iniciado em: " + new Date());
	        LOG.Logs.LogMessage("Sistema iniciado em: " + new Date(), "ServidorTCP");
        
		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			ObjectInputStream inFromClient = new ObjectInputStream(connectionSocket.getInputStream());
			ObjectOutputStream outToClient = new ObjectOutputStream(connectionSocket.getOutputStream());
//			BufferedReader inFromClient =
//					new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
//			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			
			int opc = (Integer)inFromClient.readObject();
			System.out.println("Opcao selecionada:" + opc);
			switch (opc) {
			case 1: // adiciona
				veiculo = (Veiculo)inFromClient.readObject();
				try {
					Operacoes.adicionaVeiculo(veiculo);
					System.out.println(connectionSocket.getInetAddress() + " adicionou veiculo com codigo: " + veiculo.getCodigo() + " na base de dados.");
                                        LOG.Logs.LogMessage(connectionSocket.getInetAddress() + " adicionou veiculo com codigo: " + veiculo.getCodigo() + " na base de dados.", "ServidorTCP");
					outToClient.writeObject("Veiculo adicionado com sucesso!");
				} catch (SQLException e) {
					// TODO: handle exception
					System.out.println(connectionSocket.getInetAddress() + " tentou adicionar veiculo ja existente. Msg SQLException:"+ e.getMessage());
                                        LOG.Logs.LogMessage(connectionSocket.getInetAddress() + " tentou adicionar veiculo ja existente. Msg SQLException:"+ e.getMessage(), "ServidorTCP");
					outToClient.writeObject("Veiculo informado ja existe.");
				}
				break;
			case 2: // altera
				veiculo = (Veiculo)inFromClient.readObject();
				try {
                                        Operacoes.consultaVeiculo(veiculo.getCodigo());
					Operacoes.alteraVeiculo(veiculo);
					System.out.println(connectionSocket.getInetAddress() + " alterou veiculo com codigo: " + veiculo.getCodigo() + " na base de dados.");
					LOG.Logs.LogMessage(connectionSocket.getInetAddress() + " alterou veiculo com codigo: " + veiculo.getCodigo() + " na base de dados.", "ServidorTCP");
					outToClient.writeObject("Veiculo alterado com sucesso!");
				} catch (SQLException e) {
					// TODO: handle exception
                                        System.out.println(connectionSocket.getInetAddress() + " tentou alterar veiculo nao existente. Msg SQLException:"+ e.getMessage());
					LOG.Logs.LogMessage(connectionSocket.getInetAddress() + " tentou alterar veiculo nao existente. Msg SQLException:"+ e.getMessage(), "ServidorTCP");
					outToClient.writeObject("Veículo informado nao existe.");
				}
				break;
			case 3: // deleta
				codigo = (Integer)inFromClient.readObject();
				try {
                                        Operacoes.consultaVeiculo(codigo);
					Operacoes.deletaVeiculo(codigo);
					System.out.println(connectionSocket.getInetAddress() + " deletou veiculo com codigo: " + codigo + " na base de dados.");
					LOG.Logs.LogMessage(connectionSocket.getInetAddress() + " deletou veiculo com codigo: " + codigo + " na base de dados.", "ServidorTCP");
					outToClient.writeObject("Veiculo excluido com sucesso!");
				} catch (Exception e) {
					// TODO: handle exception
                                        System.out.println(connectionSocket.getInetAddress() + " tentou deletar veiculo nao existente. Msg SQLException:"+ e.getMessage());
					LOG.Logs.LogMessage(connectionSocket.getInetAddress() + " tentou deletar veiculo nao existente. Msg SQLException:"+ e.getMessage(), "ServidorTCP");
					outToClient.writeObject("Veiculo informado nao existe.");
				}
				break;
			case 4: // consulta 
				codigo = (Integer)inFromClient.readObject();
				try {
					veiculo = Operacoes.consultaVeiculo(codigo);
					System.out.println(connectionSocket.getInetAddress() + " consultou veiculo com codigo:" +veiculo.getCodigo() + " na base de dados.");
					LOG.Logs.LogMessage(connectionSocket.getInetAddress() + " consultou veiculo com codigo:" +veiculo.getCodigo() + " na base de dados.", "ServidorTCP");
					outToClient.writeObject("Codigo: " + veiculo.getCodigo() + " Placa: " + veiculo.getPlaca() + " Tipo: " + veiculo.getTipo()
					+ " Capacidade: " + veiculo.getCapacidade() + " Unpac: " + veiculo.getUnpac());
				} catch (Exception e) {
					// TODO: handle exception
                                        System.out.println(connectionSocket.getInetAddress() + " tentou consultar veiculo nao existente. Msg SQLException:"+ e.getMessage());
					LOG.Logs.LogMessage(connectionSocket.getInetAddress() + " tentou consultar veiculo nao existente. Msg SQLException:"+ e.getMessage(), "ServidorTCP");
					outToClient.writeObject("Veiculo informado nao existe.");
				}
				break;
			case 5: // lista tipo
				System.out.println("Entrei na listagem de tipo");
				List<Veiculo> veiculos = new ArrayList<>();
				int tipo = (Integer)inFromClient.readObject(); // salvo o tipo numa variável para salvar no log
				veiculos = Operacoes.listaTipo(tipo);
				outToClient.writeObject("Lista com veiculos do tipo: " + tipo);
				outToClient.writeObject(veiculos);
				LOG.Logs.LogMessage("Cliente com ip: " + connectionSocket.getInetAddress() + " listou veiculos do tipo: " + tipo, "ServidorTCP");
				break;
			case 6:
        			System.out.println("Entrei na listagem de localizacoes!");
				codigo = (Integer)inFromClient.readObject();
				String dtstr = (String) inFromClient.readObject();
                                Date dt = null;
                                if(!dtstr.isEmpty()){
                                    String[] part = dtstr.split("-");
                                    Calendar cal = Calendar.getInstance();
                                    cal.set(Calendar.YEAR, Integer.parseInt(part[0]));
                                    cal.set(Calendar.MONTH, Integer.parseInt(part[1])-1);
                                    cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(part[2]));
                                    cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(part[3]));
                                    cal.set(Calendar.MINUTE, Integer.parseInt(part[4]));
                                    cal.set(Calendar.SECOND, Integer.parseInt(part[5]));

                                    dt = cal.getTime();
                                }
				posicoes = new ArrayList<>();
				posicoes = Operacoes.listaPosicaoVeiculo(codigo, dt);
				outToClient.writeObject(posicoes);
				LOG.Logs.LogMessage("Cliente com ip: " + connectionSocket.getInetAddress() + " listou as posicoes do veiculo: " + codigo, "ServidorTCP");
				break;
			case 0:
				System.out.println("Cliente com ip: " + connectionSocket.getInetAddress() + " encerrou a conexao!");
				LOG.Logs.LogMessage("Cliente com ip: " + connectionSocket.getInetAddress() + " encerrou a conexao!", "ServidorTCP");
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