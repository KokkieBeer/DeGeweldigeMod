package com.Egietje.degeweldigemod.init;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import com.Egietje.degeweldigemod.entities.EntityCheeseMountable;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class CheeseUtils {

	public static void setNames(Object obj, String name) {
		if (obj instanceof Block) {
			Block block = (Block) obj;
			block.setUnlocalizedName(name);
			block.setRegistryName(name);
		} else if (obj instanceof Item) {
			Item item = (Item) obj;
			item.setUnlocalizedName(name);
			item.setRegistryName(name);
		} else {
			throw new IllegalArgumentException("Item or Block required");
		}
	}
	
	public static Object getField(Class clazz, Object obj, String fieldstr) {
		try {
			Field field = clazz.getDeclaredField(fieldstr);
			field.setAccessible(true);
			return field.get(obj);
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}
	
	public static void setField(Class clazz, Object obj, String fieldstr, Object value) {
		try {
			Field field = clazz.getDeclaredField(fieldstr);
			field.setAccessible(true);
			field.set(obj, value);
		} catch(Exception ex) {
		}
	}

	public static AxisAlignedBB getBlockBounds(EnumFacing facing, double x1, double y1, double z1, double x2, double y2,
			double z2) {
		double[] bounds = fixRotation(facing, x1, z1, x2, z2);
		return new AxisAlignedBB(bounds[0], y1, bounds[1], bounds[2], y2, bounds[3]);
	}

	private static double[] fixRotation(EnumFacing facing, double var1, double var2, double var3, double var4) {
		switch (facing) {
		case WEST:
			double var_temp_1 = var1;
			var1 = 1.0F - var3;
			double var_temp_2 = var2;
			var2 = 1.0F - var4;
			var3 = 1.0F - var_temp_1;
			var4 = 1.0F - var_temp_2;
			break;
		case NORTH:
			double var_temp_3 = var1;
			var1 = var2;
			var2 = 1.0F - var3;
			var3 = var4;
			var4 = 1.0F - var_temp_3;
			break;
		case SOUTH:
			double var_temp_4 = var1;
			var1 = 1.0F - var4;
			double var_temp_5 = var2;
			var2 = var_temp_4;
			double var_temp_6 = var3;
			var3 = 1.0F - var_temp_5;
			var4 = var_temp_6;
			break;
		default:
			break;
		}
		return new double[] { var1, var2, var3, var4 };
	}
	
	public static class Mountable {

		public static boolean sitOnBlock(World worldIn, int x, int y, int z, EntityPlayer playerIn, double d) {
			if (!checkForExistingEntity(worldIn, x,y,z,playerIn)) {
				EntityCheeseMountable entity = new EntityCheeseMountable(worldIn, x, y, z, d);
				worldIn.spawnEntity(entity);
				playerIn.startRiding(entity);
			}
			return true;
		}

		public static boolean checkForExistingEntity(World worldIn, int x, int y, int z, EntityPlayer playerIn) {
			List<EntityCheeseMountable> list = worldIn.getEntitiesWithinAABB(EntityCheeseMountable.class, new AxisAlignedBB(x, y, z, x + 1.0,  y+1.0, z+1.0).expand(1, 1, 1));
			for (EntityCheeseMountable entity : list) {
				if (entity.posX == x && entity.posY == y && entity.posZ == z) {
					if(entity.isBeingRidden()) {
						playerIn.startRiding(entity);
					}
					return true;
				}
			}
			return false;
		}

		public static boolean isSomeoneSitting(World worldIn, int x, int y, int z) {
			List<EntityCheeseMountable> list = worldIn.getEntitiesWithinAABB(EntityCheeseMountable.class, new AxisAlignedBB(x, y, z, x + 1.0,  y+1.0, z+1.0));
			for (EntityCheeseMountable entity : list) {
				if (entity.posX == x && entity.posY == y && entity.posZ == z) {
					return entity.isBeingRidden();
				}
			}
			return false;
		}
		
	}
	
	public static class CheeseMath {
		/**
		 * @return The highest number of the 2
		 * @param Integers
		 */
		public static int maxInt(int i, int j) {
			if(i > j) {
				return i;
			} else  if(j > i) {
				return j;
			} else if(i == j) {
				return i;
			} else {
				return 0;
			}
		}
		
		/**
		 * @return The lowest number of the 2
		 * @param Integers
		 */
		public static int minInt(int i, int j) {
			if(i > j) {
				return j;
			} else  if(j > i) {
				return i;
			} else if(i == j) {
				return i;
			} else {
				return 0;
			}
		}

		/**
		 * @return The highest number of the 2
		 * @param Doubles
		 */
		public static double maxDouble(double i, double j) {
			if(i > j) {
				return i;
			} else  if(j > i) {
				return j;
			} else if(i == j) {
				return i;
			} else {
				return 0;
			}
		}
		
		/**
		 * @return The lowest number of the 2
		 * @param Doubles
		 */
		public static double minDouble(double i, double j) {
			if(i > j) {
				return j;
			} else  if(j > i) {
				return i;
			} else if(i == j) {
				return i;
			} else {
				return 0;
			}
		}
		
		/**
		 * @return The highest number of the 2
		 * @param Floats
		 */
		public static float maxFloat(float i, float j) {
			if(i > j) {
				return i;
			} else  if(j > i) {
				return j;
			} else if(i == j) {
				return i;
			} else {
				return 0;
			}
		}
		
		/**
		 * @return The lowest number of the 2
		 * @param Floats
		 */
		public static float minFloat(float i, float j) {
			if(i > j) {
				return j;
			} else  if(j > i) {
				return i;
			} else if(i == j) {
				return i;
			} else {
				return 0;
			}
		}
	}
}
