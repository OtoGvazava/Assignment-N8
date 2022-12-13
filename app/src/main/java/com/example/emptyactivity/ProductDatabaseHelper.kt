package com.example.emptyactivity

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ProductDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "productDatabase"
        private const val TABLE_NAME = "products"
        private const val KEY_ID = "id"
        private const val KEY_NAME = "name"
        private const val KEY_DESCRIPTION = "description"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createDatabaseQuery =
            "CREATE TABLE $TABLE_NAME ($KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT, $KEY_NAME TEXT, $KEY_DESCRIPTION TEXT)"
        db?.execSQL(createDatabaseQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addProduct(product: ProductModel) {
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, product.name)
        contentValues.put(KEY_DESCRIPTION, product.description)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, contentValues)
        db.close()
    }

    @SuppressLint("Range")
    fun getProducts(): List<ProductModel> {
        val productList: ArrayList<ProductModel> = ArrayList()
        val selectProductsQuery = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery(selectProductsQuery, null)
        while (cursor.moveToNext()) {
            val product = ProductModel(
                id = cursor.getInt(cursor.getColumnIndex(KEY_ID)),
                name = cursor.getString(cursor.getColumnIndex(KEY_NAME)),
                description = cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION))
            )
            productList.add(product)
        }
        cursor.close()
        db.close()
        return productList
    }
}