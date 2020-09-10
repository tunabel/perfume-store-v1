package com.tunabel.perfumestorev1.model.viewmodel.admin;

import com.tunabel.perfumestorev1.model.viewmodel.common.ChartDataVM;
import com.tunabel.perfumestorev1.model.viewmodel.common.HeaderMenuAdminVM;

import java.util.List;

public class ChartVM {

    private HeaderMenuAdminVM headerMenuAdminVM;

    private List<ChartDataVM> chartDataVMS;

    public HeaderMenuAdminVM getHeaderMenuAdminVM() {
        return headerMenuAdminVM;
    }

    public void setHeaderMenuAdminVM(HeaderMenuAdminVM headerMenuAdminVM) {
        this.headerMenuAdminVM = headerMenuAdminVM;
    }

    public List<ChartDataVM> getChartDataVMS() {
        return chartDataVMS;
    }

    public void setChartDataVMS(List<ChartDataVM> chartDataVMS) {
        this.chartDataVMS = chartDataVMS;
    }
}
