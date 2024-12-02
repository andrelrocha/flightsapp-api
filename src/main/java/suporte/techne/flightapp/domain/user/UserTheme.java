package suporte.techne.flightapp.domain.user;

import lombok.Getter;

@Getter
public enum UserTheme {
    DARK("dark"),
    LIGHT("light");

    private String theme;

    UserTheme(String theme) {
        this.theme = theme;
    }
}
