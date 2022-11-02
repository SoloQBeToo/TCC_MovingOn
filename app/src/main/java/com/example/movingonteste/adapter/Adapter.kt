package com.example.movingonteste.adapter



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movingonteste.R
import com.example.movingonteste.model.Usuario


class Adapter(private val usuarioList:MutableList<Usuario>):
    RecyclerView.Adapter<Adapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.listagem_user, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userName.text = usuarioList[position].userName
        holder.userEmail.text = usuarioList[position].userEmail
    }

    override fun getItemCount(): Int {
        return usuarioList.size
    }
    class ViewHolder(view : View):RecyclerView.ViewHolder(view){
        val userName:TextView = view.findViewById(R.id.userName)
        val userEmail:TextView = view.findViewById(R.id.userEmail)
    }
}