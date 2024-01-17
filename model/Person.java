package HW_Exception.HW_Sem_03.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {
    private String surname;
    private String name;
    private String patronymic;
    private Date birthdate;
    private String phone;
    private String sex;

    public Person() {
    }

    public Person(String surname, String name, String patronymic, Date birthdate, String phone, String sex) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.birthdate = birthdate;
        this.phone = phone;
        this.sex = sex;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Person person = new Person(surname, name, patronymic, birthdate, phone, sex);
        String birthdateToString = dateFormat.format(person.getBirthdate());
        return String.format("<%s><%s><%s><%s><%s><%s>" + System.lineSeparator(), surname, name, patronymic,
        birthdateToString, phone, sex);
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getPhone() {
        return phone;
    }

    public String getSex() {
        return sex;
    }

}
