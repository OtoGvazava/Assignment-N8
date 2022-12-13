package com.example.emptyactivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentAddProduct.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentAddProduct : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_product, container, false)
        val databaseHelper = ProductDatabaseHelper(view.context)
        val nameEditText = view.findViewById<EditText>(R.id.editTextProductName)
        val descriptionEditText = view.findViewById<EditText>(R.id.editTextTextMultiLine)
        view.findViewById<Button>(R.id.buttonSaveProduct).setOnClickListener {
            databaseHelper.addProduct(ProductModel(null, nameEditText.text.toString(), descriptionEditText.text.toString()))
            Toast.makeText(view.context, "Product Add Successfully!", Toast.LENGTH_LONG).show()
        }
        return view;
    }

}