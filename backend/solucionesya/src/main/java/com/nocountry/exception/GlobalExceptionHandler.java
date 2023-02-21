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
        if (exception.getMessage().equals(EExceptionMessage.ERROR_WHEN_DISPLAYING_ACTIVE_ADMINS.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.ERROR_WHEN_DISPLAYING_ACTIVE_ADMINS.getMessage(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.ERROR_WHEN_DISPLAYING_INACTIVE_ADMINS.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.ERROR_WHEN_DISPLAYING_INACTIVE_ADMINS.getMessage(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.ERROR_WHEN_DISPLAYING_THE_LIST_OF_AVAILABLE_ADMINS.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.ERROR_WHEN_DISPLAYING_THE_LIST_OF_AVAILABLE_ADMINS.getMessage(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_ADMIN_ALREADY_CONTAINS_IMAGE.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.EXPECTATION_FAILED, typeException,
                    EExceptionMessage.THE_ADMIN_ALREADY_CONTAINS_IMAGE.getMessage(),
                    exception);
            status = HttpStatus.EXPECTATION_FAILED;
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
        if (exception.getMessage().equals(EExceptionMessage.ERROR_WHEN_DISPLAYING_ACTIVE_PROVIDERS.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.ERROR_WHEN_DISPLAYING_ACTIVE_PROVIDERS.getMessage(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.ERROR_WHEN_DISPLAYING_INACTIVE_PROVIDERS.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.ERROR_WHEN_DISPLAYING_INACTIVE_PROVIDERS.getMessage(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.ERROR_WHEN_DISPLAYING_THE_LIST_OF_AVAILABLE_PROVIDERS.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.ERROR_WHEN_DISPLAYING_THE_LIST_OF_AVAILABLE_PROVIDERS.getMessage(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_PROVIDER_ALREADY_CONTAINS_IMAGE.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.EXPECTATION_FAILED, typeException,
                    EExceptionMessage.THE_PROVIDER_ALREADY_CONTAINS_IMAGE.getMessage(),
                    exception);
            status = HttpStatus.EXPECTATION_FAILED;
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
        if (exception.getMessage().equals(EExceptionMessage.ERROR_WHEN_DISPLAYING_ACTIVE_PROVISIONS.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.ERROR_WHEN_DISPLAYING_ACTIVE_PROVISIONS.getMessage(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.ERROR_WHEN_DISPLAYING_INACTIVE_PROVISIONS.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.ERROR_WHEN_DISPLAYING_INACTIVE_PROVISIONS.getMessage(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.ERROR_WHEN_DISPLAYING_THE_LIST_OF_AVAILABLE_PROVISIONS.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.ERROR_WHEN_DISPLAYING_THE_LIST_OF_AVAILABLE_PROVISIONS.getMessage(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_PROVISION_ALREADY_CONTAINS_IMAGE.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.EXPECTATION_FAILED, typeException,
                    EExceptionMessage.THE_PROVISION_ALREADY_CONTAINS_IMAGE.getMessage(),
                    exception);
            status = HttpStatus.EXPECTATION_FAILED;
        }
        assert status != null;
        return new ResponseEntity<>(errorResponse, status);
    }

   /* @ExceptionHandler(value = ImageException.class)
    protected ResponseEntity<ErrorResponse> handlerFileException(ImageException exception) {

        ErrorResponse errorResponse = null;
        HttpStatus status = null;
        String typeException = "FILE EXCEPTION";

        if (exception.getMessage().equals(EExceptionMessage.FILE_NOT_FOUND.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND, typeException,
                    EExceptionMessage.FILE_NOT_FOUND.getMessage(),
                    exception);
            status = HttpStatus.NOT_FOUND;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_FOLDER_CANNOT_BE_INITIALIZED.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.THE_FOLDER_CANNOT_BE_INITIALIZED.getMessage(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_FILE_CANNOT_BE_SAVED.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.THE_FILE_CANNOT_BE_SAVED.getMessage(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.FILE_DELETED.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.ACCEPTED, typeException,
                    EExceptionMessage.FILE_DELETED.getMessage(),
                    exception);
            status = HttpStatus.ACCEPTED;
        }
        if (exception.getMessage().equals(EExceptionMessage.ERROR_DELETING_FILE.getMessage())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.ERROR_DELETING_FILE.getMessage(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        assert status != null;
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorResponse> handlerMaxUploadSizeException(MaxUploadSizeExceededException exception) {

        String typeException = "MAX UPLOAD SIZE EXCEEDED EXCEPTION";

        ErrorResponse errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                EExceptionMessage.REQUEST_WRONG_DATA.getMessage(), exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handlerNotValidController(MethodArgumentNotValidException exception) {

        String typeException = "NOT VALID EXCEPTION";

        ErrorResponse errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                EExceptionMessage.REQUEST_WRONG_DATA.getMessage(),
                collectErrors(exception));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }*/

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