package com.example.blizoo.blizoodiplom.models;

import java.io.Serializable;

/**
 * Created by ltd158 on 1/26/15.
 */

public class NearbyClientsContracts {

    private String mName = "";
    private String mFamily = "";
    private String mPhone = "";

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getFamily() {
        return mFamily;
    }

    public void setFamily(String mFamily) {
        this.mFamily = mFamily;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String mPhone) {
        this.mPhone = mPhone;
    }
}
