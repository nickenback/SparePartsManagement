package jp.techacademy.kenji.sparepartsmanagement

import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
//import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_test.*
//import kotlinx.android.synthetic.main.fragment_test.swipeRefreshLayout
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

class TestFragment: Fragment(), View.OnClickListener  {
    companion object {
        private val DRIVER = "oracle.jdbc.driver.OracleDriver"
        private val URL = "jdbc:oracle:thin:@10.128.57.101:1521:XEPDB1"
        private val USERNAME = "TATSUTA"
        private val PASSWORD = "tatsuta"
    }
    private lateinit var con: Connection
    private lateinit var statement: Statement
    private lateinit var stringbuffer: StringBuffer
    private lateinit var resultset: ResultSet
    private var isConnected: Boolean? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_test, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .permitAll()
                .build()
        )
        button.setOnClickListener(this)
        QSbutton.setOnClickListener(this)

        ConUpdate()

        view.setOnTouchListener({view, event ->
            if(event.actionMasked == MotionEvent.ACTION_DOWN){
                view.requestFocus()
            }
            view?.onTouchEvent(event)?: true
        })

        QuerySendText.setOnFocusChangeListener{ view, hasFocus ->
            if(!hasFocus){
                val im = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                im.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }

    }

    override fun onClick(v: View?) {
        if (v === button) {
            if (isConnected == false || isConnected == null) {

                try {
                    Class.forName(DRIVER)
                    con = DriverManager.getConnection(URL, USERNAME, PASSWORD)

                    isConnected = true
                    QuerySendText.text = null
                    ConUpdate()
                } catch (e: Exception) {
                    textView.setText(e.toString())
                }
            }else{


            }
        }

        if (v === QSbutton) {
            val im = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            im.hideSoftInputFromWindow(v.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

            if (QuerySendText == null) {
                textViewR.setText("No command available")
            } else {
                try {
                    val command = QuerySendText.text.toString()
                    statement = con.createStatement()
                    stringbuffer = StringBuffer()
                    resultset = statement.executeQuery(command)

                    while (resultset.next()) {
//                        stringbuffer.append(resultset.getString(1) + "\t \t" + resultset.getString(2) + "\n")
                        stringbuffer.append(resultset.getString(1) + "\n")
                    }

                    textViewR.setText(stringbuffer.toString())
                    resultset.close()
                    statement.close()
                    con.close()
                    isConnected = false
                    ConUpdate()
                } catch (e: Exception) {
                    textViewR.setText(e.toString())
                }
            }
        }
    }

    private fun ConUpdate(){
        if(isConnected == true){
            textView.setText("Connected")
        }else{
            textView.setText("Disconnected")
        }
    }

}
