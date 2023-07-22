package com.example.minorproject1.utils;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    public static Map<String, String> getAuthoritiesForUser() {

        HashMap<String,String> authoritiesMap = new HashMap<>();

        List<String> studentAuthorities = List.of(
                Constants.STUDENT_SELF_INFO_AUTHORITY,
                Constants.READ_BOOK_AUTHORITY,
                Constants.MAKE_PAYMENT
        );

        List<String> adminAuthorities = List.of(
                Constants.STUDENT_INFO_AUTHORITY,
                Constants.CREATE_ADMIN_AUTHORITY,
                Constants.READ_BOOK_AUTHORITY,
                Constants.CREATE_ADMIN_AUTHORITY,
                Constants.UPDATE_BOOK_AUTHORITY,
                Constants.DELETE_BOOK_AUTHORITY,
                Constants.INITIATE_TRANSACTION_AUTHORITY
        );

        String studentAuthority = String.join(Constants.DELIMITER, studentAuthorities);
        String adminAuthority = String.join(Constants.DELIMITER, adminAuthorities);

        authoritiesMap.put(Constants.STUDENT_USER,studentAuthority);
        authoritiesMap.put(Constants.ADMIN_USER,adminAuthority);

        return authoritiesMap;
    }
}
