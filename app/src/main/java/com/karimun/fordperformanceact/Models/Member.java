package com.karimun.fordperformanceact.Models;

public class Member {

    String memberId, username, email, memberRole, membershipExpiry, address, firstName, surname, mobilePhone, postcode, spouseName, state, suburb;
    boolean isAdmin;

    Member() {}

    public Member(String memberId, String username, String email, String memberRole
            , String membershipExpiry, String address, String firstName, String surname
            , String mobilePhone, String postcode, String spouseName, String state
            , String suburb, boolean isAdmin) {

        this.memberId = memberId;
        this.username = username;
        this.email = email;
        this.memberRole = memberRole;
        this.membershipExpiry = membershipExpiry;
        this.address = address;
        this.firstName = firstName;
        this.surname = surname;
        this.mobilePhone = mobilePhone;
        this.postcode = postcode;
        this.spouseName = spouseName;
        this.state = state;
        this.suburb = suburb;
        this.isAdmin = isAdmin;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(String memberRole) {
        this.memberRole = memberRole;
    }

    public String getMembershipExpiry() {
        return membershipExpiry;
    }

    public void setMembershipExpiry(String membershipExpiry) {
        this.membershipExpiry = membershipExpiry;
    }
}
