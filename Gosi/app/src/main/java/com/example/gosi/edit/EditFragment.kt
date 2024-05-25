package com.example.gosi.edit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.gosi.R
import com.example.gosi.data.MyDatabase
import com.example.gosi.models.Item

class EditFragment : Fragment() {

    private val viewModel: EditViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = MyDatabase.getDatabase(requireContext())

        val id = requireArguments().getInt("itemId")

        if (id > 0) {
            val item = db.itemDao().get(id)
            setItem(item)
        }

        view.apply {
            findViewById<EditText>(R.id.editTextName)?.doOnTextChanged { text, start, before, count ->
                viewModel.name = text.toString()
            }

            findViewById<EditText>(R.id.editTextCode)?.doOnTextChanged { text, start, before, count ->
                viewModel.code = text.toString()
            }

            findViewById<EditText>(R.id.editTextSurname)?.doOnTextChanged { text, start, before, count ->
                viewModel.surname = text.toString()
            }

            findViewById<Button>(R.id.buttonSave)?.setOnClickListener {

                if (id > 0) {
                    val newItem = Item(viewModel.name, viewModel.code.toIntOrNull() ?: 0, viewModel.surname, id)
                    db.itemDao().update(newItem)

                } else {
                    val newItem = Item(viewModel.name, viewModel.code.toIntOrNull() ?: 0, viewModel.surname)
                    db.itemDao().insert(newItem)
                }

                findNavController().popBackStack()
            }

        }
    }

    private fun setItem(item: Item){
        view?.apply {
            findViewById<EditText>(R.id.editTextName).setText(item.name)
            findViewById<EditText>(R.id.editTextSurname).setText(item.surname)
            findViewById<EditText>(R.id.editTextCode).setText(item.code.toString())
        }

        viewModel.name = item.name
        viewModel.surname = item.surname
        viewModel.code = item.code.toString()
    }
}