package com.surya_yasa_antariksa.e_yadnya.model

import android.os.Parcel
import android.os.Parcelable

class OrderModel(val itemName: String, val itemCount: Int, val itemPrice: Int) : Parcelable {

    var total: Int = 10
    val items: MutableList<OrderItemModel> = mutableListOf() // deklarasi list items

    init {
        total = itemCount * itemPrice
    }

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt()
    ) {
        total = parcel.readInt()
        // baca list items dari parcelable
        parcel.readList(items, OrderItemModel::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(itemName)
        parcel.writeInt(itemCount)
        parcel.writeInt(itemPrice)
        parcel.writeInt(total)
        // tulis list items ke parcelable
        parcel.writeList(items)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OrderModel> {
        override fun createFromParcel(parcel: Parcel): OrderModel {
            return OrderModel(parcel)
        }

        override fun newArray(size: Int): Array<OrderModel?> {
            return arrayOfNulls(size)
        }
    }
}

class OrderItemModel(val name: String, val price: Int, val quantity: Int)
