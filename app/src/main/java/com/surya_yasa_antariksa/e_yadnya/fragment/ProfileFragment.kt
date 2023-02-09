package com.surya_yasa_antariksa.e_yadnya.fragment


import android.app.Activity.RESULT_OK
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import com.surya_yasa_antariksa.e_yadnya.*
import com.surya_yasa_antariksa.e_yadnya.databinding.FragmentProfileBinding
import db_helper.SharedPreference
import java.io.ByteArrayOutputStream


public class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    lateinit var auth: FirebaseAuth
    private lateinit var sPH: SharedPreference
    private lateinit var buttonKeluar: Button
    lateinit var imgUri: Uri

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        when{
            user != null ->{
                binding.usernameColumn.setText(user.displayName)
                binding.alamatEmail.setText(user.email)

                if(user.photoUrl != null){
                    Picasso.get().load(user.photoUrl).into(binding.profileIcon)
                }else{
                    Picasso.get().load("https://static.vecteezy.com/system/resources/previews/000/379/162/original/add-user-vector-icon.jpg").into(binding.profileIcon)
                }
            }
        }
        binding.userSetting.setOnClickListener {
            val i = Intent(context, UserSettingActivity::class.java)
            startActivity(i)
        }

        binding.detail.setOnClickListener {
            val i = Intent(context, DetailActivity::class.java)
            startActivity(i)
        }

        binding.profileIcon.setOnClickListener {
            goToCamera()
        }

        binding.changeImage.setOnClickListener{
            goToCamera()
        }

        binding.saveButton.setOnClickListener saveButton@{
            val image = when{
                ::imgUri.isInitialized -> imgUri
                user?.photoUrl == null -> Uri.parse("https://static.vecteezy.com/system/resources/previews/000/379/162/original/add-user-vector-icon.jpg")
                else -> user.photoUrl
            }

            val name = binding.usernameColumn.text.toString()

            if(name.isEmpty()){
                binding.usernameColumn.error = "nama belum di isi"
                binding.usernameColumn.requestFocus()
                return@saveButton
            }
            //update data
            UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .setPhotoUri(image)
                .build().also {
                    user?.updateProfile(it)?.addOnCompleteListener { task ->
                        if(task.isSuccessful){
                            val toast = Toast.makeText(activity, "Data berhasil disimpan", Toast.LENGTH_SHORT)
                            toast.setGravity(Gravity.CENTER_VERTICAL,0 , 0)
                            toast.show()
                        }else{
                            Toast.makeText(activity, "${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        }
    }

    private fun goToCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
            activity?.packageManager?.let {
                intent?.resolveActivity(it).also {
                    startActivityForResult(intent, REQ_CODE)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when{
            requestCode == REQ_CODE && resultCode == RESULT_OK -> {
                val imgBitmap = data?.extras?.get("data") as Bitmap

                uploadImageToFirebase(imgBitmap)
            }
        }
    }

    private fun uploadImageToFirebase(imgBitmap: Bitmap) {
        val baos = ByteArrayOutputStream()

        //masuk directory firebase
        val ref = FirebaseStorage.getInstance().reference.child("image_user/${FirebaseAuth.getInstance().currentUser?.email}")
        imgBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)

        val img = baos.toByteArray()
        ref.putBytes(img)
            .addOnCompleteListener{
                when{
                    it.isSuccessful -> {
                        ref.downloadUrl.addOnCompleteListener { Task ->
                            Task.result?.let { uri ->
                                imgUri = uri
                                binding.profileIcon.setImageBitmap(imgBitmap)}
                        }
                    }
                }
            }
    }

    companion object{
        const val REQ_CODE = 100
    }
}
