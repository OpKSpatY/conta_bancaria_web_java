import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BancoServlet")
public class BancoServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PessoaFisica tipoConta1 = null;
		PessoaJuridica tipoConta2 = null;
		String resposta = " ";
		int retorno2 = 0;
		String tipoContas = " ";
		int valorAtual;
		String tipo = request.getParameter("tipoCliente");
		String operacao = request.getParameter("operacao");
		String nome = request.getParameter("nomeCliente");
		Cliente cliente = new Cliente(nome);
		ContaBancaria contaCliente = new ContaBancaria(cliente);
		
		if(tipo.equals("pessoaFisica")) {
			String cpf = request.getParameter("cpfCnpj");
			tipoConta1 = new PessoaFisica(cliente.getNome(), cpf);
			tipoContas = "0";
		}
		if(tipo.equals("pessoaJuridica")) {
			String cnpj = request.getParameter("cpfCnpj");
			tipoConta2 = new PessoaJuridica(cliente.getNome(), cnpj);
			tipoContas = "1";
		}
		
		switch(operacao) {
			case "recuperarNomeCliente":
				resposta = cliente.getNome();
				break;
			case "recuperarCpfCliente":
				if(tipoContas == "0") {
					resposta = tipoConta1.getCpf();
					break;
				}
				if(tipoContas == "1") {
					resposta = " ";
					break;
				}
			case "recuperarCnpjCliente":
				if(tipoContas == "1") {
					resposta = tipoConta2.getCnpj();
					break;
				}
				else if(tipoContas == "0") {
					resposta = " ";
				}
			case "recuperarSaldo":
				retorno2 = contaCliente.getSaldo();
				break;
				
			case "depositarValor":
				String valorDepositoStr = request.getParameter("valorDeposito");
				Integer valorDeposito = Integer.valueOf(valorDepositoStr);
				retorno2 = contaCliente.depositar(valorDeposito);
				break;
				
			case "sacarValor":
				valorAtual = contaCliente.getSaldo();
				String valorSaquestr = request.getParameter("valorSaque");
				Integer valorSaque = Integer.valueOf(valorSaquestr);
				contaCliente.sacar(valorSaque);
				/*if (valorAtual == contaCliente.getSaldo()){
					System.out.println("Saldo insuficiente.");
				}
				*/
				//System.out.println("Saldo atual: ");
				retorno2 = valorSaque;
				break;
			case "depositarSacarValor":
				valorDepositoStr = request.getParameter("valorDeposito");
				valorSaquestr = request.getParameter("valorSaque");
				valorDeposito = Integer.valueOf(valorDepositoStr);
				valorSaque = Integer.valueOf(valorSaquestr);
				
				retorno2 = contaCliente.depositar(valorDeposito) - contaCliente.sacar(valorSaque);
				if(retorno2 <= 0) {
					retorno2 = 0;
				}
				break;
		}
		String respostaFinal = resposta + '\n' + retorno2;	
		if(operacao.equals("recuperarNomeCliente") || operacao.equals("recuperarCpfCliente")
				|| operacao.equals("recuperarCnpjCliente")) {
			respostaFinal = resposta;
		}
		
		request.setAttribute("resposta", respostaFinal);
		request.getRequestDispatcher("resposta.jsp").forward(request, response);

	}
}
