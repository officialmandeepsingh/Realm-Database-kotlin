package com.mandeep.realmlearn.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mandeep.realmlearn.data.model.Item
import com.mandeep.realmlearn.ui.viewmodel.RealViewModel
import com.mandeep.realmlearn.data.client.RealmeClient
import com.mandeep.realmlearn.databinding.FragmentFirstBinding
import io.realm.kotlin.query.RealmResults
import kotlin.random.Random

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var items: RealmResults<Item>

    val viewModel: RealViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        setObservers()
        return binding.root
    }

    private fun setObservers() {
        viewModel.items.observe(viewLifecycleOwner) { items ->
            Log.d("TAG", "setObservers() called with: items = ${items.size}")
            this.items = items
            items.forEachIndexed { index, item ->
                Log.d(
                    "TAG",
                    "setObservers() called with: item $index = ID : ${item._id}, isComplete: ${item.isComplete}, Summary: ${item.summary} and Owner Id: ${item.owner_id}"
                )
            }
        }

        viewModel.item.observe(viewLifecycleOwner) { item ->
            Log.d("TAG", "setObservers() called with: item")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setObservers()
        binding.btnAdd.setOnClickListener {
            binding.apply {
                viewModel.addItem(Item().apply {
                    summary = txtSummary.text.toString()
                    isComplete = false
                })
            }
        }

        binding.btnGetAll.setOnClickListener {
            viewModel.getAllItem()
        }

        binding.btnUpdate.setOnClickListener {
            RealmeClient.realm.writeBlocking {
                findLatest(items[0])?.isComplete = true
            }
        }

        binding.btnDelete.setOnClickListener {
            viewModel.deleteItem(items)
        }

        binding.btnUpdate.setOnClickListener {
            viewModel.updateItem(items[Random.nextInt(0,items.size)])
//            var index = Random.nextInt(0, items.size)
            /*RealmeClient.realm.writeBlocking {
                items[index].summary = "update new method"
            }*/
//            viewModel.newUpdateItem<Item>(item = items[index])

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}