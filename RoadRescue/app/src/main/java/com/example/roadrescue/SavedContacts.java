package com.example.roadrescue;

public class SavedContacts {

    public static final String DATABASE_NAME = "soscontact.db";

    public static final int DATABASE_VERSION = 3;

    public static final String TABLE_NAME = "contacts_table";

    public static final String CONTACT_ID = "ID";
    public static final String CONTACT_NAME = "NAME";
    public static final String CONTACT_PHONE = "PHONE";
    public static final String CONTACT_EMAIL = "EMAIL";



    public static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+CONTACT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+CONTACT_NAME+" VARCHAR(255),"+CONTACT_PHONE+" INTEGER,"+CONTACT_EMAIL+" VARCHAR(255));";





}
