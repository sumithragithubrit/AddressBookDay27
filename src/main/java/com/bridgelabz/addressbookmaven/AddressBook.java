package com.bridgelabz.addressbookmaven;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class  AddressBook {

    ArrayList<ContactPerson> contactList = new ArrayList<>();
    HashMap<String,ArrayList<ContactPerson>> contactsByCity = new HashMap<>();
    HashMap<String,ArrayList<ContactPerson>> contactsByState = new HashMap<>();

    public void addContact() {
        ContactPerson contactPerson = new ContactPerson();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the details of contact person");
        System.out.print("Enter first name:");
        contactPerson.setName(sc.next());          // used to read and set the contact person firstname
        System.out.print("Enter Last name:");
        contactPerson.setLastName(sc.next());
        System.out.println("Enter the Address : ");
        contactPerson.setAddress(sc.next());
        System.out.println("Enter the City : ");
        contactPerson.setCity(sc.next());
        System.out.println("Enter the State : ");
        contactPerson.setState(sc.next());
        System.out.println("Enter the ZipCode : ");
        contactPerson.setZipCode(sc.next());
        System.out.println("Enter the Mobile no : ");
        contactPerson.setPhoneNo(sc.next());
        contactList.add(contactPerson);             // contactList is the instance of arraylist and here we will add contact person into contactlist


        // here this "if" is used to add the person to corresponding city in the contactsByCity map
        if(contactsByCity.containsKey(contactPerson.getCity())){          // here we use containskey to check  contacts are already there for the contact persons c city
            ArrayList<ContactPerson> contacts = contactsByCity.get(contactPerson.getCity());    // get the list of contacts by city
            contacts.add(contactPerson);
            contactsByCity.put(contactPerson.getCity(), contacts);
        } else{
            ArrayList<ContactPerson> list = new ArrayList<>(); // create an empty array list for adding first contact
            list.add(contactPerson);
            contactsByCity.put(contactPerson.getCity(), list);
        }

        // here this "if" is used to add the person to corresponding state in the contactsByState map
        if(contactsByState.containsKey(contactPerson.getState())){
            ArrayList<ContactPerson> contacts = contactsByState.get(contactPerson.getState());
            contacts.add(contactPerson);
            contactsByCity.put(contactPerson.getState(), contacts);
        } else{
            ArrayList<ContactPerson> list = new ArrayList<>();
            list.add(contactPerson);
            contactsByState.put(contactPerson.getState(), new ArrayList<>(list));
        }
    }

    @Override
    public String toString() {
        return "AddressBook{" +
                "contactList=" + contactList +
                '}';
    }

    public void editContact() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first name:");
        String name = sc.next();
        for (ContactPerson contactPerson : contactList) {
            if (name.equals(contactPerson.getName())) {     // here if the name that we read the name same as contact person then we can edit the contact person
                String city = contactPerson.getCity();
                String state = contactPerson.getState();
                System.out.println("Set Details");
                System.out.print("Enter first name:");
                contactPerson.setName(sc.next());
                System.out.print("Enter Last name:");
                contactPerson.setLastName(sc.next());
                System.out.println("Enter the Address : ");
                contactPerson.setAddress(sc.next());
                System.out.println("Enter the City : ");
                contactPerson.setCity(sc.next());
                System.out.println("Enter the State : ");
                contactPerson.setState(sc.next());
                System.out.println("Enter the ZipCode : ");
                contactPerson.setZipCode(sc.next());
                System.out.println("Enter the Mobile no : ");
                contactPerson.setPhoneNo(sc.next());

                if(contactsByCity.containsKey(city)){     // here this is used to edit person in the contactByCity map also
                    for (int i=0; i<contactsByCity.get(city).size();i++){
                        if(name.equals(contactsByCity.get(city).get(i).getName())){
                            contactsByCity.get(city).remove(i);
                            contactsByCity.get(city).add(contactPerson);
                        }
                    }
                }

                if(contactsByState.containsKey(state)){   // here this is used to edit person in the contactByState map also
                    for (int i=0; i<contactsByState.get(state).size();i++){
                        if(name.equals(contactsByState.get(state).get(i).getName())){
                            contactsByState.get(state).remove(i);
                            contactsByState.get(state).add(contactPerson);
                        }
                    }
                }


                break;
            }
        }


    }

    public void deleteContact() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first name:");
        String name = sc.next();
        for (ContactPerson contactPerson : contactList) {
            if (name.equals(contactPerson.getName())) {
                contactList.remove(contactPerson);       // to remove person from contact list
                if(contactsByState.containsKey(contactPerson.getState())){      // to remove the person from  contactByState map
                    ArrayList<ContactPerson> contacts = contactsByState.get(contactPerson.getState());
                    contacts = new ArrayList<>(contacts.stream().filter(x-> x.getName() != contactPerson.getName()).toList());
                    contactsByState.put(contactPerson.getState(), contacts);
                }
                if(contactsByCity.containsKey(contactPerson.getCity())){     //  // to remove person from contact list
                    ArrayList<ContactPerson> contacts = contactsByCity.get(contactPerson.getCity());
                    contacts = new ArrayList<>(contacts.stream().filter(x-> x.getName() != contactPerson.getName()).toList());
                    contactsByCity.put(contactPerson.getCity(), contacts);
                }
                break;
            }

        }
    }

    public void sortByName(){
        //contactList.sort((person1,person2)-> person1.getName().compareTo(person2.getName()));
        contactList = new ArrayList<>(contactList.stream().sorted((person1,person2)-> person1.getName().compareTo(person2.getName())).toList());
    }

    public void sortByZip(){
        //contactList.sort((person1,person2)-> person1.getZipCode().compareTo(person2.getZipCode()));
        contactList = new ArrayList<>(contactList.stream().sorted((person1,person2)-> person1.getZipCode().compareTo(person2.getZipCode())).toList());
    }

    public void sortByCity(){
        //contactList.sort((person1,person2)-> person1.getCity().compareTo(person2.getCity()));
        contactList = new ArrayList<>(contactList.stream().sorted((person1,person2)-> person1.getCity().compareTo(person2.getCity())).toList());
    }

    public void sortByState(){
        //contactList.sort((person1,person2)-> person1.getState().compareTo(person2.getState()));
        contactList = new ArrayList<>(contactList.stream().sorted((person1,person2)-> person1.getState().compareTo(person2.getState())).toList());
    }

    public void  operation(){
        Scanner scanner = new Scanner(System.in);
        int opration;
        do {
            System.out.println(
                    "1. ADD CONTACT \n2. DISPLAY CONTACT \n3 EDIT \n4 Delete \n 5 Display by city " +
                            "\n 6 Display by state \n 7 Sort by \n8. EXIT ");
            System.out.println("Enter the Operation Number");
            opration = scanner.nextInt();
            switch (opration) {
                case 1:
                    addContact();
                    break;
                case 2:
                    System.out.println(this);
                    break;
                case 3:
                    editContact();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    System.out.println("Enter the  city");
                    String city = scanner.next();
                    System.out.println(contactsByCity.get(city));  // get method will give contact person list for the city
                    System.out.println("Number of contacts with city = " + city + " " + contactsByCity.get(city).size());
                    break;
                case 6:
                    System.out.println("Enter the  state");
                    String state = scanner.next();
                    System.out.println(contactsByState.get(state));
                    System.out.println("Number of contacts with state = " + state + " " + contactsByState.get(state).size());
                    break;
                case 7:
                    System.out.println("1. Name");
                    System.out.println("2. City");
                    System.out.println("3. State");
                    System.out.println("4. Zip");
                    int opt = scanner.nextInt();
                    switch (opt){
                        case 1:
                            this.sortByName();
                            break;
                        case 2:
                            this.sortByCity();
                            break;
                        case 3:
                            this.sortByState();
                            break;
                        case 4:
                            this.sortByZip();
                            break;
                    }
                    System.out.println(this);
                    break;
                case 8:
                    System.out.println("Exiting");
                    break;
                default:
                    System.out.println("Enter The Wrong Opration Number");
            }
        } while (opration != 8);
    }

}