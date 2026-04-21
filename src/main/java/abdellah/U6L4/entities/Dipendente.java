package abdellah.U6L4.entities;


import abdellah.U6L4.enums.Ruolo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table (name = "dipendenti")
@JsonIgnoreProperties({"accountNonExpired","accountNonLocked","authorities","credentialsNonExpired",  "enabled"})
public class Dipendente implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "name", nullable = false)
    private String nome;

    @Column(name = "surname", nullable = false)
    private String cognome;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Ruolo role;

    public Dipendente(String username, String nome, String cognome, String email, String password) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.role = Ruolo.USER;
    }

    public Collection <? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }
}
