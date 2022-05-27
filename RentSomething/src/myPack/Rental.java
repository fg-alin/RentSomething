package myPack;

public interface Rental extends Visitable {

	public abstract RentType getRentType();

	public abstract String getName();

	public abstract double getCostPerDay();

	public abstract boolean getHasDamage();

	public abstract double getSpecificBillCalc();

	// # Here, we can also define a method "getCostOfDamage()", if we know how to
	// calculate it for each single car (or each rental)
}
