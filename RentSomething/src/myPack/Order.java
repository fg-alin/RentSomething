package myPack;

import java.time.LocalDate;

public class Order {

	private Customer customer;
	private Rental rental;
	private LocalDate startDate;
	private LocalDate endDate;
	private double bill;
	private OrderStatus status;

	public Order(Customer customer, Rental rental, LocalDate startDate, LocalDate endDate) {
		this.customer = customer;
		this.rental = rental;
		this.startDate = startDate;
		this.endDate = endDate;

	}

	public Customer getCustomer() {
		return customer;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public Rental getRental() {
		return rental;
	}

	public double getBill() {
		return bill;
	}

	public void setBill(double bill) {
		this.bill = bill;
	}
}
