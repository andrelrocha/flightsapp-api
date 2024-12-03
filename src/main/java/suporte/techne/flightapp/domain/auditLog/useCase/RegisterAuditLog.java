package suporte.techne.flightapp.domain.auditLog.useCase;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import suporte.techne.flightapp.domain.auditLog.AuditLog;
import suporte.techne.flightapp.domain.auditLog.AuditLogRepository;
import suporte.techne.flightapp.domain.auditLog.LoginStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class RegisterAuditLog {
    @Autowired
    private AuditLogRepository auditLogRepository;

    //Isola a transação para que se torne independente em caso de exceção lançada que geraria o rollback quando fosse chamado em outra parte do código
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logLogin(String userName, HttpServletRequest request, LoginStatus loginStatus, String userAgent) {
        AuditLog auditLog = new AuditLog();

        auditLog.setUserName(userName);
        auditLog.setLoginTime(LocalDateTime.now());
        auditLog.setIpAddress(request.getRemoteAddr());
        auditLog.setLoginStatus(loginStatus);
        auditLog.setUserAgent(userAgent);
        auditLog.setHostName(request.getRemoteHost());
        auditLog.setServerName(request.getServerName());

        auditLogRepository.save(auditLog);
    }
}
