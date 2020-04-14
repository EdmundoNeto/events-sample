package com.edmundo.events.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.edmundo.events.databinding.DialogCheckinBinding
import com.edmundo.events.extensions.observe
import com.edmundo.events.viewmodel.CheckInViewModel
import kotlinx.android.synthetic.main.dialog_checkin.*
import org.koin.android.viewmodel.ext.android.viewModel

class CheckinDialog: DialogFragment() {

    private val viewModel: CheckInViewModel by viewModel()

    companion object {

        fun getDialog(eventId: String): CheckinDialog {
            val dialog = CheckinDialog()
            val arguments = Bundle()
            arguments.putString("eventId", eventId)
            dialog.arguments = arguments

            return dialog
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.setCanceledOnTouchOutside(true)

        val detailFragmentBinding = DialogCheckinBinding.inflate(inflater, container, false)

        detailFragmentBinding.checkinViewModel = viewModel
        detailFragmentBinding.eventId = arguments?.getString("eventId")

        return detailFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.run {
            observe(checkinFinished) {
                it?.run {
                    Toast.makeText(context!!, "Check-In realizado com sucesso!", Toast.LENGTH_LONG).show()
                    dismiss()
                }
            }

        }
    }
}