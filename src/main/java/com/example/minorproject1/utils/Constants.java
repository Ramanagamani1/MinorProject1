package com.example.minorproject1.utils;

public class Constants {

    // accessible only by admin
    public static final String STUDENT_INFO_AUTHORITY = "STUDENT_INFO";
    public static final String CREATE_ADMIN_AUTHORITY = "CREATE_ADMIN";
    public static final String CREATE_BOOK_AUTHORITY = "CREATE_BOOK";
    public static final String UPDATE_BOOK_AUTHORITY = "UPDATE_BOOK";
    public static final String DELETE_BOOK_AUTHORITY = "DELETE_BOOK";
    public static final String INITIATE_TRANSACTION_AUTHORITY = "INITIATE_TRANSACTION";


    // accessible only by student

    public static final String STUDENT_SELF_INFO_AUTHORITY = "STUDENT_SELF_INFO";
    public static final String MAKE_PAYMENT = "MAKE_PAYMENT";


    // accessible by admin and student
    public static final String READ_BOOK_AUTHORITY = "READ_BOOK_AUTHORITY";


    public static final String DELIMITER = "::";
    public static final String STUDENT_USER = "STUDENT";
    public static final String ADMIN_USER = "ADMIN";

    public static final String STUDENT_KEY_PREFIX = "student::";
    public static final Integer STUDENT_KEY_EXPIRY = 600;
}
