public class PessoaFisica extends Cliente{

	private String cpf;
	
	protected PessoaFisica(String nome, String cpf) {
		super(nome);
		this.cpf = cpf;
		
	}

	public String getCpf() {
		return cpf;
	}
}
