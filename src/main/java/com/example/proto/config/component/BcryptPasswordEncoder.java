package com.example.proto.config.component;

import at.favre.lib.crypto.bcrypt.BCrypt;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 *  php password_hash 함수 대응 패스워드 인코더
 */
public class BcryptPasswordEncoder {
    private static final BCrypt.Hasher hasher = BCrypt.with(BCrypt.Version.VERSION_2Y);
    private static final BCrypt.Verifyer verifyer = BCrypt.verifyer(BCrypt.Version.VERSION_2Y);

    private static final int HASH_COST = 10;

    public static String encode(String password) {
        byte[] hash = hasher.hash(HASH_COST, password.getBytes());
        return new String(hash, UTF_8);
    }

    public static boolean verify(String password, String hashed) {
        return verifyer.verify(password.getBytes(UTF_8), hashed.getBytes(UTF_8)).verified;
    }
}
