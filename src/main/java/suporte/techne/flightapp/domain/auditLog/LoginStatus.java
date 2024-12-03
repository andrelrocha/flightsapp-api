package suporte.techne.flightapp.domain.auditLog;

public enum LoginStatus {
    SUCCESS("success"),
    FAILURE("failure");

    private String status;

    LoginStatus(String status) {
        this.status = status;
    }
}
