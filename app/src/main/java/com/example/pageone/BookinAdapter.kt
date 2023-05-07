package com.example.pageone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookingAdapter : RecyclerView.Adapter<BookingAdapter.BookingViewHolder>() {
    private var stdList: ArrayList<BookingModel> = ArrayList()
    private var onClickItem:((BookingModel) -> Unit)? =null
    private var onClickDeleteItem:((BookingModel) -> Unit)? =null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = BookingViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.booking_card, parent, false)
    )

    override fun onBindViewHolder(holder: BookingAdapter.BookingViewHolder, position: Int) {
        val std = stdList[position]
        holder.bindView(std)
        holder.itemView.setOnClickListener { onClickItem?.invoke(std) }
        holder.btnDelete.setOnClickListener { onClickDeleteItem?.invoke(std) }
    }

    override fun getItemCount(): Int {
        return stdList.size
    }

    fun addItems(items: ArrayList<BookingModel>){
        this.stdList = items
        notifyDataSetChanged()
    }

    class BookingViewHolder(var view: View): RecyclerView.ViewHolder(view){
        private var id = view.findViewById<TextView>(R.id.tvId)
        private var booking_date = view.findViewById<TextView>(R.id.tvBookingDate)
        private var no_of_days = view.findViewById<TextView>(R.id.tvNoOfDays)
        private var no_of_adults = view.findViewById<TextView>(R.id.tvNoOfAdults)
        private var no_of_children = view.findViewById<TextView>(R.id.tvNoOfChildren)
        private var room_type = view.findViewById<TextView>(R.id.tvRoomType)
        var btnDelete = view.findViewById<TextView>(R.id.btnDelete)

        fun bindView(std: BookingModel){
            id.text = "ID : " + std.id.toString()
            booking_date.text = "Booking Date : " + std.booking_date
            no_of_days.text = "No of Days : " + std.no_of_days.toString()
            no_of_adults.text = "No of Adults : " + std.no_of_adults.toString()
            no_of_children.text = "No of Children : " + std.no_of_children.toString()
            room_type.text = "Room Type : " + std.room_type
        }
    }

    fun setOnClickItem(callback: (BookingModel) -> Unit){
        this.onClickItem = callback
    }

    fun setonClickDeleteItem(callback: (BookingModel) -> Unit){
        this.onClickDeleteItem = callback
        }

}