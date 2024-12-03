CREATE TABLE users (
    id UUID PRIMARY KEY,
    login VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    username VARCHAR(20) UNIQUE,
    name VARCHAR(100),
    country_id UUID REFERENCES countries(id),
    social_number VARCHAR(30) UNIQUE,
    role VARCHAR(100) NOT NULL,
    phone VARCHAR(14),
    birthday DATE,
    theme VARCHAR(20),
    token_expiration TIMESTAMP,
    token_mail VARCHAR(255),
    access_failed_count INT DEFAULT 0,
    lockout_enabled BOOLEAN DEFAULT FALSE,
    lockout_end TIMESTAMP NULL,
    two_factor_enabled BOOLEAN DEFAULT FALSE,
    refresh_token_enabled BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE OR REPLACE FUNCTION update_timestamp()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_user_timestamp
    BEFORE UPDATE ON users
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamp();