package kdo.one.stupidkotlinroom.ui.main

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import kdo.one.stupidkotlinroom.R
import kdo.one.stupidkotlinroom.data.model.CountriesAdapter
import kdo.one.stupidkotlinroom.data.model.CountryModel
import kdo.one.stupidkotlinroom.util.snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CountriesAdapter.CountryListener {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        if (isNetworkConnected(this)) {
            viewModel.getCountriesFromApi()
        } else {
            root_main.snackbar("Please, check your network connection")
        }

        viewModel.getAllCountryList().observe(this, Observer {
            setupRecyclerView(it)
        })
    }

    private fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    private fun setupRecyclerView(countries: List<CountryModel>) {
        val mAdapter = CountriesAdapter(this, countries, this)
        countryRecycler.apply {
            adapter = mAdapter
            layoutManager = GridLayoutManager(applicationContext, 2)
            setHasFixedSize(true)
        }
    }

    override fun onCountryClicked(model: CountryModel) {

    }
}
