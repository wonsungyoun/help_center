package com.kakaopay.helpcenter.dto;

import com.kakaopay.helpcenter.common.constants.AppointStatus;
import lombok.Getter;

import java.util.List;

@Getter
public class CounselsData {
    private List<CounselData> unspecifiedCounselDataList;
    private List<CounselData> appointedCounselDataList;

    private AppointStatus appointStatus;

    public CounselsData() {

    }

    public CounselsData(AppointStatus appointStatus) {
        this.appointStatus = appointStatus;
    }

    public CounselsData(List<CounselData> unspecifiedCounselDataList, List<CounselData> appointedCounselDataList) {
        this.unspecifiedCounselDataList = unspecifiedCounselDataList;
        this.appointedCounselDataList = appointedCounselDataList;
    }


    public void appointResult(AppointStatus appointStatus) {
        this.appointStatus = appointStatus;
    }
}
