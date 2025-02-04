package com.github.revival.client.model.prehistoric;

import com.github.revival.client.model.prehistoric.test.ModelNewPrehistoric;
import com.github.revival.server.entity.mob.EntityAnkylosaurus;
import com.github.revival.server.entity.mob.test.EntityNewPrehistoric;
import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAnkylosaurus extends ModelNewPrehistoric {
    public AdvancedModelRenderer Body;
    public AdvancedModelRenderer BodyUpper;
    public AdvancedModelRenderer BodySpikes;
    public AdvancedModelRenderer Tail1;
    public AdvancedModelRenderer BackThighR;
    public AdvancedModelRenderer BackThighL;
    public AdvancedModelRenderer Neck;
    public AdvancedModelRenderer BodyUpperSpikes;
    public AdvancedModelRenderer FrontThighR;
    public AdvancedModelRenderer FrontThighL;
    public AdvancedModelRenderer Head;
    public AdvancedModelRenderer HornL1;
    public AdvancedModelRenderer HeadBlock;
    public AdvancedModelRenderer Mouth;
    public AdvancedModelRenderer HornL2;
    public AdvancedModelRenderer HornR1;
    public AdvancedModelRenderer HornR2;
    public AdvancedModelRenderer Mouth_1;
    public AdvancedModelRenderer FrontLegR;
    public AdvancedModelRenderer FrontLegL;
    public AdvancedModelRenderer Tail2;
    public AdvancedModelRenderer TailSpikes;
    public AdvancedModelRenderer Tail3;
    public AdvancedModelRenderer TailClub;
    public AdvancedModelRenderer BackLegR;
    public AdvancedModelRenderer BackLegL;
    private ModelAnimator animator;

    public ModelAnkylosaurus() {
        this.textureWidth = 256;
        this.textureHeight = 128;
        this.Body = new AdvancedModelRenderer(this, 68, 2);
        this.Body.setRotationPoint(0.0F, 13.7F, -3.0F);
        this.Body.addBox(-7.0F, -5.0F, 0.0F, 14, 10, 12, 0.0F);
        this.setRotateAngle(Body, -3.887035670558508E-15F, -0.0F, 0.0F);
        this.Head = new AdvancedModelRenderer(this, 1, 1);
        this.Head.setRotationPoint(0.0F, 0.0F, -6.1F);
        this.Head.addBox(-4.0F, -1.9F, -7.0F, 8, 7, 7, 0.0F);
        this.setRotateAngle(Head, 0.22759093446006054F, -0.0F, 0.0F);
        this.BodyUpper = new AdvancedModelRenderer(this, 34, 17);
        this.BodyUpper.setRotationPoint(0.0F, -0.4F, 0.6F);
        this.BodyUpper.addBox(-6.0F, -4.2F, -8.1F, 12, 9, 8, 0.0F);
        this.setRotateAngle(BodyUpper, 0.045553093477052F, -0.0F, 0.0F);
        this.HeadBlock = new AdvancedModelRenderer(this, 24, 0);
        this.HeadBlock.setRotationPoint(0.0F, -0.9F, -4.1F);
        this.HeadBlock.addBox(-3.0F, -1.0F, 0.0F, 6, 2, 5, 0.0F);
        this.setRotateAngle(HeadBlock, 0.18203784098300857F, -0.0F, 0.0F);
        this.Mouth = new AdvancedModelRenderer(this, 0, 15);
        this.Mouth.setRotationPoint(0.0F, 0.9F, -6.3F);
        this.Mouth.addBox(-2.0F, -2.0F, -4.0F, 4, 5, 4, 0.0F);
        this.setRotateAngle(Mouth, 0.136659280431156F, -0.0F, 0.0F);
        this.HornL2 = new AdvancedModelRenderer(this, 32, 7);
        this.HornL2.setRotationPoint(2.8F, 1.5F, -1.5F);
        this.HornL2.addBox(0.0F, 0.0F, -1.5F, 3, 2, 3, 0.0F);
        this.setRotateAngle(HornL2, 0.8726646259971648F, -0.3490658503988659F, 0.5235987755982988F);
        this.FrontLegL = new AdvancedModelRenderer(this, 0, 35);
        this.FrontLegL.mirror = true;
        this.FrontLegL.setRotationPoint(0.0F, 4.1F, -1.3F);
        this.FrontLegL.addBox(-1.5F, 0.0F, -1.0F, 3, 4, 4, 0.0F);
        this.setRotateAngle(FrontLegL, -0.2617993877991494F, -0.0F, 0.0F);
        this.HornR1 = new AdvancedModelRenderer(this, 32, 7);
        this.HornR1.mirror = true;
        this.HornR1.setRotationPoint(-2.8F, -2.5F, -0.4F);
        this.HornR1.addBox(-3.0F, 0.0F, -0.5F, 3, 2, 3, 0.0F);
        this.setRotateAngle(HornR1, 0.6283185307179586F, -0.08726646259971647F, -0.8726646259971648F);
        this.TailClub = new AdvancedModelRenderer(this, 72, 36);
        this.TailClub.setRotationPoint(0.0F, 0.1F, 8.9F);
        this.TailClub.addBox(-3.5F, -3.0F, -0.4F, 7, 5, 6, 0.0F);
        this.setRotateAngle(TailClub, 0.136659280431156F, -0.0F, 0.0F);
        this.BackLegL = new AdvancedModelRenderer(this, 0, 35);
        this.BackLegL.mirror = true;
        this.BackLegL.setRotationPoint(0.0F, 4.8F, 1.0F);
        this.BackLegL.addBox(-1.5F, -0.2F, -2.0F, 3, 4, 4, 0.0F);
        this.Tail3 = new AdvancedModelRenderer(this, 100, 35);
        this.Tail3.setRotationPoint(0.0F, -0.4F, 4.9F);
        this.Tail3.addBox(-2.0F, -1.6F, 0.9F, 4, 4, 8, 0.0F);
        this.setRotateAngle(Tail3, 0.045553093477052F, -0.0F, 0.0F);
        this.FrontThighR = new AdvancedModelRenderer(this, 18, 24);
        this.FrontThighR.setRotationPoint(-6.5F, 2.1F, -5.9F);
        this.FrontThighR.addBox(-2.0F, 0.0F, -2.0F, 4, 5, 4, 0.0F);
        this.setRotateAngle(FrontThighR, 0.22759093446006054F, -0.0F, 0.0F);
        this.HornR2 = new AdvancedModelRenderer(this, 32, 7);
        this.HornR2.mirror = true;
        this.HornR2.setRotationPoint(-2.8F, 1.5F, -1.5F);
        this.HornR2.addBox(-3.0F, 0.0F, -1.5F, 3, 2, 3, 0.0F);
        this.setRotateAngle(HornR2, 0.8726646259971648F, 0.3490658503988659F, -0.5235987755982988F);
        this.TailSpikes = new AdvancedModelRenderer(this, 14, 68);
        this.TailSpikes.setRotationPoint(0.0F, -1.2F, 1.2F);
        this.TailSpikes.addBox(-7.5F, 0.0F, -2.6F, 15, 2, 7, 0.0F);
        this.setRotateAngle(TailSpikes, 0.091106186954104F, -0.0F, 0.0F);
        this.BodyUpperSpikes = new AdvancedModelRenderer(this, 14, 35);
        this.BodyUpperSpikes.setRotationPoint(0.0F, -1.8F, -4.5F);
        this.BodyUpperSpikes.addBox(-7.0F, 0.0F, -5.0F, 14, 2, 10, 0.0F);
        this.Tail2 = new AdvancedModelRenderer(this, 105, 24);
        this.Tail2.setRotationPoint(0.0F, 0.5F, 5.7F);
        this.Tail2.addBox(-2.5F, -2.5F, 0.0F, 5, 5, 6, 0.0F);
        this.setRotateAngle(Tail2, 0.045553093477052F, -0.0F, 0.0F);
        this.BackThighR = new AdvancedModelRenderer(this, 0, 25);
        this.BackThighR.setRotationPoint(-7.0F, 1.7F, 9.3F);
        this.BackThighR.addBox(-2.0F, 0.0F, -2.5F, 4, 5, 5, 0.0F);
        this.FrontThighL = new AdvancedModelRenderer(this, 18, 24);
        this.FrontThighL.mirror = true;
        this.FrontThighL.setRotationPoint(6.5F, 2.1F, -5.9F);
        this.FrontThighL.addBox(-2.0F, 0.0F, -2.0F, 4, 5, 4, 0.0F);
        this.setRotateAngle(FrontThighL, 0.22759093446006054F, -0.0F, 0.0F);
        this.Neck = new AdvancedModelRenderer(this, 57, 48);
        this.Neck.setRotationPoint(0.0F, -1.9F, -7.8F);
        this.Neck.addBox(-3.0F, -1.4F, -6.1F, 6, 6, 6, 0.0F);
        this.BodySpikes = new AdvancedModelRenderer(this, 0, 48);
        this.BodySpikes.setRotationPoint(0.0F, -2.4F, 6.0F);
        this.BodySpikes.addBox(-9.0F, 0.0F, -8.0F, 18, 2, 13, 0.0F);
        this.setRotateAngle(BodySpikes, 0.008726646259971648F, -0.0F, 0.0F);
        this.FrontLegR = new AdvancedModelRenderer(this, 0, 35);
        this.FrontLegR.setRotationPoint(0.0F, 4.1F, -1.3F);
        this.FrontLegR.addBox(-1.5F, 0.0F, -1.0F, 3, 4, 4, 0.0F);
        this.setRotateAngle(FrontLegR, -0.2617993877991494F, -0.0F, 0.0F);
        this.BackLegR = new AdvancedModelRenderer(this, 0, 35);
        this.BackLegR.setRotationPoint(0.0F, 4.8F, 1.0F);
        this.BackLegR.addBox(-1.5F, -0.2F, -2.0F, 3, 4, 4, 0.0F);
        this.HornL1 = new AdvancedModelRenderer(this, 32, 7);
        this.HornL1.setRotationPoint(2.8F, -2.5F, -0.4F);
        this.HornL1.addBox(0.0F, 0.0F, -1.0F, 3, 2, 3, 0.0F);
        this.setRotateAngle(HornL1, 0.6283185307179586F, -0.08726646259971647F, 0.8726646259971648F);
        this.Tail1 = new AdvancedModelRenderer(this, 83, 49);
        this.Tail1.setRotationPoint(0.0F, -1.2F, 11.5F);
        this.Tail1.addBox(-3.0F, -3.0F, 0.0F, 6, 7, 6, 0.0F);
        this.setRotateAngle(Tail1, -0.18203784098300857F, -0.0F, 0.0F);
        this.BackThighL = new AdvancedModelRenderer(this, 0, 25);
        this.BackThighL.mirror = true;
        this.BackThighL.setRotationPoint(7.0F, 1.7F, 9.3F);
        this.BackThighL.addBox(-2.0F, 0.0F, -2.5F, 4, 5, 5, 0.0F);
        this.Mouth_1 = new AdvancedModelRenderer(this, 0, 47);
        this.Mouth_1.setRotationPoint(0.0F, 3.4F, -6.0F);
        this.Mouth_1.addBox(-2.0F, -0.3F, -1.7F, 4, 4, 1, 0.0F);
        this.setRotateAngle(Mouth_1, 1.593485607070823F, -3.141592653589793F, 0.0F);
        this.Neck.addChild(this.Head);
        this.Body.addChild(this.BodyUpper);
        this.Head.addChild(this.HeadBlock);
        this.Head.addChild(this.Mouth);
        this.Head.addChild(this.HornL2);
        this.FrontThighL.addChild(this.FrontLegL);
        this.Head.addChild(this.HornR1);
        this.Tail3.addChild(this.TailClub);
        this.BackThighL.addChild(this.BackLegL);
        this.Tail2.addChild(this.Tail3);
        this.BodyUpper.addChild(this.FrontThighR);
        this.Head.addChild(this.HornR2);
        this.Tail1.addChild(this.TailSpikes);
        this.BodyUpper.addChild(this.BodyUpperSpikes);
        this.Tail1.addChild(this.Tail2);
        this.Body.addChild(this.BackThighR);
        this.BodyUpper.addChild(this.FrontThighL);
        this.BodyUpper.addChild(this.Neck);
        this.Body.addChild(this.BodySpikes);
        this.FrontThighR.addChild(this.FrontLegR);
        this.BackThighR.addChild(this.BackLegR);
        this.Head.addChild(this.HornL1);
        this.Body.addChild(this.Tail1);
        this.Body.addChild(this.BackThighL);
        this.Head.addChild(this.Mouth_1);
        this.updateDefaultPose();
        animator = ModelAnimator.create();
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        animate((IAnimatedEntity) entity, f, f1, f2, f3, f4, f5);
        this.Body.render(f5);
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        animator.update(entity);
        blockMovement(f, f1, f2, f3, f4, f5, (Entity) entity);
        this.resetToDefaultPose();
        setRotationAngles(f, f1, f2, f3, f4, f5, (Entity) entity);
        animator.setAnimation(EntityAnkylosaurus.ATTACK_ANIMATION);
        animator.startKeyframe(5);
        ModelUtils.rotate(animator, Body, 0, 15, 0);
        ModelUtils.rotate(animator, Tail1, 0, 10, 0);
        ModelUtils.rotate(animator, Tail2, 0, 18, 0);
        ModelUtils.rotate(animator, Tail3, 0, 20, 0);
        ModelUtils.rotate(animator, TailClub, 0, 20, 0);
        animator.endKeyframe();
        animator.setStaticKeyframe(5);
        animator.startKeyframe(10);
        ModelUtils.rotate(animator, Body, 0, -127, 0);
        ModelUtils.rotate(animator, Tail1, 0, -10, 0);
        ModelUtils.rotate(animator, Tail2, 0, -18, 0);
        ModelUtils.rotate(animator, Tail3, 0, -20, 0);
        ModelUtils.rotate(animator, TailClub, 0, -23, 0);
        animator.endKeyframe();
        animator.setStaticKeyframe(5);
        animator.resetKeyframe(5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
    	AdvancedModelRenderer[] tailParts = {this.Tail1, this.Tail2, this.Tail3, this.TailClub};
		AdvancedModelRenderer[] neckParts = {this.Neck, this.Head};
		ModelUtils.faceTargetMod(Head, f3, f4, 0.5F);
		ModelUtils.faceTargetMod(Neck, f3, f4, 0.5F);
		float speed = 0.1F;
		float speed2 = 0.6F;
		this.chainWave(tailParts, speed, 0.05F, -3, entity.ticksExisted, 1);
		this.chainSwing(tailParts, speed, 0.15F, -2, entity.ticksExisted, 1);
		this.chainWave(neckParts, speed, 0.15F, 3, entity.ticksExisted, 1);
		this.bob(Body, speed, 0.4F, false, entity.ticksExisted, 1);
		this.bob(FrontThighL, speed, -0.4F, false, entity.ticksExisted, 1);
		this.bob(FrontThighR, speed, -0.4F, false, entity.ticksExisted, 1);
		this.bob(BackThighL, speed, -0.4F, false, entity.ticksExisted, 1);
		this.bob(BackThighR, speed, -0.4F, false, entity.ticksExisted, 1);
		this.walk(FrontThighL, speed2, 0.8F, true, 0F, -0.4F, f, f1);
		this.walk(FrontThighR, speed2, 0.8F, false, 0F, -0.4F, f, f1);
		this.walk(BackThighL, speed2, 0.8F, false, 0F, 0.4F, f, f1);
		this.walk(BackThighR, speed2, 0.8F, true, 0F, 0.4F, f, f1);
		this.walk(FrontLegL, speed2, 0.6F, true, 0F, 0.4F, f, f1);
		this.walk(FrontLegR, speed2, 0.6F, false, 0F, 0.4F, f, f1);
		this.walk(BackLegL, speed2, 0.6F, false, 0F, 0.4F, f, f1);
		this.walk(BackLegR, speed2, 0.6F, true, 0F, 0.4F, f, f1);
		{
			float sitProgress = ((EntityNewPrehistoric) (entity)).sitProgress;
			sitAnimationRotation(BackThighR, sitProgress, (float) Math.toRadians(70.43D), -((float) Math.toRadians(2.61D)), (float) Math.toRadians(2.61D));
			sitAnimationRotation(Neck, sitProgress, -((float) Math.toRadians(31.3D)), 0, 0);
			sitAnimationRotation(FrontThighR, sitProgress, -((float) Math.toRadians(78.26D)), -((float) Math.toRadians(23.48D)), (float) Math.toRadians(49.57D));
			sitAnimationRotation(FrontThighL, sitProgress, -((float) Math.toRadians(75.65D)), (float) Math.toRadians(26.09D), -((float) Math.toRadians(39.13D)));
			sitAnimationRotation(Mouth_1, sitProgress, (float) Math.toRadians(91.3D), -((float) Math.toRadians(180.0D)), 0);
			sitAnimationRotation(FrontLegL, sitProgress, -((float) Math.toRadians(15.0D)), 0, 0);
			sitAnimationRotation(HornR2, sitProgress, (float) Math.toRadians(50.0D), (float) Math.toRadians(20.0D), -((float) Math.toRadians(30.0D)));
			sitAnimationRotation(Tail2, sitProgress, (float) Math.toRadians(2.61D), 0, 0);
			sitAnimationRotation(BodySpikes, sitProgress, (float) Math.toRadians(0.5D), 0, 0);
			sitAnimationRotation(Head, sitProgress, (float) Math.toRadians(33.91D), 0, 0);
			sitAnimationRotation(HornR1, sitProgress, (float) Math.toRadians(36.0D), -((float) Math.toRadians(5.0D)), -((float) Math.toRadians(50.0D)));
			sitAnimationRotation(BodyUpper, sitProgress, (float) Math.toRadians(2.61D), 0, 0);
			sitAnimationRotation(HornL2, sitProgress, (float) Math.toRadians(50.0D), -((float) Math.toRadians(20.0D)), (float) Math.toRadians(30.0D));
			sitAnimationRotation(TailClub, sitProgress, (float) Math.toRadians(7.83D), 0, 0);
			sitAnimationRotation(TailSpikes, sitProgress, (float) Math.toRadians(5.22D), 0, 0);
			sitAnimationRotation(Tail1, sitProgress, -((float) Math.toRadians(10.43D)), 0, 0);
			sitAnimationRotation(Tail3, sitProgress, (float) Math.toRadians(2.61D), 0, 0);
			sitAnimationRotation(HeadBlock, sitProgress, (float) Math.toRadians(10.43D), 0, 0);
			sitAnimationRotation(FrontLegR, sitProgress, -((float) Math.toRadians(15.0D)), 0, 0);
			sitAnimationRotation(BackThighL, sitProgress, (float) Math.toRadians(65.22D), 0, -((float) Math.toRadians(41.74D)));
			sitAnimationRotation(HornL1, sitProgress, (float) Math.toRadians(36.0D), -((float) Math.toRadians(5.0D)), (float) Math.toRadians(50.0D));
			sitAnimationPos(Body, sitProgress, 0, 4.3F, -3);
		}
		{
			float sitProgress = ((EntityNewPrehistoric) (entity)).sleepProgress;
			sitAnimationRotation(HornR2, sitProgress, (float)Math.toRadians(50.0D), (float)Math.toRadians(20.0D), -((float)Math.toRadians(30.0D)));
			sitAnimationRotation(HornL2, sitProgress, (float)Math.toRadians(50.0D), -((float)Math.toRadians(20.0D)), (float)Math.toRadians(30.0D));
			sitAnimationRotation(BackThighR, sitProgress, (float)Math.toRadians(78.26D), -((float)Math.toRadians(13.04D)), (float)Math.toRadians(26.09D));
			sitAnimationRotation(HornR1, sitProgress, (float)Math.toRadians(36.0D), -((float)Math.toRadians(5.0D)), -((float)Math.toRadians(50.0D)));
			sitAnimationRotation(TailClub, sitProgress, (float)Math.toRadians(7.83D), 0, 0);
			sitAnimationRotation(HornL1, sitProgress, (float)Math.toRadians(36.0D), -((float)Math.toRadians(5.0D)), (float)Math.toRadians(50.0D));
			sitAnimationRotation(BackThighL, sitProgress, (float)Math.toRadians(65.22D), 0, -((float)Math.toRadians(41.74D)));
			sitAnimationRotation(Neck, sitProgress, ((float) Math.toRadians(31.3D)), 0, 0);
			sitAnimationRotation(Head, sitProgress, -((float)Math.toRadians(62.61D)), (float)Math.toRadians(26.09D), 0);
			sitAnimationRotation(FrontThighL, sitProgress, -((float)Math.toRadians(75.65D)), 0, -((float)Math.toRadians(39.13D)));
			sitAnimationRotation(FrontLegR, sitProgress, -((float)Math.toRadians(15.0D)), 0, 0);
			sitAnimationRotation(Tail2, sitProgress, (float)Math.toRadians(2.61D), -((float)Math.toRadians(15.65D)), 0);
			sitAnimationRotation(BodySpikes, sitProgress, (float)Math.toRadians(0.5D), 0, 0);
			sitAnimationRotation(Tail3, sitProgress, (float)Math.toRadians(2.61D), -((float)Math.toRadians(5.22D)), -((float)Math.toRadians(7.83D)));
			sitAnimationRotation(FrontThighR, sitProgress, -((float)Math.toRadians(78.26D)), (float)Math.toRadians(15.65D), 0);
			sitAnimationRotation(TailSpikes, sitProgress, (float)Math.toRadians(5.22D), 0, 0);
			sitAnimationRotation(HeadBlock, sitProgress, (float)Math.toRadians(10.43D), 0, 0);
			sitAnimationRotation(FrontLegL, sitProgress, -((float)Math.toRadians(15.0D)), 0, 0);
			sitAnimationRotation(Tail1, sitProgress, -((float)Math.toRadians(10.43D)), -((float)Math.toRadians(13.04D)), 0);
			sitAnimationRotation(BodyUpper, sitProgress, (float)Math.toRadians(2.61D), 0, 0);
			sitAnimationPos(Body, sitProgress, 0, 4.3F, -3);
		}
		((EntityNewPrehistoric)entity).chainBuffer.applyChainSwingBuffer((ModelRenderer[]) tailParts);
    }

}