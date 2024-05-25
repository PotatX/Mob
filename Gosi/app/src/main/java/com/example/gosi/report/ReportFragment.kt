package com.example.gosi.report

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.room.Database
import com.example.gosi.R
import com.example.gosi.data.MyDatabase
import com.example.gosi.models.Item

class ReportFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = MyDatabase.getDatabase(requireContext())
        val items = db.itemDao().loadWithCodeLessThen(2)

        var arrayAdapter = ArrayAdapter<Item>(requireContext(), android.R.layout.simple_list_item_1)
        arrayAdapter.addAll(items)

        view.findViewById<ListView>(R.id.report_list)?.adapter = arrayAdapter
    }
}