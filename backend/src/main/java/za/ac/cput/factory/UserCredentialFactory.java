package za.ac.cput.factory;

import za.ac.cput.domain.UserCredential;
import za.ac.cput.util.Helper;

public class UserCredentialFactory
{
    public static UserCredential buildUserCredential(String username, String password, String role)
    {
        if (Helper.isNullorEmpty(username) || Helper.isNullorEmpty(password) || Helper.isNullorEmpty(role))
            return null;

       return new UserCredential.Builder()
                .setUsername(username)
                .setPassword(password)
                .setRole(role)
                .build();
    }
}
