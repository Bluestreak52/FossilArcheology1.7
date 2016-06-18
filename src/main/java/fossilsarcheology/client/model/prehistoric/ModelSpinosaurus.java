package fossilsarcheology.client.model.prehistoric;

import fossilsarcheology.server.entity.mob.EntitySpinosaurus;
import fossilsarcheology.server.entity.mob.test.EntityNewPrehistoric;
import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.entity.Entity;

import fossilsarcheology.client.model.prehistoric.test.ModelNewPrehistoric;

public class ModelSpinosaurus extends ModelNewPrehistoric {
	public AdvancedModelRenderer lowerBody;
	public AdvancedModelRenderer leftThigh;
	public AdvancedModelRenderer rightThigh;
	public AdvancedModelRenderer tail1;
	public AdvancedModelRenderer upperBody;
	public AdvancedModelRenderer Sail4;
	public AdvancedModelRenderer Sail6;
	public AdvancedModelRenderer Sail5;
	public AdvancedModelRenderer Sail7;
	public AdvancedModelRenderer Sail8;
	public AdvancedModelRenderer Sail9;
	public AdvancedModelRenderer tail2;
	public AdvancedModelRenderer Sail10;
	public AdvancedModelRenderer Sail12;
	public AdvancedModelRenderer Sail11;
	public AdvancedModelRenderer Sail13;
	public AdvancedModelRenderer tail3;
	public AdvancedModelRenderer Sail14;
	public AdvancedModelRenderer neck;
	public AdvancedModelRenderer leftUpperArm;
	public AdvancedModelRenderer rightUpperArm;
	public AdvancedModelRenderer Sail3;
	public AdvancedModelRenderer Sail2;
	public AdvancedModelRenderer head;
	public AdvancedModelRenderer Sail1;
	public AdvancedModelRenderer upperJaw;
	public AdvancedModelRenderer lowerJaw;
	public AdvancedModelRenderer crest;
	public AdvancedModelRenderer teeth;
	public AdvancedModelRenderer leftLowerArm;
	public AdvancedModelRenderer rightLowerArm;
	public AdvancedModelRenderer leftLeg;
	public AdvancedModelRenderer leftFoot;
	public AdvancedModelRenderer rightLeg;
	public AdvancedModelRenderer rightFoot;
	private ModelAnimator animator;

	public ModelSpinosaurus() {
		this.textureWidth = 128;
		this.textureHeight = 128;
		this.upperBody = new AdvancedModelRenderer(this, 0, 27);
		this.upperBody.setRotationPoint(0.0F, 3.0F, 0.2F);
		this.upperBody.addBox(-2.5F, -2.5F, -6.0F, 5, 6, 6, 0.0F);
		this.setRotateAngle(upperBody, 0.07347836150896127F, -0.0F, 0.0F);
		this.Sail9 = new AdvancedModelRenderer(this, 48, 69);
		this.Sail9.setRotationPoint(0.0F, 0.73F, 10.0F);
		this.Sail9.addBox(-0.5F, -10.0F, -1.0F, 1, 10, 2, 0.0F);
		this.setRotateAngle(Sail9, 0.03490658503988659F, 0.0F, 0.0F);
		this.leftFoot = new AdvancedModelRenderer(this, 18, 22);
		this.leftFoot.setRotationPoint(-0.5F, 4.8F, 1.0F);
		this.leftFoot.addBox(-1.0F, 0.0F, -6.0F, 3, 2, 5, 0.0F);
		this.setRotateAngle(leftFoot, 0.2617993877991494F, -0.0F, 0.0F);
		this.leftUpperArm = new AdvancedModelRenderer(this, 40, 26);
		this.leftUpperArm.mirror = true;
		this.leftUpperArm.setRotationPoint(2.5F, 0.0F, -4.5F);
		this.leftUpperArm.addBox(0.0F, 0.0F, -1.5F, 2, 4, 3, 0.0F);
		this.setRotateAngle(leftUpperArm, 0.22689280275926282F, -0.0F, 0.0F);
		this.teeth = new AdvancedModelRenderer(this, 15, 0);
		this.teeth.setRotationPoint(0.0F, 1.5F, -3.0F);
		this.teeth.addBox(-2.0F, 0.0F, -4.0F, 4, 1, 4, 0.0F);
		this.Sail6 = new AdvancedModelRenderer(this, 30, 69);
		this.Sail6.setRotationPoint(0.0F, 0.93F, 4.0F);
		this.Sail6.addBox(-0.5F, -10.0F, -1.0F, 1, 10, 2, 0.0F);
		this.setRotateAngle(Sail6, 0.03490658503988659F, -0.0F, 0.0F);
		this.rightThigh = new AdvancedModelRenderer(this, 22, 11);
		this.rightThigh.setRotationPoint(-3.0F, 13.2F, 4.9F);
		this.rightThigh.addBox(-3.0F, -1.0F, -2.0F, 3, 6, 4, 0.0F);
		this.Sail13 = new AdvancedModelRenderer(this, 72, 69);
		this.Sail13.setRotationPoint(0.0F, 4.5F, 7.1F);
		this.Sail13.addBox(-0.5F, -10.0F, -1.0F, 1, 10, 2, 0.0F);
		this.setRotateAngle(Sail13, 0.019198621771937624F, 0.0F, 0.0F);
		this.lowerBody = new AdvancedModelRenderer(this, 0, 46);
		this.lowerBody.setRotationPoint(0.0F, 9.7F, -3.0F);
		this.lowerBody.addBox(-3.5F, 0.0F, 0.0F, 7, 7, 11, 0.0F);
		this.setRotateAngle(lowerBody, -0.03490658503988659F, -0.0F, 0.0F);
		this.crest = new AdvancedModelRenderer(this, 0, 10);
		this.crest.setRotationPoint(0.0F, -1.3F, -1.0F);
		this.crest.addBox(-0.5F, -1.0F, -1.0F, 1, 1, 2, 0.0F);
		this.Sail4 = new AdvancedModelRenderer(this, 18, 69);
		this.Sail4.setRotationPoint(0.0F, 2.0F, 0.07F);
		this.Sail4.addBox(-0.5F, -10.0F, -1.0F, 1, 10, 2, 0.0F);
		this.setRotateAngle(Sail4, 0.03490658503988659F, -0.0F, 0.0F);
		this.Sail11 = new AdvancedModelRenderer(this, 60, 69);
		this.Sail11.setRotationPoint(0.0F, 1.6F, 3.1F);
		this.Sail11.addBox(-0.5F, -10.0F, -1.0F, 1, 10, 2, 0.0F);
		this.setRotateAngle(Sail11, 0.017453292519943295F, 0.0F, 0.0F);
		this.leftThigh = new AdvancedModelRenderer(this, 22, 11);
		this.leftThigh.mirror = true;
		this.leftThigh.setRotationPoint(3.0F, 13.2F, 4.9F);
		this.leftThigh.addBox(0.0F, -1.0F, -2.0F, 3, 6, 4, 0.0F);
		this.Sail1 = new AdvancedModelRenderer(this, 0, 74);
		this.Sail1.setRotationPoint(0.0F, 3.7F, -6.0F);
		this.Sail1.addBox(-0.5F, -10.0F, -1.0F, 1, 5, 2, 0.0F);
		this.setRotateAngle(Sail1, -0.03490658503988659F, 0.0F, 0.0F);
		this.leftLeg = new AdvancedModelRenderer(this, 30, 2);
		this.leftLeg.setRotationPoint(1.1F, 4.0F, 2.0F);
		this.leftLeg.addBox(-1.0F, 0.0F, -2.0F, 2, 6, 3, 0.0F);
		this.setRotateAngle(leftLeg, -0.2617993877991494F, -0.0F, 0.0F);
		this.Sail3 = new AdvancedModelRenderer(this, 12, 69);
		this.Sail3.setRotationPoint(0.0F, 0.0F, -2.06F);
		this.Sail3.addBox(-0.5F, -10.0F, -1.0F, 1, 10, 2, 0.0F);
		this.setRotateAngle(Sail3, -0.03490658503988659F, -0.0F, 0.0F);
		this.Sail10 = new AdvancedModelRenderer(this, 54, 69);
		this.Sail10.setRotationPoint(0.0F, 0.65F, 1.09F);
		this.Sail10.addBox(-0.5F, -10.0F, -1.0F, 1, 10, 2, 0.0F);
		this.setRotateAngle(Sail10, 0.017453292519943295F, -0.0F, 0.0F);
		this.tail2 = new AdvancedModelRenderer(this, 38, 13);
		this.tail2.setRotationPoint(0.0F, 1.0F, 7.9F);
		this.tail2.addBox(-1.5F, 0.0F, 0.0F, 3, 3, 10, 0.0F);
		this.setRotateAngle(tail2, 0.030368728984701335F, -0.0F, 0.0F);
		this.tail3 = new AdvancedModelRenderer(this, 50, 26);
		this.tail3.setRotationPoint(0.0F, 0.5F, 10.0F);
		this.tail3.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 5, 0.0F);
		this.setRotateAngle(tail3, -0.10698868314725239F, -0.0F, 0.0F);
		this.upperJaw = new AdvancedModelRenderer(this, 0, 10);
		this.upperJaw.setRotationPoint(0.0F, 0.5F, -4.8F);
		this.upperJaw.addBox(-2.0F, -1.5F, -7.0F, 4, 3, 7, 0.0F);
		this.lowerJaw = new AdvancedModelRenderer(this, 0, 20);
		this.lowerJaw.setRotationPoint(0.0F, 1.9F, -5.0F);
		this.lowerJaw.addBox(-1.5F, 0.0F, -6.0F, 3, 1, 6, 0.0F);
		this.Sail2 = new AdvancedModelRenderer(this, 6, 69);
		this.Sail2.setRotationPoint(0.0F, 1.9F, -4.1F);
		this.Sail2.addBox(-0.5F, -10.0F, -1.0F, 1, 10, 2, 0.0F);
		this.setRotateAngle(Sail2, -0.03490658503988659F, 0.0F, 0.0F);
		this.Sail5 = new AdvancedModelRenderer(this, 24, 69);
		this.Sail5.setRotationPoint(0.0F, 1.0F, 2.0F);
		this.Sail5.addBox(-0.5F, -10.0F, -1.0F, 1, 10, 2, 0.0F);
		this.setRotateAngle(Sail5, 0.03490658503988659F, -0.0F, 0.0F);
		this.Sail14 = new AdvancedModelRenderer(this, 78, 72);
		this.Sail14.setRotationPoint(0.0F, 5.6F, 1.0F);
		this.Sail14.addBox(-0.5F, -10.0F, -1.0F, 1, 7, 2, 0.0F);
		this.setRotateAngle(Sail14, -0.020943951023931952F, 0.0F, 0.0F);
		this.Sail7 = new AdvancedModelRenderer(this, 36, 69);
		this.Sail7.setRotationPoint(0.0F, 0.86F, 6.0F);
		this.Sail7.addBox(-0.5F, -10.0F, -1.0F, 1, 10, 2, 0.0F);
		this.setRotateAngle(Sail7, 0.03490658503988659F, -0.0F, 0.0F);
		this.Sail12 = new AdvancedModelRenderer(this, 66, 69);
		this.Sail12.setRotationPoint(0.0F, 2.55F, 5.1F);
		this.Sail12.addBox(-0.5F, -10.0F, -1.0F, 1, 10, 2, 0.0F);
		this.setRotateAngle(Sail12, 0.017453292519943295F, 0.0F, 0.0F);
		this.head = new AdvancedModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, 0.2F, -6.1F);
		this.head.addBox(-2.5F, -2.0F, -5.0F, 5, 5, 5, 0.0F);
		this.setRotateAngle(head, 0.593411945678072F, -0.0F, 0.0F);
		this.rightLeg = new AdvancedModelRenderer(this, 30, 2);
		this.rightLeg.setRotationPoint(-1.1F, 4.0F, 2.0F);
		this.rightLeg.addBox(-1.0F, 0.0F, -2.0F, 2, 6, 3, 0.0F);
		this.setRotateAngle(rightLeg, -0.2617993877991494F, -0.0F, 0.0F);
		this.rightUpperArm = new AdvancedModelRenderer(this, 40, 26);
		this.rightUpperArm.setRotationPoint(-2.5F, 0.0F, -4.5F);
		this.rightUpperArm.addBox(-2.0F, 0.0F, -1.5F, 2, 4, 3, 0.0F);
		this.setRotateAngle(rightUpperArm, 0.22689280275926282F, -0.0F, 0.0F);
		this.leftLowerArm = new AdvancedModelRenderer(this, 38, 13);
		this.leftLowerArm.setRotationPoint(0.99F, 2.9F, 0.1F);
		this.leftLowerArm.addBox(-1.0F, 0.0F, 0.0F, 2, 4, 2, 0.0F);
		this.setRotateAngle(leftLowerArm, -0.6457718232379019F, -0.0F, 0.0F);
		this.neck = new AdvancedModelRenderer(this, 22, 29);
		this.neck.setRotationPoint(0.0F, -0.5F, -4.6F);
		this.neck.addBox(-2.0F, -1.5F, -7.0F, 4, 4, 7, 0.0F);
		this.setRotateAngle(neck, -0.5213298475707062F, -0.0F, 0.0F);
		this.Sail8 = new AdvancedModelRenderer(this, 42, 69);
		this.Sail8.setRotationPoint(0.0F, 0.8F, 8.0F);
		this.Sail8.addBox(-0.5F, -10.0F, -1.0F, 1, 10, 2, 0.0F);
		this.setRotateAngle(Sail8, 0.03490658503988659F, 0.0F, 0.0F);
		this.rightLowerArm = new AdvancedModelRenderer(this, 38, 13);
		this.rightLowerArm.mirror = true;
		this.rightLowerArm.setRotationPoint(-0.99F, 2.9F, 0.1F);
		this.rightLowerArm.addBox(-1.0F, 0.0F, 0.0F, 2, 4, 2, 0.0F);
		this.setRotateAngle(rightLowerArm, -0.6457718232379019F, -0.0F, 0.0F);
		this.rightFoot = new AdvancedModelRenderer(this, 18, 22);
		this.rightFoot.setRotationPoint(0.5F, 4.8F, 1.0F);
		this.rightFoot.addBox(-2.0F, 0.0F, -6.0F, 3, 2, 5, 0.0F);
		this.setRotateAngle(rightFoot, 0.2617993877991494F, -0.0F, 0.0F);
		this.tail1 = new AdvancedModelRenderer(this, 40, 0);
		this.tail1.setRotationPoint(0.0F, 1.0F, 10.9F);
		this.tail1.addBox(-2.0F, 0.0F, 0.0F, 4, 5, 8, 0.0F);
		this.setRotateAngle(tail1, 0.017453292519943295F, -0.0F, 0.0F);
		this.lowerBody.addChild(this.upperBody);
		this.lowerBody.addChild(this.Sail9);
		this.leftLeg.addChild(this.leftFoot);
		this.upperBody.addChild(this.leftUpperArm);
		this.upperJaw.addChild(this.teeth);
		this.lowerBody.addChild(this.Sail6);
		this.tail1.addChild(this.Sail13);
		this.upperJaw.addChild(this.crest);
		this.lowerBody.addChild(this.Sail4);
		this.tail1.addChild(this.Sail11);
		this.upperBody.addChild(this.Sail1);
		this.leftThigh.addChild(this.leftLeg);
		this.upperBody.addChild(this.Sail3);
		this.tail1.addChild(this.Sail10);
		this.tail1.addChild(this.tail2);
		this.tail2.addChild(this.tail3);
		this.head.addChild(this.upperJaw);
		this.head.addChild(this.lowerJaw);
		this.upperBody.addChild(this.Sail2);
		this.lowerBody.addChild(this.Sail5);
		this.tail2.addChild(this.Sail14);
		this.lowerBody.addChild(this.Sail7);
		this.tail1.addChild(this.Sail12);
		this.neck.addChild(this.head);
		this.rightThigh.addChild(this.rightLeg);
		this.upperBody.addChild(this.rightUpperArm);
		this.leftUpperArm.addChild(this.leftLowerArm);
		this.upperBody.addChild(this.neck);
		this.lowerBody.addChild(this.Sail8);
		this.rightUpperArm.addChild(this.rightLowerArm);
		this.rightLeg.addChild(this.rightFoot);
		this.lowerBody.addChild(this.tail1);
		this.updateDefaultPose();
		animator = ModelAnimator.create();
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		animate((IAnimatedEntity) entity, f, f1, f2, f3, f4, f5);
		this.leftThigh.render(f5);
		this.lowerBody.render(f5);
		this.rightThigh.render(f5);
	}

	public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		animator.update(entity);
		blockMovement(f, f1, f2, f3, f4, f5, (Entity) entity);
		this.resetToDefaultPose();
		setRotationAngles(f, f1, f2, f3, f4, f5, (Entity) entity);
		animator.setAnimation(EntitySpinosaurus.SHAKE_ANIMATION);
		animator.startKeyframe(15);
		ModelUtils.rotate(animator, lowerBody, 10F, 0, 0);
		ModelUtils.rotate(animator, neck, 2F, 0, 0);
		ModelUtils.rotate(animator, head, 26F, 0, 0);
		ModelUtils.rotate(animator, lowerJaw, 21F, 0, 0);
		animator.endKeyframe();
		animator.startKeyframe(10);
		ModelUtils.rotate(animator, neck, 0, -40F, 0);
		ModelUtils.rotate(animator, head, 0, -40F, -23);
		ModelUtils.rotate(animator, lowerJaw, 21F, 0, 0);
		animator.endKeyframe();
		animator.startKeyframe(10);
		ModelUtils.rotate(animator, neck, 0, 40F, 0);
		ModelUtils.rotate(animator, head, 0, 40F, 23);
		ModelUtils.rotate(animator, lowerJaw, 21F, 0, 0);
		animator.endKeyframe();
		animator.startKeyframe(10);
		ModelUtils.rotate(animator, neck, 0, -40F, 0);
		ModelUtils.rotate(animator, head, 0, -40F, -23);
		ModelUtils.rotate(animator, lowerJaw, 21F, 0, 0);
		animator.endKeyframe();
		animator.startKeyframe(10);
		ModelUtils.rotate(animator, neck, 0, 40F, 0);
		ModelUtils.rotate(animator, head, 0, 40F, 23);
		ModelUtils.rotate(animator, lowerJaw, 21F, 0, 0);
		animator.endKeyframe();
		animator.resetKeyframe(10);
		animator.setAnimation(EntitySpinosaurus.ATTACK_ANIMATION);
		animator.startKeyframe(10);
		ModelUtils.rotate(animator, neck, -36, 0, 0);
		ModelUtils.rotate(animator, head, 46, 0, 0);
		ModelUtils.rotate(animator, lowerJaw, 31, 0, 0);
		animator.endKeyframe();
		animator.startKeyframe(10);
		ModelUtils.rotate(animator, neck, -18, 0, 0);
		ModelUtils.rotate(animator, head, 26, 0, 0);
		animator.endKeyframe();
		animator.resetKeyframe(5);
		animator.setAnimation(EntitySpinosaurus.FISH_ANIMATION);
		animator.startKeyframe(15);
		ModelUtils.rotate(animator, lowerBody, -14, 0, 0);
		ModelUtils.rotate(animator, head, 73, 0, 0);
		ModelUtils.rotate(animator, neck, -41, 0, 0);
		animator.endKeyframe();
		animator.startKeyframe(10);
		ModelUtils.rotate(animator, lowerBody, 10, 0, 0);
		ModelUtils.rotate(animator, head, 54, 0, 0);
		ModelUtils.rotate(animator, neck, -36, 0, 0);
		ModelUtils.rotate(animator, leftUpperArm, -36, 0, 0);
		ModelUtils.rotate(animator, rightUpperArm, -36, 0, 0);
		ModelUtils.rotate(animator, leftLowerArm, -18, 0, 0);
		ModelUtils.rotate(animator, rightLowerArm, -18, 0, 0);
		animator.endKeyframe();
		animator.resetKeyframe(10);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		AdvancedModelRenderer[] tailParts = { this.tail1, this.tail2, this.tail3 };
		AdvancedModelRenderer[] antiNeckParts = { this.Sail1 };
		AdvancedModelRenderer[] neckParts = { this.neck, this.head };
		AdvancedModelRenderer[] leftArmParts = { this.leftUpperArm, this.leftLowerArm };
		AdvancedModelRenderer[] rightArmParts = { this.rightUpperArm, this.rightLowerArm };
		if(((EntityNewPrehistoric) entity).isSkeleton()){
			return;
		}
		float speed = 0.1F;
		float speed2 = entity.isInWater() ? 0.25F : 0.5F;
		this.bob(lowerBody, speed, 0.7F, false, entity.ticksExisted, 1);
		this.walk(leftThigh, speed2, 0.6F, false, 0F, 0.4F, f, f1);
		this.walk(leftLeg, speed2, 0.2F, false, 0F, -0.6F, f, f1);
		this.walk(leftFoot, speed2, -0.6F, true, 2.5F, -0.4F, f, f1);
		this.walk(rightThigh, speed2, 0.6F, true, 0F, -0.4F, f, f1);
		this.walk(rightLeg, speed2, 0.2F, true, 0F, 0.6F, f, f1);
		this.walk(rightFoot, speed2, -0.6F, false, 2.5F, 0.4F, f, f1);
		this.chainWave(leftArmParts, speed, 0.15F, -3, entity.ticksExisted, 1);
		this.chainWave(rightArmParts, speed, 0.15F, -3, entity.ticksExisted, 1);
		this.chainSwing(tailParts, speed, 0.15F, -3, entity.ticksExisted, 1);
		this.chainSwing(tailParts, speed2, 0.25F, -3, f, f1);
		this.chainWave(neckParts, speed, 0.15F, 3, entity.ticksExisted, 1);
		((EntityNewPrehistoric) entity).chainBuffer.applyChainSwingBuffer((AdvancedModelRenderer[]) tailParts);
		if (entity.riddenByEntity == null) {
			ModelUtils.faceTargetMod(neck, f3, f4, 0.5F);
			ModelUtils.faceTargetMod(head, f3, f4, 0.5F);
		}
		{
			float sitProgress = ((EntitySpinosaurus) entity).sitProgress;
			sitAnimationRotationPrev(leftLowerArm, sitProgress, -((float)Math.toRadians(12.0D)), 0, 0);
	        sitAnimationRotationPrev(Sail6, sitProgress, (float)Math.toRadians(2.0D), 0, 0);
	        sitAnimationRotationPrev(tail2, sitProgress, (float)Math.toRadians(1.74D), 0, 0);
	        sitAnimationRotationPrev(Sail10, sitProgress, (float)Math.toRadians(10.8D), 0, 0);
	        sitAnimationRotationPrev(Sail11, sitProgress, (float)Math.toRadians(10.0D), 0, 0);
	        sitAnimationRotationPrev(leftThigh, sitProgress, -((float)Math.toRadians(10.0D)), 0, 0);
	        sitAnimationRotationPrev(Sail14, sitProgress, (float)Math.toRadians(8.8D), 0, 0);
	        sitAnimationRotationPrev(Sail9, sitProgress, (float)Math.toRadians(2.0D), 0, 0);
	        sitAnimationRotationPrev(upperBody, sitProgress, (float)Math.toRadians(9.21D), 0, 0);
	        sitAnimationRotationPrev(tail3, sitProgress, -((float)Math.toRadians(6.13D)), 0, 0);
	        sitAnimationRotationPrev(rightLeg, sitProgress, -((float)Math.toRadians(70.0D)), 0, 0);
	        sitAnimationRotationPrev(head, sitProgress, (float)Math.toRadians(41.0D), 0, 0);
	        sitAnimationRotationPrev(rightFoot, sitProgress, (float)Math.toRadians(80.0D), 0, 0);
	        sitAnimationRotationPrev(Sail12, sitProgress, (float)Math.toRadians(10.0D), 0, 0);
	        sitAnimationRotationPrev(Sail4, sitProgress, (float)Math.toRadians(2.0D), 0, 0);
	        sitAnimationRotationPrev(Sail7, sitProgress, (float)Math.toRadians(2.0D), 0, 0);
	        sitAnimationRotationPrev(Sail2, sitProgress, -((float)Math.toRadians(8.0D)), 0, 0);
	        sitAnimationRotationPrev(rightUpperArm, sitProgress, -((float)Math.toRadians(60.0D)), 0, 0);
	        sitAnimationRotationPrev(leftFoot, sitProgress, (float)Math.toRadians(80.0D), 0, 0);
	        sitAnimationRotationPrev(Sail3, sitProgress, -((float)Math.toRadians(7.0D)), 0, 0);
	        sitAnimationRotationPrev(rightThigh, sitProgress, -((float)Math.toRadians(10.0D)), 0, 0);
	        sitAnimationRotationPrev(tail1, sitProgress, -((float)Math.toRadians(10.0D)), 0, 0);
	        sitAnimationRotationPrev(lowerBody, sitProgress, -((float)Math.toRadians(2.0D)), 0, 0);
	        sitAnimationRotationPrev(leftUpperArm, sitProgress, -((float)Math.toRadians(60.0D)), 0, 0);
	        sitAnimationRotationPrev(Sail13, sitProgress, (float)Math.toRadians(10.1D), 0, 0);
	        sitAnimationRotationPrev(Sail8, sitProgress, (float)Math.toRadians(2.0D), 0, 0);
	        sitAnimationRotationPrev(rightLowerArm, sitProgress, -((float)Math.toRadians(12.0D)), 0, 0);
	        sitAnimationRotationPrev(neck, sitProgress, -((float)Math.toRadians(46.87D)), 0, 0);
	        sitAnimationRotationPrev(leftLeg, sitProgress, -((float)Math.toRadians(70.0D)), 0, 0);
	        sitAnimationRotationPrev(Sail5, sitProgress, (float)Math.toRadians(2.0D), 0, 0);
	        sitAnimationPos(lowerBody, sitProgress, 0, 5, 0);
	        sitAnimationPos(rightThigh, sitProgress, 0, 5, 0);
	        sitAnimationPos(leftThigh, sitProgress, 0, 5, 0);
	        sitAnimationPos(leftFoot, sitProgress, 0, -2, -1.5F);
	        sitAnimationPos(rightFoot, sitProgress, 0, -2, -1.5F);
		}
		{
			float sitProgress = ((EntitySpinosaurus) entity).sleepProgress;
			sitAnimationRotationPrev(lowerBody, sitProgress, -((float)Math.toRadians(2.0D)), -((float)Math.toRadians(11.0D)), 0);
	        sitAnimationRotationPrev(Sail10, sitProgress, (float)Math.toRadians(19.8D), 0, (float)Math.toRadians(1.0D));
	        sitAnimationRotationPrev(Sail6, sitProgress, (float)Math.toRadians(2.0D), 0, 0);
	        sitAnimationRotationPrev(Sail14, sitProgress, (float)Math.toRadians(8.8D), 0, -((float)Math.toRadians(6.0D)));
	        sitAnimationRotationPrev(leftThigh, sitProgress, -((float)Math.toRadians(60.0D)), -((float)Math.toRadians(11.0D)), 0);
	        sitAnimationRotationPrev(rightThigh, sitProgress, -((float)Math.toRadians(60.0D)), -((float)Math.toRadians(11.0D)), 0);
	        sitAnimationRotationPrev(Sail7, sitProgress, (float)Math.toRadians(2.0D), 0, 0);
	        sitAnimationRotationPrev(Sail2, sitProgress, (float)Math.toRadians(1.0D), (float)Math.toRadians(4.0D), (float)Math.toRadians(3.0D));
	        sitAnimationRotationPrev(Sail11, sitProgress, (float)Math.toRadians(20.0D), 0, 0);
	        sitAnimationRotationPrev(upperBody, sitProgress, (float)Math.toRadians(4.21D), (float)Math.toRadians(28.0D), 0);
	        sitAnimationRotationPrev(Sail9, sitProgress, (float)Math.toRadians(2.0D), 0, 0);
	        sitAnimationRotationPrev(leftLowerArm, sitProgress, -((float)Math.toRadians(62.0D)), 0, 0);
	        sitAnimationRotationPrev(tail1, sitProgress, -((float)Math.toRadians(19.0D)), -((float)Math.toRadians(15.0D)), 0);
	        sitAnimationRotationPrev(rightLowerArm, sitProgress, -((float)Math.toRadians(62.0D)), 0, 0);
	        sitAnimationRotationPrev(leftLeg, sitProgress, -((float)Math.toRadians(20.0D)), 0, 0);
	        sitAnimationRotationPrev(leftUpperArm, sitProgress, -((float)Math.toRadians(22.0D)), (float)Math.toRadians(11.0D), 0);
	        sitAnimationRotationPrev(Sail8, sitProgress, (float)Math.toRadians(2.0D), 0, 0);
	        sitAnimationRotationPrev(rightFoot, sitProgress, (float)Math.toRadians(80.0D), 0, 0);
	        sitAnimationRotationPrev(Sail5, sitProgress, (float)Math.toRadians(2.0D), 0, 0);
	        sitAnimationRotationPrev(Sail12, sitProgress, (float)Math.toRadians(19.0D), 0, 0);
	        sitAnimationRotationPrev(head, sitProgress, -((float)Math.toRadians(29.0D)), (float)Math.toRadians(35.0D), -((float)Math.toRadians(20.0D)));
	        sitAnimationRotationPrev(Sail3, sitProgress, -((float)Math.toRadians(3.0D)), 0, (float)Math.toRadians(2.0D));
	        sitAnimationRotationPrev(rightLeg, sitProgress, -((float)Math.toRadians(20.0D)), 0, 0);
	        sitAnimationRotationPrev(rightUpperArm, sitProgress, -((float)Math.toRadians(22.0D)), (float)Math.toRadians(11.0D), 0);
	        sitAnimationRotationPrev(Sail13, sitProgress, (float)Math.toRadians(19.1D), -((float)Math.toRadians(12.0D)), -((float)Math.toRadians(1.0D)));
	        sitAnimationRotationPrev(leftFoot, sitProgress, (float)Math.toRadians(80.0D), 0, 0);
	        sitAnimationRotationPrev(Sail4, sitProgress, (float)Math.toRadians(2.0D), 0, 0);
	        sitAnimationRotationPrev(tail2, sitProgress, (float)Math.toRadians(8.74D), -((float)Math.toRadians(28.0D)), 0);
	        sitAnimationRotationPrev(tail3, sitProgress, -((float)Math.toRadians(0.13D)), -((float)Math.toRadians(29.0D)), -((float)Math.toRadians(7.0D)));
	        sitAnimationRotationPrev(neck, sitProgress, (float)Math.toRadians(27.13D), (float)Math.toRadians(22.0D), 0);
	        sitAnimationPos(lowerBody, sitProgress, 0, 5, 0);
	        sitAnimationPos(rightThigh, sitProgress, 0, 5, 0);
	        sitAnimationPos(leftThigh, sitProgress, 0, 5, 0);
	        sitAnimationPos(leftFoot, sitProgress, 0, -2, -1.5F);
	        sitAnimationPos(rightFoot, sitProgress, 0, -2, -1.5F);

		}
		{
			float sitProgress = ((EntitySpinosaurus) entity).swimProgress;
			sitAnimationRotationPrev(leftThigh, sitProgress, (float)Math.toRadians(65.0D), 0, 0);
	        sitAnimationRotationPrev(Sail4, sitProgress, (float)Math.toRadians(2.0D), 0, 0);
	        sitAnimationRotationPrev(rightLeg, sitProgress, -((float)Math.toRadians(15.0D)), 0, 0);
	        sitAnimationRotationPrev(neck, sitProgress, -((float)Math.toRadians(13.04D)), 0, 0);
	        sitAnimationRotationPrev(leftUpperArm, sitProgress, (float)Math.toRadians(44.0D), 0, 0);
	        sitAnimationRotationPrev(head, sitProgress, (float)Math.toRadians(13.04D), 0, 0);
	        sitAnimationRotationPrev(rightThigh, sitProgress, (float)Math.toRadians(65.0D), 0, 0);
	        sitAnimationRotationPrev(Sail7, sitProgress, (float)Math.toRadians(2.0D), 0, 0);
	        sitAnimationRotationPrev(Sail8, sitProgress, (float)Math.toRadians(1.0D), 0, 0);
	        sitAnimationRotationPrev(Sail5, sitProgress, (float)Math.toRadians(2.0D), 0, 0);
	        sitAnimationRotationPrev(leftLeg, sitProgress, -((float)Math.toRadians(15.0D)), 0, 0);
	        sitAnimationRotationPrev(leftFoot, sitProgress, (float)Math.toRadians(83.0D), -((float)Math.toRadians(83.0D)), 0);
	        sitAnimationRotationPrev(tail1, sitProgress, (float)Math.toRadians(1.0D), 0, 0);
	        sitAnimationRotationPrev(rightFoot, sitProgress, (float)Math.toRadians(83.0D), (float)Math.toRadians(83.0D), 0);
	        sitAnimationRotationPrev(lowerBody, sitProgress, -((float)Math.toRadians(2.0D)), 0, 0);
	        sitAnimationRotationPrev(rightLowerArm, sitProgress, -((float)Math.toRadians(3.0D)), 0, 0);
	        sitAnimationRotationPrev(tail3, sitProgress, -((float)Math.toRadians(6.13D)), 0, 0);
	        sitAnimationRotationPrev(Sail2, sitProgress, -((float)Math.toRadians(2.0D)), 0, 0);
	        sitAnimationRotationPrev(Sail6, sitProgress, (float)Math.toRadians(2.0D), 0, 0);
	        sitAnimationRotationPrev(upperBody, sitProgress, (float)Math.toRadians(4.21D), 0, 0);
	        sitAnimationRotationPrev(Sail9, sitProgress, (float)Math.toRadians(1.0D), 0, 0);
	        sitAnimationRotationPrev(Sail3, sitProgress, -((float)Math.toRadians(2.0D)), 0, 0);
	        sitAnimationRotationPrev(rightUpperArm, sitProgress, (float)Math.toRadians(44.0D), 0, 0);
	        sitAnimationRotationPrev(leftLowerArm, sitProgress, -((float)Math.toRadians(3.0D)), 0, 0);
	        sitAnimationRotationPrev(tail2, sitProgress, (float)Math.toRadians(1.74D), 0, 0);
	        sitAnimationPos(lowerBody, sitProgress, 0, 5, 0);
	        sitAnimationPos(rightThigh, sitProgress, 0, 5, 0);
	        sitAnimationPos(leftThigh, sitProgress, 0, 5, 0);
	        sitAnimationPos(leftFoot, sitProgress, 0, -2, -1.5F);
	        sitAnimationPos(rightFoot, sitProgress, 0, -2, -1.5F);
		}
		{
			float sitProgress = ((EntitySpinosaurus) entity).weakProgress;
			sitAnimationRotationPrev(lowerBody, sitProgress, -((float)Math.toRadians(2.0D)), -((float)Math.toRadians(11.0D)), 0);
	        sitAnimationRotationPrev(Sail10, sitProgress, (float)Math.toRadians(19.8D), 0, (float)Math.toRadians(1.0D));
	        sitAnimationRotationPrev(Sail6, sitProgress, (float)Math.toRadians(2.0D), 0, 0);
	        sitAnimationRotationPrev(Sail14, sitProgress, (float)Math.toRadians(8.8D), 0, -((float)Math.toRadians(6.0D)));
	        sitAnimationRotationPrev(leftThigh, sitProgress, -((float)Math.toRadians(60.0D)), -((float)Math.toRadians(11.0D)), 0);
	        sitAnimationRotationPrev(rightThigh, sitProgress, -((float)Math.toRadians(60.0D)), -((float)Math.toRadians(11.0D)), 0);
	        sitAnimationRotationPrev(Sail7, sitProgress, (float)Math.toRadians(2.0D), 0, 0);
	        sitAnimationRotationPrev(Sail2, sitProgress, (float)Math.toRadians(1.0D), (float)Math.toRadians(4.0D), (float)Math.toRadians(3.0D));
	        sitAnimationRotationPrev(Sail11, sitProgress, (float)Math.toRadians(20.0D), 0, 0);
	        sitAnimationRotationPrev(upperBody, sitProgress, (float)Math.toRadians(4.21D), (float)Math.toRadians(28.0D), 0);
	        sitAnimationRotationPrev(Sail9, sitProgress, (float)Math.toRadians(2.0D), 0, 0);
	        sitAnimationRotationPrev(leftLowerArm, sitProgress, -((float)Math.toRadians(62.0D)), 0, 0);
	        sitAnimationRotationPrev(tail1, sitProgress, -((float)Math.toRadians(19.0D)), -((float)Math.toRadians(15.0D)), 0);
	        sitAnimationRotationPrev(rightLowerArm, sitProgress, -((float)Math.toRadians(62.0D)), 0, 0);
	        sitAnimationRotationPrev(leftLeg, sitProgress, -((float)Math.toRadians(20.0D)), 0, 0);
	        sitAnimationRotationPrev(leftUpperArm, sitProgress, -((float)Math.toRadians(22.0D)), (float)Math.toRadians(11.0D), 0);
	        sitAnimationRotationPrev(Sail8, sitProgress, (float)Math.toRadians(2.0D), 0, 0);
	        sitAnimationRotationPrev(rightFoot, sitProgress, (float)Math.toRadians(80.0D), 0, 0);
	        sitAnimationRotationPrev(Sail5, sitProgress, (float)Math.toRadians(2.0D), 0, 0);
	        sitAnimationRotationPrev(Sail12, sitProgress, (float)Math.toRadians(19.0D), 0, 0);
	        sitAnimationRotationPrev(head, sitProgress, -((float)Math.toRadians(29.0D)), (float)Math.toRadians(35.0D), -((float)Math.toRadians(20.0D)));
	        sitAnimationRotationPrev(Sail3, sitProgress, -((float)Math.toRadians(3.0D)), 0, (float)Math.toRadians(2.0D));
	        sitAnimationRotationPrev(rightLeg, sitProgress, -((float)Math.toRadians(20.0D)), 0, 0);
	        sitAnimationRotationPrev(rightUpperArm, sitProgress, -((float)Math.toRadians(22.0D)), (float)Math.toRadians(11.0D), 0);
	        sitAnimationRotationPrev(Sail13, sitProgress, (float)Math.toRadians(19.1D), -((float)Math.toRadians(12.0D)), -((float)Math.toRadians(1.0D)));
	        sitAnimationRotationPrev(leftFoot, sitProgress, (float)Math.toRadians(80.0D), 0, 0);
	        sitAnimationRotationPrev(Sail4, sitProgress, (float)Math.toRadians(2.0D), 0, 0);
	        sitAnimationRotationPrev(tail2, sitProgress, (float)Math.toRadians(8.74D), -((float)Math.toRadians(28.0D)), 0);
	        sitAnimationRotationPrev(tail3, sitProgress, -((float)Math.toRadians(0.13D)), -((float)Math.toRadians(29.0D)), -((float)Math.toRadians(7.0D)));
	        sitAnimationRotationPrev(neck, sitProgress, (float)Math.toRadians(27.13D), (float)Math.toRadians(22.0D), 0);
	        sitAnimationPos(lowerBody, sitProgress, 0, 5, 0);
	        sitAnimationPos(rightThigh, sitProgress, 0, 5, 0);
	        sitAnimationPos(leftThigh, sitProgress, 0, 5, 0);
	        sitAnimationPos(leftFoot, sitProgress, 0, -2, -1.5F);
	        sitAnimationPos(rightFoot, sitProgress, 0, -2, -1.5F);

		}
		{
			float sitProgress = ((EntitySpinosaurus) entity).swimProgress;
			sitAnimationRotationPrev(leftThigh, sitProgress, (float)Math.toRadians(65.0D), 0, 0);
	        sitAnimationRotationPrev(Sail4, sitProgress, (float)Math.toRadians(2.0D), 0, 0);
	        sitAnimationRotationPrev(rightLeg, sitProgress, -((float)Math.toRadians(15.0D)), 0, 0);
	        sitAnimationRotationPrev(neck, sitProgress, -((float)Math.toRadians(13.04D)), 0, 0);
	        sitAnimationRotationPrev(leftUpperArm, sitProgress, (float)Math.toRadians(44.0D), 0, 0);
	        sitAnimationRotationPrev(head, sitProgress, (float)Math.toRadians(13.04D), 0, 0);
	        sitAnimationRotationPrev(rightThigh, sitProgress, (float)Math.toRadians(65.0D), 0, 0);
	        sitAnimationRotationPrev(Sail7, sitProgress, (float)Math.toRadians(2.0D), 0, 0);
	        sitAnimationRotationPrev(Sail8, sitProgress, (float)Math.toRadians(1.0D), 0, 0);
	        sitAnimationRotationPrev(Sail5, sitProgress, (float)Math.toRadians(2.0D), 0, 0);
	        sitAnimationRotationPrev(leftLeg, sitProgress, -((float)Math.toRadians(15.0D)), 0, 0);
	        sitAnimationRotationPrev(leftFoot, sitProgress, (float)Math.toRadians(83.0D), -((float)Math.toRadians(83.0D)), 0);
	        sitAnimationRotationPrev(tail1, sitProgress, (float)Math.toRadians(1.0D), 0, 0);
	        sitAnimationRotationPrev(rightFoot, sitProgress, (float)Math.toRadians(83.0D), (float)Math.toRadians(83.0D), 0);
	        sitAnimationRotationPrev(lowerBody, sitProgress, -((float)Math.toRadians(2.0D)), 0, 0);
	        sitAnimationRotationPrev(rightLowerArm, sitProgress, -((float)Math.toRadians(3.0D)), 0, 0);
	        sitAnimationRotationPrev(tail3, sitProgress, -((float)Math.toRadians(6.13D)), 0, 0);
	        sitAnimationRotationPrev(Sail2, sitProgress, -((float)Math.toRadians(2.0D)), 0, 0);
	        sitAnimationRotationPrev(Sail6, sitProgress, (float)Math.toRadians(2.0D), 0, 0);
	        sitAnimationRotationPrev(upperBody, sitProgress, (float)Math.toRadians(4.21D), 0, 0);
	        sitAnimationRotationPrev(Sail9, sitProgress, (float)Math.toRadians(1.0D), 0, 0);
	        sitAnimationRotationPrev(Sail3, sitProgress, -((float)Math.toRadians(2.0D)), 0, 0);
	        sitAnimationRotationPrev(rightUpperArm, sitProgress, (float)Math.toRadians(44.0D), 0, 0);
	        sitAnimationRotationPrev(leftLowerArm, sitProgress, -((float)Math.toRadians(3.0D)), 0, 0);
	        sitAnimationRotationPrev(tail2, sitProgress, (float)Math.toRadians(1.74D), 0, 0);
	        sitAnimationPos(lowerBody, sitProgress, 0, 5, 0);
	        sitAnimationPos(rightThigh, sitProgress, 0, 5, 0);
	        sitAnimationPos(leftThigh, sitProgress, 0, 5, 0);
	        sitAnimationPos(leftFoot, sitProgress, 0, -2, -1.5F);
	        sitAnimationPos(rightFoot, sitProgress, 0, -2, -1.5F);
		}
	}
}
