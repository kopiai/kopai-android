package com.kopai.shinkansen.view.productmaps

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.kopai.shinkansen.R
import com.kopai.shinkansen.data.ResultState
import com.kopai.shinkansen.data.remote.response.ProductItem
import com.kopai.shinkansen.databinding.ActivityProductMapsBinding
import javax.inject.Inject

class ProductMapsActivity : AppCompatActivity(), OnMapReadyCallback {
    @Inject lateinit var viewModel: ProductMapsViewModel

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityProductMapsBinding
    private val boundsBuilder = LatLngBounds.Builder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment =
            supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        setMapStyle()
        fetchProducts()
    }

    private fun setMapStyle() {
        try {
            val success =
                mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style))
            if (!success) {
                Toast.makeText(this, getString(R.string.style_parsing_failed), Toast.LENGTH_SHORT)
                    .show()
            }
        } catch (exception: Resources.NotFoundException) {
            Toast.makeText(this, getString(R.string.can_t_find_style_error), Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun fetchProducts() {
        viewModel.getProductsWithLocation().observe(this) {
            when (it) {
                is ResultState.Error -> {
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                }

                ResultState.Loading -> {
                    Log.d("ProductMapsActivity", "Loading")
                }

                is ResultState.Success -> {
                    if (it.data.listProduct.isEmpty()) {
                        Toast.makeText(
                            this,
                            getString(R.string.can_t_find_any_product_with_location),
                            Toast.LENGTH_SHORT,
                        ).show()
                    } else {
                        addManyMarker(it.data.listProduct)
                    }
                }
            }
        }
    }

    private fun addManyMarker(productItem: List<ProductItem>) {
        productItem.forEach { item ->
            if (item.lat != null && item.lon != null && item.lat != 0.0 && item.lon != 0.0) {
                val latLng = LatLng(item.lat, item.lon)
                mMap.addMarker(MarkerOptions().position(latLng).title(item.name))
                boundsBuilder.include(latLng)
            }
        }

        val bounds: LatLngBounds = boundsBuilder.build()
        mMap.animateCamera(
            CameraUpdateFactory.newLatLngBounds(
                bounds,
                resources.displayMetrics.widthPixels,
                resources.displayMetrics.heightPixels,
                300,
            ),
        )
    }
}
