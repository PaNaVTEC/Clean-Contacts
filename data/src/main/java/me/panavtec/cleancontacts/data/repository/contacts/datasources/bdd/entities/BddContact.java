package me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities;

import com.j256.ormlite.field.DatabaseField;
import com.mobandme.android.transformer.compiler.Mappable;
import com.mobandme.android.transformer.compiler.Mapped;
import me.panavtec.cleancontacts.domain.entities.Contact;

@Mappable(with = Contact.class)
public class BddContact {

    public static final String FIELD_MD5 = "md5";
    public static final String FIELD_ID = "id";

    @DatabaseField(generatedId = true, columnName = FIELD_ID) private int id;
    @DatabaseField @Mapped public String gender;
    @DatabaseField(foreign = true, foreignAutoRefresh = true) @Mapped public BddName name;
    @DatabaseField(foreign = true, foreignAutoRefresh = true) @Mapped public BddLocation location;
    @DatabaseField @Mapped public String email;
    @DatabaseField @Mapped public String username;
    @DatabaseField @Mapped public String password;
    @DatabaseField @Mapped public String salt;
    @DatabaseField(columnName = FIELD_MD5) @Mapped public String md5;
    @DatabaseField @Mapped public String sha1;
    @DatabaseField @Mapped public String sha256;
    @DatabaseField @Mapped public String registered;
    @DatabaseField @Mapped public String dob;
    @DatabaseField @Mapped public String phone;
    @DatabaseField @Mapped public String cell;
    @DatabaseField @Mapped public String SSN;
    @DatabaseField(foreign = true, foreignAutoRefresh = true) @Mapped public BddPicture picture;
    @DatabaseField @Mapped public String version;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public BddName getName() {
        return name;
    }

    public void setName(BddName name) {
        this.name = name;
    }

    public BddLocation getLocation() {
        return location;
    }

    public void setLocation(BddLocation location) {
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

    public BddPicture getPicture() {
        return picture;
    }

    public void setPicture(BddPicture picture) {
        this.picture = picture;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
