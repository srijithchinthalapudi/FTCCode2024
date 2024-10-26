// Import Required Files
package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
//import org.firstinspires.ftc.teamcode.util.Encoder;


// Send Code And Operating Mode To Game Board
@TeleOp(name = "Srijith NoLimitViper", group = "MCA EAGLES PROGRAMS")
public class noLimitsViper extends LinearOpMode {

    double speedFactor = 0.8;

    // Define All Motors
    DcMotor frontLeft = null;
    DcMotor frontRight = null;
    DcMotor backLeft = null;
    DcMotor backRight = null;
    DcMotor verticalViper = null;
    DcMotor rightActuator = null;
    DcMotor leftActuator = null;


    CRServo intakeServo = null;
    Servo clawL = null;
    Servo clawR = null;

    @Override
    public void runOpMode() throws InterruptedException {


        // Hardware Map All Motors
        frontLeft = hardwareMap.dcMotor.get("Front_Left");
        frontRight = hardwareMap.dcMotor.get("Front_Right");
        backLeft = hardwareMap.dcMotor.get("Back_Left");
        backRight = hardwareMap.dcMotor.get("Back_Right");
        verticalViper = hardwareMap.dcMotor.get("Viper_Vertical");
        intakeServo = hardwareMap.crservo.get("Intake");
        clawL = hardwareMap.servo.get("Claw_Left");
        clawR = hardwareMap.servo.get("Claw_Right");

        // Set The Motors To Brake
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        verticalViper.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        // Reverse Direction Of One Side's Wheel Motors
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        // Only Start Code And Movement When Start Button Is Pressed
        waitForStart();

        double initialPos = verticalViper.getCurrentPosition();

        while (opModeIsActive()) {
            //telemetry.addData("Left pod position: ", leftEncoder.getCurrentPosition());
            //telemetry.addData("Right pod position: ", rightEncoder.getCurrentPosition());
            //telemetry.addData("Back pod position: ", frontEncoder.getCurrentPosition());
            telemetry.update();
            // Set drivetrain to move based off of the inputs of the left and right sticks of gamepad 1.
            //drivetrain
            frontLeft.setPower((-gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x)*speedFactor);
            backLeft.setPower((-gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x)*speedFactor);
            frontRight.setPower((-gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x)*speedFactor);
            backRight.setPower((-gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x)*speedFactor);

            //peripherals
            //viper

            double vertViperPow = (gamepad2.left_trigger-gamepad2.right_trigger)*speedFactor;
            /*
            if (verticalViper.getCurrentPosition() - initialPos < -11500 && vertViperPow < 0){
                vertViperPow=0;
            }
            if (verticalViper.getCurrentPosition() - initialPos >= 0 && vertViperPow > 0){
                vertViperPow=0;
            }*/
            verticalViper.setPower(vertViperPow);

            telemetry.addData("Initial viper Position", initialPos);
            telemetry.addData("Current viper position", verticalViper.getCurrentPosition());
            //rightActuator.setPower(gamepad2.left_stick_y*speedFactor);

            //intake
            if (gamepad2.left_bumper) intakeServo.setPower(1);
            else if (gamepad2.right_bumper) intakeServo.setPower(-1);
            else intakeServo.setPower(0);

            //specimen claw
            if (gamepad2.dpad_left) clawR.setPosition(0.25);
            if (gamepad2.dpad_right) clawR.setPosition(0);
            if (gamepad2.dpad_left) clawL.setPosition(0.35);
            if (gamepad2.dpad_right) clawL.setPosition(0.6);
            //-11641


        }
    }
}

