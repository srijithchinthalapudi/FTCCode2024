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
@TeleOp(name = "Srijith Drivetrain", group = "MCA EAGLES PROGRAMS")
public class Drivetrain extends LinearOpMode {

    double speedFactor = 1.0;

    // Define All Motors
    DcMotor frontLeft = null;
    DcMotor frontRight = null;
    DcMotor backLeft = null;
    DcMotor backRight = null;


    @Override
    public void runOpMode() throws InterruptedException {


        // Hardware Map All Motors
        frontLeft = hardwareMap.dcMotor.get("Front_Left");
        frontRight = hardwareMap.dcMotor.get("Front_Right");
        backLeft = hardwareMap.dcMotor.get("Back_Left");
        backRight = hardwareMap.dcMotor.get("Back_Right");

        // Set The Motors To Brake
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Reverse Direction Of One Side's Wheel Motors
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        // Only Start Code And Movement When Start Button Is Pressed
        waitForStart();

        while (opModeIsActive()) {
            //telemetry.addData("Left pod position: ", leftEncoder.getCurrentPosition());
            //telemetry.addData("Right pod position: ", rightEncoder.getCurrentPosition());
            //telemetry.addData("Back pod position: ", frontEncoder.getCurrentPosition());
            telemetry.update();
            // Set drivetrain to move based off of the inputs of the left and right sticks of gamepad 1.
            frontLeft.setPower((-gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x)*speedFactor);
            backLeft.setPower((-gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x)*speedFactor);
            frontRight.setPower((-gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x)*speedFactor);
            backRight.setPower((-gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x)*speedFactor);
        }
    }
}

