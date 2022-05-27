//# This application implements the logic for a rental company. More details in BillCalc.java. 

package myPack;

import java.time.LocalDate;

public class Main {

	public static void main(String args[]) throws Exception {

		// #Experimental data:

		Company mycomp = new Company("RentSomething");

		Vehicle car1 = new Vehicle("Tesla", VehicleType.SEDAN, 100);
		Vehicle car2 = new Vehicle("VolksWagen", VehicleType.SEDAN, 30);
		Vehicle car3 = new Vehicle("Audi", VehicleType.SEDAN, 40);
		Vehicle car4 = new Vehicle("BMW", VehicleType.SEDAN, 50);

		mycomp.addRental(car1);
		mycomp.addRental(car2);
		mycomp.addRental(car3);
		mycomp.addRental(car4);

		System.out.println("Total number of vehicles: " + Vehicle.getAutoIndex() + "\n");

		Customer cust1 = new Customer("Client1", "07...");
		Customer cust2 = new Customer("Client2", "07...");

		mycomp.rent(cust2, car2, LocalDate.of(2021, 11, 23), LocalDate.of(2021, 12, 24));
		mycomp.rent(cust1, car3, LocalDate.of(2021, 11, 23), LocalDate.of(2021, 12, 25));

// 1). startDate after(or similar to the) endDate (throws exception):

//		mycomp.rent(cust1, car1, LocalDate.of(2021, 12, 19), LocalDate.of(2021, 11, 19));
//
//		
// 2). Can not return a rental before the startDate of the Order (throws exception):		
//		
//		mycomp.returnRental(car2);
//
//		
// 3). Rental not available, being already rented (throws exception):
//		
//		mycomp.rent(cust1, car2, LocalDate.of(2021, 11, 22), LocalDate.of(2021, 11, 27));
//
//		
// 4). Damage experiment (throws exception):
// 
//		car2.setHasDamage(true);
//		mycomp.returnRental(car2); //Here, the car should be evaluated, because it is damaged. 
//		The OrderStaus become WAITING 

		car3.setRefillCost(47);
		mycomp.returnRental(car3);

		mycomp.rent(cust1, car2, LocalDate.of(2021, 12, 25), LocalDate.of(2021, 12, 31));

		mycomp.rent(cust1, car4, LocalDate.of(2021, 11, 11), LocalDate.of(2021, 12, 25));
		mycomp.returnRental(car4);

		System.out.println("Open Orders: " + mycomp.provideOrders() + "\n");

		System.out.println("Bills: ");
		mycomp.showBills();

	}

}
