package facade;

public class ReservaProduto { 

	protected ReservaVestido rreservaVestido;
	protected ReservaCalcado rreservaCalcado;
	protected Pagamentos rpagamentos;

	public void ReservaProdutos() {

		rreservaVestido = new ReservaVestido();
		rreservaVestido.reservaVestido();

		rreservaCalcado = new ReservaCalcado();
		rreservaCalcado.reservaCalcado();

		rpagamentos = new Pagamentos();
		rpagamentos.pagamento();
	}
}