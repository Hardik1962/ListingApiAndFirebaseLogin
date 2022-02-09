package com.example.hopspractical.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PatientModel(
    @SerializedName("message")
    @Expose
    var message: String? = null,

    @SerializedName("payload")
    @Expose
    var payload: List<Payload>? = null,

    @SerializedName("error")
    @Expose
    var error: Boolean? = null
)
class Payload {
    @SerializedName("firstName")
    @Expose
    var firstName: String? = null

    @SerializedName("lastName")
    @Expose
    var lastName: String? = null

    @SerializedName("gender")
    @Expose
    var gender: String? = null

    @SerializedName("dob")
    @Expose
    var dob: String? = null

    @SerializedName("patientId")
    @Expose
    var patientId: String? = null

    @SerializedName("MACAddress")
    @Expose
    var mACAddress: String? = null

    @SerializedName("createdDate")
    @Expose
    var createdDate: Long? = null

    @SerializedName("id")
    @Expose
    var id: String? = null
}
