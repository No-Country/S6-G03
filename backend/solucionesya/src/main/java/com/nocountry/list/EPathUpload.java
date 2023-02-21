package com.nocountry.list;

public enum EPathUpload {

    CREATE_FILE_FOLDER("./uploadImages/images"),

    PATH_FILE_IMAGE("/uploadImages/images/"),

    CREATE_ADMIN_FOLDER("./uploadImages/admin"),

    PATH_ADMIN_IMAGE("/uploadImages/admin/"),

    CREATE_PROVIDER_FOLDER("./uploadImages/provider"),

    PATH_PROVIDER_IMAGE("/uploadImages/provider/"),

    CREATE_PROVISION_FOLDER("./uploadImages/provision"),

    PATH_PROVISION_IMAGE("/uploadImages/provision/");

    private final String pathFileUploadString;

    EPathUpload(String pathFileUploadString) {
        this.pathFileUploadString = pathFileUploadString;
    }

    @Override
    public String toString() {
        return pathFileUploadString;
    }

    public static EPathUpload typeOrValue(String value) {
        for (EPathUpload type : EPathUpload.class.getEnumConstants()) {
            if (type.toString().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("EPathUpload NOT FOUND");
    }
}