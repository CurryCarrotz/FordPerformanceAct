package com.karimun.fordperformanceact.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.karimun.fordperformanceact.Adapters.MemberAdapter;
import com.karimun.fordperformanceact.MainActivity;
import com.karimun.fordperformanceact.Models.Member;
import com.karimun.fordperformanceact.R;

import java.util.ArrayList;
import java.util.List;

public class ManageMembersFragment extends Fragment {

    EditText searchMembers;
    Button btnSearch;
    TextView noMemberText;
    TextView a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z;
    RecyclerView recyclerView;

    List<Member> members;
    MemberAdapter memberAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manage_members, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        MainActivity.toggle.setDrawerIndicatorEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_hamburger);
        MainActivity.appBarLayout.setVisibility(View.VISIBLE);

        noMemberText = view.findViewById(R.id.no_member_text);
        searchMembers = view.findViewById(R.id.search_members);
        btnSearch = view.findViewById(R.id.btn_search);
        a = view.findViewById(R.id.a);
        b = view.findViewById(R.id.b);
        c = view.findViewById(R.id.c);
        d = view.findViewById(R.id.d);
        e = view.findViewById(R.id.e);
        f = view.findViewById(R.id.f);
        g = view.findViewById(R.id.g);
        h = view.findViewById(R.id.h);
        i = view.findViewById(R.id.i);
        j = view.findViewById(R.id.j);
        k = view.findViewById(R.id.k);
        l = view.findViewById(R.id.l);
        m = view.findViewById(R.id.m);
        n = view.findViewById(R.id.n);
        o = view.findViewById(R.id.o);
        p = view.findViewById(R.id.p);
        q = view.findViewById(R.id.q);
        r = view.findViewById(R.id.r);
        s = view.findViewById(R.id.s);
        t = view.findViewById(R.id.t);
        u = view.findViewById(R.id.u);
        v = view.findViewById(R.id.v);
        w = view.findViewById(R.id.w);
        x = view.findViewById(R.id.x);
        y = view.findViewById(R.id.y);
        z = view.findViewById(R.id.z);

        recyclerView = view.findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        members = new ArrayList<>();

        getAllMembers();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMemberSearch(searchMembers.getText().toString());
            }
        });

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAClick();
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBClick();
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCClick();
            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDClick();
            }
        });
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEClick();
            }
        });
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFClick();
            }
        });
        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGClick();
            }
        });
        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onHClick();
            }
        });
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onIClick();
            }
        });
        j.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onJClick();
            }
        });
        k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onKClick();
            }
        });
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLClick();
            }
        });
        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMClick();
            }
        });
        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNClick();
            }
        });
        o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOClick();
            }
        });
        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPClick();
            }
        });
        q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onQClick();
            }
        });
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRClick();
            }
        });
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSClick();
            }
        });
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTClick();
            }
        });
        u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUClick();
            }
        });
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onVClick();
            }
        });
        w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onWClick();
            }
        });
        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onXClick();
            }
        });
        y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onYClick();
            }
        });
        z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onZClick();
            }
        });
    }

    private void getAllMembers() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                members.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Member member = snapshot.getValue(Member.class);

                    members.add(member);

                    memberAdapter = new MemberAdapter(getContext(), members);
                    recyclerView.setAdapter(memberAdapter);
                }

                if (members.size() > 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    noMemberText.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    noMemberText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void onMemberSearch(String input) {

        Query query = FirebaseDatabase.getInstance().getReference("Member").orderByChild("username")
                .startAt(input)
                .endAt(input + "\uf8ff");

        if (!input.trim().equals("")) {
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    members.clear();

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Member member = snapshot.getValue(Member.class);

                        members.add(member);
                        memberAdapter = new MemberAdapter(getContext(), members);
                        recyclerView.setAdapter(memberAdapter);
                    }

                    if (members.size() > 0) {
                        recyclerView.setVisibility(View.VISIBLE);
                        noMemberText.setVisibility(View.GONE);
                    } else {
                        recyclerView.setVisibility(View.GONE);
                        noMemberText.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else {
            getAllMembers();
        }
    }

    private void onAClick() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                members.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Member member = snapshot.getValue(Member.class);

                    if (member != null && member.getUsername().substring(0, 1).equalsIgnoreCase("a")) {

                        members.add(member);
                        memberAdapter = new MemberAdapter(getContext(), members);
                        recyclerView.setAdapter(memberAdapter);
                    }
                }

                if (members.size() > 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    noMemberText.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    noMemberText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void onBClick() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                members.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Member member = snapshot.getValue(Member.class);

                    if (member != null && member.getUsername().substring(0, 1).equalsIgnoreCase("b")) {

                        members.add(member);
                        memberAdapter = new MemberAdapter(getContext(), members);
                        recyclerView.setAdapter(memberAdapter);
                    }
                }

                if (members.size() > 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    noMemberText.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    noMemberText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void onCClick() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                members.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Member member = snapshot.getValue(Member.class);

                    if (member != null && member.getUsername().substring(0, 1).equalsIgnoreCase("c")) {

                        members.add(member);
                        memberAdapter = new MemberAdapter(getContext(), members);
                        recyclerView.setAdapter(memberAdapter);
                    }
                }

                if (members.size() > 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    noMemberText.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    noMemberText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void onDClick() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                members.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Member member = snapshot.getValue(Member.class);

                    if (member != null && member.getUsername().substring(0, 1).equalsIgnoreCase("d")) {

                        members.add(member);
                        memberAdapter = new MemberAdapter(getContext(), members);
                        recyclerView.setAdapter(memberAdapter);
                    }
                }

                if (members.size() > 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    noMemberText.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    noMemberText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void onEClick() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                members.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Member member = snapshot.getValue(Member.class);

                    if (member != null && member.getUsername().substring(0, 1).equalsIgnoreCase("e")) {

                        members.add(member);
                        memberAdapter = new MemberAdapter(getContext(), members);
                        recyclerView.setAdapter(memberAdapter);
                    }
                }

                if (members.size() > 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    noMemberText.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    noMemberText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void onFClick() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                members.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Member member = snapshot.getValue(Member.class);

                    if (member != null && member.getUsername().substring(0, 1).equalsIgnoreCase("f")) {

                        members.add(member);
                        memberAdapter = new MemberAdapter(getContext(), members);
                        recyclerView.setAdapter(memberAdapter);
                    }
                }

                if (members.size() > 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    noMemberText.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    noMemberText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void onGClick() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                members.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Member member = snapshot.getValue(Member.class);

                    if (member != null && member.getUsername().substring(0, 1).equalsIgnoreCase("g")) {

                        members.add(member);
                        memberAdapter = new MemberAdapter(getContext(), members);
                        recyclerView.setAdapter(memberAdapter);
                    }
                }

                if (members.size() > 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    noMemberText.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    noMemberText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void onHClick() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                members.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Member member = snapshot.getValue(Member.class);

                    if (member != null && member.getUsername().substring(0, 1).equalsIgnoreCase("h")) {

                        members.add(member);
                        memberAdapter = new MemberAdapter(getContext(), members);
                        recyclerView.setAdapter(memberAdapter);
                    }
                }

                if (members.size() > 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    noMemberText.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    noMemberText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void onIClick() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                members.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Member member = snapshot.getValue(Member.class);

                    if (member != null && member.getUsername().substring(0, 1).equalsIgnoreCase("i")) {

                        members.add(member);
                        memberAdapter = new MemberAdapter(getContext(), members);
                        recyclerView.setAdapter(memberAdapter);
                    }
                }

                if (members.size() > 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    noMemberText.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    noMemberText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void onJClick() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                members.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Member member = snapshot.getValue(Member.class);

                    if (member != null && member.getUsername().substring(0, 1).equalsIgnoreCase("j")) {

                        members.add(member);
                        memberAdapter = new MemberAdapter(getContext(), members);
                        recyclerView.setAdapter(memberAdapter);
                    }
                }

                if (members.size() > 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    noMemberText.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    noMemberText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void onKClick() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                members.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Member member = snapshot.getValue(Member.class);

                    if (member != null && member.getUsername().substring(0, 1).equalsIgnoreCase("k")) {

                        members.add(member);
                        memberAdapter = new MemberAdapter(getContext(), members);
                        recyclerView.setAdapter(memberAdapter);
                    }
                }

                if (members.size() > 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    noMemberText.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    noMemberText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void onLClick() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                members.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Member member = snapshot.getValue(Member.class);

                    if (member != null && member.getUsername().substring(0, 1).equalsIgnoreCase("l")) {

                        members.add(member);
                        memberAdapter = new MemberAdapter(getContext(), members);
                        recyclerView.setAdapter(memberAdapter);
                    }
                }

                if (members.size() > 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    noMemberText.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    noMemberText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void onMClick() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                members.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Member member = snapshot.getValue(Member.class);

                    if (member != null && member.getUsername().substring(0, 1).equalsIgnoreCase("m")) {

                        members.add(member);
                        memberAdapter = new MemberAdapter(getContext(), members);
                        recyclerView.setAdapter(memberAdapter);
                    }
                }

                if (members.size() > 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    noMemberText.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    noMemberText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void onNClick() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                members.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Member member = snapshot.getValue(Member.class);

                    if (member != null && member.getUsername().substring(0, 1).equalsIgnoreCase("n")) {

                        members.add(member);
                        memberAdapter = new MemberAdapter(getContext(), members);
                        recyclerView.setAdapter(memberAdapter);
                    }
                }

                if (members.size() > 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    noMemberText.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    noMemberText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void onOClick() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                members.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Member member = snapshot.getValue(Member.class);

                    if (member != null && member.getUsername().substring(0, 1).equalsIgnoreCase("o")) {

                        members.add(member);
                        memberAdapter = new MemberAdapter(getContext(), members);
                        recyclerView.setAdapter(memberAdapter);
                    }
                }

                if (members.size() > 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    noMemberText.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    noMemberText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void onPClick() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                members.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Member member = snapshot.getValue(Member.class);

                    if (member != null && member.getUsername().substring(0, 1).equalsIgnoreCase("p")) {

                        members.add(member);
                        memberAdapter = new MemberAdapter(getContext(), members);
                        recyclerView.setAdapter(memberAdapter);
                    }
                }

                if (members.size() > 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    noMemberText.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    noMemberText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void onQClick() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                members.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Member member = snapshot.getValue(Member.class);

                    if (member != null && member.getUsername().substring(0, 1).equalsIgnoreCase("q")) {

                        members.add(member);
                        memberAdapter = new MemberAdapter(getContext(), members);
                        recyclerView.setAdapter(memberAdapter);
                    }
                }

                if (members.size() > 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    noMemberText.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    noMemberText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void onRClick() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                members.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Member member = snapshot.getValue(Member.class);

                    if (member != null && member.getUsername().substring(0, 1).equalsIgnoreCase("r")) {

                        members.add(member);
                        memberAdapter = new MemberAdapter(getContext(), members);
                        recyclerView.setAdapter(memberAdapter);
                    }
                }

                if (members.size() > 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    noMemberText.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    noMemberText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void onSClick() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                members.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Member member = snapshot.getValue(Member.class);

                    if (member != null && member.getUsername().substring(0, 1).equalsIgnoreCase("s")) {

                        members.add(member);
                        memberAdapter = new MemberAdapter(getContext(), members);
                        recyclerView.setAdapter(memberAdapter);
                    }
                }

                if (members.size() > 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    noMemberText.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    noMemberText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void onTClick() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                members.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Member member = snapshot.getValue(Member.class);

                    if (member != null && member.getUsername().substring(0, 1).equalsIgnoreCase("t")) {

                        members.add(member);
                        memberAdapter = new MemberAdapter(getContext(), members);
                        recyclerView.setAdapter(memberAdapter);
                    }
                }

                if (members.size() > 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    noMemberText.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    noMemberText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void onUClick() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                members.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Member member = snapshot.getValue(Member.class);

                    if (member != null && member.getUsername().substring(0, 1).equalsIgnoreCase("u")) {

                        members.add(member);
                        memberAdapter = new MemberAdapter(getContext(), members);
                        recyclerView.setAdapter(memberAdapter);
                    }
                }

                if (members.size() > 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    noMemberText.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    noMemberText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void onVClick() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                members.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Member member = snapshot.getValue(Member.class);

                    if (member != null && member.getUsername().substring(0, 1).equalsIgnoreCase("v")) {

                        members.add(member);
                        memberAdapter = new MemberAdapter(getContext(), members);
                        recyclerView.setAdapter(memberAdapter);
                    }
                }

                if (members.size() > 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    noMemberText.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    noMemberText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void onWClick() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                members.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Member member = snapshot.getValue(Member.class);

                    if (member != null && member.getUsername().substring(0, 1).equalsIgnoreCase("w")) {

                        members.add(member);
                        memberAdapter = new MemberAdapter(getContext(), members);
                        recyclerView.setAdapter(memberAdapter);
                    }
                }

                if (members.size() > 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    noMemberText.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    noMemberText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void onXClick() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                members.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Member member = snapshot.getValue(Member.class);

                    if (member != null && member.getUsername().substring(0, 1).equalsIgnoreCase("x")) {

                        members.add(member);
                        memberAdapter = new MemberAdapter(getContext(), members);
                        recyclerView.setAdapter(memberAdapter);
                    }
                }

                if (members.size() > 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    noMemberText.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    noMemberText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void onYClick() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                members.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Member member = snapshot.getValue(Member.class);

                    if (member != null && member.getUsername().substring(0, 1).equalsIgnoreCase("y")) {

                        members.add(member);
                        memberAdapter = new MemberAdapter(getContext(), members);
                        recyclerView.setAdapter(memberAdapter);
                    }
                }

                if (members.size() > 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    noMemberText.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    noMemberText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void onZClick() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                members.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Member member = snapshot.getValue(Member.class);

                    if (member != null && member.getUsername().substring(0, 1).equalsIgnoreCase("z")) {

                        members.add(member);
                        memberAdapter = new MemberAdapter(getContext(), members);
                        recyclerView.setAdapter(memberAdapter);
                    }
                }

                if (members.size() > 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    noMemberText.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    noMemberText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}