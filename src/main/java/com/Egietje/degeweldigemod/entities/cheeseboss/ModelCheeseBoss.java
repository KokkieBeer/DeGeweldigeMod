package com.Egietje.degeweldigemod.entities.cheeseboss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelCheeseBoss extends ModelBase {
	ModelRenderer body;
	ModelRenderer rightLeg;
	ModelRenderer leftLeg;
	ModelRenderer rightArm;
	ModelRenderer leftArm;
	ModelRenderer head;

	public ModelCheeseBoss() {
		textureWidth = 128;
		textureHeight = 64;

		body = new ModelRenderer(this, 0, 0);
		body.addBox(-15F, -20F, -10F, 30, 40, 20);
		body.setRotationPoint(0F, -15F, 0F);
		body.setTextureSize(128, 64);
		body.mirror = true;
		rightLeg = new ModelRenderer(this, 100, 20);
		rightLeg.addBox(-1F, 0F, -1F, 2, 19, 2);
		rightLeg.setRotationPoint(-10F, 5F, 0F);
		rightLeg.setTextureSize(128, 64);
		rightLeg.mirror = true;
		leftLeg = new ModelRenderer(this, 100, 20);
		leftLeg.addBox(-1F, 0F, -1F, 2, 19, 2);
		leftLeg.setRotationPoint(10F, 5F, 0F);
		leftLeg.setTextureSize(128, 64);
		leftLeg.mirror = true;
		rightArm = new ModelRenderer(this, 100, 20);
		rightArm.addBox(-2F, 0F, -1F, 2, 19, 2);
		rightArm.setRotationPoint(-15F, -26F, 0F);
		rightArm.setTextureSize(128, 64);
		rightArm.mirror = true;
		leftArm = new ModelRenderer(this, 100, 20);
		leftArm.addBox(0F, 0F, -1F, 2, 19, 2);
		leftArm.setRotationPoint(15F, -26F, 0F);
		leftArm.setTextureSize(128, 64);
		leftArm.mirror = true;
		head = new ModelRenderer(this, 80, 0);
		head.addBox(-5F, -10F, -5F, 10, 10, 10);
		head.setRotationPoint(0F, -35F, 0F);
		head.setTextureSize(128, 64);
		head.mirror = true;
	}

	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
            this.head.render(scale);
            this.body.render(scale);
            this.leftLeg.render(scale);
            this.rightLeg.render(scale);
            this.leftArm.render(scale);
            this.rightArm.render(scale);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        this.head.rotateAngleX = headPitch * 0.017453292F;
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.body.rotateAngleX = ((float)Math.PI / 1F);
        this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }

}
