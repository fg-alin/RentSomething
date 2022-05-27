package myPack;

import java.time.LocalDate;

public interface Visitable {
	public double accept(Visitor visitor, LocalDate startDate, LocalDate endDate);

}