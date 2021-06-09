package com.example.ventas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ventas.databinding.ItemVentaBinding

class VentaAdapter (private var products : MutableList<ProductEntity>,
                    private var listener: OnClickListener) : RecyclerView.Adapter<VentaAdapter.ViewHolder>(){

    private lateinit var mContext : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_venta, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products.get(position)
        with(holder) {
            setListener(product)
            binding.tvNombre.text = product.name
            binding.cbFavorito.isChecked = product.isFavorite
        }
    }

    override fun getItemCount(): Int = products.size

    fun setProducts(productEntities: MutableList<ProductEntity>) {
        this.products = productEntities
        notifyDataSetChanged()
    }

    fun add(productEntity: ProductEntity) {
        products.add(productEntity)
        notifyDataSetChanged()
    }

    fun update(productEntity: ProductEntity) {
        val index = products.indexOf(productEntity)
        if (index != -1) {
            products.set(index, productEntity)
            notifyItemChanged(index)
        }
    }

    fun delete(productEntity: ProductEntity) {
        val index = products.indexOf(productEntity)
        if (index != -1) {
            products.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    inner class ViewHolder (view : View) : RecyclerView.ViewHolder(view) {
        val binding = ItemVentaBinding.bind(view)

        fun setListener(productEntity : ProductEntity) {
            with(binding.root) {
                setOnClickListener {
                    listener.onCLick(productEntity)
                }
                setOnLongClickListener {
                    listener.onDelete(productEntity)
                    true
                }
            }
            binding.cbFavorito.setOnClickListener {
                listener.onFavoriteProduct(productEntity)
            }
        }
    }
}