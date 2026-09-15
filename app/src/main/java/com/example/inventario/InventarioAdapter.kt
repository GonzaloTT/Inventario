package com.example.inventario

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class InventarioAdapter(
    private val listaProductos: List<Component>
) : RecyclerView.Adapter<InventarioAdapter.ProductoViewHolder>() {

    class ProductoViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val tvNombre: TextView =
            itemView.findViewById(R.id.tvNombreProducto)

        val tvCantidad: TextView =
            itemView.findViewById(R.id.tvCantidadProducto)

        val tvPrecio: TextView =
            itemView.findViewById(R.id.tvPrecioProducto)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductoViewHolder {

        val view = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_component,
                parent,
                false
            )

        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ProductoViewHolder,
        position: Int
    ) {

        val producto = listaProductos[position]

        holder.tvNombre.text = producto.nombre
        holder.tvCantidad.text = "Cantidad: ${producto.cantidad}"
        holder.tvPrecio.text = "Precio: $%.2f".format(producto.precio)
    }

    override fun getItemCount(): Int {
        return listaProductos.size
    }
}