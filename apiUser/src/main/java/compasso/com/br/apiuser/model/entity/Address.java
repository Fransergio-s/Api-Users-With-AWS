package compasso.com.br.apiuser.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Address {
    @Id
    private String zipCode;
    private String street;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;

}
