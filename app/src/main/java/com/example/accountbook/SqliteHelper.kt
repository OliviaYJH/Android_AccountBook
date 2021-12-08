package com.example.accountbook

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class AccountBook(var no:Long?, var datetime:Long, var type:String, var asset:Long, var content:String)

class SqliteHelper(context: Context, name: String, version: Int) : SQLiteOpenHelper(context, name, null, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        // 처음 파일 생성
        val create = "create table accountBook (`no` integer primary key, datetime integer, type content, asset integer, content text)"
        db?.execSQL(create)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // version 바뀐 경우(table에 변경사항이 있는 경우에 호출됨)
        
        // SqliteHelper()의 생성자를 호출할 때 기존 db와 version 비교해 높으면 호출됨
        
    }

    // data 입력
    fun insertAccount(accountbook: AccountBook){
        // db 가져오기
        val wd = writableDatabase

        // AccountBook을 입력타입으로 변환
        val values = ContentValues()
        values.put("datetime", accountbook.datetime)
        values.put("type", accountbook.type)
        values.put("asset", accountbook.asset)
        values.put("content", accountbook.content)

        // db에 넣기
        wd.insert("accountBook", null, values)

        // db 닫기
        wd.close()

    }

    // data 조회
    @SuppressLint("Range")
    fun selectAccount(): MutableList<AccountBook>{
        val list = mutableListOf<AccountBook>()

        val select = "select * from accountBook"
        val rd = readableDatabase
        val cursor = rd.rawQuery(select, null)

        // cursor는 반복문을 돌면서 꺼낼 수 있음
        while(cursor.moveToNext()){
            val no = cursor.getLong(cursor.getColumnIndex("no"))
            val datetime = cursor.getLong(cursor.getColumnIndex("datetime"))
            val type = cursor.getString(cursor.getColumnIndex("type"))
            val asset = cursor.getLong(cursor.getColumnIndex("asset"))
            val content = cursor.getString(cursor.getColumnIndex("content"))

            val accountbook = AccountBook(no, datetime, type, asset, content)
            list.add(accountbook)
        }
        cursor.close()
        rd.close()

        return list
    }

    // data 수정
    fun updateMemo(accountbook: AccountBook){
        val wd = writableDatabase

        val values = ContentValues()
        values.put("datetime", accountbook.datetime)
        values.put("type", accountbook.type)
        values.put("asset", accountbook.asset)
        values.put("content", accountbook.content)

        wd.update("accountBook", values, "no = ${accountbook.no}", null)
        wd.close()
    }

    // data 삭제
    fun deleteAccount(accountbook: AccountBook){
        val wd = writableDatabase
        wd.delete("accountBook", "no = ${accountbook.no}", null)
        wd.close()
    }



}