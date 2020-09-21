package com.tunabel.perfumestorev1.controller.api;

import com.tunabel.perfumestorev1.data.repository.*;
import com.tunabel.perfumestorev1.model.api.DataApiResult;
import com.tunabel.perfumestorev1.model.viewmodel.common.ChartDataVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.*;


@RestController
@RequestMapping(path = "/api/chart")
public class ChartApiController {

    @Autowired
    private ProductSKURepository productSKURepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/order/monthly-revenue")
    public DataApiResult getRevenueMonthByYear() {
        DataApiResult result = new DataApiResult();
        LocalDate date = LocalDate.now();
        List<ChartDataVM> chartDataVMS = orderRepository.getRevenueMonthByYear(date.getYear());
        List<ChartDataVM> newChartDataVMS = generateChartData(chartDataVMS, "year", "000");

        result.setMessage("success");
        result.setSuccessful(true);
        result.setData(newChartDataVMS);
        return result;
    }

    @GetMapping("/order/daily-revenue/{month}")
    public DataApiResult getRevenueDayByMonth(@PathVariable(required = false, name = "month") Integer month) {
        DataApiResult result = new DataApiResult();
        LocalDate date = LocalDate.now();
        List<ChartDataVM> chartDataVMS;
        if (month == 0) {
            chartDataVMS = orderRepository.getRevenueDayByMonth(date.getMonthValue(), date.getYear());
        } else {
            chartDataVMS = orderRepository.getRevenueDayByMonth(month, date.getYear());
        }
        List<ChartDataVM> newChartDataVMS = generateChartData(chartDataVMS, "month", "000");

        result.setMessage("success");
        result.setSuccessful(true);
        result.setData(newChartDataVMS);
        return result;
    }

    @GetMapping("/product/bestseller-month")
    public DataApiResult getBestsellersOfMonth() {
        DataApiResult result = new DataApiResult();
        LocalDate date = LocalDate.now();
        List<ChartDataVM> chartDataVMS = productRepository.getBestSellersOfMonth(date.getMonthValue(), date.getYear());

        for (ChartDataVM chartDataVM : chartDataVMS) {
            String rawValue = chartDataVM.getValue();
            chartDataVM.setValue(rawValue + "000");
        }

        result.setMessage("success");
        result.setSuccessful(true);
        result.setData(chartDataVMS);
        return result;
    }

    @GetMapping("/product/bestseller-year")
    public DataApiResult getBestsellersOfYear() {
        DataApiResult result = new DataApiResult();
        LocalDate date = LocalDate.now();
        List<ChartDataVM> chartDataVMS = productRepository.getBestSellersOfYear(date.getYear());

        for (ChartDataVM chartDataVM : chartDataVMS) {
            String rawValue = chartDataVM.getValue();
            chartDataVM.setValue(rawValue + "000");
        }

        result.setMessage("success");
        result.setSuccessful(true);
        result.setData(chartDataVMS);
        return result;
    }

    @GetMapping("/brand/bestseller-year")
    public DataApiResult getBestsellersBrandOfYear() {
        DataApiResult result = new DataApiResult();
        LocalDate date = LocalDate.now();
        List<ChartDataVM> chartDataVMS = brandRepository.getBestSellersBrandOfYear(date.getYear());

        for (ChartDataVM chartDataVM : chartDataVMS) {
            String rawValue = chartDataVM.getValue();
            chartDataVM.setValue(rawValue + "000");
        }

        result.setMessage("success");
        result.setSuccessful(true);
        result.setData(chartDataVMS);
        return result;
    }

    @GetMapping("/user/monthly-registration")
    public DataApiResult getUserRegistrationByMonth() {
        DataApiResult result = new DataApiResult();
        LocalDate date = LocalDate.now();
        List<ChartDataVM> chartDataVMS = userRepository.getUserRegistrationByYear(date.getYear());
        List<ChartDataVM> newChartDataVMS = generateChartData(chartDataVMS, "year", "");

        result.setMessage("success");
        result.setSuccessful(true);
        result.setData(newChartDataVMS);
        return result;
    }

    private List<ChartDataVM> generateChartData(List<ChartDataVM> sourceChartVM, String option, String valueSuffix) {
        LocalDate now = LocalDate.now();

        List<ChartDataVM> newChartDataVMS = new ArrayList<>();
        Calendar calendar = new GregorianCalendar(now.getYear(), now.getMonthValue() - 1, 1);

        if (option.equalsIgnoreCase("month")) {
            int numberOfDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            for (int i = 1; i <= numberOfDays; i++) {
                ChartDataVM addVM = new ChartDataVM();
                boolean isTheSameDay = false;
                for (ChartDataVM chartDataVM : sourceChartVM) {
                    if (i == Integer.parseInt(chartDataVM.getLabel())) {
                        String rawValue = chartDataVM.getValue();
                        addVM.setValue(rawValue + valueSuffix);
                        addVM.setLabel("Ngày " + chartDataVM.getLabel());
                        isTheSameDay = true;
                    }
                }

                if (!isTheSameDay) {
                    addVM.setLabel("Ngày " + i);
                    addVM.setValue("0");
                }
                newChartDataVMS.add(addVM);
            }
        }

        if (option.equalsIgnoreCase("year")) {
            for (int i = 1; i <= 12; i++) {
                ChartDataVM addVM = new ChartDataVM();
                boolean isTheSameMonth = false;
                for (ChartDataVM chartDataVM : sourceChartVM) {
                    if (i == Integer.parseInt(chartDataVM.getLabel())) {
                        String rawValue = chartDataVM.getValue();
                        addVM.setValue(rawValue + valueSuffix);
                        addVM.setLabel("Tháng " + chartDataVM.getLabel());
                        isTheSameMonth = true;
                    }
                }
                if (!isTheSameMonth) {
                    addVM.setLabel("Tháng " + i);
                    addVM.setValue("0");
                }
                newChartDataVMS.add(addVM);
            }
        }

        return newChartDataVMS;
    }

}
