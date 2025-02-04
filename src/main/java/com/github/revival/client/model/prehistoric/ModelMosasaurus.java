package com.github.revival.client.model.prehistoric;

import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import com.github.revival.client.model.prehistoric.test.ModelNewPrehistoric;
import com.github.revival.server.entity.mob.test.EntityNewPrehistoric;

public class ModelMosasaurus extends ModelNewPrehistoric {
	public AdvancedModelRenderer upperBody;
	public AdvancedModelRenderer lowerBody;
	public AdvancedModelRenderer neck;
	public AdvancedModelRenderer leftFrontFlipper;
	public AdvancedModelRenderer rightFrontFlipper;
	public AdvancedModelRenderer tail1;
	public AdvancedModelRenderer leftHindFlipper;
	public AdvancedModelRenderer rightHindFlipper;
	public AdvancedModelRenderer tail2;
	public AdvancedModelRenderer lowerTailFin;
	public AdvancedModelRenderer upperTailFin;
	public AdvancedModelRenderer head;
	public AdvancedModelRenderer jawBase;
	public AdvancedModelRenderer upperJaw;
	public AdvancedModelRenderer bottomJaw;
	public AdvancedModelRenderer teeth;
	private ModelAnimator animator;

	public ModelMosasaurus() {
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.upperBody = new AdvancedModelRenderer(this, 77, 43);
		this.upperBody.setRotationPoint(0.0F, 10.0F, -6.0F);
		this.upperBody.addBox(-4.0F, 0.0F, 0.0F, 8, 7, 10, 0.0F);
		this.leftHindFlipper = new AdvancedModelRenderer(this, 50, 24);
		this.leftHindFlipper.mirror = true;
		this.leftHindFlipper.setRotationPoint(1.5F, 4.5F, 9.5F);
		this.leftHindFlipper.addBox(0.0F, 0.0F, -2.0F, 1, 4, 3, 0.0F);
		this.setRotateAngle(leftHindFlipper, 0.5235987755982987F, 0.0F, -0.5235987755982987F);
		this.jawBase = new AdvancedModelRenderer(this, 5, 52);
		this.jawBase.setRotationPoint(0.0F, 1.5F, 0.1F);
		this.jawBase.addBox(-2.5F, 0.0F, -6.0F, 5, 2, 6, 0.0F);
		this.bottomJaw = new AdvancedModelRenderer(this, 30, 51);
		this.bottomJaw.setRotationPoint(0.0F, 0.01F, -5.5F);
		this.bottomJaw.addBox(-1.5F, 0.0F, -7.0F, 3, 2, 7, 0.0F);
		this.teeth = new AdvancedModelRenderer(this, 27, 28);
		this.teeth.setRotationPoint(0.0F, -0.03F, 0.0F);
		this.teeth.addBox(-2.0F, 2.0F, -7.0F, 4, 1, 7, 0.0F);
		this.lowerBody = new AdvancedModelRenderer(this, 79, 25);
		this.lowerBody.setRotationPoint(0.0F, 0.5F, 10.0F);
		this.lowerBody.addBox(-3.0F, 0.0F, 0.0F, 6, 6, 10, 0.0F);
		this.head = new AdvancedModelRenderer(this, 1, 40);
		this.head.setRotationPoint(0.0F, -0.5F, -4.0F);
		this.head.addBox(-3.0F, -2.0F, -6.0F, 6, 4, 6, 0.0F);
		this.tail1 = new AdvancedModelRenderer(this, 85, 12);
		this.tail1.setRotationPoint(0.0F, 0.5F, 10.0F);
		this.tail1.addBox(-2.0F, 0.0F, 0.0F, 4, 5, 7, 0.0F);
		this.rightFrontFlipper = new AdvancedModelRenderer(this, 62, 21);
		this.rightFrontFlipper.setRotationPoint(-2.5F, 5.0F, 2.5F);
		this.rightFrontFlipper.addBox(-1.0F, 0.0F, -2.0F, 1, 7, 4, 0.0F);
		this.setRotateAngle(rightFrontFlipper, 0.5235987755982987F, 0.0F, 0.5235987755982987F);
		this.lowerTailFin = new AdvancedModelRenderer(this, 36, 11);
		this.lowerTailFin.setRotationPoint(0.0F, 2.0F, 8.4F);
		this.lowerTailFin.addBox(-0.5F, 0.0F, -2.0F, 1, 9, 4, 0.0F);
		this.setRotateAngle(lowerTailFin, 1.1335913491703171F, -0.0F, 0.0F);
		this.upperTailFin = new AdvancedModelRenderer(this, 21, 11);
		this.upperTailFin.setRotationPoint(-0.5F, 2.3F, 8.73F);
		this.upperTailFin.addBox(0.0F, 0.0F, -2.0F, 1, 5, 4, 0.0F);
		this.setRotateAngle(upperTailFin, 1.8938567713390468F, -0.0F, 0.0F);
		this.leftFrontFlipper = new AdvancedModelRenderer(this, 62, 21);
		this.leftFrontFlipper.mirror = true;
		this.leftFrontFlipper.setRotationPoint(2.5F, 5.0F, 2.5F);
		this.leftFrontFlipper.addBox(0.0F, 0.0F, -2.0F, 1, 7, 4, 0.0F);
		this.setRotateAngle(leftFrontFlipper, 0.5235987755982987F, 0.0F, -0.5235987755982987F);
		this.neck = new AdvancedModelRenderer(this, 52, 50);
		this.neck.setRotationPoint(0.0F, 3.5F, 0.0F);
		this.neck.addBox(-3.5F, -3.0F, -4.0F, 7, 6, 4, 0.0F);
		this.upperJaw = new AdvancedModelRenderer(this, 27, 40);
		this.upperJaw.setRotationPoint(0.0F, -0.13F, -6.0F);
		this.upperJaw.addBox(-2.0F, -1.0F, -7.0F, 4, 3, 7, 0.0F);
		this.rightHindFlipper = new AdvancedModelRenderer(this, 50, 24);
		this.rightHindFlipper.setRotationPoint(-1.5F, 4.5F, 9.5F);
		this.rightHindFlipper.addBox(-1.0F, 0.0F, -2.0F, 1, 4, 3, 0.0F);
		this.setRotateAngle(rightHindFlipper, 0.5235987755982987F, 0.0F, 0.5235987755982987F);
		this.tail2 = new AdvancedModelRenderer(this, 59, 0);
		this.tail2.setRotationPoint(0.0F, 0.5F, 7.0F);
		this.tail2.addBox(-1.5F, 0.0F, 0.0F, 3, 4, 9, 0.0F);
		this.lowerBody.addChild(this.leftHindFlipper);
		this.head.addChild(this.jawBase);
		this.jawBase.addChild(this.bottomJaw);
		this.upperJaw.addChild(this.teeth);
		this.upperBody.addChild(this.lowerBody);
		this.neck.addChild(this.head);
		this.lowerBody.addChild(this.tail1);
		this.upperBody.addChild(this.rightFrontFlipper);
		this.tail2.addChild(this.lowerTailFin);
		this.tail2.addChild(this.upperTailFin);
		this.upperBody.addChild(this.leftFrontFlipper);
		this.upperBody.addChild(this.neck);
		this.head.addChild(this.upperJaw);
		this.lowerBody.addChild(this.rightHindFlipper);
		this.tail1.addChild(this.tail2);
		this.updateDefaultPose();
		animator = ModelAnimator.create();
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
		animate((IAnimatedEntity) entity, f, f1, f2, f3, f4, f5);
		this.upperBody.render(f5);
	}

	public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		animator.update(entity);
		blockMovement(f, f1, f2, f3, f4, f5, (Entity) entity);
		this.resetToDefaultPose();
		setRotationAngles(f, f1, f2, f3, f4, f5, (Entity) entity);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		AdvancedModelRenderer[] tailParts = {this.lowerBody, this.tail1, this.tail2, this.upperTailFin};
		AdvancedModelRenderer[] neckParts = {this.neck, this.head};
		this.lowerTailFin.rotateAngleY = this.upperTailFin.rotateAngleY;
		ModelUtils.faceTargetMod(neck, f3, f4, 0.5F);
		ModelUtils.faceTargetMod(head, f3, f4, 0.5F);
		float speed = 0.1F;
		float speed2 = 0.4F;
		this.lowerTailFin.setScale(1.01F, 1.01F, 1.01F);
		this.chainSwing(tailParts, speed2, 0.3F, -3, f, f1);
		this.swing(upperBody, speed2, 0.1F, true, 0, 0, f, f1);
		this.swing(lowerBody, speed2, 0.1F, true, 0, 0, f, f1);
		this.chainSwing(neckParts, speed2, -0.1F, 2, f, f1);
		this.flap(leftFrontFlipper, speed2, 0.6F, true, 0, 0, f, f1);
		this.flap(rightFrontFlipper, speed2, 0.6F, false, 0, 0, f, f1);
		this.flap(leftHindFlipper, speed2, 0.6F, false, 0, 0, f, f1);
		this.flap(rightHindFlipper, speed2, 0.6F, true, 0, 0, f, f1);
		((EntityNewPrehistoric) entity).chainBuffer.applyChainSwingBuffer((ModelRenderer[]) tailParts);
	}

}
