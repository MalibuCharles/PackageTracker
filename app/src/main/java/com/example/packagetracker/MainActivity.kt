package com.example.packagetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var packageAdapter: PackageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        packageAdapter = PackageAdapter(mutableListOf())

        rvPackageTracker.adapter = packageAdapter
        rvPackageTracker.layoutManager = LinearLayoutManager(this)

        btnAddPackage.setOnClickListener{
            val packageTitle = etPackageTitle.text.toString()
            if(packageTitle.isNotEmpty()){
                val packae = Package(packageTitle)
                packageAdapter.addPackage(packae)
                etPackageTitle.text.clear()
            }
        }
        btnDeletePackage.setOnClickListener{
            packageAdapter.deleteDeliveredPackages()
        }
    }
}