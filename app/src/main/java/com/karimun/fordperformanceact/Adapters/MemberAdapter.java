package com.karimun.fordperformanceact.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.karimun.fordperformanceact.EditMemberActivity;
import com.karimun.fordperformanceact.Models.Member;
import com.karimun.fordperformanceact.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder> {

    Context context;
    List<Member> members;

    public MemberAdapter(Context context, List<Member> members) {
        this.context = context;
        this.members = members;
    }

    @NonNull
    @Override
    public MemberAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_member, viewGroup, false);
        return new MemberAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberAdapter.ViewHolder viewHolder, int i) {

        final Member member = members.get(i);

        viewHolder.username.setText(member.getUsername());
        viewHolder.memberRole.setText(member.getMemberRole());
        viewHolder.membershipExpiry.setText(member.getMembershipExpiry());

        viewHolder.editMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, EditMemberActivity.class);
                intent.putExtra("memberId", member.getMemberId());
                intent.putExtra("username", member.getUsername());
                intent.putExtra("firstName", member.getFirstName());
                intent.putExtra("surname", member.getSurname());
                intent.putExtra("email", member.getEmail());
                intent.putExtra("memberRole", member.getMemberRole());
                intent.putExtra("membershipExpiry", member.getMembershipExpiry());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView profileImage;
        TextView username, memberRole, membershipExpiry;
        ImageView editMember;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profileImage = itemView.findViewById(R.id.profile_image);
            username = itemView.findViewById(R.id.username);
            memberRole = itemView.findViewById(R.id.member_role);
            membershipExpiry = itemView.findViewById(R.id.membership_expiry);
            editMember = itemView.findViewById(R.id.edit_member);
        }
    }
}
