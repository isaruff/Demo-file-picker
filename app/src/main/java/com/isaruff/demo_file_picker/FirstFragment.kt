package com.isaruff.demo_file_picker

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.isaruff.demo_file_picker.databinding.FragmentFirstBinding
import java.io.File
import java.lang.Exception

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private var pdfURI : Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }


    private val pickPDF = registerForActivityResult(ActivityResultContracts.OpenDocument()){ uri ->
        pdfURI = uri
      uri?.path?.let {
          val file = File(it)
      }
        binding.textViewFileName.text  = uri?.getFileName(requireContext()) ?: "File Name"
    }

    private fun openPDF(uri: Uri){
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, "application/pdf")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        try {
            startActivity(intent)
        }catch (e: Exception){
            e.printStackTrace()
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            pickPDF.launch(arrayOf("application/pdf"))
        }
        binding.textViewFileName.setOnClickListener {
            pdfURI?.let {
                openPDF(it)
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}