package com.pos.pucpr.shoppingcrud.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.pos.pucpr.shoppingcrud.R
import com.pos.pucpr.shoppingcrud.common.State
import com.pos.pucpr.shoppingcrud.common.extensions.showDialog
import com.pos.pucpr.shoppingcrud.databinding.ShoppingListFragmentBinding
import com.pos.pucpr.shoppingcrud.ui.controllers.ShoppingListController
import com.pos.pucpr.shoppingcrud.ui.viewModels.ShoppingListViewModel
import com.pos.pucpr.shoppingcrud.ui.viewdata.ShoppingViewData
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShoppingListFragment : Fragment(), ShoppingListController.OnClickListener {

    private val viewModel: ShoppingListViewModel by viewModel()
    private lateinit var binding: ShoppingListFragmentBinding

    private val controller: ShoppingListController by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller.setListener(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ShoppingListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.fetchShoppingListState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Loading -> showLoading(isVisible = true)
                is State.Error -> showLoading(isVisible = false)
                is State.Success -> {
                    showLoading(isVisible = false)
                    loadShopping(viewModel.shoppingList)
                }
            }
        })
        viewModel.deleteShoppingState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Success -> {
                    viewModel.fetchShopping()
                }
            }
        })
    }

    private fun loadShopping(shopping: List<ShoppingViewData>) {
        controller.setData(shopping)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        binding.fabAddItem.setOnClickListener {
            navigateToShoppingFragment(id = null)
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = controller.adapter
        binding.recyclerView.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchShopping()
    }

    private fun showLoading(isVisible: Boolean) {
        binding.progressBar.isVisible = isVisible
    }

    override fun onClickListener(shoppingItem: ShoppingViewData) {
        navigateToShoppingFragment(id = shoppingItem.id)
    }

    override fun onDeleteListener(shoppingItem: ShoppingViewData) {
        shoppingItem.id?.let { id ->
            showDeleteDialog {
                viewModel.deleteShopping(id = id)
            }
        }
    }

    private fun navigateToShoppingFragment(id: String?) {
        findNavController().navigate(
            ShoppingListFragmentDirections
                .actionShoppingListFragmentToShoppingFragment(id)
        )
    }

    private fun showDeleteDialog(onConfirmed: () -> Unit) {
        requireContext().showDialog(
            title = R.string.title_delete_shopping,
            message = R.string.message_delete_shopping,
            positiveButton = R.string.action_confirm,
            negativeButton = R.string.action_cancel,
            onPositiveButtonClick = {
                onConfirmed.invoke()
            }
        )
    }

}
