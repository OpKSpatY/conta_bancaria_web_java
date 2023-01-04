public class ContaBancaria {
	private int saldoConta = 0;
	private Cliente cliente;
	private int id;
	private static int contador = 0;
	
	public ContaBancaria(Cliente cliente) {
		this.cliente = cliente;
		contador++;
		id = contador;
	}

	public int getSaldo() {
		return saldoConta;
	}

	public int depositar(int deposito) {
		if(deposito >= 0){
			this.saldoConta += deposito;
		}
		return deposito;
	}
	
	public int sacar(int saldo) {
		if(0 < saldo && saldoConta >= saldo) {
			this.saldoConta -= saldo;
		}
		return saldo;
		
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getId() {
		return id;
	}

}
