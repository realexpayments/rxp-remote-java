package com.realexpayments.remote.sdk.domain;

import com.realexpayments.remote.sdk.domain.payment.Comment;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Domain object representing Payer information to be passed to Realex.
 * </p>
 *
 * <p><code><pre>
 * Payer payer = new Payer()
 * .addType("Business")
 * .addRef("smithj01")
 * .addTitle("Mr")
 * .addFirstName("John")
 * .addSurname("Smith")
 * .addCompany("Acme")
 * .addAddress(address)
 * .addHomePhoneNumber("+35317285355")
 * .addWorkPhoneNumber("+35317433923")
 * .addFaxPhoneNumber("+35317893248")
 * .addMobilePhoneNumber("+353873748392")
 * .addEmail("jsmith@acme.com")
 * .addComment("Comment1")
 * .addComment("Comment2");
 *
 * </pre></code></p>
 *
 * @author vicpada
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Payer {

    /**
     * The payer type can be used to identify the category of the Payer.
     * This can be defaulted to “Business”
     */
    @XmlAttribute(name = "type")
    private String type;

    /**
     * The payer ref is the reference for this customer. It must be unique.
     */
    @XmlAttribute(name = "ref")
    private String ref;

    /**
     * The payer’s title
     */
    @XmlElement(name = "title")
    private String title;

    /**
     * First name of payer.
     */
    @XmlElement(name = "firstname")
    private String firstName;

    /**
     * Surname of payer
     */
    @XmlElement(name = "surname")
    private String surname;

    /**
     * Company Name
     */
    @XmlElement(name = "company")
    private String company;

    /**
     * {@link PayerAddress} object containing the payer address.
     */
    @XmlElement(name = "address")
    private PayerAddress address;

    /**
     * {@link PhoneNumbers} object containing the payer phone numbers.
     */
    @XmlElement(name = "phonenumbers")
    private PhoneNumbers phoneNumbers;

    /**
     * The payer email
     */
    @XmlElement(name = "email")
    private String email;

    /**
     * List of {@link Comment} objects to be passed in request.
     * Optionally, up to two comments can be associated with any payer.
     */
    @XmlElementWrapper(name = "comments")
    @XmlElements(@XmlElement(name = "comment", type = Comment.class))
    private List<Comment> comments;

    /**
     * Constructor
     */
    public Payer() {
    }

    /**
     * Getter for type
     *
     * @return String
     */
    public String getType() {
        return type;
    }

    /**
     * Setter for type
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for ref
     *
     * @return String
     */
    public String getRef() {
        return ref;
    }

    /**
     * Setter for ref
     *
     * @param ref
     */
    public void setRef(String ref) {
        this.ref = ref;
    }

    /**
     *
     * Getter for title
     *
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for title
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for firstName
     *
     * @return String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for firstName
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for surname
     *
     * @return String
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Setter for surname
     *
     *  @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Getter for company
     *
     * @return String
     */
    public String getCompany() {
        return company;
    }

    /**
     * Setter for company
     *
     * @param company
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * Getter for address
     *
     * @return Address
     */
    public PayerAddress getAddress() {
        return address;
    }

    /**
     * Setter for address
     *
     * @param address
     */
    public void setAddress(PayerAddress address) {
        this.address = address;
    }

    /**
     * Getter for phoneNumbers
     *
     * @return PhoneNumbers
     */
    public PhoneNumbers getPhoneNumbers() {
        return phoneNumbers;
    }

    /**
     * Setter for phoneNumbers
     *
     * @param phoneNumbers
     */
    public void setPhoneNumbers(PhoneNumbers phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    /**
     * Getter for email
     *
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for email
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for {@link Comment} list.
     *
     * @return List<Comment>
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * Setter for comments
     *
     * @param comments
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    /**
     * Helper method for adding a payer home phone number.
     *
     * @param phoneNumber
     * @return Payer
     */
    public Payer addHomePhoneNumber(String phoneNumber) {

        //create new comments array list if null
        if (null == this.phoneNumbers) {
            this.phoneNumbers = new PhoneNumbers();
        }

        this.phoneNumbers.setHomePhoneNumber(phoneNumber);
        return this;
    }

    /**
     * Helper method for adding a payer work phone number.
     *
     * @param phoneNumber
     * @return Payer
     */
    public Payer addWorkPhoneNumber(String phoneNumber) {

        //create new comments array list if null
        if (null == this.phoneNumbers) {
            this.phoneNumbers = new PhoneNumbers();
        }

        this.phoneNumbers.setWorkPhoneNumber(phoneNumber);
        return this;
    }

    /**
     * Helper method for adding a payer fax phone number.
     *
     * @param phoneNumber
     * @return Payer
     */
    public Payer addFaxPhoneNumber(String phoneNumber) {

        //create new comments array list if null
        if (null == this.phoneNumbers) {
            this.phoneNumbers = new PhoneNumbers();
        }

        this.phoneNumbers.setFaxPhoneNumber(phoneNumber);
        return this;
    }

    /**
     * Helper method for adding a payer mobile phone number.
     *
     * @param phoneNumber
     * @return Payer
     */
    public Payer addMobilePhoneNumber(String phoneNumber) {

        //create new comments array list if null
        if (null == this.phoneNumbers) {
            this.phoneNumbers = new PhoneNumbers();
        }

        this.phoneNumbers.setMobilePhoneNumber(phoneNumber);
        return this;
    }


    /**
     * Helper method for adding a payer ref.
     *
     * @param ref
     * @return Payer
     */
    public Payer addRef(String ref) {
        this.ref = ref;
        return this;
    }

    /**
     * Helper method for adding a payer title.
     *
     * @param title
     * @return Payer
     */
    public Payer addTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Helper method for adding a payer first name.
     *
     * @param firstName
     * @return Payer
     */
    public Payer addFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Helper method for adding a payer type.
     *
     * @param surname
     * @return Payer
     */
    public Payer addSurname(String surname) {
        this.surname = surname;
        return this;
    }

    /**
     * Helper method for adding a payer type.
     *
     * @param company
     * @return Payer
     */
    public Payer addCompany(String company) {
        this.company = company;
        return this;
    }

    /**
     * Helper method for adding a payer type.
     *
     * @param address
     * @return Payer
     */
    public Payer addPayerAddress(PayerAddress address) {
        this.address = address;
        return this;
    }

    /**
     * Helper method for adding a payer type.
     *
     * @param type
     * @return Payer
     */
    public Payer addType(String type) {
        this.type = type;
        return this;
    }

    /**
     * Helper method for adding a payer type.
     *
     * @param email
     * @return Payer
     */
    public Payer addEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * Helper method for adding a comment. NB Only 2 comments will be accepted by Realex.
     *
     * @param comment
     * @return Payer
     */
    public Payer addComment(String comment) {
        //create new comments array list if null
        if (null == this.comments) {
            this.comments = new ArrayList<Comment>();
        }

        int size = comments.size();
        this.comments.add(new Comment().addComment(comment).addId(++size));
        return this;
    }


}



