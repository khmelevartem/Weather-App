package com.tubetoast.weather.view.fragments.details

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

import com.tubetoast.weather.R
import com.tubetoast.weather.databinding.FragmentDetailsBinding
import com.tubetoast.weather.model.entities.City
import com.tubetoast.weather.model.entities.Weather
import com.tubetoast.weather.viewmodel.MainViewModel
import com.tubetoast.weather.viewmodel.entities.AppState

class DetailsFragment : Fragment() {

    private lateinit var city: City
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val ARG_CITY = "city"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            city = it.getParcelable(ARG_CITY)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        viewModel.getWeather(city).observe(viewLifecycleOwner) {
            onWeatherGot(it)
        }

        viewModel.getAppState().observe(viewLifecycleOwner) {
            handleAppState(it)
        }
    }

    private fun handleAppState(it: AppState?) {
        when (it)  {
            is AppState.Loading -> {
                binding.factCard.visibility = View.GONE
            }
            is AppState.Error -> {
                binding.factCard.visibility = View.GONE
                //TODO custom error view
            }
            is AppState.Success -> {
                binding.factCard.visibility = View.VISIBLE
            }
        }
    }

    private fun onWeatherGot(weather: Weather) {
        binding.cityName.text = weather.city.name
        binding.temperature.text = weather.fact.temperature.toString() + "°"
        binding.feelsLike.text = String.format(resources.getString(R.string.feels_like), weather.fact.feelsLike)
        binding.condition.text = weather.fact.condition
        binding.icon.let {
            GlideToVectorYou.justLoadImage(
                activity,
                Uri.parse("https://yastatic.net/weather/i/icons/blueye/color/svg/${weather.fact.icon}.svg"),
                it
            )
        }

        binding.forecastPart1.partTime.text = weather.forecast1?.time
        binding.forecastPart1.partTemp.text = weather.forecast1?.temperature.toString() + "°"
        binding.forecastPart1.partIcon.let {
            GlideToVectorYou.justLoadImage(
                activity,
                Uri.parse("https://yastatic.net/weather/i/icons/blueye/color/svg/${weather.forecast1?.icon}.svg"),
                it
            )
        }

        binding.forecastPart2.partTime.text = weather.forecast2?.time
        binding.forecastPart2.partTemp.text = weather.forecast2?.temperature.toString() + "°"
        binding.forecastPart2.partIcon.let {
            GlideToVectorYou.justLoadImage(
                activity,
                Uri.parse("https://yastatic.net/weather/i/icons/blueye/color/svg/${weather.forecast2?.icon}.svg"),
                it
            )
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}