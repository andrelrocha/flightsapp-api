package suporte.techne.flightapp.domain.auditLog;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuditLogRepository extends JpaRepository<AuditLog, UUID> {
}
