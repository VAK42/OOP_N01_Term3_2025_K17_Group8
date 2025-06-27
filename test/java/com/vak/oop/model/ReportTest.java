package com.vak.oop.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ReportTest {
  @Test
  public void testReportFields() {
    Report report = new Report();
    UUID id = UUID.randomUUID();
    User user = new User();
    String name = "Monthly Report";
    String info = "Detailed Analysis";
    report.setReportId(id);
    report.setUser(user);
    report.setRpName(name);
    report.setRpInfo(info);
    assertEquals(id, report.getReportId());
    assertEquals(user, report.getUser());
    assertEquals(name, report.getRpName());
    assertEquals(info, report.getRpInfo());
  }
}