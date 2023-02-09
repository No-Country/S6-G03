package com.nocountry.list;

public enum EPathUpload {

    CREATE_FILE_FOLDER("src/main/resources/static/fileUploads/file"),

    PATH_FILE_IMAGE("/fileUploads/file/"),

    CREATE_ADMIN_FOLDER("src/main/resources/static/fileUploads/admin"),

    PATH_ADMIN_IMAGE("/fileUploads/admin/");

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