package com.example.inventario

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etCantidad: EditText
    private lateinit var etPrecio: EditText
    private lateinit var btnAgregar: Button
    private lateinit var rvInventario: RecyclerView

    private val listaProductos = mutableListOf(
        Component("Teclado", 5, 499.99),
        Component("Mouse", 10, 299.50),
        Component("Monitor", 3, 3499.00),
        Component("Laptop", 2, 15999.00)
    )

    private lateinit var inventarioAdapter: InventarioAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        etNombre = findViewById(R.id.etNombre)
        etCantidad = findViewById(R.id.etCantidad)
        etPrecio = findViewById(R.id.etPrecio)
        btnAgregar = findViewById(R.id.btnAgregar)
        rvInventario = findViewById(R.id.rvInventario)

        configurarRecyclerView()

        btnAgregar.setOnClickListener {
            agregarProducto()
        }
    }

    private fun configurarRecyclerView() {

        inventarioAdapter = InventarioAdapter(listaProductos)

        rvInventario.layoutManager = LinearLayoutManager(this)

        rvInventario.adapter = inventarioAdapter
    }

    private fun agregarProducto() {

        val nombre = etNombre.text.toString().trim()
        val cantidadTexto = etCantidad.text.toString().trim()
        val precioTexto = etPrecio.text.toString().trim()

        if (nombre.isEmpty()) {
            etNombre.error = "Ingresa el nombre del producto"
            return
        }

        if (cantidadTexto.isEmpty()) {
            etCantidad.error = "Ingresa la cantidad"
            return
        }

        if (precioTexto.isEmpty()) {
            etPrecio.error = "Ingresa el precio"
            return
        }

        val cantidad = cantidadTexto.toIntOrNull()
        val precio = precioTexto.toDoubleOrNull()

        if (cantidad == null || cantidad <= 0) {
            etCantidad.error = "Ingresa una cantidad válida"
            return
        }

        if (precio == null || precio <= 0) {
            etPrecio.error = "Ingresa un precio válido"
            return
        }

        val producto = Component(
            nombre = nombre,
            cantidad = cantidad,
            precio = precio
        )

        listaProductos.add(producto)

        inventarioAdapter.notifyItemInserted(
            listaProductos.size - 1
        )

        limpiarCampos()

        Toast.makeText(
            this,
            "Producto agregado",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun limpiarCampos() {

        etNombre.text.clear()
        etCantidad.text.clear()
        etPrecio.text.clear()

        etNombre.requestFocus()
    }
}