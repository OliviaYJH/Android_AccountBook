package com.example.accountbook

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.Exception

class SQLiteHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "account"
        private const val TBL_AMOUNT = "tbl_amount"
        private const val ID = "id"
        private const val DATE = "date"
        private const val TYPE = "type"
        private const val AMOUNT = "amount"
        private const val CONTEXT = "context"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblAmount = ("CREATE TABLE " + TBL_AMOUNT + "("
            + ID + " INTEGER PRIMARY KEY," + DATE + " TEXT," +
            TYPE + " TEXT," + AMOUNT + " INTEGER," + CONTEXT + " TEXT" + ")")
        db?.execSQL(createTblAmount)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_AMOUNT")
        onCreate(db)
    }

    fun insertAmount(atd: AmountModel):Long{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, atd.id)
        contentValues.put(DATE, atd.date)
        contentValues.put(TYPE, atd.type)
        contentValues.put(AMOUNT, atd.amount)
        contentValues.put(CONTEXT, atd.context)

        val success = db.insert(TBL_AMOUNT, null, contentValues)
        db.close()
        return success
    }

    @SuppressLint("Range")
    fun getAllAmount():ArrayList<AmountModel>{
        val atdList : ArrayList<AmountModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_AMOUNT"
        val db = this.readableDatabase

        val cursor: Cursor?

        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id:Int
        var date: String
        var type: String
        var amount: Int
        var context: String

        if(cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex("id"))
                date = cursor.getString(cursor.getColumnIndex("date"))
                type = cursor.getString(cursor.getColumnIndex("type"))
                amount = cursor.getInt(cursor.getColumnIndex("amount"))
                context = cursor.getString(cursor.getColumnIndex("context"))

                val atd = AmountModel(id = id, date = date, type = type, amount = amount, context = context)
                atdList.add(atd)
            }while(cursor.moveToNext())
        }

        return atdList

    }

    fun updateAmount(atd: AmountModel): Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, atd.id)
        contentValues.put(DATE, atd.date)
        contentValues.put(TYPE, atd.type)
        contentValues.put(AMOUNT, atd.amount)
        contentValues.put(CONTEXT, atd.context)

        val success = db.update(TBL_AMOUNT, contentValues, "id=" + atd.id, null)
        db.close()
        return success
    }

}