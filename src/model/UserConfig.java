package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserConfig {
    private String hashedPassword;
    private String userType;
    private int privilegeLevel;

    @JsonCreator
    public UserConfig(
            @JsonProperty("hashedPassword") String hashedPassword,
            @JsonProperty("userType") String userType,
            @JsonProperty("privilegeLevel") int privilegeLevel
    ) {
        this.hashedPassword = hashedPassword;
        this.userType = userType;
        this.privilegeLevel = privilegeLevel;
    }


    @JsonProperty("hashedPassword")
    public String getHashedPassword() {
        return hashedPassword;
    }

    @JsonProperty("userType")
    public String getUserType() {
        return userType;
    }

    @JsonProperty("privilegeLevel")
    public int getPrivilegeLevel() {
        return privilegeLevel;
    }
}

