package za.ac.cput.helper;

// Singleton pattern. Know this for exam
public class SessionManager {
    private static SessionManager instance;
    private String bearerToken;
    private String username;

    private SessionManager(){}

    public static SessionManager getInstance(){
        if (instance == null)
        {
            instance = new SessionManager();
        }
        return instance;
    }

    public void setBearerToken(String token) {
        bearerToken = token;
    }

    public String getBearerToken(){
        return bearerToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
