package com.Egietje.degeweldigemod.entities.cheeseboss;

import java.util.List;

import javax.annotation.Nullable;

import com.Egietje.degeweldigemod.entities.cheeseball.EntityCheeseBall;
import com.Egietje.degeweldigemod.init.CheeseAchievements;
import com.Egietje.degeweldigemod.init.CheeseItems;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiScreenEvent.PotionShiftEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityCheeseBoss extends EntityMob implements IRangedAttackMob {

	private final BossInfoServer bossInfo = (BossInfoServer) (new BossInfoServer(this.getDisplayName(),
			BossInfo.Color.YELLOW, BossInfo.Overlay.PROGRESS)).setDarkenSky(true).setCreateFog(true);;
	private static final DataParameter<Integer> TARGET = EntityDataManager.<Integer>createKey(EntityCheeseBoss.class,
			DataSerializers.VARINT);
	private final int[] nextHeadUpdate = new int[2];
	private final int[] idleHeadUpdates = new int[2];
	private static final Predicate<Entity> NOT_UNDEAD = new Predicate<Entity>() {
		public boolean apply(@Nullable Entity p_apply_1_) {
			return p_apply_1_ instanceof EntityLivingBase
					&& ((EntityLivingBase) p_apply_1_).getCreatureAttribute() != EnumCreatureAttribute.UNDEAD;
		}
	};

	public EntityCheeseBoss(World worldIn) {
		super(worldIn);
		this.setHealth(this.getMaxHealth());
		this.setSize(1.5F, 4.25F);
		this.isImmuneToFire = true;
		((PathNavigateGround) this.getNavigator()).setCanSwim(true);
		this.experienceValue = 50;
	}

	@Override
	public float getEyeHeight() {
		return height - 0.05F;
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(500.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.10567463927824D);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(5.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
	}

	public static void registerFixesCheeseBoss(DataFixer fixer) {
		EntityLiving.registerFixesMob(fixer, EntityCheeseBoss.class);
	}

	@Override
	public void onDeath(DamageSource cause) {
		if (!world.isRemote) {
			world.spawnEntity(new EntityItem(world, this.posX, this.posY, this.posZ, new ItemStack(Items.DIAMOND, 3)));
			world.spawnEntity(new EntityItem(world, this.posX, this.posY, this.posZ,
					new ItemStack(CheeseItems.CHEESE, rand.nextInt(4) + 7)));
			for (EntityPlayer entityplayer : this.world.getEntitiesWithinAABB(EntityPlayer.class,
					this.getEntityBoundingBox().expand(50.0D, 100.0D, 50.0D))) {
				entityplayer.addStat(CheeseAchievements.KILL);
			}
		}
		super.onDeath(cause);
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, 1.0F, 0.001F);
	}

	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackRanged(this, 1.0D, 40, 20.0F));
		this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
		this.targetTasks.addTask(2,
				new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, false, NOT_UNDEAD));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(TARGET, Integer.valueOf(0));
	}

	@Override
	public void setInWeb() {
		this.motionX *= 1.5;
		this.motionY *= 1.5;
		this.motionZ *= 1.5;
	}

	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}

	protected boolean canBeRidden(Entity entityIn) {
		return false;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	public boolean isNonBoss() {
		return false;
	}

	public void onLivingUpdate() {
		if (this.motionX * this.motionX + this.motionZ * this.motionZ > 0.05000000074505806D) {
			this.rotationYaw = (float) MathHelper.atan2(this.motionZ, this.motionX) * (180F / (float) Math.PI) - 90.0F;
		}
		super.onLivingUpdate();
	}

	@Override
	protected void updateAITasks() {
		for (int i = 1; i < 2; ++i) {
			this.nextHeadUpdate[i - 1] = this.ticksExisted + 10 + this.rand.nextInt(10);

			if (this.world.getDifficulty() == EnumDifficulty.NORMAL
					|| this.world.getDifficulty() == EnumDifficulty.HARD) {
				int j3 = i - 1;
				int k3 = this.idleHeadUpdates[i - 1];
				this.idleHeadUpdates[j3] = this.idleHeadUpdates[i - 1] + 1;
			}

			int k1 = this.getWatchedTargetId(i);

			if (k1 > 0) {
				Entity entity = this.world.getEntityByID(k1);

				if (entity != null && entity.isEntityAlive() && this.getDistanceSqToEntity(entity) <= 900.0D
						&& this.canEntityBeSeen(entity)) {
					if (entity instanceof EntityPlayer && ((EntityPlayer) entity).capabilities.disableDamage) {
						this.updateWatchedTargetId(i, 0);
					} else if (entity instanceof EntityPlayer && !((EntityPlayer) entity).capabilities.isCreativeMode) {
						if (rand.nextInt(10000) == 0)
							this.launchCheeseToEntity(i + 1, (EntityLivingBase) entity);
						this.nextHeadUpdate[i - 1] = this.ticksExisted + 40 + this.rand.nextInt(20);
						this.idleHeadUpdates[i - 1] = 0;
					} else {
						this.nextHeadUpdate[i - 1] = this.ticksExisted + 40 + this.rand.nextInt(20);
						this.idleHeadUpdates[i - 1] = 0;
					}
				} else {
					this.updateWatchedTargetId(i, 0);
				}
			} else {
				List<EntityLivingBase> list = this.world.<EntityLivingBase>getEntitiesWithinAABB(EntityLivingBase.class,
						this.getEntityBoundingBox().expand(20.0D, 8.0D, 20.0D),
						Predicates.<EntityLivingBase>and(NOT_UNDEAD, EntitySelectors.NOT_SPECTATING));

				for (int j2 = 0; j2 < 10 && !list.isEmpty(); ++j2) {
					EntityLivingBase entitylivingbase = (EntityLivingBase) list.get(this.rand.nextInt(list.size()));

					if (entitylivingbase != this && entitylivingbase.isEntityAlive()
							&& this.canEntityBeSeen(entitylivingbase)) {
						if (entitylivingbase instanceof EntityPlayer) {
							if (!((EntityPlayer) entitylivingbase).capabilities.disableDamage) {
								this.updateWatchedTargetId(i, entitylivingbase.getEntityId());
							}
						} else {
							this.updateWatchedTargetId(i, entitylivingbase.getEntityId());
						}

						break;
					}

					list.remove(entitylivingbase);
				}
			}
		}

		if (this.getAttackTarget() != null) {
			this.updateWatchedTargetId(0, this.getAttackTarget().getEntityId());
		} else {
			this.updateWatchedTargetId(0, 0);
		}

		if (this.ticksExisted % 20 == 0) {
			this.heal(1.0F);
		}

		this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
	}

	@Override
	public void addTrackingPlayer(EntityPlayerMP player) {
		super.addTrackingPlayer(player);
		this.bossInfo.addPlayer(player);
	}

	public void removeTrackingPlayer(EntityPlayerMP player) {
		super.removeTrackingPlayer(player);
		this.bossInfo.removePlayer(player);
	}

	private double getHeadX(int p_82214_1_) {
		if (p_82214_1_ <= 0) {
			return this.posX;
		} else {
			float f = (this.renderYawOffset + (float) (180 * (p_82214_1_ - 1))) * 0.017453292F;
			float f1 = MathHelper.cos(f);
			return this.posX + (double) f1 * 1.3D;
		}
	}

	private double getHeadY(int p_82208_1_) {
		return p_82208_1_ <= 0 ? this.posY + 3.0D : this.posY + 2.2D;
	}

	private double getHeadZ(int p_82213_1_) {
		if (p_82213_1_ <= 0) {
			return this.posZ;
		} else {
			float f = (this.renderYawOffset + (float) (180 * (p_82213_1_ - 1))) * 0.017453292F;
			float f1 = MathHelper.sin(f);
			return this.posZ + (double) f1 * 1.3D;
		}
	}

	private float rotlerp(float p_82204_1_, float p_82204_2_, float p_82204_3_) {
		float f = MathHelper.wrapDegrees(p_82204_2_ - p_82204_1_);

		if (f > p_82204_3_) {
			f = p_82204_3_;
		}

		if (f < -p_82204_3_) {
			f = -p_82204_3_;
		}

		return p_82204_1_ + f;
	}

	private void launchCheeseToEntity(int p_82216_1_, EntityLivingBase p_82216_2_) {
		this.launchCheeseToCoords(p_82216_1_, p_82216_2_.posX,
				p_82216_2_.posY + (double) p_82216_2_.getEyeHeight() * 0.5D, p_82216_2_.posZ,
				p_82216_1_ == 0 && this.rand.nextFloat() < 0.001F);
	}

	private void launchCheeseToCoords(int p_82209_1_, double x, double y, double z, boolean invulnerable) {
		double d0 = this.getHeadX(p_82209_1_);
		double d1 = this.getHeadY(p_82209_1_);
		double d2 = this.getHeadZ(p_82209_1_);
		double d3 = x - d0;
		double d4 = y - d1;
		double d5 = z - d2;
		EntityCheeseBall entitycheese = new EntityCheeseBall(this.world, this, d3, d4, d5);

		if (invulnerable) {
			entitycheese.setInvulnerable(true);
		}

		entitycheese.posY = d1;
		entitycheese.posX = d0;
		entitycheese.posZ = d2;
		this.world.spawnEntity(entitycheese);
	}

	public void attackEntityWithRangedAttack(EntityLivingBase target, float p_82196_2_) {
		this.launchCheeseToEntity(0, target);
	}

	public int getWatchedTargetId(int head) {
		return ((Integer) this.dataManager.get(TARGET)).intValue();
	}

	public void updateWatchedTargetId(int targetOffset, int newId) {
		this.dataManager.set(TARGET, Integer.valueOf(newId));
	}

	public void addPotionEffect(PotionEffect potioneffectIn) {
	}

	protected void despawnEntity() {
		this.entityAge = 0;
	}
}
