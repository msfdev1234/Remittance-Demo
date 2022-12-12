package com.madmobiledevs.tayseer_tech.Model;

import com.google.gson.annotations.SerializedName;
import com.madmobiledevs.tayseer_tech.SubClasses.Inner;
import com.madmobiledevs.tayseer_tech.SubClasses.PCountryId;
import com.madmobiledevs.tayseer_tech.SubClasses.SCountryId;
import com.madmobiledevs.tayseer_tech.SubClasses.SystemId;

public class Payload1 {
    @SerializedName("payload")
    Inner inner = new Inner();

    public Payload1() {}


}
