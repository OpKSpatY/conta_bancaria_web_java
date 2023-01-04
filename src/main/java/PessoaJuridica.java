public class PessoaJuridica extends Cliente{

	private String cnpj;
	
	protected PessoaJuridica(String nome, String cnpj) {
		super(nome);
		this.cnpj = cnpj;		
	}

	public String getCnpj() {
		return cnpj;
	}
}
