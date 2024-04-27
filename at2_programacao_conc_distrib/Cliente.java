import java.util.Random;

class Cliente implements Runnable {
    private final String nome;
    private final Conta conta;
    private final Loja[] lojas;
    private final Random random;

    public Cliente(String nome, Conta conta, Loja[] lojas) {
        this.nome = nome;
        this.conta = conta;
        this.lojas = lojas;
        this.random = new Random();
    }

    @Override
public void run() {
    while (conta.getSaldo() > 0) {
        // Escolhe aleatoriamente uma loja para fazer a compra
        Loja loja = lojas[random.nextInt(lojas.length)];

        // Gera um valor aleatório para a compra
        double valorCompra = random.nextDouble() < 0.5 ? 100 : 200;

        // Transfere o valor da compra da conta do cliente para a conta da loja
        synchronized (conta) {
            if (conta.getSaldo() >= valorCompra) {
                conta.debitar(valorCompra);
                loja.getConta().creditar(valorCompra);
                System.out.println(nome + " realizou uma compra de R$ " + valorCompra + " na loja " + loja.getNome());
            }
        }

        // Aguarda um curto período de tempo antes de fazer outra compra
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    System.out.println(nome + " terminou suas compras e não possui saldo suficiente.");
}

}
