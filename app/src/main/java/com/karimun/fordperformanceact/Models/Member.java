package com.karimun.fordperformanceact.Models;

public class Member {

    String username, email, memberRole, membershipExpiry;
    boolean isAdmin;

    Member() {}

    Member(String username, String email, boolean isAdmin, String memberRole, String membershipExpiry) {
        this.username = username;
        this.email = email;
        this.isAdmin = isAdmin;
        this.memberRole = memberRole;
        this.membershipExpiry = membershipExpiry;
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
