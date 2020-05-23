package com.pos.pucpr.shoppingcrud.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.pos.pucpr.shoppingcrud.common.State
import com.pos.pucpr.shoppingcrud.databinding.ShoppingFragmentBinding
import com.pos.pucpr.shoppingcrud.ui.viewModels.ShoppingViewModel
import com.pos.pucpr.shoppingcrud.ui.viewdata.ShoppingViewData
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShoppingFragment : Fragment() {
    private val args: ShoppingFragmentArgs by navArgs()

    private val viewModel: ShoppingViewModel by viewModel()
    private lateinit var binding: ShoppingFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args.id?.let { viewModel.fetchShopping(id = it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ShoppingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showOrHideId(!viewModel.shopping.id.isNullOrEmpty())

        binding.buttonSave.setOnClickListener {
            viewModel.saveShopping(
                shopping =
                ShoppingViewData(
                    id = args.id,
                    name = binding.editName.text.toString(),
                    amount = binding.editAmount.text.toString().toInt(),
                    brand = binding.editBrand.text.toString(),
                    shelfLife = binding.editShelfLife.text.toString()
                )
            )
        }
    }

    private fun subscribeObservers() {
        viewModel.fetchShoppingState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Loading -> showLoading(isVisible = true)
                is State.Error -> showLoading(isVisible = false)
                is State.Success -> {
                    showLoading(isVisible = false)
                    loadShopping(viewModel.shopping)
                }
            }
        })

        viewModel.saveShoppingState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Loading -> showLoading(isVisible = true)
                is State.Error -> showLoading(isVisible = false)
                is State.Success -> {
                    showLoading(isVisible = false)
                }
            }
        })
    }

    private fun loadShopping(shopping: ShoppingViewData) {
        showOrHideId(!shopping.id.isNullOrEmpty())

        binding.editId.setText(shopping.id ?: "")
        binding.editName.setText(shopping.name)
        binding.editBrand.setText(shopping.brand)
        binding.editAmount.setText(shopping.amount.toString())
        binding.editShelfLife.setText(shopping.shelfLife)
    }

    private fun showLoading(isVisible: Boolean) {
        binding.progressBar.isVisible = isVisible
    }

    private fun showOrHideId(isVisible: Boolean) {
        binding.textInputLayoutId.isVisible = isVisible
    }

}
