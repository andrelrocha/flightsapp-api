package suporte.techne.flightapp.domain.user;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("admin"),
    USER("user"),
    PASSENGER("passenger"),
    ATTENDANT("attendant"),
    PILOT("pilot"),
    FLIGHT_CREW("flight_crew"),
    CUSTOMER_SUPPORT("customer_support"),
    MANAGER("manager"),
    MAINTENANCE("maintenance");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

}
