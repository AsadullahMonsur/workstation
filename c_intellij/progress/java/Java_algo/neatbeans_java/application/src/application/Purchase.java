package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Purchase {
	private final SimpleStringProperty PrName;
	private final SimpleIntegerProperty PrPrice;


	public Purchase(String name, Integer price) {
		super();
		this.PrName = new SimpleStringProperty(name);
		this.PrPrice = new SimpleIntegerProperty(price);

	}
	public String getPrName() {
		return PrName.get();
	}
	public Integer getPrPrice() {
		return PrPrice.get();
	}
	
}
