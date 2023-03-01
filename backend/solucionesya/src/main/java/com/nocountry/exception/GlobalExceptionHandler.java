package com.nocountry.exception;

import com.nocountry.dto.response.ErrorResponse;
import com.nocountry.list.EExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static ErrorResponse buildErrorResponse(HttpStatus httpStatus, String typeException, String message, Exception exception) {
        return new ErrorResponse(httpStatus.value(), typeException, message, exception.getMessage());
    }

    private static ErrorResponse buildErrorResponse(HttpStatus httpStatus, String typeException, String message, List<String> moreInfo) {
        return new ErrorResponse(httpStatus.value(), typeException, message, moreInfo);
    }

    @ExceptionHandler(value = EmailAlreadyExistException.class)
    protected ResponseEntity<ErrorResponse> handlerEmailAlreadyExistException(EmailAlreadyExistException exception) {
        ErrorResponse errorResponse;
        HttpStatus status;
        String typeException = "EMAIL EXCEPTION";
        String message = "EMAIL ALREADY EXIST";

        errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                message,
                exception);
        status = HttpStatus.INTERNAL_SERVER_ERROR;

        assert status != null;
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(value = AdminException.class)
    protected ResponseEntity<ErrorResponse> handlerAdminException(AdminException exception) {

        ErrorResponse errorResponse = null;
        HttpStatus status = null;
        String typeException = "ADMIN EXCEPTION";

        if (exception.getMessage().equals(EExceptionMessage.ADMIN_NOT_FOUND.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND, typeException,
                    EExceptionMessage.ADMIN_NOT_FOUND.getMessage(),
                    exception);
            status = HttpStatus.NOT_FOUND;
        }
        if (exception.getMessage().equals(EExceptionMessage.REQUEST_WRONG_DATA.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                    EExceptionMessage.REQUEST_WRONG_DATA.getMessage(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_LIST_OF_ACTIVE_ADMIN_IS_EMPTY.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.NO_CONTENT, typeException,
                    EExceptionMessage.THE_LIST_OF_ACTIVE_ADMIN_IS_EMPTY.getMessage(),
                    exception);
            status = HttpStatus.NO_CONTENT;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_LIST_OF_INACTIVE_ADMIN_IS_EMPTY.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.NO_CONTENT, typeException,
                    EExceptionMessage.THE_LIST_OF_INACTIVE_ADMIN_IS_EMPTY.getMessage(),
                    exception);
            status = HttpStatus.NO_CONTENT;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_ADMIN_ALREADY_CONTAINS_IMAGE.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.EXPECTATION_FAILED, typeException,
                    EExceptionMessage.THE_ADMIN_ALREADY_CONTAINS_IMAGE.getMessage(),
                    exception);
            status = HttpStatus.EXPECTATION_FAILED;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_ADMIN_LIST_IS_EMPTY.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.NO_CONTENT, typeException,
                    EExceptionMessage.THE_ADMIN_LIST_IS_EMPTY.getMessage(),
                    exception);
            status = HttpStatus.NO_CONTENT;
        }
        assert status != null;
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(value = ProviderException.class)
    protected ResponseEntity<ErrorResponse> handlerProviderException(ProviderException exception) {

        ErrorResponse errorResponse = null;
        HttpStatus status = null;
        String typeException = "PROVIDER EXCEPTION";

        if (exception.getMessage().equals(EExceptionMessage.PROVIDER_NOT_FOUND.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND, typeException,
                    EExceptionMessage.PROVIDER_NOT_FOUND.getMessage(),
                    exception);
            status = HttpStatus.NOT_FOUND;
        }
        if (exception.getMessage().equals(EExceptionMessage.REQUEST_WRONG_DATA.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                    EExceptionMessage.REQUEST_WRONG_DATA.getMessage(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_LIST_OF_ACTIVE_PROVIDERS_IS_EMPTY.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.NO_CONTENT, typeException,
                    EExceptionMessage.THE_LIST_OF_ACTIVE_PROVIDERS_IS_EMPTY.getMessage(),
                    exception);
            status = HttpStatus.NO_CONTENT;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_LIST_OF_INACTIVE_PROVIDERS_IS_EMPTY.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.NO_CONTENT, typeException,
                    EExceptionMessage.THE_LIST_OF_INACTIVE_PROVIDERS_IS_EMPTY.getMessage(),
                    exception);
            status = HttpStatus.NO_CONTENT;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_PROVIDER_ALREADY_CONTAINS_IMAGE.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.EXPECTATION_FAILED, typeException,
                    EExceptionMessage.THE_PROVIDER_ALREADY_CONTAINS_IMAGE.getMessage(),
                    exception);
            status = HttpStatus.EXPECTATION_FAILED;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_PROVIDERS_LIST_IS_EMPTY.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.NO_CONTENT, typeException,
                    EExceptionMessage.THE_PROVIDERS_LIST_IS_EMPTY.getMessage(),
                    exception);
            status = HttpStatus.NO_CONTENT;
        }
        assert status != null;
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(value = ProvisionException.class)
    protected ResponseEntity<ErrorResponse> handlerProvisionException(ProvisionException exception) {

        ErrorResponse errorResponse = null;
        HttpStatus status = null;
        String typeException = "PROVISION EXCEPTION";

        if (exception.getMessage().equals(EExceptionMessage.PROVISION_NOT_FOUND.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND, typeException,
                    EExceptionMessage.PROVISION_NOT_FOUND.getMessage(),
                    exception);
            status = HttpStatus.NOT_FOUND;
        }
        if (exception.getMessage().equals(EExceptionMessage.REQUEST_WRONG_DATA.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                    EExceptionMessage.REQUEST_WRONG_DATA.getMessage(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_LIST_OF_ACTIVE_PROVISIONS_IS_EMPTY.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.NO_CONTENT, typeException,
                    EExceptionMessage.THE_LIST_OF_ACTIVE_PROVISIONS_IS_EMPTY.getMessage(),
                    exception);
            status = HttpStatus.NO_CONTENT;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_LIST_OF_INACTIVE_PROVISIONS_IS_EMPTY.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.NO_CONTENT, typeException,
                    EExceptionMessage.THE_LIST_OF_INACTIVE_PROVISIONS_IS_EMPTY.getMessage(),
                    exception);
            status = HttpStatus.NO_CONTENT;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_PROVISION_ALREADY_CONTAINS_IMAGE.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.EXPECTATION_FAILED, typeException,
                    EExceptionMessage.THE_PROVISION_ALREADY_CONTAINS_IMAGE.getMessage(),
                    exception);
            status = HttpStatus.EXPECTATION_FAILED;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_PROVISIONS_LIST_IS_EMPTY.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.NO_CONTENT, typeException,
                    EExceptionMessage.THE_PROVISIONS_LIST_IS_EMPTY.getMessage(),
                    exception);
            status = HttpStatus.NO_CONTENT;
        }
        assert status != null;
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(value = OpinionException.class)
    protected ResponseEntity<ErrorResponse> handlerOpinionException(OpinionException exception) {

        ErrorResponse errorResponse = null;
        HttpStatus status = null;
        String typeException = "OPINION EXCEPTION";

        if (exception.getMessage().equals(EExceptionMessage.OPINION_NOT_FOUND.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND, typeException,
                    EExceptionMessage.OPINION_NOT_FOUND.getMessage(),
                    exception);
            status = HttpStatus.NOT_FOUND;
        }
        if (exception.getMessage().equals(EExceptionMessage.REQUEST_WRONG_DATA.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                    EExceptionMessage.REQUEST_WRONG_DATA.getMessage(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_LIST_OF_ACTIVE_OPINIONS_IS_EMPTY.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.NO_CONTENT, typeException,
                    EExceptionMessage.THE_LIST_OF_ACTIVE_OPINIONS_IS_EMPTY.getMessage(),
                    exception);
            status = HttpStatus.NO_CONTENT;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_LIST_OF_INACTIVE_OPINIONS_IS_EMPTY.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.NO_CONTENT, typeException,
                    EExceptionMessage.THE_LIST_OF_INACTIVE_OPINIONS_IS_EMPTY.getMessage(),
                    exception);
            status = HttpStatus.NO_CONTENT;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_OPINIONS_LIST_IS_EMPTY.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.NO_CONTENT, typeException,
                    EExceptionMessage.THE_OPINIONS_LIST_IS_EMPTY.getMessage(),
                    exception);
            status = HttpStatus.NO_CONTENT;
        }
        assert status != null;
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(value = ClientException.class)
    protected ResponseEntity<ErrorResponse> handlerClientException(ClientException exception) {

        ErrorResponse errorResponse = null;
        HttpStatus status = null;
        String typeException = "CLIENT EXCEPTION";

        if (exception.getMessage().equals(EExceptionMessage.CLIENT_NOT_FOUND.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND, typeException,
                    EExceptionMessage.PROVISION_NOT_FOUND.getMessage(),
                    exception);
            status = HttpStatus.NOT_FOUND;
        }
        if (exception.getMessage().equals(EExceptionMessage.REQUEST_WRONG_DATA.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                    EExceptionMessage.REQUEST_WRONG_DATA.getMessage(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_CONTRACT_LIST_IS_EMPTY.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.NO_CONTENT, typeException,
                    EExceptionMessage.THE_CONTRACT_LIST_IS_EMPTY.getMessage(),
                    exception);
            status = HttpStatus.NO_CONTENT;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_LIST_OF_INACTIVE_CLIENTS_IS_EMPTY.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.NO_CONTENT, typeException,
                    EExceptionMessage.THE_LIST_OF_INACTIVE_CLIENTS_IS_EMPTY.getMessage(),
                    exception);
            status = HttpStatus.NO_CONTENT;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_CLIENT_ALREADY_CONTAINS_IMAGE.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.EXPECTATION_FAILED, typeException,
                    EExceptionMessage.THE_CLIENT_ALREADY_CONTAINS_IMAGE.getMessage(),
                    exception);
            status = HttpStatus.EXPECTATION_FAILED;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_CLIENTS_LIST_IS_EMPTY.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.NO_CONTENT, typeException,
                    EExceptionMessage.THE_CLIENTS_LIST_IS_EMPTY.getMessage(),
                    exception);
            status = HttpStatus.NO_CONTENT;
        }
        assert status != null;
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(value = ImageException.class)
    protected ResponseEntity<ErrorResponse> handlerImageException(ImageException exception) {

        ErrorResponse errorResponse = null;
        HttpStatus status = null;
        String typeException = "IMAGE EXCEPTION";

        if (exception.getMessage().equals(EExceptionMessage.IMAGE_NOT_VALID.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND, typeException,
                    EExceptionMessage.IMAGE_NOT_VALID.getMessage(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_FOLDER_CANNOT_BE_INITIALIZED.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.THE_FOLDER_CANNOT_BE_INITIALIZED.getMessage(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_IMAGE_CANNOT_BE_SAVED.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.THE_IMAGE_CANNOT_BE_SAVED.getMessage(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.IMAGE_DELETED.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.NO_CONTENT, typeException,
                    EExceptionMessage.IMAGE_DELETED.getMessage(),
                    exception);
            status = HttpStatus.NO_CONTENT;
        }
        if (exception.getMessage().equals(EExceptionMessage.IMAGE_NOT_VALID.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                    EExceptionMessage.IMAGE_NOT_VALID.getMessage(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.ERROR_DELETING_IMAGE.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                    EExceptionMessage.ERROR_DELETING_IMAGE.getMessage(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        assert status != null;
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorResponse> handlerMaxUploadSizeException(MaxUploadSizeExceededException exception) {

        String typeException = "MAX UPLOAD SIZE EXCEEDED EXCEPTION";

        ErrorResponse errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                EExceptionMessage.REQUEST_WRONG_DATA.getMessage(), exception);
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(value = CloudinaryException.class)
    protected ResponseEntity<ErrorResponse> handlerCloudinaryException(CloudinaryException exception) {

        ErrorResponse errorResponse = null;
        HttpStatus status = null;
        String typeException = "CLOUDINARY EXCEPTION";

        if (exception.getMessage().equals(EExceptionMessage.UNABLE_TO_FIND_APPLICATION_PROPERTIES_FILE.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND, typeException,
                    EExceptionMessage.UNABLE_TO_FIND_APPLICATION_PROPERTIES_FILE.getMessage(),
                    exception);
            status = HttpStatus.NOT_FOUND;
        }
        if (exception.getMessage().equals(EExceptionMessage.ERROR_READING_APPLICATION_PROPERTIES_FILE.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.ERROR_READING_APPLICATION_PROPERTIES_FILE.getMessage(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        assert status != null;
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(value = ContractException.class)
    protected ResponseEntity<ErrorResponse> handlerContractException(ContractException exception) {

        ErrorResponse errorResponse = null;
        HttpStatus status = null;
        String typeException = "CLOUDINARY EXCEPTION";

        if (exception.getMessage().equals(EExceptionMessage.CONTRACT_NOT_FOUND.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND, typeException,
                    EExceptionMessage.CONTRACT_NOT_FOUND.getMessage(),
                    exception);
            status = HttpStatus.NOT_FOUND;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_LIST_OF_ACTIVE_CONTRACT_IS_EMPTY.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.NO_CONTENT, typeException,
                    EExceptionMessage.THE_LIST_OF_ACTIVE_CONTRACT_IS_EMPTY.getMessage(),
                    exception);
            status = HttpStatus.NO_CONTENT;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_LIST_OF_INACTIVE_CONTRACT_IS_EMPTY.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.NO_CONTENT, typeException,
                    EExceptionMessage.THE_LIST_OF_INACTIVE_CONTRACT_IS_EMPTY.getMessage(),
                    exception);
            status = HttpStatus.NO_CONTENT;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_CONTRACT_LIST_IS_EMPTY.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.NO_CONTENT, typeException,
                    EExceptionMessage.THE_CONTRACT_LIST_IS_EMPTY.getMessage(),
                    exception);
            status = HttpStatus.NO_CONTENT;
        }
        assert status != null;
        return new ResponseEntity<>(errorResponse, status);
    }

    private List<String> collectErrors(MethodArgumentNotValidException exception) {
        return exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::formatErrorField)
                .toList();
    }

    private String formatErrorField(FieldError fieldError) {
        return fieldError.getDefaultMessage();
    }
}