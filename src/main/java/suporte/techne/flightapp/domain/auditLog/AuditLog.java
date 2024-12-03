package suporte.techne.flightapp.domain.auditLog;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "audit_log")
@Entity(name = "AuditLog")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class AuditLog {
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id")
    private UUID id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "login_time", nullable = false)
    private LocalDateTime loginTime;

    @Column(name = "logout_time")
    private LocalDateTime logoutTime;

    @Column(name = "ip_address", length = 45)
    private String ipAddress;

    @Enumerated(EnumType.STRING)
    private LoginStatus loginStatus;

    @Column(name = "user_agent", columnDefinition = "TEXT")
    private String userAgent;

    @Column(name = "host_name")
    private String hostName;

    @Column(name = "server_name")
    private String serverName;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
