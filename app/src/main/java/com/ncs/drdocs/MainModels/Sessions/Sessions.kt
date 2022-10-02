package com.ncs.drdocs.MainModels.Sessions

import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ncs.drdocs.R
import com.ncs.drdocs.databinding.ActivityMainHostBinding
import com.ncs.drdocs.databinding.FragmentSessionsBinding

class Sessions : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance() = Sessions()
    }

    private lateinit var viewModel: SessionsViewModel
    private  var _binding: FragmentSessionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSessionsBinding.inflate(layoutInflater,container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //binding.imgposter.posterImg.setImageResource(R.drawable.session)
        binding.empty.startSession.setOnClickListener(this)


    }

    override fun onClick(p0: View?) {

    }

}