package com.example.mycookbook

import android.os.Parcel
import android.os.Parcelable

data class Recipe(
    var id: Int,
    var nazwa: String,
    var trudnosc: Int,
    var rating: Float,
    var opis: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()?: "",
        parcel.readInt(),
        parcel.readFloat(),
        parcel.readString()?: ""
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nazwa)
        parcel.writeInt(trudnosc)
        parcel.writeFloat(rating)
        parcel.writeString(opis)
    }

    companion object CREATOR : Parcelable.Creator<Recipe> {
        override fun createFromParcel(parcel: Parcel): Recipe {
            return Recipe(parcel)
        }

        override fun newArray(size: Int): Array<Recipe?> {
            return arrayOfNulls(size)
        }
    }
}
