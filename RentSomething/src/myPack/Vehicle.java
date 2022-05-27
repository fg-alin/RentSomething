package myPack;

import java.time.LocalDate;


public class Vehicle implements Rental {
	private static int autoIndex = 0;
	private String model;
	private double costPerDay;
	private VehicleType type;
	private boolean hasDamage = false;
	private double refillCost;

	public Vehicle(String model, VehicleType type, double costPerDay) {
		autoIndex++;
		this.type = type;
		this.model = model;
		this.costPerDay = costPerDay;
	}

	@Override
	public RentType getRentType() {
		return RentType.VEHICLE;
	}

	public static int getAutoIndex() {
		return autoIndex;
	}

	public VehicleType getType() {
		return type;
	}

	@Override
	public String getName() {
		return model;
	}

	@Override
	public double getCostPerDay() {
		return this.costPerDay;
	}

	public void setCostPerDay(double costPerDay) {
		this.costPerDay = costPerDay;
	}

	@Override
	public boolean getHasDamage() {
		return this.hasDamage;
	}

	public void setHasDamage(boolean hasDamage) {
		this.hasDamage = hasDamage;
	}

	public double getRefillCost() {
		return refillCost;
	}

	public void setRefillCost(double refillCost) {
		this.refillCost = refillCost;
	}

	@Override
	public double getSpecificBillCalc() {
		return refillCost;
	}

	@Override
	public double accept(Visitor visitor, LocalDate startDate, LocalDate endDate) {
		return visitor.visit(this, startDate, endDate);
	}

}
