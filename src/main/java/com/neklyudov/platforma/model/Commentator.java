package com.neklyudov.platforma.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class Commentator {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Commentator(){}

    public Commentator(String firstName){
        this.firstName = firstName;
    }

    public static CommentatorBuilder builder() {
        return new CommentatorBuilder();
    }

    public static final class CommentatorBuilder {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String password;


        private CommentatorBuilder() {
        }

        public CommentatorBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CommentatorBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public CommentatorBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public CommentatorBuilder email(String email) {
            this.email = email;
            return this;
        }

        public CommentatorBuilder password(String password) {
            this.password = password;
            return this;
        }

        public Commentator build() {
            Commentator commentator = new Commentator();
            commentator.setId(id);
            commentator.setFirstName(firstName);
            commentator.setLastName(lastName);
            commentator.setEmail(email);
            commentator.setPassword(password);
            return commentator;
        }
    }
}
