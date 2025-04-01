package org.com;

public interface IAuthenticationService {
    String login(String username, String password);
    boolean matchAuthToken(String username, String authToken);
}
