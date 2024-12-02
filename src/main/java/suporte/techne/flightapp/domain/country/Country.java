package suporte.techne.flightapp.domain.country;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Table(name = "countries")
@Entity(name = "Country")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Country {
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id", columnDefinition = "UUID")
    private UUID id;

    private String name;

    @Column(name = "iso_code")
    @Length(max = 3)
    private String isoCode;
}