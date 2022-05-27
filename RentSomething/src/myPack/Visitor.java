package myPack;

import java.time.LocalDate;

public interface Visitor {
	public double visit(Rental rent, LocalDate startDate, LocalDate endDate);

}

