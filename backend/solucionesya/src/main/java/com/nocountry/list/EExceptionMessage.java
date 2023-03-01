package com.nocountry.list;

import java.text.MessageFormat;

public enum EExceptionMessage {

    ////////////////////////////////////////////////////////////////////////////////////////////
    // GENERALS EXCEPTION MESSAGE
    ////////////////////////////////////////////////////////////////////////////////////////////

    ID_NOT_FOUND("ID NOT FOUND"),
    ID_ALREADY_EXISTS("ID ALREADY EXISTS"),
    PARAM_NOT_FOUND("PARAM NOT FOUND"),
    REQUEST_WRONG_DATA("INVALID REQUEST"),
    RESPONSE_WRONG_DATA("INVALID RESPONSE"),
    DOCUMENT_ALREADY_EXISTS("DOCUMENT {0} ALREADY EXISTS"),
    EMAIL_ALREADY_EXISTS("EMAIL {0} ALREADY EXISTS"),
    WRONG_PASSWORD("WRONG PASSWORD"),
    PASSWORD_DO_NOT_MATCH("PASSWORD DO NOT MATCH"),

    ////////////////////////////////////////////////////////////////////////////////////////////
    // ADMIN
    ////////////////////////////////////////////////////////////////////////////////////////////
    ADMIN_NOT_FOUND("ADMIN NOT FOUND"),
    THE_LIST_OF_ACTIVE_ADMIN_IS_EMPTY("THE LIST OF ACTIVE ADMIN IS EMPTY"),
    THE_LIST_OF_INACTIVE_ADMIN_IS_EMPTY("THE LIST OF INACTIVE ADMIN IS EMPTY"),
    THE_ADMIN_ALREADY_CONTAINS_IMAGE("THE ADMIN ALREADY CONTAINS IMAGE"),
    THE_ADMIN_LIST_IS_EMPTY("THE ADMIN LIST IS EMPTY"),

    ////////////////////////////////////////////////////////////////////////////////////////////
    // PROVIDER
    ////////////////////////////////////////////////////////////////////////////////////////////
    PROVIDER_NOT_FOUND("PROVIDER NOT FOUND"),
    THE_LIST_OF_ACTIVE_PROVIDERS_IS_EMPTY("THE LIST OF ACTIVE PROVIDERS IS EMPTY"),
    THE_LIST_OF_INACTIVE_PROVIDERS_IS_EMPTY("THE LIST OF INACTIVE PROVIDERS IS EMPTY"),
    THE_PROVIDER_ALREADY_CONTAINS_IMAGE("THE PROVIDER ALREADY CONTAINS IMAGE"),
    THE_PROVIDERS_LIST_IS_EMPTY("THE PROVIDERS LIST IS EMPTY"),

    ////////////////////////////////////////////////////////////////////////////////////////////
    // PROVISION
    ////////////////////////////////////////////////////////////////////////////////////////////
    PROVISION_NOT_FOUND("PROVISION NOT FOUND"),
    THE_LIST_OF_ACTIVE_PROVISIONS_IS_EMPTY("THE LIST OF ACTIVE PROVISIONS IS EMPTY"),
    THE_LIST_OF_INACTIVE_PROVISIONS_IS_EMPTY("THE LIST OF INACTIVE PROVISIONS IS EMPTY"),
    THE_PROVISION_ALREADY_CONTAINS_IMAGE("THE PROVISION ALREADY CONTAINS IMAGE"),
    THE_PROVISIONS_LIST_IS_EMPTY("THE PROVISIONS LIST IS EMPTY"),

    ////////////////////////////////////////////////////////////////////////////////////////////
    // OPINION
    ////////////////////////////////////////////////////////////////////////////////////////////
    OPINION_NOT_FOUND("OPINION NOT FOUND"),
    THE_LIST_OF_ACTIVE_OPINIONS_IS_EMPTY("THE LIST OF ACTIVE OPINIONS IS EMPTY"),
    THE_LIST_OF_INACTIVE_OPINIONS_IS_EMPTY("THE LIST OF INACTIVE OPINIONS IS EMPTY"),
    THE_OPINIONS_LIST_IS_EMPTY("THE OPINIONS LIST IS EMPTY"),

    ////////////////////////////////////////////////////////////////////////////////////////////
    // CLIENT
    ////////////////////////////////////////////////////////////////////////////////////////////
    CLIENT_NOT_FOUND("CLIENT NOT FOUND"),
    THE_LIST_OF_ACTIVE_CLIENTS_IS_EMPTY("THE LIST OF ACTIVE CLIENTS IS EMPTY"),
    THE_LIST_OF_INACTIVE_CLIENTS_IS_EMPTY("THE LIST OF INACTIVE CLIENTS IS EMPTY"),
    THE_CLIENT_ALREADY_CONTAINS_IMAGE("THE CLIENT ALREADY CONTAINS IMAGE"),
    THE_CLIENTS_LIST_IS_EMPTY("THE CLIENTS LIST IS EMPTY"),

    ////////////////////////////////////////////////////////////////////////////////////////////
    // IMAGE
    ////////////////////////////////////////////////////////////////////////////////////////////
    IMAGE_NOT_FOUND("IMAGE NOT FOUND"),
    THE_FOLDER_CANNOT_BE_INITIALIZED("THE FOLDER CANNOT BE INITIALIZED"),
    THE_IMAGE_CANNOT_BE_SAVED("THE IMAGE CANNOT BE SAVED ERROR"),
    IMAGE_DELETED("IMAGE DELETED"),
    IMAGE_NOT_VALID("IMAGE NOT VALID"),
    ERROR_DELETING_IMAGE("ERROR DELETING IMAGE"),

    ////////////////////////////////////////////////////////////////////////////////////////////
    // CLOUDINARY
    ////////////////////////////////////////////////////////////////////////////////////////////

    UNABLE_TO_FIND_APPLICATION_PROPERTIES_FILE("UNABLE TO FIND APPLICATION.PROPERTIES FILE"),
    ERROR_READING_APPLICATION_PROPERTIES_FILE("ERROR READING APPLICATION.PROPERTIES FILE"),

    ////////////////////////////////////////////////////////////////////////////////////////////
    // CONTRACT
    ////////////////////////////////////////////////////////////////////////////////////////////

    CONTRACT_NOT_FOUND("CONTRACT NOT FOUND"),
    THE_LIST_OF_ACTIVE_CONTRACT_IS_EMPTY("THE LIST OF ACTIVE CONTRACT IS EMPTY"),
    THE_LIST_OF_INACTIVE_CONTRACT_IS_EMPTY("THE LIST OF INACTIVE CONTRACT IS EMPTY"),
    THE_CONTRACT_LIST_IS_EMPTY("THE CONTRACT LIST IS EMPTY");

    private final String messageString;

    EExceptionMessage(String messageString) {
        this.messageString = messageString;
    }

    public String getMessage() {
        return messageString;
    }

    public String getMessage(String stringObject) {
        return MessageFormat.format(messageString, stringObject);
    }

    @Override
    public String toString() {
        return messageString;
    }

    public static EExceptionMessage typeOrValue(String value) {
        for (EExceptionMessage type : EExceptionMessage.class.getEnumConstants()) {
            if (type.toString().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("ENUM MESSAGE NOT FOUND");
    }
}