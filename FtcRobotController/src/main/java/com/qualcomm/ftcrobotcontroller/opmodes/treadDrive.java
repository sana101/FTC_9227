package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


public class treadDrive extends OpMode {

    DcMotor motorRight1;
    DcMotor motorLeft1;

    /**
     * Constructor
     */

    public treadDrive(){

    }

    @Override
    public void init() {
        motorRight1 = hardwareMap.dcMotor.get("r1");

        motorLeft1 = hardwareMap.dcMotor.get("l1");
    }

    @Override
    public void loop() {
        float left = gamepad1.left_stick_y;
        float right = -gamepad1.right_stick_y;

        setLeftPower(left);
        setRightPower(right);

        telemetry.addData("left", left);
        telemetry.addData("right", right);
    }
    public void setLeftPower(float power){
        motorLeft1.setPower(scaleInput(power));
    }

    public void setRightPower(float power){
        motorRight1.setPower(scaleInput(power));
    }

    @Override
    public void stop() {

    }

    double scaleInput(double dVal) {
        double[] scaleArray = {0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00};


        int index = (int) (dVal * 16.0);
        if (index < 0) {
            index = -index;
        } else if (index > 16) {
            index = 16;
        }
        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        return dScale;
    }
}
