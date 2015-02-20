package me.panavtec.cleancontacts.data.repository.contacts.datasources.api.entities;

import com.google.gson.annotations.Expose;
import com.mobandme.android.transformer.compiler.Mappable;
import com.mobandme.android.transformer.compiler.Mapped;
import me.panavtec.cleancontacts.domain.entities.Contact;

@Mappable(with = Contact.class)
public class ApiContact {

    @Expose @Mapped public String gender;
    @Expose @Mapped public ApiName name;
    @Expose @Mapped public ApiLocation location;
    @Expose @Mapped public String email;
    @Expose @Mapped public String username;
    @Expose @Mapped public String password;
    @Expose @Mapped public String salt;
    @Expose @Mapped public String md5;
    @Expose @Mapped public String sha1;
    @Expose @Mapped public String sha256;
    @Expose @Mapped public String registered;
    @Expose @Mapped public String dob;
    @Expose @Mapped public String phone;
    @Expose @Mapped public String cell;
    @Expose @Mapped public String SSN;
    @Expose @Mapped public ApiPicture picture;
    @Expose @Mapped public String version;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ApiName getName() {
        return name;
    }

    public void setName(ApiName name) {
        this.name = name;
    }

    public ApiLocation getLocation() {
        return location;
    }

    public void setLocation(ApiLocation location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public String getSha256() {
        return sha256;
    }

    public void setSha256(String sha256) {
        this.sha256 = sha256;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public ApiPicture getPicture() {
        return picture;
    }

    public void setPicture(ApiPicture picture) {
        this.picture = picture;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
