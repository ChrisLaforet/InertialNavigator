package com.chrislaforetsoftware.device;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class I2CSupportTest {

    @Test
    void givenNMEAString_whenMultipleDollarSigns_thenReturnsOptionalEmpty() {
        assertTrue(I2CSupport.validateNMEALine("$abscde$").isEmpty());
    }

    @Test
    void givenNMEAString_whenNotInitiatedWithDollarSign_thenReturnsOptionalEmpty() {
        assertTrue(I2CSupport.validateNMEALine("noDollar").isEmpty());
    }

    @Test
    void givenNMEAString_whenLongerThan84Chars_thenReturnsOptionalEmpty() {
        assertTrue(I2CSupport.validateNMEALine("$123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890").isEmpty());
    }

    @Test
    void givenNMEAString_whenContainsIllegalChar_thenReturnsOptionalEmpty() {
        assertTrue(I2CSupport.validateNMEALine("$012345\n").isEmpty());
    }

    @Test
    void givenNMEAString_whenContainsValueOftxbuf_thenReturnsOptionalEmpty() {
        assertTrue(I2CSupport.validateNMEALine("$012345txbufabc").isEmpty());
    }

    @Test
    void givenNMEAString_whenContainValidString_thenReturnsString() {
        assertTrue(I2CSupport.validateNMEALine("$GNVTG,,T,,M,0.252,N,0.466,K,A*3C").isPresent());
    }

    @Test
    void givenNMEAString_whenContainValidString2_thenReturnsString() {
        assertTrue(I2CSupport.validateNMEALine("$GNGSA,A,3,30,14,07,17,13,19,15,,,,,,1.66,1.01,1.31*17").isPresent());
    }

    @Test
    void givenNMEAString_whenContainInvalidString_thenReturnsString() {
        assertFalse(I2CSupport.validateNMEALine("$GNRMC,071423.00,A,3254.18201,S,15243.27916,E,0.252,,110721,,,A*72").isPresent());
    }

    @Test
    void givenGPSString_whenExtractingComponents_thenReturnsComponents() {
        final String [] components = I2CSupport.extractGPSComponents("$GNVTG,,T,,M,0.252,N,0.466,K,A*3C");
        assertEquals(10, components.length);
        assertEquals("GNVTG", components[0]);
        assertEquals("A", components[9]);
    }

}