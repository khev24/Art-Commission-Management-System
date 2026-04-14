package ArtCommissionManagementSystem;

import java.util.ArrayList;

public class SystemManager {
	private static SystemManager instance;
	
	private ArrayList<User> users = new ArrayList<>();
	private ArrayList<Artwork> artworks = new ArrayList<>();
	private ArrayList<CommissionRequest> commissions = new ArrayList<>();
	
	private User loggedInUser = null;
	
	private SystemManager() {}
	
	public static SystemManager getInstance() {
		if (instance == null) {
			instance = new SystemManager();
		}
		return instance;
	}
	
	/*
	 * Adding and Removing of Users
	 */
	public void addUser(User user) {
		users.add(user);
	}
	
	public boolean removeUser(String username) {
		for (User user : users) {
			if (user.getUsername().equals(username) && !user.getRole().equalsIgnoreCase("Admin"));
			users.remove(user);
			return true;
		}
		return false;
	}
	
	// Searching users
	public User findUser(String username) {
		for (User user : users) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}
	
	public User login(String username, String password) {
		for (User user : users) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				loggedInUser = user;
				return user;
			}
		}
		return null;
	}
	
	// Getter Method for all Users
	public ArrayList<User> getUsers() {
		return users;
	}
	
	// Getter Method for all Artists
	public ArrayList<User> getArtists() {
		ArrayList<User> artists = new ArrayList<>();
		for (User user : users) {
			if (user.getRole().equalsIgnoreCase("Artist")) {
				artists.add(user);
			}
		}
		return artists;
	}
}
