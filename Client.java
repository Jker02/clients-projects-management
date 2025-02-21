import java.util.Vector;

public class Client {
	private String name;
	private String surname;
	private String email;
	private String telephoneNumber;
	private String address;
	private Vector<Project> clientProjects;
	
	// costructor
	public Client(String name, String surname, String email, String telephoneNumber, String address) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.telephoneNumber = telephoneNumber;
		this.address = address;
		this.clientProjects = new Vector<Project>();
	}
	
	//setters
	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setClientProjects(Project clientProject) {
		clientProjects.add(clientProject);
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getEmail() {
		return email;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public Vector<Project> getClientProjects() {
		return clientProjects;
	}

	//toString
	public String toStringClient() {
		return "Name: " + name + "\nSurname: " + surname + "\nEmail: " + email + "\nTelephoneNumber: "
				+ telephoneNumber + "\nAddress: " + address + "\nProjects:\n";		
	}
	
	public String toStringClientProjects() {
		String string =  "";
		for (Project cP : clientProjects) { //for each project of a client
			string += cP.toString();
		}
		return string;
	}
	
	public String toString() {
		String string = "Name: " + name + "\nSurname: " + surname + "\nEmail: " + email + "\nTelephoneNumber: "
				+ telephoneNumber + "\nAddress: " + address + "\nProjects:\n";
		for (Project cP : clientProjects) { //for each project of a client
			string += cP.toString();
		}
		return string;
	}
	
	public void removeClientProject(int i) {
		clientProjects.remove(i);
	}
	
	
	
	
	
	
	
	

}
