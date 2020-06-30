package com.ankit.crunch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.ankit.crunch.databinding.FragmentDatabaseFragemntBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass.
 */
class databaseFragemnt : Fragment() {
    private lateinit var binding: FragmentDatabaseFragemntBinding
    private lateinit var saveAdatpers: SavedRecyclerView
    val saveviewModel:com.ankit.crunch.ViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_database_fragemnt, container, false)
        saveAdatpers= SavedRecyclerView(this@databaseFragemnt.requireActivity())
        binding.saveAdapter=saveAdatpers
        binding.lifecycleOwner=viewLifecycleOwner
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        saveviewModel.saveNewsLive().observe(viewLifecycleOwner, Observer {
            saveAdatpers.submitList(it)
            debugger("Saved data $it")
        })

        super.onViewCreated(view, savedInstanceState)
    }
}
