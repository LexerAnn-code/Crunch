package com.ankit.crunch.Ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.ankit.crunch.*
import com.ankit.crunch.databinding.FragmentHomeBinding
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
        newsViewModels.datas.observe(viewLifecycleOwner, Observer {
            secondAdapter.submitList(it)
        })
    }

    override fun onStart() {
        super.onStart()
        val nnViewModels by viewModel<ViewModel>()
        nnViewModels.fetchData()
    }

}
