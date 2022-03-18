package application;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Search {
	private final SimpleStringProperty Name;
	private final SimpleIntegerProperty Price;
	private final SimpleStringProperty Details;
	private final SimpleStringProperty Shopname;
	private final SimpleIntegerProperty Distance;
	private final SimpleStringProperty ShopAddress;

	public Search(String name, Integer price,String details,
			String shopname, Integer distance, String shopAddress) {
		super();
		this.Name = new SimpleStringProperty(name);
		this.Price = new SimpleIntegerProperty(price);
		this.Details = new SimpleStringProperty(details);
		this.Shopname = new SimpleStringProperty(shopname);
		this.Distance = new SimpleIntegerProperty(distance);
		this.ShopAddress = new SimpleStringProperty(shopAddress);
	}
	public String getName() {
		return Name.get();
	}
	public Integer getPrice() {
		return Price.get();
	}
	public String getDetails() {
		return Details.get();
	}
	public String getShopname() {
		return Shopname.get();
	}
	public Integer getDistance() {
		return Distance.get();
	}
	public String getShopAddress() {
		return ShopAddress.get();
	}
	
}
