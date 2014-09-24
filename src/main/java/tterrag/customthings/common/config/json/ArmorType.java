package tterrag.customthings.common.config.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import tterrag.customthings.CustomThings;
import tterrag.customthings.common.item.ItemCustomArmor;
import cpw.mods.fml.common.registry.GameRegistry;

public class ArmorType
{
    /* JSON Fields */
    public String name   = "null";
    
    public int    durability        = 5;
    public int[]  reductionAmounts  = {1, 3, 2, 1};
    public int    enchantability    = 15;
    /* End JSON Fields */

    private transient Item[] items;
    
    public String getTextureName(int armorType)
    {
        int num = armorType == 2 ? 2 : 1;
        return CustomThings.MODID.toLowerCase() + ":" + name + num;
    }
    
    public String getMaterialName()
    {
        return name + "Material";
    }
    
    public ArmorMaterial getMaterial()
    {
        return ArmorMaterial.valueOf(getMaterialName());
    }
    
    private static final String[] names = {"Helm", "Chest", "Legs", "Boots"};

    private void register()
    {
        items = new Item[4];
        for (int i = 0; i <= 3; i++)
        {
            items[i] = new ItemCustomArmor(this, i).setTextureName(getIconName(i)).setUnlocalizedName(getUnlocName(i));
            GameRegistry.registerItem(items[i], name + names[i]);
        }
    }
    
    private String getUnlocName(int slot)
    {
        return name + names[slot];
    }
    
    private String getIconName(int slot)
    {
        return CustomThings.MODID.toLowerCase() + ":" + getUnlocName(slot);
    }
    
    public static final List<ArmorType> types = new ArrayList<ArmorType>();
    public static void addType(ArmorType type)
    {
        EnumHelper.addArmorMaterial(type.getMaterialName(), type.durability, type.reductionAmounts, type.enchantability);
        types.add(type);
        type.register();
    }
    
    public static void addAll(Collection<? extends ArmorType> col)
    {
        for (ArmorType type : col)
        {
            addType(type);
        }
    }
}
