package com.usend.base


import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.usend.utils.getViewModel


abstract class ViewModelFragment<B, V> : BaseBindingFragment<B>() where B : ViewDataBinding, V : ViewModel {

    open lateinit var viewModel: V

    abstract val modelClass: Class<V>
    protected abstract fun initControls()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        viewModel = requireActivity().getViewModel(modelClass)
        addObserver()

        initControls()
    }

    /**
     *  Add your observer here
     *
     *  here no need to remove observer because it will manage by android lifecycle
     */
    open fun addObserver() {}

}