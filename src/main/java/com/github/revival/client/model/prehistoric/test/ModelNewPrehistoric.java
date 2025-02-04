package com.github.revival.client.model.prehistoric.test;

import com.github.revival.server.entity.mob.test.EntityNewPrehistoric;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.minecraft.entity.Entity;

public abstract class ModelNewPrehistoric extends AdvancedModelBase {

    public ModelNewPrehistoric() {
    }

    public void blockMovement(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        if (entity instanceof EntityNewPrehistoric && ((EntityNewPrehistoric) entity).isMovementBlocked()) {
            f = 0;
            f1 = 0;
            f3 = 0;
            f4 = 0;
        }
    }

    public void setRotateAngle(AdvancedModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    public void sitAnimationRotation(AdvancedModelRenderer modelRenderer, float sitProgress, float rotX, float rotY, float rotZ) {
        modelRenderer.rotateAngleX += sitProgress * rotX / 25.0F;
        modelRenderer.rotateAngleY += sitProgress * rotY / 25.0F;
        modelRenderer.rotateAngleZ += sitProgress * rotZ / 25.0F;
    }

    public void sitAnimationPos(AdvancedModelRenderer modelRenderer, float sitProgress, float x, float y, float z) {
        modelRenderer.rotationPointX += sitProgress * x / 20.0F;
        modelRenderer.rotationPointY += sitProgress * y / 20.0F;
        modelRenderer.rotationPointZ += sitProgress * z / 20.0F;
    }
}
