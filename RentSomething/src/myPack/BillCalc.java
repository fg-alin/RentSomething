package myPack;

import java.time.LocalDate;
import java.time.Period;

class BillCalc implements Visitor {

	@Override
	public double visit(Rental rent, LocalDate startDate, LocalDate endDate) {

		double bill = rent.getCostPerDay() * (Period.between(startDate, LocalDate.now()).getDays())
				+ rent.getSpecificBillCalc();

		return bill;
	}

}

/*
 * # In order to permit simple extensions, I preferred to use the Visitor Design
 * Pattern.
 * 
 * In case of a new type of rental, we have just to add it into the enum
 * "RentType" (if not present already), and to make a new class
 * for it (similar to "Vehicle"), which implements the "Rental" interface.
 * 
 * Rental extends the "Visitable" interface from the Pattern, so the concrete
 * class which implements it ("Vehicle" and others) should override the "accept"
 * method. Of course, those classes should also implement all the abstract
 * methods inherited from "Rental".
 * 
 * I used the method "public abstract double getSpecificBillCalc()" in "Rental"
 * interface, having in mind that every type of rental should have a specific
 * cost. For example, in the case of Vehicle, it will return the refillCost. Anyway,
 * if a type of rental does not involve a specific cost, this method could
 * simply return 0 and it will not affect the bill value.
 * 
 * In case of extension for new type of vehicles (Tractors, for example), we
 * should just add the type into the enum "VehicleType".
 * Knowing that each unit of rental has it's own costPerDay (which is
 * initialized in Vehicle constructor), it does not depend on the type of
 * vehicle.
 * 
 */