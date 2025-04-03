package org.com.auth;

public interface AuthenticationService {
    String login(String username, String password);
    boolean validateAuthToken(String authToken, String username);
}
