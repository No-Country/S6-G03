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
    EMAIL_DOES_NOT_EXIST("EMAIL {0} DOES NOT EXISTS"),
    WRONG_PASSWORD("WRONG PASSWORD"),
    PASSWORD_DOES_NOT_MATCH("PASSWORD DO NOT MATCH"),

    ////////////////////////////////////////////////////////////////////////////////////////////
    // ADMIN
    ////////////////////////////////////////////////////////////////////////////////////////////
    ADMIN_NOT_FOUND("ADMIN NOT FOUND"),
    ERROR_WHEN_DISPLAYING_ACTIVE_ADMINS("ERROR WHEN DISPLAYING ACTIVE ADMINS"),
    ERROR_WHEN_DISPLAYING_INACTIVE_ADMINS("ERROR WHEN DISPLAYING INACTIVE ADMINS"),
    ERROR_WHEN_DISPLAYING_THE_LIST_OF_AVAILABLE_ADMINS("ERROR WHEN DISPLAYING THE LIST OF AVAILABLE ADMINS"),
    THE_ADMIN_ALREADY_CONTAINS_IMAGE("THE ADMIN ALREADY CONTAINS IMAGE"),
    ERROR_DISPLAYING_ADMIN_ACTIVE("ERROR DISPLAYING ADMIN ACTIVE"),
    ERROR_DISPLAYING_ALL_ADMIN("ERROR DISPLAYING ALL ADMIN"),

    ////////////////////////////////////////////////////////////////////////////////////////////
    // PROVIDER
    ////////////////////////////////////////////////////////////////////////////////////////////
    PROVIDER_NOT_FOUND("PROVIDER NOT FOUND"),
    ERROR_WHEN_DISPLAYING_ACTIVE_PROVIDERS("ERROR WHEN DISPLAYING ACTIVE PROVIDERS"),
    ERROR_WHEN_DISPLAYING_INACTIVE_PROVIDERS("ERROR WHEN DISPLAYING INACTIVE PROVIDERS"),
    ERROR_WHEN_DISPLAYING_THE_LIST_OF_AVAILABLE_PROVIDERS("ERROR WHEN DISPLAYING THE LIST OF AVAILABLE PROVIDERS"),
    THE_PROVIDER_ALREADY_CONTAINS_IMAGE("THE PROVIDER ALREADY CONTAINS IMAGE"),
    ERROR_DISPLAYING_PROVIDER_ACTIVE("ERROR DISPLAYING PROVIDER ACTIVE"),
    ERROR_DISPLAYING_ALL_PROVIDER("ERROR DISPLAYING ALL PROVIDER"),

    ////////////////////////////////////////////////////////////////////////////////////////////
    // PROVISION
    ////////////////////////////////////////////////////////////////////////////////////////////
    PROVISION_NOT_FOUND("PROVISION NOT FOUND"),
    ERROR_WHEN_DISPLAYING_ACTIVE_PROVISIONS("ERROR WHEN DISPLAYING ACTIVE PROVISIONS"),
    ERROR_WHEN_DISPLAYING_INACTIVE_PROVISIONS("ERROR WHEN DISPLAYING INACTIVE PROVISIONS"),
    ERROR_WHEN_DISPLAYING_THE_LIST_OF_AVAILABLE_PROVISIONS("ERROR WHEN DISPLAYING THE LIST OF AVAILABLE PROVISIONS"),
    THE_PROVISION_ALREADY_CONTAINS_IMAGE("THE PROVISION ALREADY CONTAINS IMAGE"),
    ERROR_DISPLAYING_PROVISION_ACTIVE("ERROR DISPLAYING PROVISION ACTIVE"),
    ERROR_DISPLAYING_ALL_PROVISION("ERROR DISPLAYING ALL PROVISION"),

    ////////////////////////////////////////////////////////////////////////////////////////////
    // OPINION
    ////////////////////////////////////////////////////////////////////////////////////////////
    OPINION_NOT_FOUND("OPINION NOT FOUND"),
    ERROR_WHEN_DISPLAYING_ACTIVE_OPINIONS("ERROR WHEN DISPLAYING ACTIVE OPINIONS"),
    ERROR_WHEN_DISPLAYING_INACTIVE_OPINIONS("ERROR WHEN DISPLAYING INACTIVE OPINIONS"),
    ERROR_WHEN_DISPLAYING_THE_LIST_OF_AVAILABLE_OPINIONS("ERROR WHEN DISPLAYING THE LIST OF AVAILABLE OPINIONS"),
    ERROR_DISPLAYING_OPINION_ACTIVE("ERROR DISPLAYING OPINION ACTIVE"),
    ERROR_DISPLAYING_ALL_OPINION("ERROR DISPLAYING ALL OPINION"),

    ////////////////////////////////////////////////////////////////////////////////////////////
    // CLIENT
    ////////////////////////////////////////////////////////////////////////////////////////////
    CLIENT_NOT_FOUND("CLIENT NOT FOUND"),
    ERROR_WHEN_DISPLAYING_ACTIVE_CLIENTS("ERROR WHEN DISPLAYING ACTIVE CLIENTS"),
    ERROR_WHEN_DISPLAYING_INACTIVE_CLIENTS("ERROR WHEN DISPLAYING INACTIVE CLIENTS"),
    ERROR_WHEN_DISPLAYING_THE_LIST_OF_AVAILABLE_CLIENTS("ERROR WHEN DISPLAYING THE LIST OF AVAILABLE CLIENTS"),
    THE_CLIENT_ALREADY_CONTAINS_IMAGE("THE CLIENT ALREADY CONTAINS IMAGE"),
    ERROR_DISPLAYING_CLIENT_ACTIVE("ERROR DISPLAYING CLIENT ACTIVE"),
    ERROR_DISPLAYING_ALL_CLIENT("ERROR DISPLAYING ALL CLIENT"),

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
    // CONTRACT
    ////////////////////////////////////////////////////////////////////////////////////////////

    CONTRACT_NOT_FOUND("CONTRACT NOT FOUND"),
    ERROR_DISPLAYING_ALL_CONTRACT("ERROR DISPLAYING ALL CONTRACT"),
    ERROR_DISPLAYING_CONTRACT_ACTIVE("ERROR DISPLAYING CONTRACT ACTIVE");

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