package com.example.baemin.widget.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.baemin.model.CellType
import com.example.baemin.model.Model
import com.example.baemin.screen.base.BaseViewModel
import com.example.baemin.util.mapper.ModelViewHolderMapper
import com.example.baemin.util.provider.ResourcesProvider
import com.example.baemin.widget.adapter.viewholder.ModelViewHolder
import com.example.baemin.widget.listener.AdapterListener

class ModelRecyclerAdapter<M: Model, VM: BaseViewModel>(
    private var modelList: List<Model>,
    private val viewModel: VM,
    private val resourceProvider: ResourcesProvider,
    private val adapterListener: AdapterListener
    ): ListAdapter<Model, ModelViewHolder<M>>(Model.DIFF_CALLBACK) {

    override fun getItemCount(): Int = modelList.size

    override fun getItemViewType(position: Int): Int = modelList[position].type.ordinal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder<M> {
        return ModelViewHolderMapper.map(parent, CellType.values()[viewType], viewModel, resourceProvider)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: ModelViewHolder<M>, position: Int) {
        with (holder) {
            bindData(modelList[position] as M)
            bindViews(modelList[position] as M, adapterListener)
        }
    }

    override fun submitList(list: MutableList<Model>?) {
        list?.let { modelList = it }
        super.submitList(list)
    }
}