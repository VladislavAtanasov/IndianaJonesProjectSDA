package indiana.jones.project;

import java.util.Comparator;

public class Treasure {

	private int weight;
	private long price;
	private String name;
	private boolean used = false;

	public Treasure(String name, long price, int w) {
		this.price = price;
		this.weight = w;
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public long getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	@Override
	public String toString() {
		return "Treasure [weight=" + weight + ", price=" + price + ", name=" + name + "]";
	}

	public static Comparator<Treasure> byRatio() {
		return new Comparator<Treasure>() {
			public int compare(Treasure i1, Treasure i2) {
				return Double.compare(i2.getRatio(), i1.getRatio());
			}
		};
	}

	public double getRatio() {
		return price / weight;
	}

}
