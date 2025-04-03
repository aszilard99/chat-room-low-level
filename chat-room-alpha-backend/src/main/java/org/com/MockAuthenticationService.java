package org.com;

public class MockAuthenticationService implements AuthenticationService {

    @Override
    public String login(String username, String password) {
        if (username.equals("szilard99") && password.equals("abc123")){
            return "authenticated";
        }
        return "unauthenticated";
    }

    @Override
    public boolean matchAuthToken(String username, String authToken) {
        return username.equals("szilard99") && authToken.equals("authenticated");
    }
}
