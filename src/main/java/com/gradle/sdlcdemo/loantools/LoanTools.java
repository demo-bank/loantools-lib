/*
 *    Copyright 2024 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.gradle.sdlcdemo.loantools;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoanTools {
    private static final Logger LOG = LogManager.getLogger(LoanTools.class);

    public BigDecimal calculatePayment(double amount, double rate, int years) {
        if (rate == 0.0) {
            return calculatePaymentWithoutInterest(amount, years);
        } else {
            return calculatePaymentWithInterest(amount, rate, years);
        }
    }

    private BigDecimal calculatePaymentWithInterest(double amount, double rate, int years) {
        LOG.debug("calculatePaymentWithInterest called with parameters {}, {}, {}", amount, rate, years);
        double monthlyRate = rate / 100.0 / 12.0;
        int numberOfPayments = years * 12;
        double payment = (monthlyRate * amount) / (1.0 - Math.pow(1.0 + monthlyRate, -numberOfPayments));
        BigDecimal answer = toMoney(payment);
        LOG.debug("Calculated payment {}", answer);
        return answer;
    }

    private BigDecimal calculatePaymentWithoutInterest(double amount, int years) {
        LOG.debug("calculatePaymentWithoutInterest called with parameters {}, {}", amount, years);
        int numberOfPayments = years * 12;
        BigDecimal answer = toMoney(amount / numberOfPayments);
        LOG.debug("Calculated payment {}", answer);
        return answer;
    }

    private BigDecimal toMoney(double d) {
        BigDecimal bd = new BigDecimal(d);
        return bd.setScale(2, RoundingMode.HALF_UP);
    }

    public int calculatePeriods(double amount, double rate, double payment) {
        if (rate == 0.0) {
            return calculatePeriodsWithoutInterest(amount, payment);
        } else {
            return calculatePeriodsWithInterest(amount, rate, payment);
        }
    }

    public int calculatePeriodsWithoutInterest(double amount, double payment) {
        LOG.debug("calculatePeriodsWithoutInterest called with parameters {}, {}", amount, payment);
        double nper = amount / payment;
        LOG.debug("Calculated periods {}", nper);
        return (int) Math.round(nper);
    }

    private int calculatePeriodsWithInterest(double amount, double rate, double payment) {
        LOG.debug("calculatePeriodsWithInterest called with parameters {}, {}, {}", amount, rate, payment);
        double monthlyRate = rate / 100 / 12;
        double nper = -Math.log(1 - amount / payment * monthlyRate) / Math.log(1 + monthlyRate);
        LOG.debug("Calculated periods {}", nper);
        return (int) Math.round(nper);
    }
}
