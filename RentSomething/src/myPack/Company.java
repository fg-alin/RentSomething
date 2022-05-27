//# For extensions of application, see BillCalc.java
package myPack;

//# I considered that a rental can be rented for at least one day 

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Company {

	private String name;
	private Set<Rental> setOfRentals = new HashSet<>();
	private List<Order> listOfOrders = new ArrayList<>();

	public Company(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addRental(Rental rental) {
		if (rental == null) {
			throw new RuntimeException("Rental cannot be null");
		}
		setOfRentals.add(rental);
	}

	public List<Order> provideOrders() {

		return listOfOrders.stream().filter(ord -> ord.getStatus() == OrderStatus.OPEN).collect(Collectors.toList());

	}

	public void showBills() {
		listOfOrders.stream().filter(ord -> ord.getStatus() == OrderStatus.CLOSED)
				.forEach(ord -> System.out.println(ord.getRental().getName() + " " + ord.getBill()));
	}

	public Set<Rental> provideAllFreeRentals(LocalDate startDate, LocalDate endDate) {

		Set<Rental> free = new HashSet<>();
		Set<Rental> busy = new HashSet<>();

		for (Order ord : listOfOrders) {

			boolean startDateBetween = (startDate.isAfter(ord.getStartDate()) || startDate.isEqual(ord.getStartDate()))
					&& ((startDate.isBefore(ord.getEndDate()) || startDate.isEqual(ord.getEndDate())));

			boolean endDateBetween = (endDate.isAfter(ord.getStartDate()) || endDate.isEqual(ord.getStartDate()))
					&& ((endDate.isBefore(ord.getEndDate()) || endDate.isEqual(ord.getEndDate())));

			if (ord.getStatus() == OrderStatus.OPEN && (startDateBetween || endDateBetween)) {
				busy.add(ord.getRental());
			}
		}

		free.addAll(setOfRentals);
		free.removeAll(busy);

		return free;
	}

	public void rent(Customer customer, Rental rental, LocalDate startDate, LocalDate endDate) throws Exception {

		if (startDate.isAfter(endDate) || startDate.isEqual(endDate)) {
			throw new Exception("You cannot enter an endDate which is before or similar to startDate.");
		}

		if (provideAllFreeRentals(startDate, endDate).contains(rental)) {
			Order newOrder = new Order(customer, rental, startDate, endDate);
			newOrder.setStatus(OrderStatus.OPEN);
			listOfOrders.add(newOrder);
		} else {
			throw new Exception("Rental \"" + rental.getName() + "\" not available!\n");
		}
	}

//  The next method calculates based on the date we RUN the code (we have LocalDate.now() in BillCals class)  	
	public void returnRental(Rental rental) throws Exception {
		for (Order ord : listOfOrders) {
			if (ord.getRental().equals(rental)
					&& (ord.getStatus() == OrderStatus.OPEN || ord.getStatus() == OrderStatus.WAITING)) {

				if (ord.getStartDate().isAfter(LocalDate.now())) {
					throw new Exception("You cannot return a rental before the startDate of the Order.");
				}

				if (rental.getHasDamage()) {
					ord.setStatus(OrderStatus.WAITING);
					throw new Exception("Rental \"" + rental.getName()
							+ "\" must be evaluated by a damage expert. This order remains in \"Waiting\" state.");
				}

				else {
					ord.setBill(rental.accept(new BillCalc(), ord.getStartDate(), LocalDate.now()));
					ord.setStatus(OrderStatus.CLOSED);

				}

			}
		}
	}

}
