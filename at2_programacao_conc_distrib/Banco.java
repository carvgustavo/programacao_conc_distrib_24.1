import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Banco {
    private final Lock lock = new ReentrantLock();

    public void transferir(Conta origem, Conta destino, double valor) {
        lock.lock();
        try {
            if (origem.getSaldo() >= valor) {
                origem.debitar(valor);
                destino.creditar(valor);
                System.out.println("Transferência de " + valor + " de " + origem.getCliente() + " para " + destino.getCliente());
            }
        } finally {
            lock.unlock();
        }
    }
}
