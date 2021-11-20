package com.paasta.newernews.presentation.ui.mainfragment.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.paasta.newernews.R
import com.paasta.newernews.databinding.FragmentHomeBinding
import com.paasta.newernews.presentation.ui.WebViewActivity
import com.paasta.newernews.presentation.ui.mainfragment.home.adapter.HomeNewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val smallLocationAdapter by lazy { HomeNewsAdapter(homeViewModel.delegate, 0) }
    private val bigLocationAdapter by lazy { HomeNewsAdapter(homeViewModel.delegate, 1) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        homeViewModel.requestUserName()
        homeViewModel.userNameLiveData.observe(viewLifecycleOwner, {
            binding.tvHello.text = getString(R.string.home_info_hello) + " "
            binding.tvHomeUserName.text = it + "님,"
        })

        homeViewModel.requestCurrentAddress()
        homeViewModel.currentAddressLiveData.observe(viewLifecycleOwner, {
            binding.tvLocation.text = getString(R.string.home_info_location) + " "
            binding.tvLocationCity.text = it.city + " "
            binding.tvLocationGu.text = it.gu + " "
            binding.tvLocationDong.text = it.dong
            binding.tvLocationEnd.text = getString(R.string.home_info_location_end)
            binding.tvWeatherTitle.text = String.format(getString(R.string.home_info_weather), it.gu)
            binding.tvBigLocationNewsTitle.text = String.format(getString(R.string.news_title_format), it.city)

            homeViewModel.requestCurrentWeather(it.lat, it.lon)
            //homeViewModel.requestNewsList(it.gu, 0)
            homeViewModel.requestNewsList(it.city, 1)
        })

        /*binding.rvSmallLocationNews.also {
            it.layoutManager = LinearLayoutManager(this.context)
            it.adapter = smallLocationAdapter
        }*/

        binding.rvBigLocationNews.also {
            it.layoutManager = LinearLayoutManager(this.context)
            it.adapter = bigLocationAdapter
        }

        homeViewModel.weatherLiveData.observe(viewLifecycleOwner, {
            it.let {
                val iconId = it.weather[0].icon.substring(0, 2)
                binding.ivWeatherIcon.setImageResource(resources.getIdentifier("@drawable/weather_icon_$iconId", "drawable", activity?.packageName))

                binding.tvWeatherCurrentTemp.text = String.format(getString(R.string.weather_temp_format), it.main.temp.roundToInt())

                binding.tvWeatherDescription.text = it.weather[0].description
                binding.tvWeatherMinTemp.text = String.format(getString(R.string.weather_temp_format), it.main.minTemp.roundToInt())
                binding.tvDivider.text = " / "
                binding.tvWeatherMaxTemp.text = String.format(getString(R.string.weather_temp_format), it.main.maxTemp.roundToInt())
                binding.tvWeatherFeelsLikeTemp.text = String.format(getString(R.string.weather_feels_like_temp), it.main.feelsLikeTemp.roundToInt())

                binding.progressBarWeather.visibility = View.GONE
            }
        })

        homeViewModel.smallNewsListLivedata.observe(viewLifecycleOwner, {
            it.let {
                smallLocationAdapter.submitList(it.news.toMutableList())
                binding.progressBarWeather.visibility = View.GONE
            }
        })

        homeViewModel.bigNewsListLiveData.observe(viewLifecycleOwner, {
            it.let {
                bigLocationAdapter.submitList(it.news.toMutableList())
                binding.progressBarBigNews.visibility = View.GONE
            }
        })

        homeViewModel.delegate.urlLiveDelegate.observe(viewLifecycleOwner, {
            val intent = Intent(activity, WebViewActivity::class.java)
            intent.putExtra("url", it)
            startActivity(intent)
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}