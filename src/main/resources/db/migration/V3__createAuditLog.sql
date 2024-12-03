CREATE TABLE audit_log (
    id UUID PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    login_time TIMESTAMP NOT NULL,
    logout_time TIMESTAMP,
    ip_address VARCHAR(45),
    login_status VARCHAR(50) NOT NULL,
    user_agent TEXT,
    host_name VARCHAR(255),
    server_name VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE OR REPLACE FUNCTION update_timestamp()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated_at = NOW();
   RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_audit_log_timestamp
BEFORE UPDATE ON audit_log
FOR EACH ROW
EXECUTE FUNCTION update_timestamp();