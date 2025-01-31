package com.example.ise308.ozkan.ezgi.g14_trinkcar
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build.*


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.car_add_page.*

class NewCarPage : DialogFragment() {
    lateinit var imageView: ImageView
    lateinit var button: Button
    private val pickImage = 100
    private var imageUri: Uri? = null


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        val builder = AlertDialog.Builder(activity!!)

        val inflater = activity!!.layoutInflater

        val dialogView = inflater.inflate(R.layout.car_add_page, null)

        val brandName = dialogView.findViewById(R.id.brandName) as EditText

        val modelName = dialogView.findViewById(R.id.modelName) as EditText

        val fuelType = dialogView.findViewById(R.id.fuelType) as EditText

        val gearType = dialogView.findViewById(R.id.gearType) as EditText

        val price = dialogView.findViewById(R.id.price) as EditText

        val kilometer = dialogView.findViewById(R.id.kilometer) as EditText

        val color = dialogView.findViewById(R.id.color) as EditText

        val description = dialogView.findViewById(R.id.description) as EditText


        builder.setView(dialogView)
        //user can add a new car for tap done button

        val btnOk = dialogView.findViewById(R.id.btnOk) as Button


        imageView = dialogView.findViewById(R.id.imageView) as ImageView
        button = dialogView.findViewById(R.id.buttonLoadPicture) as Button
        button.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }


        btnOk.setOnClickListener() {


            var newCar = Car()



            newCar.brandName = brandName.text.toString()
            newCar.modelName = modelName.text.toString()

            newCar.price = price.text.toString().toDouble()

            newCar.fuelType = fuelType.text.toString()
            newCar.gearType = gearType.text.toString()
            newCar.km = kilometer.text.toString().toDouble()


            val callingActivity = activity as MainActivity?

            // Pass newCar back to MainActivity
            callingActivity!!.createNewCar(newCar)

            dismiss()

        }

        return builder.create()


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            imageView.setImageURI(imageUri)
        }
    }
}

