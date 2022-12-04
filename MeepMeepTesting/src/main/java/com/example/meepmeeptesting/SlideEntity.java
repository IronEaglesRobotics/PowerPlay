/*
 * Make sure to use MeepMeep 1.0.1
 *
 * This is a very rough POC and not polished at all. Velocity doesn't decelerate, no clear units, etc.
 * Also the shot in the marker should be moved to a reusable function
 */

/**** SlideEntity.java *****/
package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.entity.Entity;
import com.noahbres.meepmeep.core.util.FieldUtil;

import org.jetbrains.annotations.NotNull;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class SlideEntity implements Entity {
    private String tag = "SLIDE_ENTITY";

    private int zIndex = 10;

    private MeepMeep meepMeep;

    private double canvasWidth = FieldUtil.getCANVAS_WIDTH();
    private double canvasHeight = FieldUtil.getCANVAS_WIDTH();

    private double radius = 2.5;
    private double thickness = 4;

    private Vector2d startPos;
    private Vector2d endPos;
    private Vector2d velocity;
    private Vector2d currentPos;

    private boolean extending = true;

    public SlideEntity(MeepMeep meepMeep, Vector2d startPos, Vector2d endPos, double travelTime) {
        this.meepMeep = meepMeep;

        this.startPos = startPos;
        this.currentPos = this.startPos;
        this.endPos = endPos;

        this.velocity = endPos.minus(startPos).div(travelTime);
    }

    @Override
    public void update(long deltaTime) {

        if (extending && currentPos.minus(startPos).norm() >= endPos.minus(startPos).norm()) {
            extending = false;
        }

        if (extending) {
            currentPos = currentPos.plus(this.velocity.times(deltaTime / 1e9));
        } else {
            currentPos = currentPos.minus(this.velocity.times(deltaTime / 1e9));

            if (currentPos.minus(startPos).norm() <= 1) {
                meepMeep.requestToRemoveEntity(this);
            }
        }

        if (currentPos.getX() > FieldUtil.getFIELD_WIDTH() / 2.0 || currentPos.getX() < -FieldUtil.getFIELD_WIDTH() / 2.0 || currentPos.getY() > FieldUtil.getFIELD_HEIGHT() / 2.0 || currentPos.getY() < -FieldUtil.getFIELD_HEIGHT() / 2.0) {
            meepMeep.requestToRemoveEntity(this);
        }
    }

    @Override
    public void render(Graphics2D gfx, int i, int i1) {
        Vector2d screenCoords = FieldUtil.fieldCoordsToScreenCoords(startPos);
        double radPixels = FieldUtil.scaleInchesToPixel(radius, canvasWidth, canvasHeight);

        Vector2d startCoords = FieldUtil.fieldCoordsToScreenCoords(startPos);
        Vector2d positionCoords = FieldUtil.fieldCoordsToScreenCoords(currentPos);

        gfx.setStroke(new BasicStroke((int) FieldUtil.scaleInchesToPixel(thickness, canvasWidth, canvasHeight)));
        gfx.setColor(new Color(49, 95, 206));
//        gfx.drawOval(
//                (int) (screenCoords.getX() - radPixels),
//                (int) (screenCoords.getY() - radPixels),
//                (int) (radPixels * 2),
//                (int) (radPixels * 2)
//        );

//        gfx.drawString("Hello", 0, 0);
        gfx.drawLine((int)startCoords.getX(), (int)startCoords.getY(), (int)positionCoords.getX(), (int)positionCoords.getY());
    }

    @NotNull
    @Override
    public MeepMeep getMeepMeep() {
        return null;
    }

    @NotNull
    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public int getZIndex() {
        return this.zIndex;
    }

    @Override
    public void setZIndex(int i) {
        this.zIndex = i;
    }

    @Override
    public void setCanvasDimensions(double width, double height) {
        this.canvasWidth = width;
        this.canvasHeight = height;
    }
}