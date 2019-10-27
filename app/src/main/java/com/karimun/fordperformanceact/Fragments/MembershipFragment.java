package com.karimun.fordperformanceact.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.karimun.fordperformanceact.R;



public class MembershipFragment extends Fragment {

    private EditText mMembershipRole;
    private EditText mMembershipRenewalDue;
    private EditText mMembershipExpiry;
    private EditText mMemberSince;
    private EditText mMembershipRenewalPaid;
    private TextView mSendTo;
    private TextView mSubject;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_membership, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMembershipRole = view.findViewById(R.id.edt_txt_role);
        mMembershipRenewalDue = view.findViewById(R.id.edt_txt_due);
        mMembershipExpiry = view.findViewById(R.id.edt_txt_exp_date);
        mMemberSince = view.findViewById(R.id.edt_txt_mem_since);
        mMembershipRenewalPaid = view.findViewById(R.id.edt_txt_rnwal_paid);
        mSendTo = view.findViewById(R.id.recipient);
        mSubject = view.findViewById(R.id.subject);

        Button btnSend = view.findViewById(R.id.btn_renewal_em);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });
    }

    private void sendMail(){
        String recipient = mSendTo.getText().toString();
        String subject = mSubject.getText().toString();
        String[] message = new String[] {mMembershipRole.getText().toString(), mMembershipRenewalDue.getText().toString(), mMembershipExpiry.getText().toString(), mMemberSince.getText().toString(), mMembershipRenewalPaid.getText().toString()};

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipient);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }
}
