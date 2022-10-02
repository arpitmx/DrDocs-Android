package com.ncs.drdocs.MainModels.Reports

import com.ncs.drdocs.R
import android.content.Context
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView


/**
 * Created by Alok Ranjan on 01-10-2022
 * Project : NCS Dr.Docs
 * Description :
 * Github : https://github.com/arpitmx
 **/
class FolderAdapter(context: Context, data: ArrayList<Dataholder>) : BaseAdapter() {
        var context: Context
        var data: ArrayList<Dataholder>


        override fun getCount(): Int {
            // TODO Auto-generated method stub
            return data.size
        }

        override fun getItem(position: Int): Any {
            // TODO Auto-generated method stub
            return data[position]
        }

        override fun getItemId(position: Int): Long {
            // TODO Auto-generated method stub
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            // TODO Auto-generated method stub
            var vi: View? = convertView
            if (vi == null) vi = inflater!!.inflate(R.layout.folder_item, null)
            val folderName = vi!!.findViewById(R.id.folder_name) as TextView
            val folderSize = vi.findViewById(R.id.folder_size) as TextView
            folderName.text = data[position].foldername
            folderSize.text = data[position].foldersize
            return vi
        }

        companion object {
            private var inflater: LayoutInflater? = null
        }

        init {
            // TODO Auto-generated constructor stub
            this.context = context
            this.data = data
            inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
        }
    }
