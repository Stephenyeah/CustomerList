package fi.haagahelia.customer.domain;

public class Customer {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    private String firstName;
    private String lastName;

    public Customer(){
        this.id = 0;
        this.firstName = null;
        this.lastName = null;
    }

    public Customer(String firstName, String lastName) {
        this.id = 0;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("Person [id=%d, firstName= '%s', lastName ='%s']",id, firstName, lastName);
    }
}
