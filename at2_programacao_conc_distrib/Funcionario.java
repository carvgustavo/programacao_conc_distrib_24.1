import java.util.Random;

class Funcionario {
    private final String nome;
    private final double salario;
    private final Conta contaSalario;
    private final Conta contaInvestimento;
    private final Random random;

    public Funcionario(String nome, double salario, Conta contaSalario, Conta contaInvestimento) {
        this.nome = nome;
        this.salario = salario;
        this.contaSalario = contaSalario;
        this.contaInvestimento = contaInvestimento;
        this.random = new Random();
    }

    public double getSalario() {
        return salario;
    }

    public Conta getContaSalario() {
        return contaSalario;
    }

    public Conta getContaInvestimento() {
        return contaInvestimento;
    }

    public String getNome() {
        return nome;
    }

    public void realizarInvestimento(double valor) {
        synchronized (contaSalario) {
            if (contaSalario.getSaldo() >= valor) {
                contaSalario.debitar(valor);
                contaInvestimento.creditar(valor);
                System.out.println("Funcionário " + nome + " realizou um investimento de R$ " + valor);
            } else {
                System.out.println("Saldo insuficiente para investimento para o funcionário " + nome);
            }
        }
    }

    public double consultarSaldoSalario() {
        synchronized (contaSalario) {
            return contaSalario.getSaldo();
        }
    }

    public double consultarSaldoInvestimento() {
        synchronized (contaInvestimento) {
            return contaInvestimento.getSaldo();
        }
    }
}
