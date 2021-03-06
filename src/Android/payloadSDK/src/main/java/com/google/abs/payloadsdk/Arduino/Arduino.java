package com.google.abs.payloadsdk.Arduino;

import android.util.Log;
import com.google.abs.payloadsdk.SBD.SDB;
import com.google.abs.payloadsdk.SBD.SDBPacket;

public class Arduino {

    private SDB sdb;

    /**
     * Arduino constructor
     * @param sdb
     */

    public Arduino(SDB sdb)
    {
        this.sdb = sdb;
    }

    /**
     * Write a 1 or a 0 value to a digital pin.
     *
     * @param pin   the number of the digital pin you want to write (int)
     * @param value 1 or 0
     * @return      (integer) error_code
     */

    public int digitalWrite(int pin, int value)
    {
        byte[] array = {(byte)pin, (byte)value};
        SDBPacket response = sdb.send(new SDBPacket(
                SDBPacket.CMD.DIGITAL_WRITE, array, null));

        if(response.getCMD() == SDBPacket.CMD.OK) {
            return 1;
        } else {
            return -1; /* something has gone wrong */
        }
    }

    /**
     * Reads the value from a specified digital pin.
     *
     * @param pin   the number of the digital pin you want to read (int)
     * @return      (boolean) 1 or 0
     */

    public int digitalRead(int pin)
    {
        byte[] array = {(byte)pin, (byte)0};
        SDBPacket response = sdb.send(new SDBPacket(
                SDBPacket.CMD.DIGITAL_READ, array, null));

        if(response.getCMD() == SDBPacket.CMD.OK_DATA) {
            return response.getParameter(0);
        } else {
            return -1; /* something has gone wrong */
        }
    }

    /**
     * Writes an analog value (PWM wave) to a pin.
     *
     * @param pin   the number of the analog pin you want to write (int)
     * @param value the duty cycle: between 0 and 255
     * @return      (integer) error_code
     */

    public int analogWrite(int pin, int value)
    {
        byte[] array = {(byte)pin, (byte)value};
        SDBPacket response = sdb.send(new SDBPacket(
                SDBPacket.CMD.ANALOG_WRITE, array, null));

        if(response.getCMD() == SDBPacket.CMD.OK) {
            return 1;
        } else {
            return -1; /* something has gone wrong */
        }
    }

    /**
     * Reads the value from a specific analog pin.
     *
     * @param pin   the number of the analog pin you want to read (int)
     * @return      (integer) 0 to 1023
     */

    public int analogRead(int pin)
    {
        byte[] array = {(byte)pin, (byte)0};
        SDBPacket response = sdb.send(new SDBPacket(
                SDBPacket.CMD.ANALOG_READ, array, null));

        if(response.getCMD() == SDBPacket.CMD.OK_DATA) {
            return response.getParameter(0) & 0xFF;
        } else {
            return -1; /* something has gone wrong */
        }
    }

    /**
     * Creates a new Event and returns an EventHandler
     *
     * @return EventHandler
     */

    public EventHandler createEvent(int interval, byte[] data)
    {
        byte[] array = {(byte)interval, (byte)0};

        SDBPacket response = sdb.send(new SDBPacket(
                SDBPacket.CMD.CREATE_EVENT, array, data));

        return null; //new EventHandler(response.getParameter(0));
    }
}