package suporte.techne.flightapp.domain.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import suporte.techne.flightapp.domain.country.Country;
import suporte.techne.flightapp.domain.user.DTO.UserCreateDTO;
import suporte.techne.flightapp.domain.user.DTO.UserForgotDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Table(name = "users")
@Entity(name = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id")
    private UUID id;

    @Column(name = "login", nullable = false, length = 100)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "username", unique = true, length = 20)
    private String username;

    @Column(name = "name", length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

    @Column(name = "social_number", unique = true, length = 30)
    private String socialNumber;

    @Column(name = "role", nullable = false, length = 100)
    private UserRole role;

    @Column(name = "phone", length = 14)
    private String phone;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "theme", length = 20)
    private UserTheme theme;

    @Column(name = "token_expiration")
    private LocalDateTime tokenExpiration;

    @Column(name = "token_mail")
    private String tokenMail;

    @Column(name = "access_failed_count")
    private Integer accessFailedCount;

    @Column(name = "lockout_enabled")
    private Boolean lockoutEnabled;

    @Column(name = "lockout_end")
    private LocalDateTime lockoutEnd;

    @Column(name = "two_factor_enabled")
    private Boolean twoFactorEnabled;

    @Column(name = "refresh_token_enabled")
    private Boolean refreshTokenEnabled;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public User(UserCreateDTO dto) {
        this.login = dto.data().login();
        this.password = dto.data().password();
        this.username = dto.data().username();
        this.name = dto.data().name();
        this.socialNumber = dto.data().socialNumber();
        this.phone = dto.data().phone();
        this.birthday = dto.data().birthday();
        this.country = dto.country();
        this.twoFactorEnabled = dto.data().twoFactorEnabled();
        this.refreshTokenEnabled = dto.data().refreshTokenEnabled();
        this.theme = UserTheme.valueOf(dto.data().theme());
        this.role = UserRole.USER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),new SimpleGrantedAuthority("ROLE_USER"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !lockoutEnabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !lockoutEnabled;
    }

    public void setPassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    public void setAdmin() { this.role = UserRole.ADMIN; }


    public void forgotPassword(UserForgotDTO data) {
        this.tokenMail = data.tokenMail();
        this.tokenExpiration = data.tokenExpiration();
    }

    public void resetAccessCount() {
        this.accessFailedCount = 0;
        this.setLockoutEnabled(false);
        this.setLockoutEnd(null);
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public void updateCountry(Country country) {
        this.country = country;
    }

    public void updateBirthday(LocalDate date) { this.birthday = date; }

    public void setTokenExpiration(LocalDateTime time) {
        this.tokenExpiration = time;
    }

    public boolean isRefreshTokenEnabled() {
        return this.refreshTokenEnabled;
    }

    public boolean isTwoFactorEnabled() {
        return this.twoFactorEnabled;
    }
}
