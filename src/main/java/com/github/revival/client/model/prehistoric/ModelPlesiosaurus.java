package com.github.revival.client.model.prehistoric;

import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import com.github.revival.client.model.prehistoric.test.ModelNewPrehistoric;
import com.github.revival.server.entity.mob.test.EntityNewPrehistoric;

public class ModelPlesiosaurus extends ModelNewPrehistoric {
	public AdvancedModelRenderer uppperBody;
	public AdvancedModelRenderer lowerBody;
	public AdvancedModelRenderer neck1;
	public AdvancedModelRenderer tail1;
	public AdvancedModelRenderer rightBackFlipper;
	public AdvancedModelRenderer leftBackFlipper;
	public AdvancedModelRenderer tail2;
	public AdvancedModelRenderer tail3;
	public AdvancedModelRenderer neck2;
	public AdvancedModelRenderer rightFrontFlipper;
	public AdvancedModelRenderer leftFrontFlipper;
	public AdvancedModelRenderer neck3;
	public AdvancedModelRenderer neck4;
	public AdvancedModelRenderer neck5;
	public AdvancedModelRenderer neck6;
	public AdvancedModelRenderer head;
	public AdvancedModelRenderer upperJaw;
	public AdvancedModelRenderer bottomJawBase;
	public AdvancedModelRenderer lowerJaw;
	private ModelAnimator animator;

	public ModelPlesiosaurus() {
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.neck1 = new AdvancedModelRenderer(this, 1, 25);
		this.neck1.setRotationPoint(0.0F, -0.5F, 1.0F);
		this.neck1.addBox(-3.5F, -2.5F, -4.0F, 7, 6, 4, 0.0F);
		this.setRotateAngle(neck1, 0.02775073510670984F, -0.0F, 0.0F);
		this.rightBackFlipper = new AdvancedModelRenderer(this, 55, 26);
		this.rightBackFlipper.mirror = true;
		this.rightBackFlipper.setRotationPoint(-3.5F, 0.3787422154456124F, 3.416151847227249F);
		this.rightBackFlipper.addBox(-7.0F, 0.0F, -1.5F, 7, 1, 3, 0.0F);
		this.setRotateAngle(rightBackFlipper, -0.24666041589909668F, 0.45513945393926325F, -0.5619057775621842F);
		this.lowerJaw = new AdvancedModelRenderer(this, 84, 55);
		this.lowerJaw.setRotationPoint(0.0F, 0.0F, -2.8F);
		this.lowerJaw.addBox(-1.0F, 0.0F, -4.0F, 2, 1, 3, 0.0F);
		this.head = new AdvancedModelRenderer(this, 0, 57);
		this.head.setRotationPoint(0.0F, 0.0F, -2.5F);
		this.head.addBox(-2.0F, -1.3F, -4.0F, 4, 2, 3, 0.0F);
		this.setRotateAngle(head, 0.136659280431156F, -0.0F, 0.0F);
		this.tail2 = new AdvancedModelRenderer(this, 50, 55);
		this.tail2.setRotationPoint(0.0F, 0.5F, 2.5F);
		this.tail2.addBox(-2.0F, -2.0F, 0.0F, 4, 3, 6, 0.0F);
		this.setRotateAngle(tail2, 0.02565634000431664F, -0.0F, 0.0F);
		this.leftFrontFlipper = new AdvancedModelRenderer(this, 53, 30);
		this.leftFrontFlipper.setRotationPoint(3.5F, 0.8264630676893034F, -2.030542485355446F);
		this.leftFrontFlipper.addBox(0.0F, 0.0F, -2.0F, 8, 1, 4, 0.0F);
		this.setRotateAngle(leftFrontFlipper, 0.04785271903493329F, -0.11692272588150397F, 0.5286416686479887F);
		this.tail1 = new AdvancedModelRenderer(this, 32, 57);
		this.tail1.setRotationPoint(-0.5F, -0.5F, 4.5F);
		this.tail1.addBox(-3.0F, -2.0F, 0.0F, 6, 4, 3, 0.0F);
		this.setRotateAngle(tail1, -0.0445058959258554F, -0.0F, 0.0F);
		this.neck2 = new AdvancedModelRenderer(this, 19, 36);
		this.neck2.setRotationPoint(0.0F, 0.8F, -3.6F);
		this.neck2.addBox(-3.0F, -3.0F, -4.0F, 6, 5, 4, 0.0F);
		this.setRotateAngle(neck2, -0.03316125578789226F, -0.0F, 0.0F);
		this.neck4 = new AdvancedModelRenderer(this, 26, 46);
		this.neck4.setRotationPoint(-0.5F, 0.3F, -4.5F);
		this.neck4.addBox(-1.0F, -2.0F, -5.0F, 3, 3, 5, 0.0F);
		this.setRotateAngle(neck4, -0.02775073510670984F, -0.0F, 0.0F);
		this.neck5 = new AdvancedModelRenderer(this, 12, 46);
		this.neck5.setRotationPoint(0.5F, 0.0F, -4.5F);
		this.neck5.addBox(-1.0F, -2.0F, -5.0F, 2, 3, 5, 0.0F);
		this.setRotateAngle(neck5, -0.02809980095710871F, -0.0F, 0.0F);
		this.leftBackFlipper = new AdvancedModelRenderer(this, 55, 26);
		this.leftBackFlipper.setRotationPoint(2.5F, 0.3787422154456124F, 3.416151847227249F);
		this.leftBackFlipper.addBox(0.0F, 0.0F, -1.5F, 7, 1, 3, 0.0F);
		this.setRotateAngle(leftBackFlipper, -0.24666041589909668F, -0.45513945393926325F, 0.5619057775621842F);
		this.uppperBody = new AdvancedModelRenderer(this, 24, 23);
		this.uppperBody.setRotationPoint(0.0F, 19.0F, -4.0F);
		this.uppperBody.addBox(-4.0F, -3.0F, 0.0F, 8, 6, 6, 0.0F);
		this.setRotateAngle(uppperBody, 0.017453292519943295F, 0.0F, 0.0F);
		this.rightFrontFlipper = new AdvancedModelRenderer(this, 53, 30);
		this.rightFrontFlipper.mirror = true;
		this.rightFrontFlipper.setRotationPoint(-3.0F, 0.8264630676893034F, -2.030542485355446F);
		this.rightFrontFlipper.addBox(-8.0F, 0.0F, -2.0F, 8, 1, 4, 0.0F);
		this.setRotateAngle(rightFrontFlipper, 0.04785271903493329F, 0.11692272588150397F, -0.5286416686479887F);
		this.neck3 = new AdvancedModelRenderer(this, 0, 36);
		this.neck3.setRotationPoint(0.0F, -0.5F, -2.3F);
		this.neck3.addBox(-2.0F, -2.0F, -5.0F, 4, 4, 5, 0.0F);
		this.setRotateAngle(neck3, 0.008726646259971648F, -0.0F, 0.0F);
		this.bottomJawBase = new AdvancedModelRenderer(this, 70, 54);
		this.bottomJawBase.setRotationPoint(0.0F, 0.7F, 0.1F);
		this.bottomJawBase.addBox(-1.5F, 0.0F, -4.0F, 3, 1, 3, 0.0F);
		this.setRotateAngle(bottomJawBase, -0.045553093477052F, -0.0F, 0.0017453292519943296F);
		this.tail3 = new AdvancedModelRenderer(this, 42, 47);
		this.tail3.setRotationPoint(0.0F, -0.5F, 5.5F);
		this.tail3.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 6, 0.0F);
		this.setRotateAngle(tail3, 0.017976891295541593F, -0.0F, 0.0F);
		this.neck6 = new AdvancedModelRenderer(this, 0, 48);
		this.neck6.setRotationPoint(0.01F, -0.81F, -4.5F);
		this.neck6.addBox(-1.0F, -1.2F, -3.9F, 2, 3, 4, 0.0F);
		this.setRotateAngle(neck6, 0.04363323129985824F, -0.0F, 0.0F);
		this.upperJaw = new AdvancedModelRenderer(this, 17, 58);
		this.upperJaw.setRotationPoint(0.0F, -0.9F, -2.9F);
		this.upperJaw.addBox(-1.5F, -0.4F, -4.0F, 3, 2, 3, 0.0F);
		this.lowerBody = new AdvancedModelRenderer(this, 40, 36);
		this.lowerBody.setRotationPoint(0.5F, 0.0F, 5.6F);
		this.lowerBody.addBox(-4.0F, -3.0F, 0.0F, 7, 6, 5, 0.0F);
		this.setRotateAngle(lowerBody, -0.05235987755982988F, -0.0F, 0.0F);
		this.uppperBody.addChild(this.neck1);
		this.lowerBody.addChild(this.rightBackFlipper);
		this.bottomJawBase.addChild(this.lowerJaw);
		this.neck6.addChild(this.head);
		this.tail1.addChild(this.tail2);
		this.neck1.addChild(this.leftFrontFlipper);
		this.lowerBody.addChild(this.tail1);
		this.neck1.addChild(this.neck2);
		this.neck3.addChild(this.neck4);
		this.neck4.addChild(this.neck5);
		this.lowerBody.addChild(this.leftBackFlipper);
		this.neck1.addChild(this.rightFrontFlipper);
		this.neck2.addChild(this.neck3);
		this.head.addChild(this.bottomJawBase);
		this.tail2.addChild(this.tail3);
		this.neck5.addChild(this.neck6);
		this.head.addChild(this.upperJaw);
		this.uppperBody.addChild(this.lowerBody);
		this.updateDefaultPose();
		animator = ModelAnimator.create();
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
		animate((IAnimatedEntity) entity, f, f1, f2, f3, f4, f5);
		this.uppperBody.render(f5);
	}

	public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		animator.update(entity);
		blockMovement(f, f1, f2, f3, f4, f5, (Entity) entity);
		this.resetToDefaultPose();
		setRotationAngles(f, f1, f2, f3, f4, f5, (Entity) entity);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		AdvancedModelRenderer[] tailParts = {this.tail1, this.tail2, this.tail3};
		AdvancedModelRenderer[] neckParts = {this.neck1, this.neck2, this.neck3, this.neck4, this.neck5, this.neck5, this.head};
		ModelUtils.faceTargetMod(neck1, f3, f4, 0.14F);
		ModelUtils.faceTargetMod(neck2, f3, f4, 0.14F);
		ModelUtils.faceTargetMod(neck3, f3, f4, 0.14F);
		ModelUtils.faceTargetMod(neck4, f3, f4, 0.14F);
		ModelUtils.faceTargetMod(neck5, f3, f4, 0.14F);
		ModelUtils.faceTargetMod(neck6, f3, f4, 0.14F);
		ModelUtils.faceTargetMod(head, f3, f4, 0.14F);
		float speed = 0.1F;
		float speed2 = 0.4F;
		this.chainSwing(tailParts, speed2, 0.3F, -3, f, f1);
		this.swing(uppperBody, speed2, 0.1F, true, 0, 0, f, f1);
		this.swing(lowerBody, speed2, 0.1F, true, 0, 0, f, f1);
		this.chainSwing(neckParts, speed2, -0.1F, 2, f, f1);
		this.chainWave(neckParts, speed2, -0.05F, 2, f, f1);
		this.chainWave(tailParts, speed2, -0.025F, 2, f, f1);
		this.flap(leftFrontFlipper, speed2, 0.6F, true, 0, 0, f, f1);
		this.flap(rightFrontFlipper, speed2, 0.6F, false, 0, 0, f, f1);
		this.flap(leftBackFlipper, speed2, 0.6F, false, 0, 0, f, f1);
		this.flap(rightBackFlipper, speed2, 0.6F, true, 0, 0, f, f1);
		((EntityNewPrehistoric) entity).chainBuffer.applyChainSwingBuffer((ModelRenderer[]) tailParts);
	}
}
