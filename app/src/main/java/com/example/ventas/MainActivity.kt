package com.example.ventas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ventas.databinding.ActivityMainBinding
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAdapter: VentaAdapter
    private lateinit var mGridLayout: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btnGuardar.setOnClickListener {
            val product = ProductEntity(name = mBinding.etNombre.text.toString().trim())

            Thread {
                VentasApplication.database.productDao().addProduct(product)
            }.start()
            mAdapter.add(product)
        }
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        mAdapter = VentaAdapter(mutableListOf(), this)
        mGridLayout = GridLayoutManager(this, 2)

        getProducts()

        mBinding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = mGridLayout
            adapter = mAdapter
        }
    }

    private fun getProducts() {
        doAsync {
            val products = VentasApplication.database.productDao().getAllProducts()
            uiThread {
                mAdapter.setProducts(products)
            }
        }
    }

    override fun onCLick(productEntity: ProductEntity) {

    }

    override fun onFavoriteProduct(productEntity: ProductEntity) {
        productEntity.isFavorite = !productEntity.isFavorite
        doAsync {
            VentasApplication.database.productDao().updateProduct(productEntity)
            uiThread {
                mAdapter.update(productEntity)
            }
        }
    }

    override fun onDelete(productEntity: ProductEntity) {
        doAsync {
            VentasApplication.database.productDao().deleteProduct(productEntity)
            uiThread {
                mAdapter.delete(productEntity)
            }
        }
    }
}