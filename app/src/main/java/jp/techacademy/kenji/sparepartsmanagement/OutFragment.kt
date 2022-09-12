package jp.techacademy.kenji.sparepartsmanagement

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_in.*
import kotlinx.android.synthetic.main.fragment_out.*
import kotlinx.android.synthetic.main.fragment_out.editTextItemSeach
import kotlinx.android.synthetic.main.fragment_test.*

class OutFragment: Fragment()  {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_out, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        swipeRefreshLayout.setOnRefreshListener{
//            updatedata()
//        }
        view.setOnTouchListener({view, event ->
            if(event.actionMasked == MotionEvent.ACTION_DOWN){
                view.requestFocus()
            }
            view?.onTouchEvent(event)?: true
        })

        editTextItemSeach.setOnFocusChangeListener{ view, hasFocus ->
            if(!hasFocus){
                val im = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                im.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
    }
//    private fun updatedata(){
//        swipeRefreshLayout.isRefreshing = false
//    }
}