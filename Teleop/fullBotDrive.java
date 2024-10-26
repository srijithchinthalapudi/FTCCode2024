// Import Required Files
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

// Send Code And Operating Mode To Game Board
@TeleOp(name = "Full Bot Drive", group = "MCA EAGLES PROGRAMS")
public class fullBotDrive extends LinearOpMode {

    double speedFactor = 1.0;

    // Define All Motors
    DcMotor frontLeft = null;
    DcMotor frontRight = null;
    DcMotor backLeft = null;
    DcMotor backRight = null;
    DcMotor viperV = null;
    DcMotor viperH = null;
    DcMotor susL = null;
    DcMotor susR = null;
    Servo sClawL = null;
    Servo sClawR = null;
    CRServo intake = null;
    Servo wrist = null;
    Servo bucket = null;
    Servo susGrip = null;

    @Override
    public void runOpMode() throws InterruptedException {

        // Hardware Map All Motors
        frontLeft = hardwareMap.dcMotor.get("Front_Left");
        frontRight = hardwareMap.dcMotor.get("Front_Right");
        backLeft = hardwareMap.dcMotor.get("Back_Left");
        backRight = hardwareMap.dcMotor.get("Back_Right");
        viperV = hardwareMap.dcMotor.get("Viper_Vertical");
        viperH = hardwareMap.dcMotor.get("Viper_Horizontal");
        susL = hardwareMap.dcMotor.get("Suspension_Left");
        susR = hardwareMap.dcMotor.get("Suspension_Right");

        // Hardware Map Servos
        sClawL = hardwareMap.servo.get("Claw_Left");
        sClawR = hardwareMap.servo.get("Claw_Right");
        intake = hardwareMap.crservo.get("Intake");
        wrist = hardwareMap.servo.get("Intake_Wrist");
        bucket = hardwareMap.servo.get("Bucket");
        susGrip = hardwareMap.servo.get("Suspension_Grip");

        // Set All Motors To Brake
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        viperV.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        viperH.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        susL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        susR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Reverse Direction Of One Side's Wheel Motors
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        viperH.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        viperH.setTargetPosition(0);

        viperV.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        viperV.setTargetPosition(0);

        // Only Start Code And Movement When Start Button Is Pressed
        waitForStart();

        while (opModeIsActive()) {

            // Set drivetrain to move based off of the inputs of the left and right sticks of gamepad 1.
            frontLeft.setPower((-gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x) * speedFactor);
            backLeft.setPower((-gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x) * speedFactor);
            frontRight.setPower((-gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x) * speedFactor);
            backRight.setPower((-gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x) * speedFactor);

            // TODO: Set viper motors to run using encoders. https://docs.revrobotics.com/duo-control/hello-robot-blocks/part-3/using-encoder

            // If gamepad 2's right stick is pushed up or down, move the horizontal viper forwards or backwards.
            if (gamepad2.left_stick_y != 0) {

                if (gamepad2.left_stick_y < 0) {
                    viperV.setPower(speedFactor);
                    viperV.setTargetPosition((int) (viperV.getCurrentPosition() - 300 * speedFactor));

                    if (viperV.getTargetPosition() < -11600) {
                        viperV.setTargetPosition(-11600);
                    }

                    viperV.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }

                else {
                    viperV.setPower(speedFactor);
                    viperV.setTargetPosition((int) (viperV.getCurrentPosition() + 300 * speedFactor));

                    if (viperV.getTargetPosition() > 0) {
                        viperV.setTargetPosition(0);
                    }

                    viperV.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }
            }

            // If gamepad 2's right stick is pushed up or down, move the horizontal viper forwards or backwards.
            if (gamepad2.right_stick_y != 0) {

                if (gamepad2.right_stick_y < 0) {
                    viperH.setPower(speedFactor);
                    viperH.setTargetPosition((int) (viperH.getCurrentPosition() - 300 * speedFactor));

                    if (viperH.getTargetPosition() < -2100) {
                        viperH.setTargetPosition(-2100);
                    }

                    viperH.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }

                else {
                    viperH.setPower(speedFactor);
                    viperH.setTargetPosition((int) (viperH.getCurrentPosition() + 300 * speedFactor));

                    if (viperH.getTargetPosition() > 0) {
                        viperH.setTargetPosition(0);
                    }

                    viperH.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }
            }

            // Otherwise, if its not moving,stop motor.
            else {
                viperH.setPower(0);
            }

            // If gamepad 2's x button is pushed, lower the suspension's linear actuators.
            if (gamepad2.x) {
                susL.setPower(-1);
                susR.setPower(-1);
            }

            // Otherwise, if gamepad 2's y button is pushed, lift the suspension's linear actuators.
            else if (gamepad2.y) {
                susL.setPower(1);
                susR.setPower(1);
            }

            // Otherwise, if neither are pressed, stop the suspension mechanism from moving.
            else {
                susL.setPower(0);
                susR.setPower(0);
            }

            // If gamepad 2's right trigger is pulled, activate the intake's servo to pull samples in.
            if (gamepad2.right_trigger > 0) {
                intake.setPower(-1);
            }

            // Otherwise, if gamepad 2's left trigger is pulled, activate the intake's servo to spit samples out.
            else if (gamepad2.left_trigger > 0) {
                intake.setPower(1);
            }

            // Otherwise, if neither are pulled, stop the intake's movement.
            else {
                intake.setPower(0);
            }

            // TODO: Fix positions of claw servos.

            // If gamepad 2's a button is pushed, close the specimen claw.
            if (gamepad2.a) {
                sClawL.setPosition(0.35);
                sClawR.setPosition(0.5);
            }

            // Otherwise, if gamepad 2's b button is pushed, close the specimen claw.
            else if (gamepad2.b) {
                sClawL.setPosition(0.55);
                sClawR.setPosition(0.21);
            }

            if (gamepad2.dpad_down) {
                wrist.setPosition(0.8950);
            }

            else if (gamepad2.dpad_left) {
                wrist.setPosition(0.26);
            }

            else if (gamepad2.dpad_up) {
                wrist.setPosition(0.5);
            }

            else if (gamepad2.dpad_right) {
                wrist.setPosition(0.26);
                sleep(750);

                intake.setPower(1);
                sleep(1500);

                intake.setPower(0);
                wrist.setPosition(0.5);

                sleep(500);
            }

            while (gamepad2.right_bumper) {
                bucket.setPosition(0.850);
            }

            while (gamepad2.left_bumper) {
                bucket.setPosition(0.1);
            }

            if (gamepad2.left_stick_x != 0) {
                susGrip.setPosition(gamepad2.left_stick_x);
            }

            // TODO: Add limits
            // TODO: Add Initial Positions
            // TODO: Add Hotkeys
            // TODO: Add Buttons For Positions

            // If gamepad 1's a button is pushed, set the max speed of all parts on the robot to full power.
            if (gamepad1.a) {
                speedFactor = 1.0;
            }

            // Otherwise, if gamepad 1's b button is pushed, set the max speed to 50% power.
            else if (gamepad2.b) {
                speedFactor = 0.5;
            }

            // Add telemetry data for all parts of the robot, for all motors and servos, for power and position, and for the current speed factor.
            telemetry.addData("Front Left Motor Power", frontLeft.getPower());
            telemetry.addData("Front Left Motor Position", frontLeft.getCurrentPosition());
            telemetry.addData("Front Right Motor Power", frontRight.getPower());
            telemetry.addData("Front Right Motor Position", frontRight.getCurrentPosition());
            telemetry.addData("Back Left Motor Power", backLeft.getPower());
            telemetry.addData("Back Left Motor Position", backLeft.getCurrentPosition());
            telemetry.addData("Back Right Motor Power", backRight.getPower());
            telemetry.addData("Back Right Motor Position", backRight.getCurrentPosition());
            telemetry.addData("Viper Vertical Motor Power", viperV.getPower());
            telemetry.addData("Viper Vertical Motor Position", viperV.getCurrentPosition());
            telemetry.addData("Viper Horizontal Motor Power", viperH.getPower());
            telemetry.addData("Viper Horizontal Motor Position", viperH.getCurrentPosition());
            telemetry.addData("Left Suspension Motor Power", susL.getPower());
            telemetry.addData("Left Suspension Motor Position", susL.getCurrentPosition());
            telemetry.addData("Right Suspension Motor Power", susR.getPower());
            telemetry.addData("Right Suspension Motor Position", susR.getCurrentPosition());
            telemetry.addData("Left Claw Servo Position", sClawL.getPosition());
            telemetry.addData("Right Claw Servo Position", sClawR.getPosition());
            telemetry.addData("Intake Servo Power", intake.getPower());
            telemetry.addData("Wrist Servo Position", wrist.getPosition());
            telemetry.addData("Bucket Servo Position", bucket.getPosition());
            telemetry.addData("Speed Factor", speedFactor);

            // Update the telemetry.
            telemetry.update();
        }
    }
}