package org.com.auth;

public class MockAuthenticationService implements AuthenticationService {

    @Override
    public String login(String username, String password) {
        if (username.equals("szilard99") && password.equals("abc123")){
            return "authenticated";
        }
        return "unauthenticated";
    }

    @Override
    public boolean validateAuthToken(String authToken, String username) {
        return "szilard99".equals(username) && "authenticated".equals(authToken);
    }
}
