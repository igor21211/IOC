package org.example.app;

import org.example.app.data.Person;
import org.example.container.annotations.Component;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;
@Component
public class Menu {


    public void start() {
        Scanner scanner =  new Scanner(System.in);
        System.out.println("Hello, its simple console application name is MyContacts");
        System.out.println("Please select the number of action what you need");
        System.out.println("Select 1:Add new contact to your ContactsBook");
        System.out.println("Select 2:Search contact by phone number");
        System.out.println("Select 3:Search contact by email that start with");
        System.out.println("Select 4:Search contact by name");
        System.out.println("Please put number of action: ");
        if(scanner.hasNextInt()) {
            command(scanner.nextInt());
        }else {
            System.out.println("Wrong input");
        }
    }

    public void command(int action) {
        switch (action) {
            case 1:
                saveContacts();
                break;
            case 2:
                searchContactsByPhoneNumber();
                break;
            case 3:
                searchContactsByEmailStartWith();
                break;
            case 4:
                searchContactByName();
                break;
            default:
                System.out.println("your number is dont have any action, or input is not available");
        }

    }

    private void searchContactByName() {
        Scanner scanner =  new Scanner(System.in);
        System.out.println("Please write name what you want to find");
        String name = scanner.nextLine();
        List<Person> results = PersonRepository.getPersonByName(name);
        if(results.size()!=0) {
            results.forEach(System.out::println);
        }else{
            System.out.println("Dont have contacts with this name in ours contacts book");
        }
        answerNow();
    }

    private void searchContactsByEmailStartWith() {
        Scanner scanner =  new Scanner(System.in);
        System.out.println("Please write character which starts email, to find him");
        String startWitch = scanner.nextLine();
        List<Person> results = PersonRepository.getPersonByStartWithEmail(startWitch);
        if(results.size()!=0) {
            results.forEach(System.out::println);
        }else{
            System.out.println("Email who start with your characters dont have in ours contacts book");
        }
        answerNow();
    }

    private void searchContactsByPhoneNumber() {
        Scanner scanner =  new Scanner(System.in);
        System.out.println("Please write phone number a contact, to find him");
        Long phoneNumber = scanner.nextLong();
        Person person = PersonRepository.getPersonByPhoneNumber(phoneNumber);
        if(person.getPhoneNumber()!= null) {
            System.out.println(person);
        }else{
            System.out.println("Dont have contact with this phone number");
        }
        answerNow();
    }

    private void saveContacts() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("So let start to create new contact in your contacts book");
        System.out.println("Please write first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Please write last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Please write email: ");
        String email = scanner.nextLine();
        System.out.println("Please write country where contact from: ");
        String country = scanner.nextLine();
        System.out.println("At least write phone number of contact: ");
        Long phoneNumber = scanner.nextLong();
        int result =  PersonRepository.save(new Person(firstName,lastName,email,country,phoneNumber));
        if(result <1){
            System.out.println("Something wrong, your contacts is dont add to you book");
        }else{
            System.out.println("successfully!!");
        }
        answerNow();
    }

    private void answerNow(){
        Scanner scanner =  new Scanner(System.in);
        System.out.println("Do you want more work with console app?Y/N");
        String answer = scanner.nextLine();
        if(answer.equalsIgnoreCase("Y")){
            start();
        }else {
            System.out.println("Thank you for using app. MyContacts, Have a nice day");
        }
    }
}
