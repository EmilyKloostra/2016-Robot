/*
 * Copyright (c) 2016 FRC Team 4931
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.frc4931.robot.drive;

import org.strongback.command.Command;
import org.strongback.components.Switch;

/**
 * The command that drives the robot at a constant forward and turn speed for a specific duration.
 */
public class DriveUntil extends Command {

    private final DriveSystem drive;
    private final Switch stopSwitch;
    private final double driveSpeed;
    
    /**
     * Create a new command that drives forward at the given speed and duration.
     * @param drive the chassis
     * @param driveSpeed the speed at which to drive forward; should be [-1.0, 1.0]
     * @param stopWhen the switch that signals the drive should stop
     */
    public DriveUntil(DriveSystem drive, double driveSpeed, Switch stopWhen) {
        this(drive,driveSpeed,0.0,stopWhen);
    }
    
    /**
     * Create a new command that drives forward at the given speed and duration.
     * @param drive the chassis
     * @param driveSpeed the speed at which to drive forward; should be [-1.0, 1.0]
     * @param turnSpeed the speed at which to turn; should be [-1.0, 1.0]
     * @param stopWhen the switch that signals the drive should stop
     */
    public DriveUntil(DriveSystem drive, double driveSpeed, double turnSpeed, Switch stopWhen) {
        super(drive);
        this.drive = drive;
        this.driveSpeed = driveSpeed;
        this.stopSwitch = stopWhen;
    }
    
    @Override
    public boolean execute() {
    	System.out.print("is driving");
        drive.arcade(driveSpeed, 0.0);
        return stopSwitch.isTriggered();
    }
    
    @Override
    public void interrupted() {
        drive.stop();
    }
    
    @Override
    public void end() {
        drive.stop();
    }

}