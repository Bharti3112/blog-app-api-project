package com.blogapplication.api.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;


@Entity
@Table(name = "users")
public class Users implements UserDetails {
	
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
@Column(name = "id")
private int id;

@Column(name = "user_name", nullable = false, length = 100)
private String name;

@Column(name = "email")
private String  email;

@Column(name = "password")
private String  password;

@Column(name = "about")
private String  about;

@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
private List<Post> posts=new ArrayList<>();

@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user", referencedColumnName = "id"),
inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "id"))
private Set<Role> roles = new HashSet<>();

public Users() {
}

public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

// Getter and Setter for name
public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

// Getter and Setter for email
public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       List<SimpleGrantedAuthority> authorities =  this.roles.stream().map((role)->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
       return authorities;
    }

    // Getter and Setter for password
public String getPassword() {
    return  this.password;
}

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void setPassword(String password) {
    this.password = password;
}

// Getter and Setter for about
public String getAbout() {
    return about;
}

public void setAbout(String about) {
    this.about = about;
}

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
