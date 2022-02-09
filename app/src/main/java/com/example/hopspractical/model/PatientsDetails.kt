package com.example.hopspractical.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PatientsDetails(
    @SerializedName("message")
    @Expose
    var message: String? = null,

    @SerializedName("payload")
    @Expose
    var patientdata: List<PatientData>? = null,

    @SerializedName("error")
    @Expose
    var error: Boolean? = null
)

class PatientData {
    @SerializedName("patientId")
    @Expose
    var patientId: String? = null

    @SerializedName("MACAddress")
    @Expose
    var mACAddress: String? = null

    @SerializedName("firstName")
    @Expose
    var firstName: String? = null

    @SerializedName("lastName")
    @Expose
    var lastName: String? = null

    @SerializedName("vital")
    @Expose
    var vital: String? = null

    @SerializedName("value1")
    @Expose
    var value1: String? = null

    @SerializedName("value1uom")
    @Expose
    var value1uom: String? = null

    @SerializedName("value2")
    @Expose
    var value2: String? = null

    @SerializedName("value2uom")
    @Expose
    var value2uom: String? = null

    @SerializedName("datetime")
    @Expose
    var datetime: Long? = null

    @SerializedName("id")
    @Expose
    var id: String? = null

}