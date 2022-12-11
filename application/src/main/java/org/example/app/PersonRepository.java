package org.example.app;

import org.example.app.data.Person;
import org.example.container.annotations.Bean;
import org.example.container.annotations.Component;
import org.example.container.annotations.Configuration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonRepository {

    public static Connection getConnection(){
        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/contacts";
        String user = "postgres";
        String password = "0191";

        try {
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
        return connection;
    }


    public static int save(Person person) {
        int status = 0;
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "insert into person(first_name, last_name, email, country, phone_number)" +
                            " values (?,?,?,?,?)");
            ps.setString(1, person.getFirstName());
            ps.setString(2, person.getLastName());
            ps.setString(3, person.getEmail());
            ps.setString(4, person.getCountry());
            ps.setLong(5, person.getPhoneNumber());

            status = ps.executeUpdate();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return status;
    }

    public static Person getPersonByPhoneNumber(Long phoneNumber){
        Person person = new Person();
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from person where phone_number=?");
            ps.setLong(1,phoneNumber);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                person.setFirstName(rs.getString("first_name"));
                person.setLastName(rs.getString("last_name"));
                person.setEmail(rs.getString("email"));
                person.setCountry(rs.getString("country"));
                person.setPhoneNumber(rs.getLong("phone_number"));
            }
            connection.close();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return person;
    }

    public static List<Person> getPersonByStartWithEmail(String startEmail){
       List<Person> persons = new ArrayList<>();
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from person where email like ?");
            ps.setString(1, startEmail+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Person person = new Person();
                person.setFirstName(rs.getString("first_name"));
                person.setLastName(rs.getString("last_name"));
                person.setEmail(rs.getString("email"));
                person.setCountry(rs.getString("country"));
                person.setPhoneNumber(rs.getLong("phone_number"));
                persons.add(person);
            }
            connection.close();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return persons;
    }

    public static List<Person> getPersonByName(String name){
        List<Person> persons = new ArrayList<>();
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from person where first_name=?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Person person = new Person();
                person.setFirstName(rs.getString("first_name"));
                person.setLastName(rs.getString("last_name"));
                person.setEmail(rs.getString("email"));
                person.setCountry(rs.getString("country"));
                person.setPhoneNumber(rs.getLong("phone_number"));
                persons.add(person);
            }
            connection.close();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return persons;
    }
}
