package com.kakaopay.helpcenter.dto;

import com.kakaopay.helpcenter.common.constants.AppointStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public class AssignmentData extends CounselData {

    private AppointStatus appointStatus;

    public AssignmentData(CounselorData counselor, CounselData counsel, AppointStatus appointStatus) {
        super(
                counsel.getNo()
                , counsel.getCreateId()
                , counsel.getCreateId()
                , counselor.getUserName()
                , counsel.getTitle()
                , counsel.getDetail()
                , counsel.getAnswer()
                , counsel.getRegTime()
                , counsel.getAnswerTime()
        );

        this.appointStatus = appointStatus;
    }

    public AssignmentData(AppointStatus appointStatus) {
        this.appointStatus =appointStatus;
    }
}
