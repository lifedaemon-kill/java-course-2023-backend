package apiErrorResponse;

public record ApiErrorResponse (
    String description,
    String code,
    String exceptionName,
    String exceptionMessage,
    StackTraceElement[] stacktrace) {
}
