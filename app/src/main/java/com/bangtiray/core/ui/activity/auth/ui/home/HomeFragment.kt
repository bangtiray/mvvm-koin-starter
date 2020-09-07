package com.bangtiray.core.ui.activity.auth.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bangtiray.core.CoreApp
import com.bangtiray.core.adapter.apps.AppsItemAdapter
import com.bangtiray.core.adapter.news.NewsItemAdapter
import com.bangtiray.core.adapter.news.NewsViewPagerAdapter
import com.bangtiray.core.database.entity.LocalApps
import com.bangtiray.core.database.entity.LocalNews
import com.bangtiray.core.di.appsNetwork
import com.bangtiray.core.di.newsNetwork
import com.bangtiray.core.network.apps.AppsNetwork
import com.bangtiray.core.network.news.NewsNetwork
import com.bangtiray.core.ui.activity.auth.R
import com.bangtiray.core.ui.viewmodel.AppsViewModel
import com.bangtiray.core.ui.viewmodel.NewsViewModel
import com.bangtiray.core.utils.logi
import com.google.android.material.switchmaterial.SwitchMaterial
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var viewPager: RecyclerView
    private var newsList = arrayListOf<LocalNews>()
    private var newsViewAdapter = NewsItemAdapter()
    private val newsViewModel: NewsViewModel by viewModel()
    private val newsNetworks: NewsNetwork by newsNetwork()
    private val appsViewModel: AppsViewModel by viewModel()
    private val appsNetwork: AppsNetwork by appsNetwork()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        viewPager = root.findViewById(R.id.viewPager)

        return root

    }


    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dummySlider()
        super.onViewCreated(view, savedInstanceState)


        val darkThemeSwitch: SwitchMaterial = view.findViewById(R.id.dark_theme_switch)
        val preferenceRepository = (requireActivity().application as CoreApp).preferenceRepository
        preferenceRepository.isDarkThemeLive.observe(this, Observer { isDarkTheme ->
            isDarkTheme?.let { darkThemeSwitch.isChecked = it }
        })

        darkThemeSwitch.setOnCheckedChangeListener { _, checked ->
            preferenceRepository.isDarkTheme = checked
        }

        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        viewPager.layoutManager = linearLayoutManager
        viewPager.adapter = newsViewAdapter
        activity?.runOnUiThread(Runnable {
            loadNews()
        })
        activity?.runOnUiThread(Runnable {
            loadApps()
        })


    }


    private fun dummySlider() {

        for (i in 0..5) {
            val items =
                LocalNews(i, "name", "jastan.png", "slug", "content", "2020-01-01", "bangtiray")
            newsList.add(items)
        }
        viewPager2.adapter = NewsViewPagerAdapter(newsList)
    }

    private fun loadApps() {
        appsNetwork.getApps(requireActivity(), object : AppsNetwork.AppsNetworkCallback {
            override fun onSuccess(localApps: List<LocalApps>) {
                GlobalScope.launch {
                    appsViewModel.insert(localApps)
                }
            }

            override fun onFailed(message: String?) {
                logi(message)
            }

        })
        val viewAppAdapter = AppsItemAdapter()
        recycler_view.layoutManager = GridLayoutManager(activity, 3)
        recycler_view.adapter = viewAppAdapter
        appsViewModel.apps.observe(viewLifecycleOwner, Observer { list ->
            viewAppAdapter.submitList(list)
        })
    }

    private fun loadNews() {
        newsNetworks.getNews(requireActivity(), object : NewsNetwork.NewsNetworkCallback {
            override fun onSuccess(localNews: List<LocalNews>) {
                GlobalScope.launch {
                    newsViewModel.insert(localNews)
                }
            }

            override fun onFailed(message: String?) {
                logi(message)
            }

        })


        newsViewModel.news.observe(viewLifecycleOwner, Observer { list ->
            logi(list.toString())
            newsViewAdapter.submitList(list)
            viewPager2.adapter = NewsViewPagerAdapter(list)
        })


    }

    companion object {
        var ITEM = "items"
    }
}