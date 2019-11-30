package beans;

public class BookStore {

    private int bookStoreId;
    private String name;
    private String street;
    private String city;
    private String country;
    private int zip;

    public int getBookStoreId() {
        return bookStoreId;
    }

    public void setBookStoreId(int bookStoreId) {
        this.bookStoreId = bookStoreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }
}
