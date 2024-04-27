import java.util.List;

class Loja {
    private final Conta conta;
    private final List<Funcionario> funcionarios;
    private final String nome;

    public Loja(String nome, Conta conta, List<Funcionario> funcionarios) {
        this.nome = nome;
        this.conta = conta;
        this.funcionarios = funcionarios;
    }

    public Conta getConta() {
        return conta;
    }

    public String getNome() {
        return nome;
    }

    public void pagarFuncionarios() {
        double totalSalarios = 0;

        // Calcula o total a ser pago a todos os funcionários
        for (Funcionario funcionario : funcionarios) {
            totalSalarios += funcionario.getSalario();
        }

        // Verifica se o saldo da conta da loja é suficiente para pagar os funcionários
        if (conta.getSaldo() >= totalSalarios) {
            // Debita o valor total da conta da loja
            conta.debitar(totalSalarios);

            // Credita o salário na conta de cada funcionário
            for (Funcionario funcionario : funcionarios) {
                funcionario.getContaSalario().creditar(funcionario.getSalario());
                System.out.println("Funcionário " + funcionario.getNome() + " recebeu salário de R$ " + funcionario.getSalario());
            }
        } else {
            System.out.println("Pagamento de funcionários não realizado devido a saldo insuficiente na conta da loja.");
        }
    }
}
