package org.example.data;

public class ClientDto {

    private Long id;
    private String firstname;
    private String lastname;
    private String zipcode;

    public ClientDto() {
    }

    public ClientDto(Long id, String firstname, String lastname, String zipcode) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.zipcode = zipcode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
