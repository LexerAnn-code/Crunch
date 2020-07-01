package com.ankit.crunch.Ui

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.ankit.crunch.*
import com.ankit.crunch.Util.LoadingState
import com.ankit.crunch.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.shimmer_container
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
private lateinit var binding:FragmentHomeBinding
private lateinit var nAdapter: HomeRecyclerView
    private lateinit var secondAdapter:NewsSecondRecycler
    val newsViewModels by viewModel<ViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        secondAdapter=NewsSecondRecycler(this@HomeFragment.requireActivity())
        nAdapter= HomeRecyclerView(this@HomeFragment.requireActivity())

        binding.secondadapter=secondAdapter
        binding.adapter = nAdapter

        binding.lifecycleOwner=viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        newsViewModels.data.observe(viewLifecycleOwner, Observer {
            nAdapter.submitList(it)

            debugger("From Home $it")
        })
        newsViewModels.loadingState.observe(viewLifecycleOwner, Observer {
            when(it.status){
                LoadingState.Status.SUCCESS->{
                    shimmer_container.stopShimmer()
                    shimmer_container.visibility=View.GONE
                   // foryou.visibility=View.VISIBLE
                }

            }
        })
        newsViewModels.datas.observe(viewLifecycleOwner, Observer {
            secondAdapter.submitList(it)
        })
    }

    override fun onStart() {
        super.onStart()
        val nnViewModels by viewModel<ViewModel>()
        nnViewModels.fetchData()
        onRefresh()
    }

    private fun onRefresh() {
        swipeRefresh.setOnRefreshListener {
            val nnViewModels by viewModel<ViewModel>()
            nnViewModels.fetchData()
            swipeRefresh.isRefreshing=false
        }
    }

    override fun onResume() {
        super.onResume()
    shimmer_container!!.startShimmer()
    }

    override fun onPause() {
        super.onPause()
    shimmer_container.stopShimmer()
    }

}
