package practice12;

public class PhonePerson {
    private String phone, adress, name;

    PhonePerson(String phone, String adress, String name){
        this.phone = phone;
        this.adress = adress;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "PhonePerson{" +
                "phone='" + phone + '\'' +
                ", adress='" + adress + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
