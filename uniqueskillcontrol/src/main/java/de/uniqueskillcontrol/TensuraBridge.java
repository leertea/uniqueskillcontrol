package de.uniqueskillcontrol;

import net.minecraft.server.level.ServerPlayer;

/**
 * Einzige Stelle, die Tensura und Great Sage direkt anspricht.
 *
 * WICHTIG: Die Methoden sind noch Platzhalter. Die echten Klassen- und
 * Methodennamen von Tensura/ManasCore/Great Sage kenne ich erst, wenn die
 * .jar-Dateien vorliegen (siehe libs/). Bis dahin tut der Mod nichts am Reset,
 * der Command und die Config funktionieren aber bereits.
 */
public final class TensuraBridge {
    private TensuraBridge() {}

    /** Wird nach einem Reset aufgerufen, um die konfigurierte Anzahl Unique Skills zu vergeben. */
    public static void grantUniqueSkillsAfterReset(ServerPlayer player, int count) {
        // TODO (mit Jars): zufaellige Unique Skills aus dem Tensura-Pool ziehen
        // und per ManasCore-Skill-API dem Spieler hinzufuegen.
        UniqueSkillControl.LOGGER.warn(
                "TensuraBridge ist noch nicht angebunden: {} Unique Skills fuer {} wurden NICHT vergeben.",
                count, player.getGameProfile().getName());
    }

    /** Spielt die Great-Sage-Animation fuer einen erhaltenen Skill ab. */
    public static void playGreatSageAnimation(ServerPlayer player, Object skill) {
        // TODO (mit Jars): Great-Sage-Ereignis/Paket fuer "Skill erhalten" ausloesen.
    }
}
