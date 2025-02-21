
public class Project {
	private String name;
	private String type;
	private String url;
	private String date; // dd-mm-yyyy
	private String city;
	private String status;
	private double price;
	
	//costructor
	public Project(String name, String type, String url, String date, String city, String status, double price) {
		this.name = name;
		this.type = type;
		this.url = url;
		this.date = date;
		this.city = city;
		this.status = status;
		this.price = price;
	}

	// setters
	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getUrl() {
		return url;
	}

	public String getDate() {
		return date;
	}

	public String getCity() {
		return city;
	}

	public String getStatus() {
		return status;
	}

	public double getPrice() {
		return price;
	}

	public String toString() {
		return "Name: " + name + "\nType: " + type + "\nUrl: " + url + "Date: " + date + "\nCity: " + city
				+ "\nStatus: " + status + "\nPrice: " + price;
	}
	
	
}
