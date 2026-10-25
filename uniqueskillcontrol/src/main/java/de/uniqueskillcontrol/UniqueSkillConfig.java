package de.uniqueskillcontrol;

import net.neoforged.neoforge.common.ModConfigSpec;

/** Gespeicherte Einstellung: wie viele Unique Skills es nach einem Reset gibt. */
public final class UniqueSkillConfig {
    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.IntValue UNIQUE_SKILL_COUNT;
    public static final ModConfigSpec.BooleanValue PLAY_GREAT_SAGE_ANIMATION;

    static {
        ModConfigSpec.Builder b = new ModConfigSpec.Builder();
        UNIQUE_SKILL_COUNT = b
                .comment("Anzahl Unique Skills, die man nach einem Reset bekommt.")
                .defineInRange("uniqueSkillCount", 1, 0, 20);
        PLAY_GREAT_SAGE_ANIMATION = b
                .comment("Great-Sage-Animation fuer jeden vergebenen Unique Skill abspielen.")
                .define("playGreatSageAnimation", true);
        SPEC = b.build();
    }

    private UniqueSkillConfig() {}
}
