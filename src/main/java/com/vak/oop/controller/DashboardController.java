package com.vak.oop.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.DatePicker;

import java.lang.classfile.Label;

public class DashboardController {
    @FXML
    private Label totalRevenueMonth;
    @FXML
    private Label totalRevenueYear;
    @FXML
    private  Label bestSellerMonth;
    @FXML
    private  Label bestSellerYear;
    @FXML
    private LineChart<Number,Number> revenueChart;
    @FXML
    private PieChart bestSellerChart;
    @FXML
    private DatePicker datePicker;

    private DashboardSerice dashboardSerice;

}
