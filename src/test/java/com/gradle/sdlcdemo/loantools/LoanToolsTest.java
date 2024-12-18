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

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class LoanToolsTest {

    @Test
    void testPaymentWithInterest() {
        LoanTools loanTools = new LoanTools();
        BigDecimal payment = loanTools.calculatePayment(100000, 3.5, 30);
        assertThat(payment).isEqualTo(new BigDecimal("449.04"));
    }

    @Test
    void testPaymentWithoutInterest() {
        LoanTools loanTools = new LoanTools();
        BigDecimal payment = loanTools.calculatePayment(100000, 0.0, 30);
        assertThat(payment).isEqualTo(new BigDecimal("277.78"));
    }

    @Test
    void testPeriodsWithInterest() {
        LoanTools loanTools = new LoanTools();
        int nper = loanTools.calculatePeriods(100000, 3.5, 449.04);
        assertThat(nper).isEqualTo(360);
    }

    @Test
    void testPeriodsWithoutInterest() {
        LoanTools loanTools = new LoanTools();
        int nper = loanTools.calculatePeriods(100000, 0.0, 277.78);
        assertThat(nper).isEqualTo(360);
    }
}
