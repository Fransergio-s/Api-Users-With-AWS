package compasso.com.br.apiuser.model.entity;

import jakarta.persistence.*;
import lombok.*;
import compasso.com.br.apiuser.model.entity.Address;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    @ManyToOne
    @JoinColumn(name = "address_zip_code")
    private Address address;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
