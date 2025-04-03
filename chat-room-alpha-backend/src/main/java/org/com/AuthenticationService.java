package org.com;

public interface AuthenticationService {
    String login(String username, String password);
    boolean matchAuthToken(String username, String authToken);
}
