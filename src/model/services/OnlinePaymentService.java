package model.services;

public interface OnlinePaymentService {
	public double interest(double amount, int mouth);
	public double paymentFee(double amount);
}
