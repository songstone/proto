package com.example.proto.auth;

import at.favre.lib.crypto.bcrypt.BCrypt;

import static java.nio.charset.StandardCharsets.UTF_8;

public class BcryptPasswordEncoder {
    private static final BCrypt.Hasher hasher = BCrypt.with(BCrypt.Version.VERSION_2Y);
    private static final BCrypt.Verifyer verifyer = BCrypt.verifyer(BCrypt.Version.VERSION_2Y);

    private static final int HASH_COST = 10;

    public static String encode(String password) {
        byte[] hash = hasher.hash(HASH_COST, password.getBytes());
        return new String(hash, UTF_8);
    }

    public static boolean verify(String password, String hashedPassword) {
        return verifyer.verify(password.getBytes(UTF_8), hashedPassword.getBytes(UTF_8)).verified;
    }
}
