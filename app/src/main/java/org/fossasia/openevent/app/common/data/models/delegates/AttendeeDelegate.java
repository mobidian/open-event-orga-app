package org.fossasia.openevent.app.common.data.models.delegates;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.View;

import com.mikepenz.fastadapter.items.AbstractItem;

import org.fossasia.openevent.app.R;
import org.fossasia.openevent.app.common.data.models.Attendee;
import org.fossasia.openevent.app.common.data.models.contract.IHeaderProvider;
import org.fossasia.openevent.app.common.utils.core.CompareUtils;
import org.fossasia.openevent.app.module.attendee.list.viewholders.AttendeeViewHolder;

import java.util.List;

public class AttendeeDelegate extends AbstractItem<Attendee, AttendeeViewHolder> implements Comparable<Attendee>, IHeaderProvider {

    private final Attendee attendee;

    public AttendeeDelegate(Attendee attendee) {
        this.attendee = attendee;
    }

    @Override
    public int compareTo(@NonNull Attendee other) {
        return CompareUtils.compareCascading(attendee, other,
            Attendee::getFirstname, Attendee::getLastname, Attendee::getEmail
        );
    }

    @Override
    public long getIdentifier() {
        return attendee.getId();
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.attendee_layout;
    }

    @Override
    public AttendeeViewHolder getViewHolder(View view) {
        return new AttendeeViewHolder(DataBindingUtil.bind(view));
    }

    @Override
    public void bindView(AttendeeViewHolder holder, List<Object> list) {
        super.bindView(holder, list);
        holder.bindAttendee(attendee);
    }

    @Override
    public void unbindView(AttendeeViewHolder holder) {
        super.unbindView(holder);
        holder.unbindAttendee();
    }

    @Override
    public String getHeader() {
        return attendee.getFirstname().substring(0, 1);
    }

    @Override
    public long getHeaderId() {
        return getHeader().charAt(0);
    }

}
