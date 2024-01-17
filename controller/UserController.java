package HW_Exception.HW_Sem_03.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import HW_Exception.HW_Sem_03.model.FileIOException;
import HW_Exception.HW_Sem_03.model.InvalidDataFormatException;
import HW_Exception.HW_Sem_03.model.Person;

public class UserController {

    public String getUserData() {
        String input = null;
        System.out.println("Введите следующие данные, разделенные пробелом: " +
                "Фамилия Имя Отчество Дата_рождения Номер_телефона пол\n" +
                "Например: Иванов Иван Иванович 01.01.1990 +74951111111 м");
        try (Scanner scan = new Scanner(System.in, "cp866")) {
            input = scan.nextLine();
        }
        return input;
    }

    public Person parseUserData(String input) throws InvalidDataFormatException, ParseException {
        String[] userData = input.split("\\s+");

        if (userData.length != 6) {
            throw new InvalidDataFormatException("Неверное количество данных. Введите корректные данные.");
        }

        String surname = userData[0];
        String name = userData[1];
        String patronymic = userData[2];
        String birthdateStr = userData[3];
        String phone = userData[4];
        String sex = userData[5];

        validateName(surname, "Фамилия");
        validateName(name, "Имя");
        validateName(patronymic, "Отчество");
        Date birthdate = validateDate(birthdateStr);
        validatePhone(phone);
        validateSex(sex);

        return new Person(surname, name, patronymic, birthdate, phone, sex);
    }

    public void processUserInput(String input) {
        try {
            Person person = parseUserData(input);
            writeToFile(person);
            displaySuccessMessage();
        } catch (InvalidDataFormatException | FileIOException e) {
            displayErrorMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validateName(String name, String fieldName) throws InvalidDataFormatException {
        if (!name.matches("[а-яА-Я]+")) {
            throw new InvalidDataFormatException(String.format("Поле <%s> должно содержать только буквы.", fieldName));
        }
    }

    private Date validateDate(String date) throws InvalidDataFormatException, ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setLenient(false);
        Date resultDate = null;

        try {
            resultDate = dateFormat.parse(date);
        } catch (ParseException e) {
            throw new InvalidDataFormatException("Неверный формат даты. Используйте формат dd.MM.yyyy.");
        }

        if (date.length() == dateFormat.toPattern().length()) {
            Date currentDate = new Date();
            long ageInMillis = currentDate.getTime() - resultDate.getTime();
            long ageInYears = ageInMillis / (1000L * 60 * 60 * 24 * 365);

            if (ageInYears > 200) {
                throw new InvalidDataFormatException(String.format("Вы не можете быть настолько стары (%s лет) )))", ageInYears));
            }
        } else {
            throw new InvalidDataFormatException("Неверное количество символов в дате. Используйте формат dd.MM.yyyy.");
        }
        return resultDate;
    }

    private void validatePhone(String phoneNumber) throws InvalidDataFormatException {
        if (!phoneNumber.matches("(\\+*)\\d{11}")) {
            throw new InvalidDataFormatException(
                    "Номер телефона должен содержать 11 цифр. Знак '+' может отсутствовать.");
        }
    }

    private void validateSex(String sexStr) throws InvalidDataFormatException {
        char sex = sexStr.charAt(0);
        if (sex != 'ж' && sex != 'м') {
            throw new InvalidDataFormatException("Неверный формат пола. Используйте символы 'ж' или 'м'.");
        }
    }

    public void displayErrorMessage(String message) {
        System.out.println("Ошибка: " + message);
    }

    public void displaySuccessMessage() {
        System.out.println("Данные успешно записаны в файл.");
    }

    private void writeToFile(Person person) throws FileIOException {
        try (FileWriter fw = new FileWriter(new File("./HW_Exception/HW_Sem_03/files/"
                /* при необходимости поправить путь к файлу */ + person.getSurname() + ".txt"), true)) {
            fw.write(person.toString());
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileIOException("Ошибка при записи в файл.", e);
        }
    }

}