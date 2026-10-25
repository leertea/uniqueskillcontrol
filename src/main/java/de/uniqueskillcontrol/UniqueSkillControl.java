package de.uniqueskillcontrol;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.logging.LogUtils;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import org.slf4j.Logger;

@Mod(UniqueSkillControl.MOD_ID)
public class UniqueSkillControl {
    public static final String MOD_ID = "uniqueskillcontrol";
    public static final Logger LOGGER = LogUtils.getLogger();

    public UniqueSkillControl(IEventBus modBus, ModContainer container) {
        container.registerConfig(ModConfig.Type.COMMON, UniqueSkillConfig.SPEC);
        NeoForge.EVENT_BUS.addListener(this::registerCommands);
    }

    private void registerCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal("uniqueskills")
                        .requires(src -> src.hasPermission(2))
                        .then(Commands.literal("get").executes(ctx -> {
                            int n = UniqueSkillConfig.UNIQUE_SKILL_COUNT.get();
                            ctx.getSource().sendSuccess(
                                    () -> Component.literal("Unique Skills nach Reset: " + n), false);
                            return n;
                        }))
                        .then(Commands.literal("set")
                                .then(Commands.argument("anzahl", IntegerArgumentType.integer(0, 20))
                                        .executes(ctx -> {
                                            int n = IntegerArgumentType.getInteger(ctx, "anzahl");
                                            UniqueSkillConfig.UNIQUE_SKILL_COUNT.set(n);
                                            UniqueSkillConfig.SPEC.save();
                                            ctx.getSource().sendSuccess(
                                                    () -> Component.literal("Unique Skills nach Reset gesetzt auf " + n), true);
                                            return n;
                                        })))
                        .then(Commands.literal("animation")
                                .then(Commands.literal("on").executes(ctx -> setAnimation(ctx.getSource(), true)))
                                .then(Commands.literal("off").executes(ctx -> setAnimation(ctx.getSource(), false)))));
    }

    private static int setAnimation(net.minecraft.commands.CommandSourceStack src, boolean on) {
        UniqueSkillConfig.PLAY_GREAT_SAGE_ANIMATION.set(on);
        UniqueSkillConfig.SPEC.save();
        src.sendSuccess(() -> Component.literal("Great-Sage-Animation: " + (on ? "an" : "aus")), true);
        return 1;
    }
}
