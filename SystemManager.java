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
	
	// User Session
	public User login(String username, String password) {
		for (User user : users) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				loggedInUser = user;
				return user;
			}
		}
		return null;
	}
	
	public User getLoggedInUser() { 
		return loggedInUser; 
	}
	
    public void logout() { 
    	loggedInUser = null; 
    }
	
	public boolean usernameExists(String username) {
        return findUser(username) != null;
    }
	
	// Getter Method for all Users
	public ArrayList<User> getUsers() {
		return users;
	}
	
	/*
	 * Adding and Removing of Artworks
	 */
	public void addArtwork(Artwork artwork) {
        artworks.add(artwork);
        User user = findUser(artwork.getArtistUsername());
        if (user instanceof Artist) {
            ((Artist) user).addArtwork(artwork);
        }
    }
	
	public boolean removeArtwork(String title, String artistUsername) {
        Artwork found = null;
        for (Artwork art : artworks) {
            if (art.getTitle().equals(title) && art.getArtistUsername().equals(artistUsername)) {
                found = art;
                break;
            }
        }
        if (found != null) {
            artworks.remove(found);
            User user = findUser(artistUsername);
            if (user instanceof Artist) {
            	((Artist) user).removeArtwork(found);
            }
            return true;
        }
        return false;
    }
	
	// Getter Method for all Artworks
	public ArrayList<Artwork> getArtworks() { 
		return artworks; 
	}
	
	public ArrayList<Artwork> getArtworksByArtist(String username) {
        ArrayList<Artwork> result = new ArrayList<>();
        for (Artwork art : artworks) {
            if (art.getArtistUsername().equals(username)) {
            	result.add(art);
            }
        }
        return result;
    }
	
	/*
	 * Adding and Removing of Commissions
	 */
	public void addCommission(CommissionRequest request) {
        commissions.add(request);
        User client = findUser(request.getClientUsername());
        if (client instanceof Client) {
            ((Client) client).addRequest(request);
        }
    }

    public boolean removeCommission(CommissionRequest request) {
        return commissions.remove(request);
    }

 // Getter Method for all Commissions
    public ArrayList<CommissionRequest> getCommissions() { 
    	return commissions; 
    }

    public ArrayList<CommissionRequest> getCommissionsByArtist(String username) {
        ArrayList<CommissionRequest> result = new ArrayList<>();
        for (CommissionRequest request : commissions) {
            if (request.getArtistUsername().equals(username)) {
            	result.add(request);
            }
        }
        return result;
    }

    public ArrayList<CommissionRequest> getCommissionsByClient(String username) {
        ArrayList<CommissionRequest> result = new ArrayList<>();
        for (CommissionRequest request : commissions) {
            if (request.getClientUsername().equals(username)) {
            	result.add(request);
            }
        }
        return result;
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
