package api.errorResponse;

public record ApiErrorResponse(
    String description,
    String code,
    String exceptionName,
    String exceptionMessage,
    StackTraceElement[] stacktrace) {
}
