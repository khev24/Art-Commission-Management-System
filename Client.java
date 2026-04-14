package ArtCommissionManagementSystem;

import java.util.ArrayList;

//Inheritance: Client (Child Class) inherents the attributes of User (Parent Class)
public class Client extends User {
	// Aggregation: Client "has" Commission Requests
	private ArrayList<CommissionRequest> clientRequests;

	public Client(String fullname, String username, String password) {
		super(fullname, username, password, "Client");
	}
	
	// Adding a Commission Request
	public void addRequest(CommissionRequest request) {
		clientRequests.add(request);
	}
	
	// Getter Method for Client Commission Requests
	public ArrayList<CommissionRequest> getClientRequests() {
		return this.clientRequests;
	}
}
