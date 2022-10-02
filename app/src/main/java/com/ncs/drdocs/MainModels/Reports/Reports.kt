package com.ncs.drdocs.MainModels.Reports

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ncs.drdocs.MainModels.Reports.CreateFolder.UploadFolder
import com.ncs.drdocs.databinding.FragmentReportsBinding
import kotlin.math.log


class Reports : Fragment(), View.OnClickListener {

    val array : ArrayList<Dataholder> = arrayListOf()
    lateinit var listview : ListView

    companion object {
        fun newInstance() = Reports()
    }

    private lateinit var viewModel: ReportsViewModel
    lateinit var binding : FragmentReportsBinding
    lateinit var adapter : FolderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReportsBinding.inflate(inflater,container,false)
        listview = binding.folderlist
        Log.d("TAG", "OncreateView")
        array.clear()
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        array.add(Dataholder("Tuberculosis","23 Items"))
        array.add(Dataholder("Cancer","23 Items"))
        array.add(Dataholder("Diabeties","23 Items"))
        array.add(Dataholder("Rabies","23 Items"))
        array.add(Dataholder("Fever","23 Items"))
        array.add(Dataholder("Hepatitis","23 Items"))


        binding.includeSearchbar.searchBar.addTextChangedListener(textWatcher)
        adapter = FolderAdapter(requireContext(), array)
        listview.setAdapter(adapter)

        binding.createFolder.setOnClickListener(this)

    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        binding.includeSearchbar.searchBar
        }


        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            filter(s.toString())
            }
    }


    fun filter(s : String){
        var temp: ArrayList<Dataholder> = arrayListOf()

        if (s != "") {
            Log.d("TAG", "onTextChanged: $s")

            for (data in array) {
                val str = data.foldername
                if (str.contains(s, ignoreCase = true)) {
                    Log.d("TAG", "Compare: $s")
                    temp.add(data)
                }
            }

            if (temp.isEmpty()) {
                temp = array
            }
        }else {
           temp = array
        }

        adapter.data = temp
        listview.adapter = adapter
    }

    override fun onClick(p0: View?) {
        startActivity(Intent(activity,UploadFolder::class.java))
    }
}

