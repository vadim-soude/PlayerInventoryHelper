package fr.vadimsoude.playerInventoryHelper;

import com.github.retrooper.packetevents.protocol.component.ComponentTypes;
import com.github.retrooper.packetevents.protocol.item.ItemStack;
import com.github.retrooper.packetevents.protocol.item.type.ItemType;
import com.github.retrooper.packetevents.protocol.item.type.ItemTypes;
import com.github.retrooper.packetevents.protocol.mapper.MappedEntitySet;
import com.github.retrooper.packetevents.protocol.nbt.NBTCompound;
import com.github.retrooper.packetevents.protocol.nbt.NBTString;
import com.github.retrooper.packetevents.protocol.recipe.RecipeBookSettings;
import com.github.retrooper.packetevents.protocol.recipe.RecipeBookType;
import com.github.retrooper.packetevents.protocol.recipe.RecipeDisplayEntry;
import com.github.retrooper.packetevents.protocol.recipe.RecipeDisplayId;
import com.github.retrooper.packetevents.protocol.recipe.category.RecipeBookCategories;
import com.github.retrooper.packetevents.protocol.recipe.display.ShapedCraftingRecipeDisplay;
import com.github.retrooper.packetevents.protocol.recipe.display.slot.EmptySlotDisplay;
import com.github.retrooper.packetevents.protocol.recipe.display.slot.ItemStackSlotDisplay;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerRecipeBookAdd;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerRecipeBookRemove;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerRecipeBookSettings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PacketUtils {

    private static final int fakeRecipeId = -4577;
    public final WrapperPlayServerRecipeBookAdd ADD_FAKE_RECIPE_PACKET;
    public final WrapperPlayServerRecipeBookRemove REMOVE_FAKE_RECIPE_PACKET;
    public final WrapperPlayServerRecipeBookSettings OPEN_RECIPE_BOOK_PACKET;

    public PacketUtils() {
        ShapedCraftingRecipeDisplay display = new ShapedCraftingRecipeDisplay(1, 1, List.of(new ItemStackSlotDisplay(createBarrier())), new ItemStackSlotDisplay(createBarrier()), EmptySlotDisplay.INSTANCE);
        MappedEntitySet<ItemType> set = new MappedEntitySet<>(List.of(ItemTypes.BEDROCK));
        WrapperPlayServerRecipeBookAdd.AddEntry entry = new WrapperPlayServerRecipeBookAdd.AddEntry(new RecipeDisplayEntry(new RecipeDisplayId(fakeRecipeId), display, null, RecipeBookCategories.CRAFTING_MISC, List.of(set)), false, true);
        ADD_FAKE_RECIPE_PACKET = new WrapperPlayServerRecipeBookAdd(List.of(entry), false);

        REMOVE_FAKE_RECIPE_PACKET = new WrapperPlayServerRecipeBookRemove(List.of(new RecipeDisplayId(fakeRecipeId)));

        Map<RecipeBookType, RecipeBookSettings.TypeState> settingsMap = new HashMap<>();
        settingsMap.put(RecipeBookType.CRAFTING, new RecipeBookSettings.TypeState(true, true));
        OPEN_RECIPE_BOOK_PACKET = new WrapperPlayServerRecipeBookSettings(new RecipeBookSettings(settingsMap));
    }

    private ItemStack createBarrier() {
        NBTCompound nbt = new NBTCompound();
        nbt.setTag("open-inventory-checker", new NBTString("bedrock"));
        return ItemStack.builder().type(ItemTypes.BEDROCK).component(ComponentTypes.CUSTOM_DATA, nbt).amount(1).build();
    }


}
