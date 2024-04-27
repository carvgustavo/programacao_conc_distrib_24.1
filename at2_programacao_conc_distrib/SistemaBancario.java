import java.util.ArrayList;
import java.util.List;

public class SistemaBancario {
    public static void main(String[] args) {
        Banco banco = new Banco();

        // Cria contas para lojas e funcionários
        Conta contaLoja1 = new Conta("Loja1", 0);
        Conta contaLoja2 = new Conta("Loja2", 0);

        List<Funcionario> funcionariosLoja1 = new ArrayList<>();
        funcionariosLoja1.add(new Funcionario("Funcionario1", 1400, new Conta("Funcionario1 Salario", 0), new Conta("Funcionario1 Investimento", 0)));
        funcionariosLoja1.add(new Funcionario("Funcionario2", 1400, new Conta("Funcionario2 Salario", 0), new Conta("Funcionario2 Investimento", 0)));

        List<Funcionario> funcionariosLoja2 = new ArrayList<>();
        funcionariosLoja2.add(new Funcionario("Funcionario3", 1400, new Conta("Funcionario3 Salario", 0), new Conta("Funcionario3 Investimento", 0)));
        funcionariosLoja2.add(new Funcionario("Funcionario4", 1400, new Conta("Funcionario4 Salario", 0), new Conta("Funcionario4 Investimento", 0)));

        Loja loja1 = new Loja("Loja1", contaLoja1, funcionariosLoja1);
        Loja loja2 = new Loja("Loja2", contaLoja2, funcionariosLoja2);

        // Cria clientes
        Cliente cliente1 = new Cliente("Cliente1", new Conta("Cliente1", 1000), new Loja[] { loja1, loja2 });
        Cliente cliente2 = new Cliente("Cliente2", new Conta("Cliente2", 1000), new Loja[] { loja1, loja2 });
        Cliente cliente3 = new Cliente("Cliente3", new Conta("Cliente3", 1000), new Loja[] { loja1, loja2 });
        Cliente cliente4 = new Cliente("Cliente4", new Conta("Cliente4", 1000), new Loja[] { loja1, loja2 });
        Cliente cliente5 = new Cliente("Cliente5", new Conta("Cliente5", 1000), new Loja[] { loja1, loja2 });

        // Cria as threads para clientes
        Thread threadCliente1 = new Thread(cliente1);
        Thread threadCliente2 = new Thread(cliente2);
        Thread threadCliente3 = new Thread(cliente3);
        Thread threadCliente4 = new Thread(cliente4);
        Thread threadCliente5 = new Thread(cliente5);

        // Inicia as threads para clientes
        threadCliente1.start();
        threadCliente2.start();
        threadCliente3.start();
        threadCliente4.start();
        threadCliente5.start();

        // Realiza os pagamentos de funcionários periodicamente
        Runnable pagamentoFuncionarios = () -> {
            while (true) {
                try {
                    Thread.sleep(5000); // Pagamento a cada 5 segundos
                    loja1.pagarFuncionarios();
                    loja2.pagarFuncionarios();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        // Cria thread para realizar o pagamento de funcionários
        Thread threadPagamentoFuncionarios = new Thread(pagamentoFuncionarios);
        threadPagamentoFuncionarios.start();
    }
}
