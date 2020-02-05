package kdo.one.stupidkotlinroom.data.model

import android.app.Activity
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener
import kdo.one.stupidkotlinroom.R
import kdo.one.stupidkotlinroom.util.toast
import kotlinx.android.synthetic.main.list_item.view.*

class CountriesAdapter(
    private val context: Activity,
    private val list: List<CountryModel>,
    private val listener: CountryListener
): RecyclerView.Adapter<CountriesAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.list_item, parent, false
        )
        return MainViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(context, list[position], listener)
    }

    class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(context: Activity, model: CountryModel, listener: CountryListener) {

            itemView.apply {
                country_name.text = model.name
                country_capital.text = model.capital

            }.also {
                GlideToVectorYou
                    .init()
                    .with(context)
                    .withListener(object: GlideToVectorYouListener {
                        override fun onLoadFailed() {
                            context.toast("Load failed")
                        }
                        override fun onResourceReady() {}
                    }).setPlaceHolder(R.drawable.loading_icon, R.drawable.error_icon)
                    .load(Uri.parse(model.flag), it.country_image)
            }

            itemView.setOnClickListener { listener.onCountryClicked(model) }
        }
    }

    interface CountryListener {
        fun onCountryClicked(model: CountryModel)
    }
}