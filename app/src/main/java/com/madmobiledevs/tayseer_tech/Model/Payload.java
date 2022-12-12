package com.madmobiledevs.tayseer_tech.Model;

import com.google.gson.annotations.SerializedName;
import com.madmobiledevs.tayseer_tech.SubClasses.SystemId;

public class Payload {
    @SerializedName("payload")
    SystemId mSystemId;

    public Payload(SystemId systemId) {
        this.mSystemId=systemId;
    }
}
