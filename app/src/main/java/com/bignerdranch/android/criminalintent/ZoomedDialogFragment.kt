package com.bignerdranch.android.criminalintent

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import java.io.File
import com.bumptech.glide.Glide


private const val ARG_IMAGE = "image"

class ZoomedDialogFragment() : DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater
            // Inflate and set the layout for the dialog
            val view = inflater.inflate(R.layout.zoomed_dialog_fragment, null)
            builder.setView(view)

            // Get the image file path argument
            val photoFile = arguments?.getSerializable(ARG_IMAGE) as File?

            // Set the picture in the ImageView
            photoFile?.let {
                val imageView = view.findViewById<ImageView>(R.id.crime_picture)
                Glide.with(requireActivity()).load(it).into(imageView)
            }

            // Set the dialog characteristics
            builder.setTitle("Zoomed Image")
                .setNegativeButton("Dismiss") { _, _ -> dialog?.cancel() }

            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    companion object {
        fun newInstance(photoFile: File): ZoomedDialogFragment {
            val args = Bundle().apply { putSerializable(ARG_IMAGE, photoFile) }
            return ZoomedDialogFragment().apply { arguments = args }
        }
    }
}

