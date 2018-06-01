package com.unesco.core.models.journal;

import com.unesco.core.models.shedule.PairModel;

import java.util.Date;

public class PairEventModel implements Cloneable {
    private long id;

    private Date date;

    private boolean everyDay;

    private PointTypeModel type;

    private PairModel pair;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isEveryDay() {
        return everyDay;
    }
    public void setEveryDay(boolean everyDay) {
        this.everyDay = everyDay;
    }

    public PointTypeModel getType() {
        return type;
    }
    public void setType(PointTypeModel type) {
        this.type = type;
    }

    public PairModel getPair() {
        return pair;
    }
    public void setPair(PairModel pair) {
        this.pair = pair;
    }

    @Override
    public PairEventModel clone() {
        Object cloned = null;
        try {
            cloned = super.clone();
        } catch (CloneNotSupportedException exc) {
            // В данном примере недостижимо.
        }
        return (PairEventModel)cloned;
    }

}
