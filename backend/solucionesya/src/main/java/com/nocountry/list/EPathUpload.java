package com.nocountry.list;

public enum EPathUpload {

    CREATE_FILE_FOLDER("./uploadImages/admin/images"),

    PATH_FILE_IMAGE("/uploadImages/files/"),

    CREATE_ADMIN_FOLDER("./uploadImages/admin"),

    PATH_ADMIN_IMAGE("/uploadImages/admin/");

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